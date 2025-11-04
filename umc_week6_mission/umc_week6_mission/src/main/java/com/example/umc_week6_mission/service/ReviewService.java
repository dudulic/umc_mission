package com.example.demo.service;

import com.example.demo.domain.mission.Mission;
import com.example.demo.domain.review.Review;
import com.example.demo.domain.store.Store;
import com.example.demo.domain.user.User;
import com.example.demo.domain.repository.MissionRepository;
import com.example.demo.domain.repository.ReviewRepository;
import com.example.demo.domain.repository.StoreRepository;
import com.example.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    /**
     * 1. 리뷰 작성 쿼리 (INSERT)
     */
    @Transactional
    public Long createReview(Long userId, Long missionId, Long storeId, String content, int rating) {

        // 1. ID로 실제 엔티티 객체들을 조회합니다.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("미션 없음"));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게 없음"));

        Review newReview = Review.builder()
                .user(user)
                .mission(mission)
                .store(store)
                .content(content)
                .rating(rating)
                .build();

        Review savedReview = reviewRepository.save(newReview);

        return savedReview.getReviewId();
    }
}