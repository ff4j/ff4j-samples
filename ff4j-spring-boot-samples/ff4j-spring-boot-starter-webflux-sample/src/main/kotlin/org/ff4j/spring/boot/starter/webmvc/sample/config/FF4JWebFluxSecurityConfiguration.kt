package org.ff4j.spring.boot.starter.webmvc.sample.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@ConditionalOnProperty(
    value = ["ff4j.security.enabled"], havingValue = "true", matchIfMissing = false
)
@EnableWebFluxSecurity
class FF4JWebFluxSecurityConfiguration {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.csrf().disable().authorizeExchange().pathMatchers("/**").permitAll()
        return http.build()
    }
}