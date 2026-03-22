package com.otto.divine._openrouter.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenRouterResponseDTO {

    public String id;
    public String object;
    public long created;
    public String model;
    public String provider;
    @JsonProperty("system_fingerprint")
    public String systemFingerprint;
    public List<Choice> choices;
    public Usage usage;

    @Data
    public static class Choice {
        public int index;
        public Object logprobs;
        @JsonProperty("finish_reason")
        public String finishReason;
        @JsonProperty("native_finish_reason")
        public String nativeFinishReason;
        public Message message;
        public String reasoning;
        @JsonProperty("reasoning_details")
        public List<ReasoningDetail> reasoningDetails;
    }

    @Data
    public static class Message {
        public String role;
        public String content;
        public Object refusal;
        public String reasoning;
        @JsonProperty("reasoning_details")
        public List<ReasoningDetail> reasoningDetails;
    }

    public static class ReasoningDetail {
        public String type;
        public String text;
        public String format;
        public Integer index;
    }

    public static class Usage {
        @JsonProperty("prompt_tokens")
        public Integer promptTokens;
        @JsonProperty("completion_tokens")
        public Integer completionTokens;
        @JsonProperty("total_tokens")
        public Integer totalTokens;
        public Double cost;
        @JsonProperty("is_byok")
        public Boolean isByok;
        @JsonProperty("prompt_tokens_details")
        public PromptTokensDetails promptTokensDetails;
        @JsonProperty("completion_tokens_details")
        public CompletionTokensDetails completionTokensDetails;
        @JsonProperty("cost_details")
        public CostDetails costDetails;
    }

    public static class PromptTokensDetails {
        @JsonProperty("cached_tokens")
        public Integer cachedTokens;
        @JsonProperty("cache_write_tokens")
        public Integer cacheWriteTokens;
        @JsonProperty("audio_tokens")
        public Integer audioTokens;
    }

    public static class CompletionTokensDetails {
        @JsonProperty("reasoning_tokens")
        public Integer reasoningTokens;
        @JsonProperty("image_tokens")
        public Integer imageTokens;
        @JsonProperty("audio_tokens")
        public Integer audioTokens;
    }

    public static class CostDetails {
        @JsonProperty("upstream_inference_cost")
        public Double upstreamInferenceCost;
        @JsonProperty("upstream_inference_prompt_cost")
        public Double upstreamInferencePromptCost;
        @JsonProperty("upstream_inference_completions_cost")
        public Double upstreamInferenceCompletionsCost;
    }
}
