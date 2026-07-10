# 08 - React Chat UI

Bai nay them frontend React de goi Spring Boot API.

## 1. Thu muc frontend

Frontend nam trong:

```text
frontend/
```

Stack:

- Vite
- React
- TypeScript
- lucide-react

## 2. API frontend dang goi

File:

```text
frontend/src/App.tsx
```

Chat:

```ts
fetch('/api/chat', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ message }),
})
```

Lesson plan:

```ts
fetch('/api/lesson-plan', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ topic }),
})
```

## 3. Vite proxy

File:

```text
frontend/vite.config.ts
```

Config:

```ts
server: {
  proxy: {
    '/api': 'http://localhost:8080',
  },
}
```

Nghia la frontend goi `/api/...`, Vite chuyen request sang Spring Boot backend o port `8080`.

## 4. Chay backend

Terminal 1:

```powershell
cd D:\tutorial\springai
$env:OPENAI_API_KEY="sk-..."
.\gradlew bootRun
```

## 5. Chay frontend

Terminal 2:

```powershell
cd D:\tutorial\springai\frontend
npm run dev
```

Mo:

```text
http://localhost:5173
```

## 6. Test nhanh

Thu prompt:

```text
Giai thich ChatClient trong Spring AI bang vi du ngan gon
```

Thu structured output:

```text
/plan Spring AI tool calling
```

Thu tool calling:

```text
What is 12.5 * 8?
```
