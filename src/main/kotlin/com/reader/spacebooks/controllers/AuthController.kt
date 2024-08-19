//package com.reader.spacebooks.controllers
//
//import org.springframework.http.ResponseEntity
//import org.springframework.security.config.annotation.rsocket.RSocketSecurity.JwtSpec
//import org.springframework.security.crypto.bcrypt.BCrypt
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//@RequestMapping("/api/auth")
//class AuthController {
//
//    @PostMapping("/register")
//    fun register(@RequestBody registerRequest : RegisterRequest): ResponseEntity<String>{
//      val hashedPassword = hasPassword(registerRequest.password)
//      return ResponseEntity.ok("Usuario registrado")
//    }
//
//    fun register(@RequestBody loginRequest : LoginRequest): ResponseEntity<String>{
//        // check pass
//        val token = generateJwtToken(loginRequest.username)
//        val encryptedToken = encryptAES(token, aesKey, iv)
//        return ResponseEntity.ok(encryptedToken)
//    }
//
//}
//
//
//data class RegisterRequest(val username: String, val password: String)
//data class LoginRequest(val username : String, val password: String)
//
//
//// fun aux
//fun hasPassword(password: String): String{
//    return BCrypt.hashpw(password, BCrypt.gensalt())
//}
//
//fun generateJwtToken(username: String): String{
//    // gen token
//    return Jwts.builder().setSubject(username).signWith(SignatureAlgorithm.HS256, "secret").compact()
//}