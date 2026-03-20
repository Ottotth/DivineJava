package com.otto.divine.openrouter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.otto.divine.openrouter.dto.ChatRequest;
import tools.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api/openrouter")
public class OpenRouterController {

    @Autowired
    private OpenRouterService service;

    @PostMapping(value = "/chat", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonNode> chat(@RequestBody ChatRequest body) {
        String prompt = body == null ? "" : body.getPrompt();
        JsonNode result = service.twoStageChat(prompt == null ? "" : prompt);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }
}
