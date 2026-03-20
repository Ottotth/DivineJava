package com.otto.divine._openrouter;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public ResponseEntity<JsonNode> chat(String prompt) {
        try {
            String systemPrompt = null;
            // try load from classpath resource first
            try (java.io.InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("tarot-format-SKILL.md")) {
                if (is != null) {
                    systemPrompt = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                }
            }
            // fallback to repo path
            if (systemPrompt == null || systemPrompt.isEmpty()) {
                systemPrompt = Files.readString(Paths.get(".copilot-skill/tarot-format/SKILL.md"), StandardCharsets.UTF_8);
            }
            JsonNode result = service.chat(prompt == null ? "" : prompt, systemPrompt == null ? "" : systemPrompt);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
