package top.fatweb.oxygen.api.http

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient
import org.springframework.stereotype.Service
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import top.fatweb.oxygen.api.http.entity.turnstile.SiteverifyResponse
import top.fatweb.oxygen.api.properties.ServerProperties

/**
 * Turnstile http request api
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Service
@RetrofitClient(baseUrl = "https://challenges.cloudflare.com/turnstile/v0/")
interface TurnstileApi {
    /**
     * Turnstile post verify captcha code
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SiteverifyResponse
     */
    @FormUrlEncoded
    @POST("siteverify")
    fun siteverify(
        @Field("response") captchaCode: String,
        @Field("secret") secret: String = ServerProperties.turnstileSecretKey
    ): SiteverifyResponse
}