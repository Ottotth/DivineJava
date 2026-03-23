package com.otto.divine.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otto.divine._openrouter.OpenRouterController;
import com.otto.divine._openrouter.dto.ChatRequest;
import com.otto.divine._openrouter.dto.OpenRouterResponseDTO;

@Service
public class DivineService {

  @Autowired
  private OpenRouterController openRouterController;


  public OpenRouterResponseDTO torotThreeCards(String lan, String about , String apiKey) {
    String prompt = "幫我隨機抽取塔羅牌占卜關於" + about
        + ", 抽取三張塔羅牌，分別代表現在過去和未來 分別解釋三張塔羅牌和總結 最後再給一句關於總結的詩句列明出處, " + lan;
    try {
      InputStream in = getClass().getClassLoader()
          .getResourceAsStream("tarot-format-SKILL.md");
      if (in == null)
        throw new FileNotFoundException(
            "tarot-format-SKILL.md not found in classpath");
      String systemPrompt =
          new String(in.readAllBytes(), StandardCharsets.UTF_8);
      ChatRequest body = new ChatRequest();
      body.setPrompt(prompt);
      body.setSystemPrompt(systemPrompt);
      body.setApiKey(apiKey);
      return openRouterController.chat(body);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public OpenRouterResponseDTO plum(String lan, String about ,String apiKey) {
    LocalDateTime now = LocalDateTime.now();
    String prompt = "現在時間是" + now + ", 幫我用梅花易數占卜，關於我的" + about
        + " 最後再給一句關於總結的詩句列明出處, " + lan;
    try {
      InputStream in = getClass().getClassLoader()
          .getResourceAsStream("plum-divination-SKILL.md");
      if (in == null)
        throw new FileNotFoundException(
            "plum-divination-SKILL.md not found in classpath");
      String systemPrompt =
          new String(in.readAllBytes(), StandardCharsets.UTF_8);
      ChatRequest body = new ChatRequest();
      body.setPrompt(prompt);
      body.setSystemPrompt(systemPrompt);
      body.setApiKey(apiKey);
      return openRouterController.chat(body);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public OpenRouterResponseDTO lenormand(String lan, String about , String apiKey) {
    String prompt = "幫我用雷諾曼卡占卜，關於我的" + about
        + " 抽取三張雷諾曼卡，分別代表現在過去和未來 最後再給一句關於總結的詩句列明出處, " + lan;
    try {
      InputStream in = getClass().getClassLoader()
          .getResourceAsStream("lenormand-divination-SKILL.md");
      if (in == null)
        throw new FileNotFoundException(
            "lenormand-divination-SKILL.md not found in classpath");
      String systemPrompt =
          new String(in.readAllBytes(), StandardCharsets.UTF_8);
      ChatRequest body = new ChatRequest();
      body.setPrompt(prompt);
      body.setSystemPrompt(systemPrompt);
      body.setApiKey(apiKey);
      return openRouterController.chat(body);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public OpenRouterResponseDTO coin(String lan, String about, String apiKey) {
    String prompt = "幫我用擲銅錢占卜，關於我的" + about
        + " 最後再給一句關於總結的詩句列明出處, " + lan;
    try {
      InputStream in = getClass().getClassLoader()
          .getResourceAsStream("coin-divination-SKILL.md");
      if (in == null)
        throw new FileNotFoundException(
            "coin-divination-SKILL.md not found in classpath");
      String systemPrompt =
          new String(in.readAllBytes(), StandardCharsets.UTF_8);
      ChatRequest body = new ChatRequest();
      body.setPrompt(prompt);
      body.setSystemPrompt(systemPrompt);
      body.setApiKey(apiKey);
      return openRouterController.chat(body);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
