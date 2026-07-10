# Spring AI Chat UI

React frontend for the Spring AI learning project.

## Run

Start the Spring Boot backend first:

```powershell
cd D:\tutorial\springai
$env:OPENAI_API_KEY="sk-..."
.\gradlew bootRun
```

Start the frontend:

```powershell
cd D:\tutorial\springai\frontend
npm run dev
```

Open:

```text
http://localhost:5173
```

Vite proxies `/api` requests to `http://localhost:8080`.
