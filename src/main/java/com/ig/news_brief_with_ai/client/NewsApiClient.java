package com.ig.news_brief_with_ai.client;

import com.ig.news_brief_with_ai.dto.NewsApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class NewsApiClient {

    @Value("${news.api.key}")
    private String apiKey;

    @Value("${news.api.base.url}")
    private String baseUrl;

    @Value("${news.api.default.country}")
    private String defaultCountry;

    @Value("${news.api.url.content.top.headlines}")
    private String topHeadlinesUrl;

    public NewsApiResponse getTopHeadlines() {
        final RestTemplate restTemplate = new RestTemplate();

        final String url = getTopHeadlinesUrl();

        final NewsApiResponse result = restTemplate.getForObject(url, NewsApiResponse.class);

        log.info("Top headlines response: {}", result);
        return result;
    }

    private String getTopHeadlinesUrl() {
        final String baseUrl = this.baseUrl + this.topHeadlinesUrl;

        final String urlWithParams = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("country", defaultCountry)
                .queryParam("apiKey", apiKey)
                .toUriString();
        log.info("Top headlines URL: {}", urlWithParams);

        return urlWithParams;
    }
}
