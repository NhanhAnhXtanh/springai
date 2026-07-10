# 10 - Persist RAG Vector Store

Bai truoc dung `SimpleVectorStore` in-memory.

Nghia la:

```text
app start -> tao embedding -> luu trong RAM
app stop  -> mat vector store
```

Bai nay luu vector store ra file JSON trong folder `build/`.

## 1. File vector store

Khi app start lan dau, no tao file:

```text
build/vector-store/spring-ai-notes.json
```

File nay chua:

```text
text chunk
metadata
embedding vector
```

Vi folder `build/` da nam trong `.gitignore`, file vector store khong bi commit len GitHub.

## 2. Doi bean thanh SimpleVectorStore

File:

```text
src/main/java/com/example/springai/rag/RagConfig.java
```

Truoc do:

```java
VectorStore vectorStore(EmbeddingModel embeddingModel)
```

Bai nay doi thanh:

```java
SimpleVectorStore vectorStore(EmbeddingModel embeddingModel)
```

Ly do: `SimpleVectorStore` co method rieng:

```java
save(File file)
load(File file)
```

## 3. Co che start app

Code:

```java
var vectorStoreFile = Path.of("build", "vector-store", "spring-ai-notes.json")
    .toAbsolutePath()
    .normalize();
```

Neu file da ton tai:

```java
if (Files.exists(vectorStoreFile)) {
  vectorStore.load(vectorStoreFile.toFile());
  return;
}
```

Nghia la app load vector store tu file, khong can embedding lai tai lieu.

Neu file chua ton tai:

```java
vectorStore.add(chunks);
vectorStore.save(vectorStoreFile.toFile());
```

Nghia la:

```text
doc -> chunks -> embeddings -> vector store -> save JSON
```

## 4. Tai sao cach nay huu ich?

Lan dau:

```text
goi embedding model
ton API cost
mat thoi gian
```

Lan sau:

```text
load file JSON
nhanh hon
khong can embedding lai
```

## 5. Khi nao can tao lai file?

Neu sua file:

```text
src/main/resources/rag/spring-ai-notes.md
```

thi nen xoa:

```text
build/vector-store/spring-ai-notes.json
```

Sau do chay app lai de index tai lieu moi.

## 6. Luu y

Day van la demo/local persistence.

Production nen dung vector database that nhu:

- pgvector
- Qdrant
- Chroma
- Redis
