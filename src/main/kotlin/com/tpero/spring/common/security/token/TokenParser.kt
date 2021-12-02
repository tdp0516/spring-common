package com.tpero.spring.common.security.token

import java.security.GeneralSecurityException

/**
 * A generic interface for parsing some kind of token
 */
interface TokenParser {
    /**
     * Parse the given token and generate a [TokenData] representing the encoded data in the token
     * @param token The token to parse
     * @return [TokenData] representing the data encoded in the token
     * @throws GeneralSecurityException If there is a problem parsing the token
     */
    @Throws(GeneralSecurityException::class)
    fun parse(token: String): TokenData
}
