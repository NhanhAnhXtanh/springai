package com.example.springai.rag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Configuration
public class RagConfig {

  private static final Logger logger = LoggerFactory.getLogger(RagConfig.class);

  @Bean
  VectorStore vectorStore(EmbeddingModel embeddingModel) {
    return SimpleVectorStore.builder(embeddingModel).build();
  }

  @Bean
  @ConditionalOnProperty(name = "rag.enabled", havingValue = "true", matchIfMissing = true)
  ApplicationRunner loadKnowledgeBase(
      VectorStore vectorStore,
      @Value("classpath:rag/spring-ai-notes.md") Resource springAiNotes) {
    return args -> {
      var text = springAiNotes.getContentAsString(StandardCharsets.UTF_8);
      var document = new Document(text, Map.of("source", springAiNotes.getFilename()));
      var splitter = TokenTextSplitter.builder().build();
      var chunks = splitter.apply(List.of(document));

      vectorStore.add(chunks);

      logger.info("Loaded {} RAG document chunk(s) from {}.", chunks.size(), springAiNotes.getFilename());
    };
  }
}
