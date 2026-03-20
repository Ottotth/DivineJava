package com.otto.divine.openrouter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.otto.divine.openrouter.ChatRequest;

@RestController
@RequestMapping("/api/openrouter")
public class OpenRouterController {

    @Autowired
    private OpenRouterService service;

    public OpenRouterController(OpenRouterService service) {
        this.service = service;
    }

    @PostMapping("/chat")
    public ResponseEntity<JsonNode> chat(@RequestBody ChatRequest body) {
        String prompt = body == null ? "" : body.getPrompt();
        JsonNode result = service.twoStageChat(prompt == null ? "" : prompt);
        return ResponseEntity.ok(result);
    }
}
