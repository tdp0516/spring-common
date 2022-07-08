package com.tpero.spring.common.security.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

/**
 * Provides common [Bean]s for Spring Boot security
 */
@Configuration
class SecurityConfig {
	@Bean
	fun securityFilterChain(
		http: HttpSecurity,
		@Value("\${security.auth.exclusions:}") authExclusions: Array<String>
	): SecurityFilterChain {
		http.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers(
				*(arrayOf(
					"/swagger-ui/**",
					"/v3/api-docs/**"
				).plus(authExclusions))
			).permitAll()
			.antMatchers("/**")
			.authenticated()
		return http.build()
	}

	@Lazy
	@Bean
	fun passwordEncoder(): PasswordEncoder {
		return BCryptPasswordEncoder()
	}
}
