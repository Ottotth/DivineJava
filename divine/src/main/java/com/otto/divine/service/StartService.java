package com.otto.divine.service;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otto.divine.config.Asii;
import com.otto.divine.controller.DivineController;
import com.otto.divine.model.AboutEnum;
import com.otto.divine.model.LanEnum;

@Service
public class StartService {

  @Autowired
  private DivineController controller;

  @Autowired
  private Asii asii;

  private LanEnum lan;

  private AboutEnum about;

  private String apiKey;

  private static final Scanner sc = new Scanner(System.in);

  public void start() {
    asii.title();
    apiKey();
  }

  public void apiKey() {
    System.out.println("\n▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒\n");
    System.out.println("You can get your API Key from OpenRouter: https://openrouter.ai/");
    System.out.println("Please Enter Your API Key / 請輸入您的 API 金鑰:");
    apiKey = sc.next();
    if (apiKey.isBlank()) {
      System.out.println("API Key cannot be empty. Please try again.");
      start();
    } else {
      // You can add additional validation for the API key format here if needed
      lan();
    }
  }

  public void lan() {
    System.out.println("\n▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒\n");
    System.out.println("Please Select Language / 請選擇語言:");
    System.out.println("1. English");
    System.out.println("2. 中文");
    System.out.println(" ");
    System.out.println("3. Exit");
    System.out.println(" ");

    String command = sc.next();
    switch (command) {
      case "1":
        lan = LanEnum.English;
        selectAbout();
        break;
      case "2":
        lan = LanEnum.Chinese;
        selectAbout();
        break;
      case "3":
        System.out.println("Goodbye!");
        System.exit(0);
        break;
      default:
        System.out.println("Invalid command, defaulting to English.");
        lan = LanEnum.English;
        selectAbout();
    }
  }

  public void selectAbout() {
    if (lan == LanEnum.English) {
      System.out.println("\n▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒\n");
      System.out.println("Please Select Your Question About:");
      System.out.println("1. Love");
      System.out.println("2. Career");
      System.out.println("3. Health");
      System.out.println("4. General");
      System.out.println(" ");
      System.out.println("9. Back");
      System.out.println("0. Exit");
      System.out.println(" ");
      String command = sc.next();
      switch (command) {
        case "1":
          about = AboutEnum.LOVE;
          divineMethod();
          break;
        case "2":
          about = AboutEnum.CAREER;
          divineMethod();
          break;
        case "3":
          about = AboutEnum.HEALTH;
          divineMethod();
          break;
        case "4":
          about = AboutEnum.ALL;
          divineMethod();
          break;
        case "9":
          start();
          break;
        case "0":
          System.out.println("Goodbye!");
          System.exit(0);
          break;
        default:
          System.out.println("Invalid command, defaulting to General.");
          about = AboutEnum.ALL;
          divineMethod();
      }

    } else {

      System.out
          .println("\n▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒\n");
      System.out.println("請選擇你想占卜的內容:");
      System.out.println("1. 愛情");
      System.out.println("2. 事業");
      System.out.println("3. 健康");
      System.out.println("4. 綜合");
      System.out.println(" ");
      System.out.println("9. 返回");
      System.out.println("0. 離開");
      System.out.println(" ");
      String command = sc.next();
      switch (command) {
        case "1":
          about = AboutEnum.LOVE;
          divineMethod();
          break;
        case "2":
          about = AboutEnum.CAREER;
          divineMethod();
          break;
        case "3":
          about = AboutEnum.HEALTH;
          divineMethod();
          break;
        case "4":
          about = AboutEnum.ALL;
          divineMethod();
          break;
        case "9":
          start();
          break;
        case "0":
          System.out.println("再見！");
          System.exit(0);
          break;
        default:
          System.out.println("無效的指令，預設為綜合。");
          about = AboutEnum.ALL;
          divineMethod();
      }
    }
  }

