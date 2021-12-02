package com.tpero.spring.common.security.token

/**
 * A generic interface intended to provide some kind of token
 */
interface TokenProvider {
	/**
	 * Provides a token based on the given [TokenData]
	 * @param data The [TokenData] to encode in the token
	 * @return The generated token
	 */
	fun provide(data: TokenData): String
}
