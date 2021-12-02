package com.tpero.spring.common.security.token

import java.util.Date

/**
 * Data to encode in a token
 * @property subject The token's subject
 * @property expiration The number of minutes until the token should expire
 * @property claims The key-value claims that should be encoded in the token
 */
data class TokenData(
	val subject: String,
	val expiration: Date,
	val claims: Map<String, String> = mapOf()
)
