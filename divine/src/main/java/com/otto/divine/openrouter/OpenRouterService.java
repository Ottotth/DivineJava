package com.otto.divine.openrouter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class OpenRouterService {
    @Value("${openrouter.api.key}")
    private String apiKey;

    private final String url = "https://openrouter.ai/api/v1/chat/completions";
    private final RestTemplate rest = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonNode twoStageChat(String prompt) {
        try {
            // 單一請求：user 提問 + 追問（Are you sure? Think carefully.），並啟用 reasoning
            ObjectNode req = mapper.createObjectNode();
            req.put("model", "nvidia/nemotron-3-super-120b-a12b:free");

            ArrayNode messages = mapper.createArrayNode();
            ObjectNode userMsg = mapper.createObjectNode();
            userMsg.put("role", "user");
            userMsg.put("content", prompt);
            messages.add(userMsg);

            ObjectNode followUp = mapper.createObjectNode();
            followUp.put("role", "user");
            followUp.put("content", "Are you sure? Think carefully.");
            messages.add(followUp);

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
