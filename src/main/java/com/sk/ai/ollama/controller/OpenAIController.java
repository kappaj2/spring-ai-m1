package com.sk.ai.ollama.controller;

import com.sk.ai.ollama.dto.BooksInfo;
import com.sk.ai.ollama.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/openapi")
@RequiredArgsConstructor
public class OpenAIController {

     private final OpenAIService openAIService;

     @GetMapping("/chat")
     public String chat(@RequestParam String request) {
          return openAIService.chat(request);
     }

     @GetMapping("/v1/chat")
     public BooksInfo getBeanResponse(@RequestParam String category, @RequestParam String year) {
          return openAIService.getBeanResponse(category, year);
     }

     @GetMapping("/v2/chat")
     public List<BooksInfo> getListBeanResponse(@RequestParam String category, @RequestParam String year) {
          return openAIService.getListBeanResponse(category, year);
     }

     @GetMapping("/v3/chat")
     public List<String> getListResponse(@RequestParam String category, @RequestParam String year) {
          return openAIService.getListResponse(category, year);
     }

     // using MapOutputConverter
     @GetMapping("/v4/chat")
     public Map<String, Object> getMapResponse(@RequestParam String category, @RequestParam String year) {
          return openAIService.getMapResponse(category, year);
     }
}
