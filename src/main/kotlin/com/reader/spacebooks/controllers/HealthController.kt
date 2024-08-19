package com.reader.spacebooks.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class HealthController {

    @GetMapping("/health")
    fun healthCheck(): ResponseEntity<Map<String, String>> {
        return try {
            val status = mapOf("status" to "UP")
            return ResponseEntity.ok(status)
        } catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("error" to "Internal Server Error"))
        }
    }

}