package com.otto.divine._openrouter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api/openrouter")
public class OpenRouterController {

    @Autowired
    private OpenRouterService service;

    @PostMapping(value = "/chat", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonNode> chat(String prompt , String systemPrompt) {
        JsonNode result = service.chat(prompt == null ? "" : prompt, systemPrompt == null ? "" : systemPrompt);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }
}
