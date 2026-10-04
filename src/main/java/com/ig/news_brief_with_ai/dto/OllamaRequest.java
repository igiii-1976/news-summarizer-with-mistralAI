package com.ig.news_brief_with_ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OllamaRequest {
    private String model;
    private String prompt;
    private boolean stream;
}
