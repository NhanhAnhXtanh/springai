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

## Lo trinh hoc

1. Chay duoc chat console voi `ChatClient`.
2. Tach logic chat ra service rieng.
3. Them memory de hoi thoai nho ngu canh.
4. Them structured output de AI tra ve object Java.
5. Them tool calling.
6. Hoc RAG voi vector store.

## Ghi chu bao mat

Khong ghi API key that vao source code. Repo nay doc key tu bien moi truong `OPENAI_API_KEY`.
