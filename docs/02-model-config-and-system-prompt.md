# 02 - Model Config And System Prompt

Sau khi chay duoc chat co ban, buoc tiep theo la hieu 2 phan:

- Cau hinh model trong `application.properties`.
- System prompt trong code Java.

## 1. Cau hinh model

File:

```text
src/main/resources/application.properties
```

Dang co:

```properties
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.chat.options.model=${OPENAI_MODEL:gpt-4o-mini}
spring.ai.openai.chat.options.temperature=${OPENAI_TEMPERATURE:0.7}
```

Y nghia:

- `spring.ai.openai.api-key`: API key de goi OpenAI.
- `spring.ai.openai.chat.options.model`: model mac dinh dung cho chat.
- `spring.ai.openai.chat.options.temperature`: muc do sang tao cua cau tra loi.

`temperature` cang thap thi cau tra loi cang on dinh. `temperature` cang cao thi cau tra loi cang linh hoat, nhung de lech hon.

## 2. Override bang bien moi truong

PowerShell:

```powershell
$env:OPENAI_API_KEY="sk-..."
$env:OPENAI_MODEL="gpt-4o-mini"
$env:OPENAI_TEMPERATURE="0.3"
.\gradlew bootRun
```

Neu khong set `OPENAI_MODEL`, app se dung mac dinh `gpt-4o-mini`.

Neu khong set `OPENAI_TEMPERATURE`, app se dung mac dinh `0.7`.

## 3. System prompt

File:

```text
src/main/java/com/example/springai/SpringAiApplication.java
```

Dang co:

```java
.defaultSystem("You are a helpful assistant for someone learning Spring AI.")
```

System prompt la huong dan nen cho AI biet no dang dong vai tro gi.

Vi du:

```java
.defaultSystem("You are a Java tutor. Explain everything for beginners.")
```

Khi system prompt thay doi, cung mot cau hoi co the cho cau tra loi khac.

## 4. Bai tap nho

Thu doi system prompt thanh:

```java
.defaultSystem("You are a Spring Boot teacher. Answer in Vietnamese with short examples.")
```

Sau do chay:

```powershell
.\gradlew bootRun
```

Hoi:

```text
ChatClient trong Spring AI la gi?
```

Quan sat xem cau tra loi co ngan gon va de hieu hon khong.
