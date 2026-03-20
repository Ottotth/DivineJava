package com.otto.divine;

import org.springframework.context.ConfigurableApplicationContext;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.boot.SpringApplication;
import com.otto.divine.service.DivineService;

public class Temp {

public static void main(String[] args) {
    try {
        String systemPrompt = Files.readString(Paths.get(".copilot-skill/Tarot-format-SKILL.md"), StandardCharsets.UTF_8);
        System.out.println(systemPrompt);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}


