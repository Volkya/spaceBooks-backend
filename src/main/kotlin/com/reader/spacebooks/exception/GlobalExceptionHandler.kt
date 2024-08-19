package com.reader.spacebooks.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CustomException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleCustomException(ex: CustomException): ResponseEntity<Map<String, String>> {
        val errorResponse = mapOf("error" to ex.message.orEmpty())
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGeneralException(ex: Exception): ResponseEntity<Map<String, String>> {
        val errorResponse = mapOf("error" to "An unexpected error occurred")
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}