package com.example.umc_week6_mission.review;

import com.example.umc_week6_mission.domain.Review;
import com.example.umc_week6_mission.dto.MyReviewSearchCondition;
import com.example.umc_week6_mission.dto.ReviewResponse;
import com.example.umc_week6_mission.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Page<ReviewResponse> getMyReviews(Long userId, MyReviewSearchCondition condition) {

        Page<Review> reviewsPage = reviewRepository.findMyReviewsWithFilter(userId, condition);

        return reviewsPage.map(this::toReviewResponse);
    }

    private ReviewResponse toReviewResponse(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}