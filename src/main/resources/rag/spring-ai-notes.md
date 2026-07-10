# Spring AI Learning Notes

Spring AI la framework giup ung dung Spring Boot ket noi voi cac model AI.

ChatClient la API cap cao de tao prompt, gui request den model, va doc response.

System prompt dung de dat vai tro mac dinh cho AI. Vi du: AI co the dong vai tro giao vien Java cho nguoi moi hoc.

Conversation memory giup AI nho cac message gan day trong cung mot conversation id. Trong project nay memory dang dung MessageWindowChatMemory voi cua so message gan nhat.

Structured output giup map cau tra loi cua AI thanh object Java nhu record LessonPlan.

Tool calling cho phep AI goi method Java co annotation @Tool. Project nay co CalculatorTools voi add va multiply.

REST API giup frontend hoac service khac goi ChatService qua HTTP. Project nay co endpoint /api/chat va /api/lesson-plan.

React frontend nam trong folder frontend va goi backend thong qua Vite proxy /api den http://localhost:8080.

RAG la Retrieval Augmented Generation. Ung dung se tim tai lieu lien quan trong vector store, dua noi dung do vao prompt, roi model tra loi dua tren context.

SimpleVectorStore la vector store in-memory, phu hop de hoc va demo. Khi tat app, du lieu vector trong RAM se mat.
