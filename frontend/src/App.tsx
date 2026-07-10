import {
  Bot,
  BookOpen,
  CornerDownLeft,
  Loader2,
  MessageSquareText,
  Network,
  Send,
  Sparkles,
  UserRound,
} from 'lucide-react'
import { useMemo, useRef, useState } from 'react'
import type { FormEvent } from 'react'
import './App.css'

type Role = 'user' | 'assistant'

type ChatMessage = {
  id: string
  role: Role
  content: string
}

type ChatResponse = {
  answer: string
}

type LessonPlan = {
  topic: string
  steps: string[]
}

const starterPrompts = [
  'Giai thich ChatClient trong Spring AI bang vi du ngan gon',
  'What is 12.5 * 8? Explain how you got it.',
  '/plan Spring AI tool calling',
  '/rag SimpleVectorStore dung de lam gi?',
]

function App() {
  const [messages, setMessages] = useState<ChatMessage[]>([
    {
      id: crypto.randomUUID(),
      role: 'assistant',
      content:
        'Chao ban. Minh la giao dien chat cho Spring AI backend. Hoi thu mot cau hoac dung /plan <topic> de tao learning plan.',
    },
  ])
  const [input, setInput] = useState('')
  const [isLoading, setIsLoading] = useState(false)
  const [error, setError] = useState('')
  const inputRef = useRef<HTMLTextAreaElement>(null)

  const lastMessage = messages[messages.length - 1]
  const statusText = isLoading
    ? 'Calling Spring AI'
    : error
      ? 'Backend issue'
      : 'Ready'

  const canSend = useMemo(() => input.trim().length > 0 && !isLoading, [input, isLoading])

  async function sendMessage(rawMessage: string) {
    const message = rawMessage.trim()
    if (!message || isLoading) {
      return
    }

    setError('')
    setInput('')
    setMessages((current) => [
      ...current,
      { id: crypto.randomUUID(), role: 'user', content: message },
    ])
    setIsLoading(true)

    try {
      const assistantMessage = message.startsWith('/plan ')
        ? await requestLessonPlan(message.substring('/plan '.length).trim())
        : message.startsWith('/rag ')
          ? await requestRag(message.substring('/rag '.length).trim())
        : await requestChat(message)

      setMessages((current) => [
        ...current,
        { id: crypto.randomUUID(), role: 'assistant', content: assistantMessage },
      ])
    } catch (err) {
      const errorMessage =
        err instanceof Error ? err.message : 'Could not connect to the Spring Boot API.'
      setError(errorMessage)
      setMessages((current) => [
        ...current,
        {
          id: crypto.randomUUID(),
          role: 'assistant',
          content: `Request failed: ${errorMessage}`,
        },
      ])
    } finally {
      setIsLoading(false)
      requestAnimationFrame(() => inputRef.current?.focus())
    }
  }

  async function requestChat(message: string) {
    const response = await fetch('/api/chat', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ message }),
    })

    if (!response.ok) {
      throw new Error(`Chat API returned ${response.status}`)
    }

    const data = (await response.json()) as ChatResponse
    return data.answer
  }

  async function requestLessonPlan(topic: string) {
    if (!topic) {
      throw new Error('Missing topic after /plan')
    }

    const response = await fetch('/api/lesson-plan', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ topic }),
    })

    if (!response.ok) {
      throw new Error(`Lesson plan API returned ${response.status}`)
    }

    const data = (await response.json()) as LessonPlan
    return [`Topic: ${data.topic}`, ...data.steps.map((step, index) => `${index + 1}. ${step}`)].join(
      '\n',
    )
  }

  async function requestRag(question: string) {
    if (!question) {
      throw new Error('Missing question after /rag')
    }

    const response = await fetch('/api/rag', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ message: question }),
    })

    if (!response.ok) {
      throw new Error(`RAG API returned ${response.status}`)
    }

    const data = (await response.json()) as ChatResponse
    return data.answer
  }

  function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    void sendMessage(input)
  }

  return (
    <main className="app-shell">
      <aside className="side-panel">
        <div className="brand-block">
          <div className="brand-mark">
            <Sparkles size={20} strokeWidth={2.2} />
          </div>
          <div>
            <p className="eyebrow">Spring AI Lab</p>
            <h1>Chat Console</h1>
          </div>
        </div>

        <section className="panel-section">
          <div className="section-title">
            <Network size={17} />
            <span>Runtime</span>
          </div>
          <div className="status-row">
            <span className={`status-dot ${error ? 'danger' : isLoading ? 'busy' : ''}`}></span>
            <span>{statusText}</span>
          </div>
          <p className="muted">Vite proxies API calls to Spring Boot on port 8080.</p>
        </section>

        <section className="panel-section">
          <div className="section-title">
            <BookOpen size={17} />
            <span>Prompts</span>
          </div>
          <div className="prompt-list">
            {starterPrompts.map((prompt) => (
              <button
                className="prompt-chip"
                key={prompt}
                type="button"
                onClick={() => {
                  setInput(prompt)
                  inputRef.current?.focus()
                }}
              >
                {prompt}
              </button>
            ))}
          </div>
        </section>
      </aside>

      <section className="chat-panel">
        <header className="chat-header">
          <div>
            <p className="eyebrow">Conversation</p>
            <h2>Ask, plan, and test tools</h2>
          </div>
          <div className="header-badge">
            <MessageSquareText size={16} />
            <span>{messages.length} messages</span>
          </div>
        </header>

        <div className="message-list" aria-live="polite">
          {messages.map((message) => (
            <article className={`message ${message.role}`} key={message.id}>
              <div className="avatar" aria-hidden="true">
                {message.role === 'assistant' ? <Bot size={18} /> : <UserRound size={18} />}
              </div>
              <div className="message-body">
                <div className="message-meta">
                  <span>{message.role === 'assistant' ? 'Spring AI' : 'You'}</span>
                </div>
                <p>{message.content}</p>
              </div>
            </article>
          ))}

          {isLoading && (
            <article className="message assistant pending">
              <div className="avatar" aria-hidden="true">
                <Bot size={18} />
              </div>
              <div className="message-body">
                <div className="message-meta">
                  <span>Spring AI</span>
                </div>
                <p>
                  <Loader2 className="spin" size={16} /> Thinking through the request
                </p>
              </div>
            </article>
          )}
        </div>

        <form className="composer" onSubmit={handleSubmit}>
          <textarea
            ref={inputRef}
            value={input}
            onChange={(event) => setInput(event.target.value)}
            onKeyDown={(event) => {
              if (event.key === 'Enter' && !event.shiftKey) {
                event.preventDefault()
                if (canSend) {
                  void sendMessage(input)
                }
              }
            }}
            placeholder="Ask Spring AI, or type /rag What is SimpleVectorStore?"
            rows={3}
          />
          <div className="composer-footer">
            <div className="shortcut-hint">
              <CornerDownLeft size={15} />
              <span>Enter to send</span>
            </div>
            <button type="submit" disabled={!canSend}>
              {isLoading ? <Loader2 className="spin" size={18} /> : <Send size={18} />}
              <span>Send</span>
            </button>
          </div>
        </form>

        {error && <p className="error-text">{error}</p>}
        {!error && lastMessage?.role === 'assistant' && (
          <p className="footer-note">Connected to your Spring Boot lesson API.</p>
        )}
      </section>
    </main>
  )
}

export default App
