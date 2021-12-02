package com.tpero.spring.common

import com.fasterxml.jackson.databind.exc.ValueInstantiationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Provides common [ExceptionHandler]s that most applications would need
 */
@ControllerAdvice
class ExceptionAdvice {
	/**
	 * A [ValueInstantiationException] implies a request object could not be generated
	 * because the data sent in the request is invalid. This should result in a [HttpStatus.BAD_REQUEST]
	 * @param ex The [ValueInstantiationException] that occurred
	 * @return The [ResponseEntity] for the request
	 */
    @ExceptionHandler(ValueInstantiationException::class)
    fun handle(ex: ValueInstantiationException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }
}
