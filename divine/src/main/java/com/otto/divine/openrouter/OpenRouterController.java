package com.otto.divine.openrouter;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/openrouter")
public class OpenRouterController {
    private final OpenRouterService service;

    public OpenRouterController(OpenRouterService service) {
        this.service = service;
    }

    @PostMapping("/chat")
    public ResponseEntity<JsonNode> chat(@RequestBody Map<String, String> body) {
        String prompt = body.getOrDefault("prompt", "");
        JsonNode result = service.twoStageChat(prompt);
        return ResponseEntity.ok(result);
    }
}
