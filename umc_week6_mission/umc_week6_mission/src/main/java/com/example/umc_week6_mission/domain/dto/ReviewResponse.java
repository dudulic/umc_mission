package com.example.umc_week6_mission.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponse {
    private Long reviewId;
    private String storeName;
    private Double rating;
    private String content;
    private LocalDateTime createdAt;
}