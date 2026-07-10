# 07 - REST API

Bai nay chuyen tu console app sang HTTP API.

## 1. Vi sao can REST API?

Console app phu hop de hoc nhanh.

Project that thuong can API de frontend hoac service khac goi vao.

Vi du:

```text
POST /api/chat
POST /api/lesson-plan
```

## 2. Them Spring Web

File:

```text
build.gradle
```

Them dependency:

```gradle
implementation 'org.springframework.boot:spring-boot-starter-web'
```

Dependency nay giup tao REST controller.

## 3. Request va response object

Package:

```text
src/main/java/com/example/springai/api
```

Files:

```text
ChatRequest.java
ChatResponse.java
LessonPlanRequest.java
```

Vi du:

```java
public record ChatRequest(String message) {
}
```

Record nay map JSON request vao Java object.

## 4. Controller

File:

```text
src/main/java/com/example/springai/api/ChatController.java
```

Endpoint chat:

```java
@PostMapping("/chat")
public ChatResponse chat(@RequestBody ChatRequest request) {
  return new ChatResponse(this.chatService.chat(request.message()));
}
```

Endpoint lesson plan:

```java
@PostMapping("/lesson-plan")
public LessonPlan lessonPlan(@RequestBody LessonPlanRequest request) {
  return this.chatService.createLessonPlan(request.topic());
}
```

## 5. Chay app

PowerShell:

```powershell
$env:OPENAI_API_KEY="sk-..."
.\gradlew bootRun
```

## 6. Goi API bang PowerShell

Chat:

```powershell
Invoke-RestMethod `
  -Method Post `
  -Uri http://localhost:8080/api/chat `
  -ContentType "application/json" `
  -Body '{"message":"Giai thich ChatClient ngan gon"}'
```

Lesson plan:

```powershell
Invoke-RestMethod `
  -Method Post `
  -Uri http://localhost:8080/api/lesson-plan `
  -ContentType "application/json" `
  -Body '{"topic":"Spring AI tool calling"}'
```

## 7. Diem can nho

`ChatController` khong goi `ChatClient` truc tiep.

No goi:

```java
chatService.chat(...)
```

Day la ly do bai 04 tach `ChatService` rat quan trong.
