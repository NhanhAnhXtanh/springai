package com.example.springai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class SpringAiApplication {

  private static final String CONVERSATION_ID = "learning-session";

  public static void main(String[] args) {
    SpringApplication.run(SpringAiApplication.class, args);
  }

  @Bean
  ChatClient chatClient(ChatClient.Builder builder) {
    return builder
        .defaultSystem("You are a helpful assistant for someone learning Spring AI.")
        .defaultAdvisors(MessageChatMemoryAdvisor.builder(
                MessageWindowChatMemory.builder()
                    .maxMessages(20)
                    .build())
            .build())
        .build();
  }

  @Bean
  ApplicationRunner chatLoop(ChatClient chatClient) {
    return args -> {
      System.out.println("Spring AI chat is ready. Type your question, or type 'exit' to quit.");

      try (Scanner scanner = new Scanner(System.in)) {
        while (true) {
          System.out.print("> ");

          if (!scanner.hasNextLine()) {
            break;
          }

          var input = scanner.nextLine().trim();
          if (input.isBlank()) {
            continue;
          }

          if (input.equalsIgnoreCase("exit")) {
            break;
          }

          var answer = chatClient.prompt()
              .user(input)
              .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, CONVERSATION_ID))
              .call()
              .content();

          System.out.println();
          System.out.println(answer);
          System.out.println();
        }
      }
    };
  }
}
