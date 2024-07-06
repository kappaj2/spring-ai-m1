package com.sk.ai.ollama.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OllamaService {

     private final OllamaChatModel ollamaChatModel;

     public String generate(String promptMessage) {
          ChatResponse response = ollamaChatModel.call(
                  new Prompt(promptMessage,
                          OllamaOptions.create()
                                  .withModel("llama3")
                                  .withTemperature(0.4F)
                  )
          );
          return response.getResult().getOutput().getContent();
     }
}
