package top.fatweb.api.util

import org.springframework.data.redis.core.BoundSetOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

/**
 * Redis util
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Suppress("UNCHECKED_CAST")
@Component
class RedisUtil(private val redisTemplate: RedisTemplate<String, Any>) {
    /**
     * 设置有效时间
     *
     * @param key     缓存的键
     * @param timeout 超时时间
     * @param timeUnit    时间颗粒度
     * @return true=设置成功；false=设置失败
     */
    fun setExpire(key: String, timeout: Long, timeUnit: TimeUnit = TimeUnit.SECONDS) =
        redisTemplate.expire(key, timeout, timeUnit)

    /**
     * 获取有效时间
     *
     * @param key   缓存的键
     * @return 有效时间
     */
    fun getExpire(key: String, timeUnit: TimeUnit = TimeUnit.SECONDS) = redisTemplate.getExpire(key, timeUnit)

    /**
     * 判断 key 是否存在
     *
     * @param key   缓存的键
     * @return true=存在; false=不存在
     */
    fun hasKey(key: String) = redisTemplate.hasKey(key)

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    fun keys(pattern: String): Set<String> = redisTemplate.keys(pattern)

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键
     * @param value 缓存的值
     */
    fun setObject(key: String, value: Any) = redisTemplate.opsForValue().set(key, value)

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键
     * @param value    缓存的值
     * @param timeout  超时时间
     * @param timeUnit 时间颗粒度
     */
    fun setObject(key: String, value: Any, timeout: Long, timeUnit: TimeUnit = TimeUnit.SECONDS) =
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit)


    /**
     * 获得缓存的基本对象
     *
     * @param key 缓存的键
     * @return 缓存的值
     */
    fun <T> getObject(key: String) = redisTemplate.opsForValue().get(key) as? T

    /**
     * 删除单个对象
     *
     * @param key 缓存的键
     * @return true=删除成功；false=删除失败
     */
    fun delObject(key: String) = redisTemplate.delete(key)

    /**
     * 删除对象集合
     *
     * @param collection 键集合
     * @return 删除个数
     */
    fun delObject(collection: Collection<String>) = redisTemplate.delete(collection)

    /**
     * 缓存 List 数据
     *
     * @param key      缓存的键
     * @param dataList 缓存的 List 数据
     * @return 缓存的个数
     */
    fun setList(key: String, dataList: List<Any>) = redisTemplate.opsForList().rightPushAll(key, dataList)

    /**
     * 获得缓存的 List 数据
     *
     * @param key 缓存的键
     * @return 缓存的键对应的 List 数据
     */
    fun <T> getList(key: String): List<T>? = redisTemplate.opsForList().range(key, 0, -1) as? List<T>

    /**
     * 缓存 Set 数据
     *
     * @param key     缓存的键
     * @param dataSet 缓存的 Set 数据
     * @return 缓存数据的对象
     */
    fun setSet(key: String, dataSet: Set<Any>): BoundSetOperations<String, Any> {
        val boundSetOps: BoundSetOperations<String, Any> = redisTemplate.boundSetOps(key)
        for (data in dataSet) {
            boundSetOps.add(data)
        }
        return boundSetOps
    }

    /**
     * 获得缓存的 Set 数据
     *
     * @param key 缓存的键
     * @return 缓存的键对应的 Set 数据
     */
    fun <T> getSet(key: String): Set<T>? = redisTemplate.opsForSet().members(key) as? Set<T>

    /**
     * 缓存 Map 数据
     *
     * @param key     缓存的键
     * @param dataMap 缓存的 Map 数据
     */
    fun setMap(key: String, dataMap: Map<String, Any>) = redisTemplate.opsForHash<String, Any>().putAll(key, dataMap)

    /**
     * 获得缓存的 Map 数据
     *
     * @param key 缓存的键
     * @return 缓存的键对应的 Map 数据
     */
    fun <T> getMap(key: String): Map<String, T>? =
        redisTemplate.opsForHash<String, Any>().entries(key) as? Map<String, T>

    /**
     * 往 Hash 中存入数据
     *
     * @param key   Redis 键
     * @param hKey  Hash 键
     * @param value 值
     */
    fun setMapValue(key: String, hKey: String, value: Any) =
        redisTemplate.opsForHash<String, Any>().put(key, hKey, value)

    /**
     * 获取 Hash 中的数据
     *
     * @param key  Redis 键
     * @param hKey Hash 键
     * @return Hash 中的对象
     */
    fun <T> getMapValue(key: String, hKey: String) = redisTemplate.opsForHash<String, T>().get(key, hKey)

    /**
     * 删除 Hash 中的数据
     *
     * @param key  Redis 键
     * @param hKey Hash 键
     */
    fun delMapValue(key: String, hKey: String) = redisTemplate.opsForHash<String, Any>().delete(key, hKey)

    /**
     * 获取多个 Hash 中的数据
     *
     * @param key   Redis 键
     * @param hKeys Hash 键集合
     * @return Hash 对象集合
     */
    fun <T> getMultiMapValue(key: String, hKeys: Collection<String>): List<T> =
        redisTemplate.opsForHash<String, T>().multiGet(key, hKeys)
}