package com.tpero.spring.common.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.GroupedOpenApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Provides Spring [Bean]s for Swagger UI documentation
 */
@Configuration
@ConditionalOnBean(SwaggerProperties::class)
class SwaggerConfig(val swaggerProps: SwaggerProperties) {
	@Bean
	@ConditionalOnProperty("swagger.paths.match", "swagger.paths.exclude")
	fun api(
		@Value("\${swagger.paths.match}") pathsToMatch: Array<String>, 
		@Value("\${swagger.path.exclude}") pathsToExclude: Array<String>
	): GroupedOpenApi {
		return GroupedOpenApi.builder()
			.group("tpero")
			.pathsToMatch(*pathsToMatch)
			.pathsToExclude(*pathsToExclude)
			.build()
	}

	@Bean
	fun info(buildProperties: BuildProperties): Info {
		val info = Info()
		info.title = this.swaggerProps.title
		info.description = this.swaggerProps.description
		info.version = buildProperties.version
		info.termsOfService = this.swaggerProps.termsUrl
		info.contact = Contact()
			.name(this.swaggerProps.contactName)
			.url(this.swaggerProps.contactUrl)
			.email(this.swaggerProps.contactEmail)
		info.license = License()
			.name(this.swaggerProps.licenseName)
			.url(this.swaggerProps.licenseUrl)
		return info
	}
	
	@Bean
	fun openApi(info: Info): OpenAPI {
		val securitySchemeName = "bearerAuth"
		return OpenAPI()
			.components(
				Components().addSecuritySchemes(
					securitySchemeName,
					SecurityScheme()
						.type(SecurityScheme.Type.HTTP)
						.scheme("bearer")
						.bearerFormat("JWT")
				)
			).security(listOf(SecurityRequirement().addList(securitySchemeName)))
			.info(info)
	}
}
