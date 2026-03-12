package com.home.prac.userservice;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {

    @GetMapping("/api/users/ping")
    Map<String, Object> ping() {
        return Map.of(
                "service", "user-service",
                "message", "user-service is running");
    }
}

