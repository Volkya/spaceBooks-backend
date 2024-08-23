package com.reader.spacebooks.ServiceTests

import com.reader.spacebooks.service.EncryptionService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
internal class EncryptationControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private lateinit var encryptionService: EncryptionService

    @BeforeEach
    fun setUp() {
        encryptionService = Mockito.mock(EncryptionService::class.java)
    }

    @Test
    fun `should encrypt text`() {
        val textToEncrypt = "Hello, World!"
        val encryptedText = "encryptedText" // Aquí iría el texto encriptado que esperas
        Mockito.`when`(encryptionService.encryptWithRSA(textToEncrypt)).thenReturn(encryptedText)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/encryption/rsa/encrypt")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""{"text": "$textToEncrypt"}"""))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.encrypted text").value(encryptedText))
    }

    @Test
    fun `should decrypt text`() {
        val encryptedText = "encryptedText"
        val decryptedText = "Hello, World!"
        Mockito.`when`(encryptionService.decryptWithRSA(encryptedText)).thenReturn(decryptedText)

        mockMvc.perform(MockMvcRequestBuilders.post("/encryption/rsa/decrypt")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""{"encryptedText": "$encryptedText"}"""))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.decryptedText").value(decryptedText))
    }

    @Test
    fun `should return bad request for missing text on encrypt`() {
        mockMvc.perform(MockMvcRequestBuilders.post("/encryption/rsa/encrypt")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""{}"""))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("text is required"))
    }

    @Test
    fun `should return bad request for missing encryptedText on decrypt`() {
        mockMvc.perform(MockMvcRequestBuilders.post("/encryption/rsa/decrypt")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""{}"""))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Encrypted text is required"))
    }




}