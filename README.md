# Spring AI Learning Project

Project nay la repo rieng de hoc Spring AI tu dau.

## Yeu cau

- Java 21
- Git
- OpenAI API key

Kiem tra nhanh:

```powershell
java -version
git --version
```

## Chay lan dau

Dat API key trong PowerShell:

```powershell
$env:OPENAI_API_KEY="sk-..."
```

Chay ung dung:

```powershell
.\gradlew bootRun
```

Sau khi thay dau nhac `>`, nhap cau hoi:

```text
Giai thich Spring AI ChatClient bang vi du ngan gon
```

Nhap `exit` de thoat.

## File can doc dau tien

- `src/main/java/com/example/springai/SpringAiApplication.java`
- `src/main/resources/application.properties`
- `build.gradle`

## Lo trinh hoc Spring AI

### 1. Chat co ban

Muc tieu:

- Hieu `ChatClient`.
- Gui prompt tu Java toi model AI.
- Nhan cau tra loi dang text.
- Chay duoc ung dung console dau tien.

Trong project nay, bat dau tu:

- `src/main/java/com/example/springai/SpringAiApplication.java`

### 2. Cau hinh model

Muc tieu:

- Biet cach cau hinh API key.
- Biet cach doi model.
- Hieu cac cau hinh nhu temperature, model name, timeout.

File can xem:

- `src/main/resources/application.properties`
- `build.gradle`

### 3. System prompt

Muc tieu:

- Dieu khien vai tro cua AI.
- Dat ngu canh mac dinh cho ung dung.
- Vi du: "Ban la tro ly day Spring Boot cho nguoi moi".

Trong code hien tai, xem doan:

```java
.defaultSystem("You are a helpful assistant for someone learning Spring AI.")
```

### 4. Conversation memory

Muc tieu:

- Cho AI nho cac cau hoi truoc do trong cung mot hoi thoai.
- Hieu `ChatMemory`.
- Hieu `MessageWindowChatMemory`.
- Hieu advisor trong Spring AI.

Day la buoc nen hoc sau khi da chay thanh cong chat co ban.

### 5. Tach logic thanh service

Muc tieu:

- Khong de tat ca code trong class main.
- Tao service rieng, vi du `ChatService`.
- Chuan bi de sau nay lam REST API.

Goi y cau truc:

```text
src/main/java/com/example/springai/
  SpringAiApplication.java
  chat/
    ChatService.java
```

### 6. Structured output

Muc tieu:

- Bat AI tra ve object Java thay vi text tu do.
- Hoc cach map cau tra loi sang record/class.
- Ung dung cho cac bai toan nhu tao todo, ke hoach hoc tap, trich xuat thong tin.

Vi du object:

```java
record LessonPlan(String title, List<String> steps) {
}
```

### 7. Tool calling

Muc tieu:

- Cho AI goi ham Java.
- Hieu annotation `@Tool`.
- Dung AI de quyet dinh khi nao can goi function.

Vi du:

- Tinh toan.
- Tra cuu du lieu user.
- Goi service noi bo.
- Lay thoi tiet gia lap.

### 8. RAG co ban

Muc tieu:

- Cho AI tra loi dua tren tai lieu rieng.
- Hieu document reader.
- Hieu embedding.
- Hieu vector store.
- Hieu retrieval.

Nen hoc RAG sau khi da nam:

- Chat co ban.
- System prompt.
- Tool calling.

### 9. Vector database

Muc tieu:

- Luu embedding ben ngoai ung dung.
- Ban dau co the dung in-memory vector store.
- Sau do hoc pgvector, Qdrant, Chroma hoac database phu hop.

### 10. AI trong REST API

Muc tieu:

- Chuyen tu console app sang backend that.
- Tao endpoint nhu `/chat`, `/ask`, `/rag`.
- Goi Spring AI tu controller/service.

Goi y cau truc:

```text
controller/
  ChatController.java
service/
  ChatService.java
```

### 11. MCP, agent va workflow

Muc tieu:

- Hieu cach AI ket noi tool/server ben ngoai.
- Hieu MCP client/server.
- Hieu workflow nhieu buoc.

Day la phan nang cao, nen hoc sau cung.

## Thu tu module nen xem trong spring-ai-recipes

Neu hoc kem repo `spring-ai-recipes`, nen xem theo thu tu:

```text
chatloop
chatclientcustomizer
logging-requests-and-responses
structured-output-validation
essential-rag
tool-rag
mcp-client
stdio-mcp-server
graph-workflow
a2a-client
a2a-server
```

3 module dau nen tap trung truoc:

```text
chatloop
chatclientcustomizer
logging-requests-and-responses
```

## Ghi chu bao mat

Khong ghi API key that vao source code. Repo nay doc key tu bien moi truong `OPENAI_API_KEY`.
