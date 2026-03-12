package com.home.prac.orderservice;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class OrderController {

    @GetMapping("/api/orders/ping")
    Map<String, Object> ping() {
        return Map.of(
                "service", "order-service",
                "message", "order-service is running");
    }
}

