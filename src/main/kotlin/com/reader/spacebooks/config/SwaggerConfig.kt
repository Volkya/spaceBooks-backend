//package com.reader.spacebooks.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import springfox.documentation.builders.PathSelectors
//import springfox.documentation.builders.RequestHandlerSelectors
//import springfox.documentation.spring.web.plugins.Docket
//import springfox.documentation.swagger2.annotations.EnableSwagger2
//
//@Configuration
//@EnableSwagger2
//class SwaggerConfig {
//
//    @Bean
//    fun api(): Docket{
//        return Docket(springfox.documentation.spi.DocumentationType.SWAGGER_2)
//            .select()
//            .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
//            .paths(PathSelectors.any())
//            .build()
//    }
//
//}