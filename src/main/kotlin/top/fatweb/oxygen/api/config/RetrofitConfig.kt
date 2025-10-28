package top.fatweb.oxygen.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import top.fatweb.oxygen.api.http.TurnstileApi

@Configuration
class RetrofitConfig {
    @Bean
    fun turnstileApi(
        jacksonConverterFactory: JacksonConverterFactory
    ): TurnstileApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://challenges.cloudflare.com/turnstile/v0/")
            .addConverterFactory(jacksonConverterFactory)
            .build()

        return retrofit.create(TurnstileApi::class.java)
    }
}
