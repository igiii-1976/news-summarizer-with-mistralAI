# news-summarizer-with-mistralAI

A Spring Boot application that fetches the latest top headlines from NewsAPI and summarizes them using a local Ollama-hosted Mistral model. The service produces a concise daily news brief in JSON format and can also render an HTML version for browser display.

## Overview

This project combines:
- NewsAPI for current global headlines
- Spring Boot for the REST API layer
- Ollama + Mistral for AI-powered summarization
- Java 21 and Maven for build and execution

It is designed to provide a quick, readable summary of breaking stories in a clean, machine-friendly output.

## Features

- Fetches top headlines for a country (default: `us`)
- Sends the article list to an AI summarizer
- Returns AI-generated summaries as JSON
- Generates a browser-ready HTML version for display in a UI
- Runs locally with Ollama using a configurable model

## Tech Stack

- Java 21
- Spring Boot 4.1.1
- Maven
- NewsAPI
- Ollama
- Mistral model (`mistral:7b` by default)

## Project Structure

```text
src/
├── main/
│   ├── java/com/ig/news_brief_with_ai/
│   │   ├── client/
│   │   │   ├── NewsApiClient.java
│   │   │   └── OllamaClient.java
│   │   ├── controller/
│   │   │   └── NewsBriefController.java
│   │   ├── dto/
│   │   │   ├── Article.java
│   │   │   ├── NewsApiResponse.java
│   │   │   ├── NewsSummaryResponse.java
│   │   │   ├── OllamaRequest.java
│   │   │   └── OllamaResponse.java
│   │   ├── service/
│   │   │   └── NewsBriefService.java
│   │   └── NewsBriefWithAiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/ig/news_brief_with_ai/NewsBriefWithAiApplicationTests.java
```

## Configuration

The application configuration is in `src/main/resources/application.properties`.

```properties
spring.application.name=news-brief-with-ai

news.api.key=YOUR_NEWSAPI_KEY
news.api.base.url=https://newsapi.org/v2
news.api.default.country=us
news.api.url.content.top.headlines=/top-headlines

ollama.base.url=http://localhost:11434/api/generate
ollama.mistral.model=mistral:7b
```

### Required setup

1. Get a NewsAPI key from: https://newsapi.org/
2. Install and run Ollama locally
3. Pull the model used by the app:

```bash
ollama pull mistral:7b
```

4. Ensure the Ollama API is available at `http://localhost:11434`

## Running the Application

Using Maven wrapper:

On macOS/Linux:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

By default, the app runs on:

```text
http://localhost:8080
```

## API Endpoints

### 1) JSON news brief

```http
GET /api/v2/news-brief/general-brief
```

Returns a JSON object like:

```json
{
  "summary": "Top global stories ...",
  "createdAt": "2026-10-08T02:00:00"
}
```

### 2) HTML-rendered brief

```http
GET /api/v2/news-brief/general-brief/render
```

<img width="1917" height="480" alt="image" src="https://github.com/user-attachments/assets/37544de2-ff45-4f90-be6d-0fad7d930271" />

Returns raw HTML content suitable for browser rendering.

## Example Requests

```bash
curl http://localhost:8080/api/v2/news-brief/general-brief
curl http://localhost:8080/api/v2/news-brief/general-brief/render
```

## Notes

- The app currently fetches top headlines from one default country (`us`).
- The AI-generated summary is constrained by the article content returned by NewsAPI and the local model available through Ollama.
- The render endpoint strips response wrapper text and returns pure HTML for display purposes.
