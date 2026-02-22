package com.example.umc_week6_mission.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class MyReviewSearch {
    private String storeName;

    private String ratingBucket;

    private Pageable pageable;
}