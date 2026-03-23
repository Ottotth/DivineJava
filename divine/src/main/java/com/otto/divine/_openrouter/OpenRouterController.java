package com.otto.divine._openrouter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.otto.divine._openrouter.dto.ChatRequest;
import com.otto.divine._openrouter.dto.OpenRouterResponseDTO;


@RestController
@RequestMapping("/api/openrouter")
public class OpenRouterController {

    @Autowired
    private OpenRouterService service;



    @PostMapping(value = "/chat")
    public OpenRouterResponseDTO chat(@RequestBody ChatRequest body) {
        try {
            OpenRouterResponseDTO json = service.chat(body.getPrompt(), body.getSystemPrompt() , body.getApiKey());
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
