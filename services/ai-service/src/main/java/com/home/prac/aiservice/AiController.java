package com.home.prac.aiservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AiController {

    private final String apiKey;

    AiController(@Value("${openai.api-key:}") String apiKey) {
        this.apiKey = apiKey;
    }

    @GetMapping("/api/ai/config")
    Map<String, Object> config() {
        return Map.of(
                "service", "ai-service",
                "configured", !apiKey.isBlank(),
                "message", "OPENAI_API_KEY can be wired here later");
    }
}

