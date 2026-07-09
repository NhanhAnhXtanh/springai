# 03 - Conversation Memory

Bai nay them memory de AI nho noi dung da hoi trong cung mot phien chat.

## 1. Vi sao can memory?

Neu khong co memory, moi lan goi AI gan nhu la mot cau hoi doc lap.

Vi du:

```text
> Toi ten la Nam
> Toi ten gi?
```

Khong co memory, AI co the khong biet ban ten gi.

Co memory, AI co the dua vao cau truoc de tra loi.

## 2. Code da them

File:

```text
src/main/java/com/example/springai/SpringAiApplication.java
```

Them import:

```java
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
```

Them conversation id:

```java
private static final String CONVERSATION_ID = "learning-session";
```

Them memory advisor vao `ChatClient`:

```java
.defaultAdvisors(MessageChatMemoryAdvisor.builder(
        MessageWindowChatMemory.builder()
            .maxMessages(20)
            .build())
    .build())
```

Them conversation id vao moi request:

```java
.advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, CONVERSATION_ID))
```

## 3. Giai thich ngan gon

- `MessageWindowChatMemory`: noi luu lich su chat gan day.
- `maxMessages(20)`: chi giu toi da 20 message gan nhat.
- `MessageChatMemoryAdvisor`: dua lich su chat vao prompt moi.
- `ChatMemory.CONVERSATION_ID`: id de biet message thuoc cuoc hoi thoai nao.

## 4. Chay thu

PowerShell:

```powershell
$env:OPENAI_API_KEY="sk-..."
.\gradlew bootRun
```

Thu hoi:

```text
> Toi ten la Nam
> Toi dang hoc framework gi?
> Toi ten gi?
```

Neu memory hoat dong, AI se nho cac thong tin vua noi trong phien chat.

## 5. Luu y

Memory hien tai la in-memory, nghia la:

- Tat app thi mat lich su.
- Phu hop de hoc va demo.
- Chua phu hop de luu hoi thoai that lau dai.
