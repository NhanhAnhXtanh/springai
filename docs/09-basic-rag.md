# 09 - Basic RAG

Bai nay them RAG co ban vao project.

RAG = Retrieval Augmented Generation.

Y tuong:

```text
User hoi
-> ung dung tim tai lieu lien quan trong vector store
-> dua tai lieu do vao prompt
-> model tra loi dua tren context
```

## 1. Dependencies

File:

```text
build.gradle
```

Them:

```gradle
implementation 'org.springframework.ai:spring-ai-vector-store'
implementation 'org.springframework.ai:spring-ai-vector-store-advisor'
```

`spring-ai-vector-store` co `SimpleVectorStore`.

`spring-ai-vector-store-advisor` co `QuestionAnswerAdvisor`.

## 2. Tai lieu noi bo

File:

```text
src/main/resources/rag/spring-ai-notes.md
```

Day la tai lieu mau de app nap vao vector store.

## 3. VectorStore

File:

```text
src/main/java/com/example/springai/rag/RagConfig.java
```

Bean:

```java
@Bean
VectorStore vectorStore(EmbeddingModel embeddingModel) {
  return SimpleVectorStore.builder(embeddingModel).build();
}
```

`SimpleVectorStore` la in-memory vector store. No phu hop de hoc va demo, khong phu hop production.

## 4. Load tai lieu

Trong `RagConfig`, app doc file markdown:

```java
var text = springAiNotes.getContentAsString(StandardCharsets.UTF_8);
var document = new Document(text, Map.of("source", springAiNotes.getFilename()));
```

Sau do split thanh chunk:

```java
var splitter = TokenTextSplitter.builder().build();
var chunks = splitter.apply(List.of(document));
```

Roi add vao vector store:

```java
vectorStore.add(chunks);
```

Khi add, Spring AI dung `EmbeddingModel` de tao embedding cho tung chunk.

## 5. Goi RAG

File:

```text
src/main/java/com/example/springai/chat/ChatService.java
```

Method:

```java
public String askKnowledgeBase(String question) {
  return this.chatClient.prompt()
      .user(question)
      .advisors(
          advisor -> advisor.param(ChatMemory.CONVERSATION_ID, CONVERSATION_ID),
          QuestionAnswerAdvisor.builder(this.vectorStore).build())
      .call()
      .content();
}
```

`QuestionAnswerAdvisor` lam viec:

```text
Lay cau hoi user
-> similarity search trong VectorStore
-> lay document lien quan
-> dua document vao prompt
-> goi model
```

## 6. Console command

Chay app:

```powershell
.\gradlew bootRun
```

Hoi:

```text
/rag SimpleVectorStore dung de lam gi?
```

## 7. REST API

Endpoint moi:

```text
POST /api/rag
```

Body:

```json
{
  "message": "SimpleVectorStore dung de lam gi?"
}
```

## 8. Frontend

Frontend ho tro command:

```text
/rag <question>
```

Vi du:

```text
/rag Tool calling trong project nay la gi?
```

## 9. Luu y

RAG trong bai nay van goi OpenAI embedding model khi app start, vi can tao embedding cho tai lieu.

Neu khong co `OPENAI_API_KEY`, app se khong nap duoc vector store.
