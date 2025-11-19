package com.example.umc_week6_mission.controller;

import com.example.umc_week6_mission.dto.MyReviewSearchCondition;
import com.example.umc_week6_mission.dto.ReviewResponse;
import com.example.umc_week6_mission.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // TODO: 실제 환경에서는 인증/보안 컨텍스트에서 가져와야 함. 현재는 임시 ID 사용.
    private final Long MOCK_USER_ID = 1L;

    /**
     * 내가 작성한 리뷰를 필터링하여 조회하는 단일 API
     * 요청 예시:
     * - 필터링 없음: GET /api/reviews/my?page=0&size=10
     * - 가게별 필터링: GET /api/reviews/my?storeName=반이학생마라탕마라반&page=0&size=10
     * - 별점별 필터링: GET /api/reviews/my?ratingBucket=4&page=0&size=10
     * - 복합 필터링: GET /api/reviews/my?storeName=반이학생마라탕마라반&ratingBucket=5&page=0&size=10
     */
    @GetMapping("/my")
    public ResponseEntity<Page<ReviewResponse>> getMyReviews(
            // @ModelAttribute 생략 가능. 쿼리 파라미터가 DTO 필드에 자동으로 바인딩됨.
            MyReviewSearchCondition condition,
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable // 페이징 정보
    ) {
        // Pageable 객체를 DTO에 주입하여 Repository로 전달 준비
        condition.setPageable(pageable);

        // 비즈니스 로직 호출
        Page<ReviewResponse> result = reviewService.getMyReviews(MOCK_USER_ID, condition);

        return ResponseEntity.ok(result);
    }
}