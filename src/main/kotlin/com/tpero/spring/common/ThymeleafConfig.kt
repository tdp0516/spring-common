package com.tpero.spring.common

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.TemplateEngine
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

/**
 * Provides common [Bean]s for Thymeleaf templates
 */
@Configuration
class ThymeleafConfig {
	@Bean
	fun htmlTemplateEngine(): TemplateEngine {
		val templateEngine = TemplateEngine()
		templateEngine.addTemplateResolver(ClassLoaderTemplateResolver().apply {
			prefix = "/templates/html/"
			suffix = ".html"
			templateMode = TemplateMode.TEXT
			characterEncoding = "UTF8"
			checkExistence = true
			isCacheable = false
		})

		return templateEngine
	}
}
