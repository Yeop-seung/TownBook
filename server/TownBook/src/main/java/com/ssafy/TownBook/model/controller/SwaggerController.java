package com.ssafy.TownBook.model.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Swagger 테스트
 */
@RestController
public class SwaggerController {
    
    @GetMapping("/test")
    public String test() {
        return "Hello this is test";
    }
}