package com.tpero.spring.common.email

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mail.javamail.JavaMailSender
import org.thymeleaf.context.Context
import spock.lang.Ignore
import spock.lang.Specification

@Ignore
@SpringBootTest
class EmailServiceSpec extends Specification {
	@Autowired
	EmailService emailService
	
	@SpringBean
	JavaMailSender mailSender = Mock()
	
	def "Test sending an email successfully"() {
		given:
		final String toAddress = "some@email.com"
		final String subject = "Email subject"
		final String bodyTemplateFileName = "email-template"
		final Context context = new Context()
		this.mailSender.createMimeMessage() >> Mock()
		
		when:
		this.emailService.send(toAddress, subject, bodyTemplateFileName, context)
		
		then:
		message.subject == subject
	}
}
