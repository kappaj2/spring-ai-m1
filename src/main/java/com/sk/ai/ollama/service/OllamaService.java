package com.sk.ai.ollama.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OllamaService {

     //private  OllamaChatModel ollamaChatModel;
     private ChatClient chatClient;

     public OllamaService(OllamaChatModel ollamaChatModel) {
          // this.ollamaChatModel = ollamaChatModel;
          ChatClient.Builder builder = ChatClient.builder(ollamaChatModel)
                  .defaultOptions(ChatOptionsBuilder.builder()
                          .withTemperature(0.0F)
                          .build());
          this.chatClient = builder.build();
     }


//     public OllamaService(OllamaChatModel ollamaChatModel) {
//          this.ollamaChatModel = ollamaChatModel;
//     }

     public String generate(String promptMessage) {

//          ChatResponse response = ollamaChatModel.call(prompt);
//          return response.getResult().getOutput().getContent();


          return chatClient
                  .prompt()
                  .system("""
                                      Tell the joke in the style of a cowboy
                          """)
                  .user(promptMessage)
                  .call()
                  .content();
     }
}
