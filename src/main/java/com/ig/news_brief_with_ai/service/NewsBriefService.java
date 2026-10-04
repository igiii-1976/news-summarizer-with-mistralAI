package com.ig.news_brief_with_ai.service;

import com.ig.news_brief_with_ai.client.NewsApiClient;
import com.ig.news_brief_with_ai.client.OllamaClient;
import com.ig.news_brief_with_ai.dto.NewsApiResponse;
import com.ig.news_brief_with_ai.dto.NewsSummaryResponse;
import com.ig.news_brief_with_ai.dto.OllamaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NewsBriefService {

    private final NewsApiClient newsApiClient;
    private final OllamaClient ollamaClient;

    @Autowired
    public NewsBriefService(NewsApiClient newsApiClient, OllamaClient ollamaClient) {
        this.newsApiClient = newsApiClient;
        this.ollamaClient = ollamaClient;
    }

    public NewsSummaryResponse generateGeneralNewsBrief(boolean isRender) {
        final NewsApiResponse newsApiResponse = newsApiClient.getTopHeadlines();
        log.info("Requesting summary for {} articles", newsApiResponse.getArticles().size());

        final OllamaResponse ollamaResponse =
                ollamaClient.generateSummary(newsApiResponse.getArticles(), isRender);

        return NewsSummaryResponse.builder()
                .createdAt(java.time.LocalDateTime.now())
                .summary(ollamaResponse.getResponse())
                .build();
    }
}
