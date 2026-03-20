package com.otto.divine;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;
import com.otto.divine.service.DivineService;

public class Temp {

  public static void main(String[] args) {
    // Start a minimal Spring context and obtain the service bean so dependencies are injected
    ConfigurableApplicationContext ctx = SpringApplication.run(DivineApplication.class);
    try {
      DivineService service = ctx.getBean(DivineService.class);
      System.out.println(service.torotThreeCards());
    } finally {
      ctx.close();
    }
  }
}


