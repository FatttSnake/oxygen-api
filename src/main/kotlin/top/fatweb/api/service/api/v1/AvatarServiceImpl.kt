package top.fatweb.api.service.api.v1

import com.talanlabs.avatargenerator.GitHubAvatar
import org.springframework.stereotype.Service
import top.fatweb.api.vo.api.v1.avatar.DefaultBase64Vo
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Service
class AvatarServiceImpl : IAvatarService {
    @OptIn(ExperimentalEncodingApi::class)
    override fun getDefault(): DefaultBase64Vo {
        val avatar = GitHubAvatar.newAvatarBuilder(396, 5).build()
        val bytes = avatar.createAsPngBytes(1232132134543L)
        return DefaultBase64Vo(Base64.encode(bytes))
    }

}