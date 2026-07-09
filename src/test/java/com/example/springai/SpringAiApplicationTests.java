package com.example.springai;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
    properties = {
        "spring.ai.openai.api-key=test-key",
        "spring.main.web-application-type=none"
    },
    args = "--spring.main.lazy-initialization=true")
class SpringAiApplicationTests {

  @Test
  void contextLoads() {
  }
}
