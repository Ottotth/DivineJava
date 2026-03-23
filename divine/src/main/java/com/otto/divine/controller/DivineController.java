package com.otto.divine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.otto.divine._openrouter.dto.OpenRouterResponseDTO;
import com.otto.divine.service.DivineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class DivineController {

@Autowired
private DivineService divineService;

@GetMapping("/tarot")
public OpenRouterResponseDTO tarot(@RequestParam String lan ,@RequestParam String about , @RequestParam String apiKey) {
    return divineService.torotThreeCards(lan , about, apiKey);
}

@GetMapping("/plum")
public OpenRouterResponseDTO plum(@RequestParam String lan, @RequestParam String about, @RequestParam String apiKey) {
    return divineService.plum(lan, about, apiKey);
}

@GetMapping("/lenormand")
public OpenRouterResponseDTO lenormand(@RequestParam String lan, @RequestParam String about, @RequestParam String apiKey) {
    return divineService.lenormand(lan, about, apiKey);
}

@GetMapping("/coin")
public OpenRouterResponseDTO coin(@RequestParam String lan, @RequestParam String about, @RequestParam String apiKey) {
    return divineService.coin(lan, about, apiKey);
}


  
}