  public void divineMethod() {
    if (lan == LanEnum.English) {
      System.out
          .println("\n▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒\n");
      System.out.println("You have selected English.");
      System.out.println("Select your method");
      System.out.println("1. Tarot");
      System.out.println("2. Lennormand Card");
      System.out.println("3. Meihua Yishu");
      System.out.println("4. Yi Coin");
      System.out.println(" ");
      System.out.println("9. Back");
      System.out.println("0. Exit");
      System.out.println(" ");
      String command = sc.next();
      switch (command) {
        case "1":
          asii.loading();
          System.out
              .println(controller.tarot(lan.getTranslation(), about.getabout(), apiKey)
                  .getChoices().get(0).getMessage().getContent());
          back();
          break;
        case "2":
          asii.loading();
          System.out.println(
              controller.lenormand(lan.getTranslation(), about.getabout(), apiKey)
                  .getChoices().get(0).getMessage().getContent());
          back();
          break;
        case "3":
          asii.loading();
          System.out
              .println(controller.plum(lan.getTranslation(), about.getabout(), apiKey)
                  .getChoices().get(0).getMessage().getContent());
          back();
          break;
        case "4":
          asii.loading();
          System.out
              .println(controller.coin(lan.getTranslation(), about.getabout(), apiKey)
                  .getChoices().get(0).getMessage().getContent());
          back();
          break;
        case "9":
          selectAbout();
          break;
        case "0":
          System.out.println("Goodbye!");
          sc.close();
          System.exit(0);
          break;
        default:
          System.out.println("Invalid command, select again.");
          divineMethod();
      }
    } else {
      System.out
          .println("\n▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒\n");

      System.out.println("請選擇你的占卜方法");
      System.out.println("1. 塔羅牌");
      System.out.println("2. 雷諾曼卡");
      System.out.println("3. 梅花易數");
      System.out.println("4. 易經銅錢卦");
      System.out.println(" ");
      System.out.println("9. 返回");
      System.out.println("0. 離開");
      System.out.println(" ");
      String command = sc.next();
      switch (command) {
        case "1":
          asii.loading();
          System.out
              .println(controller.tarot(lan.getTranslation(), about.getabout(), apiKey)
                  .getChoices().get(0).getMessage().getContent());
          back();
          break;
        case "2":
          asii.loading();
          System.out.println(
              controller.lenormand(lan.getTranslation(), about.getabout(), apiKey)
                  .getChoices().get(0).getMessage().getContent());
          back();
          break;
        case "3":
          asii.loading();
          System.out
              .println(controller.plum(lan.getTranslation(), about.getabout(), apiKey)
                  .getChoices().get(0).getMessage().getContent());
          back();
          break;
        case "4":
          asii.loading();
          System.out
              .println(controller.coin(lan.getTranslation(), about.getabout(), apiKey)
                  .getChoices().get(0).getMessage().getContent());
          back();
          break;
        case "9":
          selectAbout();
          break;
        case "0":
          System.out.println("再見！");
          sc.close();
          System.exit(0);
          break;
        default:
          System.out.println("無效的指令，請重新選擇。");
          divineMethod();
      }
    }
  }


  public void back() {
    if (lan == LanEnum.English) {
      System.out
          .println("\n▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒\n");
      System.out.println("1. Back to select your question");
      System.out.println("2. Back to select method");
      System.out.println(" ");
      System.out.println("0. Exit");
      System.out.println(" ");
      String commmand2 = sc.next();
      switch (commmand2) {
        case "1":
          selectAbout();
          break;
        case "2":
          divineMethod();
          break;
        case "0":
          System.out.println("Goodbye!");
          System.exit(0);
          break;
        default:
          System.out.println("Invalid command, back to select method.");
          divineMethod();
          break;
      }
    } else {
      System.out
          .println("\n▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒\n");
      System.out.println("1. 返回選擇你想占卜的內容");
      System.out.println("2. 返回選擇占卜方法");
      System.out.println(" ");
      System.out.println("0. 離開");
      System.out.println(" ");
      String commmand2 = sc.next();
      switch (commmand2) {
        case "1":
          selectAbout();
          break;
        case "2":
          divineMethod();
          break;
        case "0":
          System.out.println("再見！");
          System.exit(0);
          break;
        default:
          System.out.println("無效的指令，返回選擇占卜方法。");
          divineMethod();
          break;
      }
    }
  }
}


