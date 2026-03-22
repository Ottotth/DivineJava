package com.otto.divine;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.otto.divine.service.StartService;

@SpringBootApplication
public class DivineApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DivineApplication.class, args);
		StartService startService = context.getBean(StartService.class);
        startService.start();
	}

}
