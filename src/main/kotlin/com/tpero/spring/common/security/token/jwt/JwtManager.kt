package com.tpero.spring.common.security.token.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.tpero.spring.common.security.token.TokenData
import com.tpero.spring.common.security.token.TokenParser
import com.tpero.spring.common.security.token.TokenProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import java.util.*

/**
 * Manages parsing and providing JSON Web Tokens.
 *
 * Consuming applications should set `jwt.issuer` and `jwt.secret.key` to signal that this
 * [Bean] should be created for Spring autowiring.
 */
@Component
@ConditionalOnProperty("jwt.issuer", "jwt.secret.key")
class JwtManager(
	@Value("\${jwt.issuer}") val issuer: String,
	@Value("\${jwt.secret.key}") val secret: String = "some-super-secret-key"
) : TokenParser, TokenProvider {
	override fun parse(token: String): TokenData {
		val decodedToken = JWT.decode(token)

		return TokenData(
			decodedToken.subject,
			// TODO Need to handle expiration on the TokenData object better
			decodedToken.expiresAt,
			decodedToken.claims.mapValues { it.value.asString() }
		)
	}

	/**
	 * Each call to this method produces a new JWT
	 * @param data Information that should be added to the token
	 * The id is added to the subject of the JWT
	 */
	override fun provide(data: TokenData): String {
		val partialJwt = JWT.create()
			.withIssuer(this.issuer)
			.withSubject(data.subject)
			.withIssuedAt(Date())
			.withExpiresAt(data.expiration)

		data.claims.forEach {
			partialJwt.withClaim(it.key, it.value)
		}

		return partialJwt.sign(Algorithm.HMAC256(this.secret))
	}
}
