package com.tpero.spring.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration
import springfox.documentation.builders.HttpAuthenticationBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket

/**
 * Provides Spring [Bean]s for Swagger UI documentation
 */
@Configuration
@Suppress("LongParameterList")
@Import(BeanValidatorPluginsConfiguration::class)
@ConditionalOnProperty("swagger.title", "swagger.description", "swagger.version")
class SwaggerConfig(
	@Value("\${swagger.title:Postcards}") val title: String,
	@Value("\${swagger.description:Server API for the Postcards Application}") val description: String,
	@Value("\${swagger.version:0.0.0}") val version: String,
	@Value("\${swagger.terms.url:") val termsOfServiceUrl: String,
	@Value("\${swagger.contact.name:Trey Pero}") val contactName: String,
	@Value("\${swagger.contact.url:http://treypero-dev.com}") val contactUrl: String,
	@Value("\${swagger.contact.email:tpero3@gatech.edu}") val contactEmail: String,
	@Value("\${swagger.license:}") val license: String,
	@Value("\${swagger.license.url:}") val licenseUrl: String
) {
	companion object {
		const val BEARER_TOKEN = "Bearer Token"
	}

	@Bean
	fun api(): Docket {
		return Docket(DocumentationType.OAS_30)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build()
			.securityContexts(
				listOf(
					SecurityContext.builder().securityReferences(
						listOf(SecurityReference(BEARER_TOKEN, arrayOfNulls(0)))
					).build()
				)
			).securitySchemes(
				listOf(
					HttpAuthenticationBuilder()
						.scheme("bearer")
						.bearerFormat("JWT")
						.name(BEARER_TOKEN)
						.build()
				)
			).apiInfo(this.apiInfo())
	}

	private fun apiInfo(): ApiInfo {
		return ApiInfo(
			this.title,
			this.description,
			this.version,
			this.termsOfServiceUrl,
			Contact(this.contactName, this.contactUrl, this.contactEmail),
			this.license,
			this.licenseUrl,
			emptyList()
		)
	}
}
