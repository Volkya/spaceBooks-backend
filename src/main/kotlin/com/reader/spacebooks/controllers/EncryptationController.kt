package com.reader.spacebooks.controllers

import org.apache.coyote.BadRequestException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.reader.spacebooks.service.EncryptionService

@RestController
@RequestMapping("/encryption")
class EncryptationController(private val encryptionService: EncryptionService) {

    @PostMapping("/rsa/encrypt")
    fun encryptRSA(@RequestBody requestBody: Map<String, String>): Map<String, String>
    {
        val textToEncrypt = requestBody["text"] ?: throw BadRequestException("text is required")
        val encryptedText = encryptionService.encryptWithRSA(textToEncrypt)

        return mapOf("encrypted text" to encryptedText)
    }


    @PostMapping("/rsa/decrypt")
    fun decryptWithRSA(@RequestBody requestBody: Map<String, String>): Map<String, String> {
        val encryptedText = requestBody["encryptedText"] ?: throw BadRequestException("Encrypted text is required")
        val decryptedText = encryptionService.decryptWithRSA(encryptedText)
        return mapOf("decryptedText" to decryptedText)
    }


}