package com.example.springai.api;

import com.example.springai.chat.ChatService;
import com.example.springai.structured.LessonPlan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

  private final ChatService chatService;

  public ChatController(ChatService chatService) {
    this.chatService = chatService;
  }

  @PostMapping("/chat")
  public ChatResponse chat(@RequestBody ChatRequest request) {
    return new ChatResponse(this.chatService.chat(request.message()));
  }

  @PostMapping("/lesson-plan")
  public LessonPlan lessonPlan(@RequestBody LessonPlanRequest request) {
    return this.chatService.createLessonPlan(request.topic());
  }
}
