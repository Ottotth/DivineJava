package com.otto.divine.config;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Component;

@Component
public class Asii {

    public void title() {
    try {
      InputStream in =
          getClass().getClassLoader().getResourceAsStream("Title.md");
      if (in == null)
        throw new FileNotFoundException("Title.md not found in classpath");
      String title =
          new String(in.readAllBytes(), StandardCharsets.UTF_8);
          System.out.println(title);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void loading() {
    try {
      InputStream in =
          getClass().getClassLoader().getResourceAsStream("Loading.md");
      if (in == null)
        throw new FileNotFoundException("Loading.md not found in classpath");
      String title =
          new String(in.readAllBytes(), StandardCharsets.UTF_8);
          System.out.println(title);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
