package com.sk.ai.ollama.controller;

import com.sk.ai.ollama.service.OllamaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/ollama")
@RequiredArgsConstructor
public class OllamaAiController {

     private final OllamaService ollamaService;

     @GetMapping("/chat")
     public String generate(@RequestParam(value = "promptMessage") String promptMessage) {
          return ollamaService.generate(promptMessage);
     }
}
