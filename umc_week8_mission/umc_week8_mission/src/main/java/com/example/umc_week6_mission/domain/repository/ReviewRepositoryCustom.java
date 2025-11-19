package com.example.umc_week6_mission.repository;

import com.example.umc_week6_mission.domain.Review;
import com.example.umc_week6_mission.dto.MyReviewSearchCondition;
import org.springframework.data.domain.Page;

public interface ReviewRepositoryCustom {
    Page<Review> findMyReviewsWithFilter(Long userId, MyReviewSearchCondition condition);
}