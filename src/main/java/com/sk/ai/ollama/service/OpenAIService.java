package com.sk.ai.ollama.service;

import com.sk.ai.ollama.dto.BooksInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OpenAIService {

     //   We can't inject the ChatClient as per normal seeing tha we have more than one chat client configured.
     private final ChatClient chatClient;

     public OpenAIService(ChatModel openAiChatModel) {
          ChatClient.Builder builder = ChatClient.builder(openAiChatModel);
          this.chatClient = builder.build();
     }

     public String chat(String request) {

          String formatSpec = "Use the following response format {format}";

          return chatClient.prompt()
                  .user(userSpec -> userSpec.text(request.concat(formatSpec))
                          .param("format", "json_object"))
                  .call()
                  .content();
     }

     public BooksInfo getBeanResponse(String category, String year) {
          return chatClient.prompt()
                  .user(u -> u.text("Please provide the book details for the given {category} and {year}.")
                          .param("category", category)
                          .param("year", year))
                  .call()
                  .entity(BooksInfo.class);
     }

     public List<BooksInfo> getListBeanResponse(String category, String year) {
          return chatClient.prompt()
                  .user(u -> u.text("Please provide 2 book details for the given {category} and {year}.")
                          .param("category", category)
                          .param("year", year))
                  .call()
                  .entity(new ParameterizedTypeReference<>() {
                  });
     }

     public List<String> getListResponse(String category, String year) {
          return chatClient.prompt()
                  .user(u -> u.text("Please provide the names of  5 best books for the given {category} and the {year}")
                          .param("category", category)
                          .param("year", year))
                  .call()
                  .entity(new ListOutputConverter(new DefaultConversionService()));
     }

     public Map<String, Object> getMapResponse(String category, String year) {
          return chatClient.prompt()
                  .user(u -> u.text("Please provide me best book for the given {category} and the {year}.\n" +
                                  "                Please do provide a summary of the book as well, the information should be \n" +
                                  "                limited and not much in depth. The response should be in the JSON format " +
                                  "                containing this information:\n" +
                                  "                category, book, year, review, author, summary" +
                                  "                Please remove ```json from the final output"
                          )
                          .param("category", category)
                          .param("year", year))
                  .call()
                  .entity(new ParameterizedTypeReference<>() {
                  });
     }
}
