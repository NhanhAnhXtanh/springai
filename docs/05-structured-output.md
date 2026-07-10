# 05 - Structured Output

Bai nay hoc cach bat AI tra ve object Java thay vi text tu do.

## 1. Structured output la gi?

Binh thuong:

```java
.content()
```

tra ve `String`.

Structured output dung:

```java
.entity(LessonPlan.class)
```

de map cau tra loi cua AI thanh object Java.

## 2. Object dau ra

File:

```text
src/main/java/com/example/springai/structured/LessonPlan.java
```

Code:

```java
public record LessonPlan(String topic, List<String> steps) {
}
```

Nghia la AI phai tra ve du lieu co dang gan voi object:

```text
topic: ten chu de
steps: danh sach cac buoc hoc
```

## 3. Goi AI va lay object

File:

```text
src/main/java/com/example/springai/chat/ChatService.java
```

Method moi:

```java
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
```

Khac biet chinh:

```java
.content()
```

tra ve text.

```java
.entity(LessonPlan.class)
```

tra ve object `LessonPlan`.

## 4. Command trong console

File:

```text
src/main/java/com/example/springai/SpringAiApplication.java
```

Them command:

```text
/plan <topic>
```

Vi du:

```text
/plan Spring AI ChatClient
```

App se in:

```text
Topic: Spring AI ChatClient
1. ...
2. ...
3. ...
```

## 5. Khi nao dung structured output?

Dung khi ban can AI tra ve du lieu de code xu ly tiep.

Vi du:

- Tao todo list.
- Tao ke hoach hoc tap.
- Trich xuat thong tin tu van ban.
- Phan loai feedback cua user.
- Tao request object cho workflow tiep theo.

## 6. Luu y dev

Structured output khong co nghia la AI luon dung 100%.

Voi case nghiem tuc, can them:

- Validation.
- Retry khi output sai.
- Test voi nhieu prompt.
- Log response khi debug.
