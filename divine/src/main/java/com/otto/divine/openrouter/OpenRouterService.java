package com.otto.divine.openrouter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;


@Service
public class OpenRouterService {
    // Support property, environment variable, or empty default to avoid startup failure during tests
    @Value("${openrouter.api.key:${OPENROUTER_API_KEY:}}")
    private String apiKey;

    private final String url = "https://openrouter.ai/api/v1/chat/completions";
    
    @Autowired
    private RestTemplate rest;

    @Autowired
    private ObjectMapper mapper;

    private static final String TAROT_SKILL_SYSTEM_PROMPT = "You are a tarot reading assistant. MUST follow these rules:\n1. Output exactly the specified Markdown template (no extra text, no JSON, no code fences, no internal chain-of-thought or reasoning details). Only the Markdown formatted reading.\n2. All textual content must be in Traditional Chinese.\n3. For any missing field, use null or leave the textual area empty but preserve the template structure.";
    
    public JsonNode twoStageChat(String prompt) {
        try {
            ObjectNode req = mapper.createObjectNode();
            req.put("model", "nvidia/nemotron-3-super-120b-a12b:free");

            ArrayNode messages = mapper.createArrayNode();

            // Inject system prompt from tarot-format skill as the first message
            ObjectNode systemMsg = mapper.createObjectNode();
            systemMsg.put("role", "system");
            systemMsg.put("content", TAROT_SKILL_SYSTEM_PROMPT);
            messages.add(systemMsg);

            ObjectNode userMsg = mapper.createObjectNode();
            userMsg.put("role", "user");
            userMsg.put("content", prompt);
            messages.add(userMsg);

            req.set("messages", messages);
            ObjectNode reasoning = mapper.createObjectNode();
            reasoning.put("enabled", true);
            req.set("reasoning", reasoning);

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(req.toString(), headers);
            ResponseEntity<JsonNode> resp = rest.postForEntity(url, entity, JsonNode.class);

            return resp.getBody();
        } catch (Exception e) {
            ObjectNode err = mapper.createObjectNode();
            err.put("error", e.getMessage());
            return err;
        }
    }
}
