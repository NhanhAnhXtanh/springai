# 04 - Tach Logic Thanh ChatService

Bai nay tach logic goi AI ra khoi class main.

## 1. Vi sao can tach service?

Ban dau de code trong `SpringAiApplication` de de hoc.

Nhung project that nen tach:

```text
SpringAiApplication -> chay app, doc input console
ChatService         -> goi ChatClient va tra ve answer
```

Cach nay giup code de doc hon va sau nay de lam REST API.

## 2. Cau truc moi

```text
src/main/java/com/example/springai/
  SpringAiApplication.java
  chat/
    ChatService.java
```

## 3. ChatService lam gi?

File:

```text
src/main/java/com/example/springai/chat/ChatService.java
```

Code chinh:

```java
public String chat(String message) {
  return this.chatClient.prompt()
      .user(message)
      .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, CONVERSATION_ID))
      .call()
      .content();
}
```

Nghia la:

```text
Nhan message tu ben ngoai
-> dua vao ChatClient
-> gan conversation id cho memory
-> goi AI
-> tra ve noi dung cau tra loi
```

## 4. SpringAiApplication con lam gi?

File:

```text
src/main/java/com/example/springai/SpringAiApplication.java
```

Bay gio no chi goi:

```java
var answer = chatService.chat(input);
```

Nghia la class main khong can biet chi tiet `ChatClient` goi model nhu nao.

## 5. Tai sao buoc nay quan trong?

Sau nay khi lam REST API, controller co the goi lai cung service:

```java
chatService.chat(request.message());
```

Khong can viet lai logic `ChatClient`.

## 6. Bai tap nho

Mo `ChatService.java`, doi `CONVERSATION_ID` thanh:

```java
private static final String CONVERSATION_ID = "console-session";
```

Chay lai app va kiem tra memory van hoat dong.
