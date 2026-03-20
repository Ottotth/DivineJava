package com.otto.divine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.otto.divine.service.DivineService;
import tools.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class DivineController {

@Autowired
private DivineService divineService;

@GetMapping("/tarot")
public ResponseEntity<JsonNode> tarot() {
    return null;
}

  
}
