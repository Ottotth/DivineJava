package com.otto.divine.service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otto.divine._openrouter.OpenRouterController;
import com.otto.divine._openrouter.OpenRouterService;
import tools.jackson.databind.JsonNode;

@Service
public class DivineService {

  @Autowired
  private OpenRouterController openRouterController;

  public JsonNode torotThreeCards() {
    String prompt = "幫我隨機抽取塔羅牌占卜今天的運程 , 抽取三章塔羅牌，分別代表現在過去和未來 分別解釋三一張塔羅牌和總結 最後再給一句關於總結的詩句列明出處";
    try {
      String systemPrompt = Files.readString(Paths.get(".copilot-skill/Tarot-format-SKILL.md"), StandardCharsets.UTF_8);
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
}
