# 06 - Tool Calling

Bai nay hoc cach cho AI goi ham Java.

## 1. Tool calling la gi?

Tool calling nghia la model co the quyet dinh goi mot function Java khi can.

Vi du user hoi:

```text
What is 12.5 * 8?
```

AI co the goi method Java:

```java
multiply(12.5, 8)
```

roi dung ket qua de tra loi user.

## 2. Tool trong project

File:

```text
src/main/java/com/example/springai/tools/CalculatorTools.java
```

Code:

```java
@Service
public class CalculatorTools {

  @Tool(name = "add-numbers", description = "Add two numbers")
  public double add(double first, double second) {
    return first + second;
  }

  @Tool(name = "multiply-numbers", description = "Multiply two numbers")
  public double multiply(double first, double second) {
    return first * second;
  }
}
```

`@Tool` cho Spring AI biet method nay co the duoc model goi.

`@ToolParam` mo ta tham so cho model hieu can truyen gi vao method.

## 3. Dang ky tool vao ChatClient

File:

```text
src/main/java/com/example/springai/SpringAiApplication.java
```

Method `chatClient` nhan them bean:

```java
ChatClient chatClient(ChatClient.Builder builder, CalculatorTools calculatorTools)
```

Sau do dang ky tool:

```java
.defaultTools(calculatorTools)
```

Tu luc nay, cac request chat binh thuong co the dung calculator tool.

## 4. Chay thu

PowerShell:

```powershell
$env:OPENAI_API_KEY="sk-..."
.\gradlew bootRun
```

Hoi:

```text
What is 12.5 * 8?
```

Hoac:

```text
Add 15 and 27, then explain shortly.
```

## 5. Luong chay theo dev

Co the hinh dung:

```text
User message
-> ChatClient gui prompt + tool definitions cho model
-> Model quyet dinh co can tool khong
-> Neu can, Spring AI goi method Java
-> Ket qua tool duoc dua lai vao model
-> Model tao final answer cho user
```

## 6. Luu y

Tool khong tu chay moi luc. Model se quyet dinh co goi hay khong dua tren:

- Cau hoi user.
- Ten tool.
- Description cua tool.
- Description cua tham so.

Viet description ro rang thi model dung tool chinh xac hon.
