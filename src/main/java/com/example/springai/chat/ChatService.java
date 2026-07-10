package com.example.springai.chat;

import com.example.springai.structured.LessonPlan;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

  private static final String CONVERSATION_ID = "learning-session";

  private final ChatClient chatClient;

  private final VectorStore vectorStore;

  public ChatService(ChatClient chatClient, VectorStore vectorStore) {
    this.chatClient = chatClient;
    this.vectorStore = vectorStore;
  }

  public String chat(String message) {
    return this.chatClient.prompt()
        .user(message)
        .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, CONVERSATION_ID))
        .call()
        .content();
  }

  public LessonPlan createLessonPlan(String topic) {
    return this.chatClient.prompt()
        .user("""
            Create a beginner-friendly learning plan for this topic: %s.
            Return exactly 3 short steps. Use Vietnamese.
            """.formatted(topic))
        .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, CONVERSATION_ID))
        .call()
        .entity(LessonPlan.class);
  }

  public String askKnowledgeBase(String question) {
    return this.chatClient.prompt()
        .user(question)
        .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, CONVERSATION_ID))
        .advisors(QuestionAnswerAdvisor.builder(this.vectorStore).build())
        .call()
        .content();
  }
}
