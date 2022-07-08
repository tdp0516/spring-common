package com.tpero.spring.common.swagger;

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty("swagger.title", "swagger.description")
data class SwaggerProperties(
	@Value("\${swagger.title}")
	val title: String,
	
	@Value("\${swagger.description}")
	val description: String,
	
	@Value("\${swagger.terms.url:}")
	val termsUrl: String,
	
	@Value("\${swagger.contact.name:Trey Pero}")
	val contactName: String,
	
	@Value("\${swagger.contact.url:http://treypero-dev.com}")
	val contactUrl: String,
	
	@Value("\${swagger.contact.email:trey.pero@outlook.com}")
	val contactEmail: String,
	
	@Value("\${swagger.license.name:}")
	val licenseName: String,
		
	@Value("\${swagger.license.url:}")
	val licenseUrl: String
)
