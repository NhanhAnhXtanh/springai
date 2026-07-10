package com.example.springai;

import com.example.springai.chat.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
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
        .defaultAdvisors(MessageChatMemoryAdvisor.builder(
                MessageWindowChatMemory.builder()
                    .maxMessages(20)
                    .build())
            .build())
        .build();
  }

  @Bean
  ApplicationRunner chatLoop(ChatService chatService) {
    return args -> {
      System.out.println("Spring AI chat is ready. Type your question, or type 'exit' to quit.");
      System.out.println("Use '/plan <topic>' to get a structured learning plan.");

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

          if (input.startsWith("/plan ")) {
            var topic = input.substring("/plan ".length()).trim();
            var plan = chatService.createLessonPlan(topic);

            System.out.println();
            System.out.println("Topic: " + plan.topic());
            for (int i = 0; i < plan.steps().size(); i++) {
              System.out.println((i + 1) + ". " + plan.steps().get(i));
            }
            System.out.println();
            continue;
          }

          var answer = chatService.chat(input);

          System.out.println();
          System.out.println(answer);
          System.out.println();
        }
      }
    };
  }
}
