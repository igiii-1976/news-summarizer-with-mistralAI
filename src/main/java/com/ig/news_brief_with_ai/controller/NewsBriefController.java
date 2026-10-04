package com.ig.news_brief_with_ai.controller;

import com.ig.news_brief_with_ai.dto.NewsSummaryResponse;
import com.ig.news_brief_with_ai.service.NewsBriefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/news-brief")
public class NewsBriefController {
    private final NewsBriefService newsBriefService;

    @Autowired
    public NewsBriefController(NewsBriefService newsBriefService) {
        this.newsBriefService = newsBriefService;
    }

    @GetMapping(value = "/general-brief", produces = MediaType.APPLICATION_JSON_VALUE)
    public NewsSummaryResponse generalBrief() {
        return newsBriefService.generateGeneralNewsBrief(false);
    }

    @GetMapping(value = "/general-brief/render", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> generalBriefUI() {
        final NewsSummaryResponse newsSummaryResponse =
                newsBriefService.generateGeneralNewsBrief(true);

        final String htmlContent = newsSummaryResponse
                .getSummary().substring(7, newsSummaryResponse.getSummary().length() - 3);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(htmlContent);
    }
}
