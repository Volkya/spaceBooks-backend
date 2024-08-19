package com.reader.spacebooks.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/data")
class DataController {
    @PostMapping("/encrypt")
    fun encryptData(@RequestBody dataRequest: DataRequest): ResponseEntity<String>{
        val encryptedData = encryptAES(dataRequest.data, aesKey, iv)
        val encryptedKey = encryptRSA(Base64.getEncoder().encodeToString(aesKey.encoded), publicKey)
        return ResponseEntity.ok("Data: $encryptedData\nKey: $encryptedKey")
    }

    @PostMapping("/decrypt")
    fun decryptData(@RequestBody decryptRequest: DecryptRequest): ResponseEntity<String> {
        val aesKeyBytes = decryptRSA(decryptRequest.encryptedKey, privateKey)
        val decryptedData = decryptAES(decryptRequest.encryptedData, SecretKeySpec(Base64.getDecoder().decode(aesKeyBytes), "AES"), iv)
        return ResponseEntity.ok(decryptedData)
    }


}

data class DataRequest(val data: String)
data class DecryptRequest(val encryptedData: String, val encryptedKey: String)
