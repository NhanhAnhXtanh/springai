package com.example.springai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class SpringAiApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringAiApplication.class, args);
  }

  @Bean
  ChatClient chatClient(ChatClient.Builder builder) {
    return builder
        .defaultSystem("You are a helpful assistant for someone learning Spring AI.")
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
