package com.reader.spacebooks.ServiceTests

import com.reader.spacebooks.service.EncryptionService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(properties = [
    "rsa.public.key=-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8A...\n-----END PUBLIC KEY-----",
    "rsa.private.key=-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAAOCAQ8A...\n-----END PRIVATE KEY-----"
])
internal class EncryptionServiceTest {

    private lateinit var encryptionService: EncryptionService

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun `should encrypt text using RSA`() {
        val publicKey = "-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8A...\n-----END PUBLIC KEY-----"
        val textToEncrypt = "Hello, World!"
        val encryptedText = encryptionService.encryptWithRSA(textToEncrypt)

        // Aquí deberías tener la lógica para verificar el texto encriptado
        assertNotNull(encryptedText)
        // También podrías tener un valor esperado si conoces el resultado
    }

    @Test
    fun `should decrypt text using RSA`() {
        val privateKey = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAAOCAQ8A...\n-----END PRIVATE KEY-----"
        val encryptedText = "encryptedText" // Aquí debes usar un texto encriptado real
        val decryptedText = encryptionService.decryptWithRSA(encryptedText)

        // Verifica que el texto desencriptado sea igual al original
        assertEquals("Hello, World!", decryptedText)
    }


}