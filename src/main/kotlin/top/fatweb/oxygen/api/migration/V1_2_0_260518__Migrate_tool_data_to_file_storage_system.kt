package top.fatweb.oxygen.api.migration

import com.baomidou.mybatisplus.core.toolkit.IdWorker
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import okio.utf8Size
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.springframework.stereotype.Component
import top.fatweb.oxygen.api.component.storage.FileStorageProvider
import java.sql.Connection
import java.sql.Timestamp
import java.sql.Types
import java.util.zip.Inflater
import kotlin.io.encoding.Base64

/**
 * Database migrate
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 * @see ObjectMapper
 * @see FileStorageProvider
 * @see BaseJavaMigration
 */
@Component
@TargetDataSource("master")
class V1_2_0_260518__Migrate_tool_data_to_file_storage_system(
    private val objectMapper: ObjectMapper,
    private val fileStorageProvider: FileStorageProvider
) : BaseJavaMigration() {
    data class FileData(
        val name: String,
        val value: String
    )

    override fun migrate(context: Context) {
        val connection = context.connection

        val ids = mutableListOf<Long>()
        connection.createStatement().use { statement ->
            statement.executeQuery("select id from t_b_tool_data where deleted = 0").use { rs ->
                while (rs.next()) {
                    ids.add(rs.getLong("id"))
                }
            }
        }

        ids.forEach { id ->
            connection.prepareStatement("select * from t_b_tool_data where id = ?").use { statement ->
                statement.setLong(1, id)
                statement.executeQuery().use { resultSet ->
                    if (resultSet.next()) {
                        val data = Base64.decodeToStringWithZip(resultSet.getString("data"))
                        val createTime = resultSet.getTimestamp("create_time")
                        val updateTime = resultSet.getTimestamp("update_time")

                        try {
                            val type = objectMapper.typeFactory.constructMapType(
                                HashMap::class.java,
                                String::class.java,
                                FileData::class.java
                            )
                            val files = objectMapper.readValue<Map<String, FileData>>(data, type)
                            val rootId =
                                addSourceFile(
                                    connection = connection,
                                    fileName = "",
                                    content = null,
                                    parentId = null,
                                    createTime = createTime,
                                    updateTime = updateTime,
                                    nodeId = id
                                )
                            files.forEach { (_, file) ->
                                addSourceFile(
                                    connection = connection,
                                    fileName = file.name,
                                    content = file.value,
                                    parentId = rootId,
                                    createTime = createTime,
                                    updateTime = updateTime,
                                )
                            }
                        } catch (e: Exception) {
                            when (e) {
                                is JsonParseException, is MismatchedInputException -> {
                                    addDistFile(
                                        connection = connection,
                                        nodeId = id,
                                        content = data,
                                        createTime = createTime,
                                        updateTime = updateTime,
                                    )
                                }

                                else -> throw e
                            }
                        }
                    }
                }
            }
        }
    }

    private fun Base64.decodeToStringWithZip(base64String: String): String {
        if (base64String.isBlank()) {
            return ""
        }

        val binary = this.decode(base64String).toString(Charsets.ISO_8859_1)

        // zlib header (x78), level 9 (xDA)
        if (binary.startsWith("\u0078\u00DA")) {
            val byteArray = binary.toByteArray(Charsets.ISO_8859_1)
            val inflater = Inflater().apply {
                setInput(byteArray)
            }
            val uncompressed = ByteArray(byteArray.size * 10)
            val resultLength = inflater.inflate(uncompressed)
            inflater.end()

            return String(uncompressed, 0, resultLength, Charsets.UTF_8)
        } else {
            throw Exception("Invalid data")
        }
    }

    private fun addSourceFile(
        connection: Connection,
        fileName: String,
        content: String?,
        parentId: Long?,
        createTime: Timestamp,
        updateTime: Timestamp,
        nodeId: Long? = null
    ): Long {
        val nodeId = nodeId ?: IdWorker.getId()

        if (parentId == null) {
            connection.prepareStatement("insert into t_b_tool_source (id, root_id, parent_id, file_name, root_node, dir_node, create_time, update_time) values (?, ?, ?, ?, ?, ?, ?, ?)")
                .use { statement ->
                    statement.setLong(1, nodeId)
                    statement.setLong(2, nodeId)
                    statement.setNull(3, Types.BIGINT)
                    statement.setString(4, fileName)
                    statement.setInt(5, 1)
                    statement.setInt(6, 1)
                    statement.setTimestamp(7, createTime)
                    statement.setTimestamp(8, updateTime)
                    statement.executeUpdate()

                    return nodeId
                }
        }

        if (content == null) {
            throw Exception("Invalid data")
        }

        val fileHash = fileStorageProvider.save(content)

        connection.prepareStatement("insert into t_b_tool_source (id, root_id, parent_id, file_name, root_node, dir_node, create_time, update_time) values (?, ?, ?, ?, ?, ?, ?, ?)")
            .use { statement ->
                statement.setLong(1, nodeId)
                statement.setLong(2, parentId)
                statement.setLong(3, parentId)
                statement.setString(4, fileName)
                statement.setInt(5, 0)
                statement.setInt(6, 0)
                statement.setTimestamp(7, createTime)
                statement.setTimestamp(8, updateTime)
                statement.executeUpdate()
            }

        connection.prepareStatement("insert into t_b_tool_file_version (id, node_id, ver, file_hash, file_size, create_time, update_time) values (?, ?, ?, ?, ?, ?, ?)")
            .use { statement ->
                val versionId = IdWorker.getId()
                statement.setLong(1, versionId)
                statement.setLong(2, nodeId)
                statement.setLong(3, 0)
                statement.setString(4, fileHash)
                statement.setLong(5, content.utf8Size())
                statement.setTimestamp(6, createTime)
                statement.setTimestamp(7, updateTime)
                statement.executeUpdate()
            }

        updateReferenceCount(
            connection = connection,
            fileHash = fileHash
        )

        return nodeId
    }

    private fun addDistFile(
        connection: Connection,
        nodeId: Long,
        content: String,
        createTime: Timestamp,
        updateTime: Timestamp
    ) {
        val fileHash = fileStorageProvider.save(content)

        connection.prepareStatement("insert into t_b_tool_dist (id, file_hash, file_size, create_time, update_time) values (?, ?, ?, ?, ?)")
            .use { statement ->
                statement.setLong(1, nodeId)
                statement.setString(2, fileHash)
                statement.setLong(3, content.utf8Size())
                statement.setTimestamp(4, createTime)
                statement.setTimestamp(5, updateTime)
                statement.executeUpdate()
            }

        updateReferenceCount(
            connection = connection,
            fileHash = fileHash
        )
    }

    fun updateReferenceCount(connection: Connection, fileHash: String) {
        connection.prepareStatement(
            "insert into t_s_storage_blob (file_hash, reference_count) " +
                    "values (?, 1) " +
                    "on duplicate key update reference_count = reference_count + 1, " +
                    "update_time = utc_timestamp()"
        )
            .use { statement ->
                statement.setString(1, fileHash)
                statement.executeUpdate()
            }
    }
}