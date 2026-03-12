package com.home.prac.productservice;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductController {

    @GetMapping("/api/products/ping")
    Map<String, Object> ping() {
        return Map.of(
                "service", "product-service",
                "message", "product-service is running");
    }
}

