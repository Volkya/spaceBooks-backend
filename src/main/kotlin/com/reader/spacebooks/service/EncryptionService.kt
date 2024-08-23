package com.reader.spacebooks.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

@Service
class EncryptionService(
    @Value("\${encrypt.rsa.publicKey}") private val publicKeyString: String,
    @Value("\${encrypt.rsa.privateKey}") private val privateKeyString: String
) {

    private val publicKey: PublicKey
    private val privateKey: PrivateKey

    init {
        val cleanPublicKey = publicKeyString
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
            .replace("\\s".toRegex(), "")

        val cleanPrivateKey = privateKeyString
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replace("\\s".toRegex(), "")

        val keyFactory = KeyFactory.getInstance("RSA")
        val publicKeySpec = X509EncodedKeySpec(Base64.getDecoder().decode(cleanPublicKey))
        this.publicKey = keyFactory.generatePublic(publicKeySpec)

        val privateKeySpec = PKCS8EncodedKeySpec(Base64.getDecoder().decode(cleanPrivateKey))
        this.privateKey = keyFactory.generatePrivate(privateKeySpec)

    }

    fun encryptWithRSA(text: String): String {
        val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val encryptedBytes = cipher.doFinal(text.toByteArray(Charsets.UTF_8))
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    fun decryptWithRSA(encryptedText: String): String {
        val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        val decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText))
        return String(decryptedBytes, Charsets.UTF_8)
    }


}