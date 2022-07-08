package com.tpero.spring.common.security

import io.swagger.v3.oas.annotations.Operation
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
//import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * TODO Move this to spring-common
 */
@Validated
@RestController
//@ConditionalOnBean(PasswordEncoder::class)
class CryptoController(
//	val passwordEncoder: PasswordEncoder
) {
	companion object {
		const val ENCRYPT_ENDPOINT = "/encrypt"
	}
	
	@GetMapping(ENCRYPT_ENDPOINT)
	@Operation(description = "A utility for encrypting data")
	fun encrypt(@Valid @NotNull plaintext: String): String {
//		return this.passwordEncoder.encode(plaintext)
		return ""
	}
}
