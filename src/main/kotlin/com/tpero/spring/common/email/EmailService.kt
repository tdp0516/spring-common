package com.tpero.spring.common.email

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

/**
 * A [Service] for sending emails for any purpose
 * @property mailSender The underlying [JavaMailSender] that sends the given email
 * @property fromEmail The address the email will be sent from
 */
@Service
@ConditionalOnProperty("spring.mail.username")
class EmailService(
	private val mailSender: JavaMailSender,
	private val htmlTemplateEngine: TemplateEngine,
	@Value("\${spring.mail.username}") private val fromEmail: String
) {
	fun send(to: String, subject: String, bodyTemplateFileName: String, context: Context) {
		val message = this.mailSender.createMimeMessage()
		val helper = MimeMessageHelper(message, true)
		helper.setFrom(this.fromEmail)
		helper.setTo(to)
		helper.setSubject(subject)
		helper.setText(this.htmlTemplateEngine.process(bodyTemplateFileName, context), true)
		this.mailSender.send(message)
	}
}
