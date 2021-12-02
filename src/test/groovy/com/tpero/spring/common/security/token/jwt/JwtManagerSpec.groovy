package com.tpero.spring.common.security.token.jwt

import com.tpero.spring.common.security.token.TokenData
import spock.lang.Specification

class JwtManagerSpec extends Specification {
	def "Test provided token can be parsed"() {
		given:
		final JwtManager manager = new JwtManager(
			"some-issuer",
			"some-super-secret-key"
		)
		
		when:
		final Map<String, String> claims = new HashMap<>()
		claims.put("some-claim-key", "some-claim-value")
		final String token = manager.provide(new TokenData(
			"some-subject",
			new Date(),
			claims
		))
		
		then:
		final TokenData parsedData = manager.parse(token)
		parsedData.subject == "some-subject"
		parsedData.expiration.time < new Date().time
		parsedData.claims.get("some-claim-key") == "some-claim-value"
	}
}
