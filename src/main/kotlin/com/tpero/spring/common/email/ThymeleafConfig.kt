package com.tpero.spring.common.email

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.thymeleaf.TemplateEngine
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

/**
 * Provides common [Bean]s for Thymeleaf templates
 */
@Lazy
@Configuration
class ThymeleafConfig {
	@Bean
	fun htmlTemplateEngine(classLoaderTemplateResolver: ClassLoaderTemplateResolver): TemplateEngine {
		val templateEngine = SpringTemplateEngine()
		templateEngine.addTemplateResolver(classLoaderTemplateResolver)
		return templateEngine
	}
	
	@Bean
	fun classLoaderTemplateResolver(): ClassLoaderTemplateResolver {
		return ClassLoaderTemplateResolver().apply {
			prefix = "/templates/html/"
			suffix = ".html"
			templateMode = TemplateMode.TEXT
			characterEncoding = "UTF8"
			checkExistence = true
			isCacheable = false
		}
	}
}
