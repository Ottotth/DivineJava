package com.otto.divine.openrouter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenRouterService {
    @Value("${openrouter.api.key}")
    private String apiKey;

    private final String url = "https://openrouter.ai/api/v1/chat/completions";
    private final RestTemplate rest = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonNode twoStageChat(String prompt) {
        try {
            // 第一個請求：要求 reasoning enabled
            ObjectNode req1 = mapper.createObjectNode();
            req1.put("model", "nvidia/nemotron-3-super-120b-a12b:free");
            ArrayNode messages1 = mapper.createArrayNode();
            ObjectNode userMsg = mapper.createObjectNode();
            userMsg.put("role", "user");
            userMsg.put("content", prompt);
            messages1.add(userMsg);
            req1.set("messages", messages1);
            ObjectNode reasoning = mapper.createObjectNode();
            reasoning.put("enabled", true);
            req1.set("reasoning", reasoning);

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity1 = new HttpEntity<>(req1.toString(), headers);
            ResponseEntity<JsonNode> resp1 = rest.postForEntity(url, entity1, JsonNode.class);

            JsonNode assistantMsg = resp1.getBody()
                    .path("choices").get(0)
                    .path("message");

            // 第二次請求：包含 assistant.message 及其 reasoning_details（若存在）
            ArrayNode messages2 = mapper.createArrayNode();
            messages2.add(userMsg);
            ObjectNode assistantNode = mapper.createObjectNode();
            assistantNode.put("role", "assistant");
            assistantNode.put("content", assistantMsg.path("content").asText(""));
            if (assistantMsg.has("reasoning_details")) {
                assistantNode.set("reasoning_details", assistantMsg.get("reasoning_details"));
            }
            messages2.add(assistantNode);

            ObjectNode req2 = mapper.createObjectNode();
            req2.put("model", "nvidia/nemotron-3-super-120b-a12b:free");
            req2.set("messages", messages2);

            HttpEntity<String> entity2 = new HttpEntity<>(req2.toString(), headers);
            ResponseEntity<JsonNode> resp2 = rest.postForEntity(url, entity2, JsonNode.class);

            return resp2.getBody();
        } catch (Exception e) {
            ObjectNode err = mapper.createObjectNode();
            err.put("error", e.getMessage());
            return err;
        }
    }
}
