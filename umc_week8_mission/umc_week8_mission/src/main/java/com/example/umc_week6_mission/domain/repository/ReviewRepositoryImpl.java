package com.example.umc_week6_mission.repository;

import com.example.umc_week6_mission.domain.Review;
import com.example.umc_week6_mission.dto.MyReviewSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.umc_week6_mission.domain.QReview.review;
import static com.example.umc_week6_mission.domain.QStore.store;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * @param userId 현재 사용자 ID (필수 조건)
     * @param condition 가게별, 별점별 필터링 조건
     */
    @Override
    public Page<Review> findMyReviewsWithFilter(Long userId, MyReviewSearchCondition condition) {

        // 1. 데이터 조회 쿼리
        List<Review> content = queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin() // 가게명 필터를 위해 조인
                .where(
                        // 필수 조건: 내가 작성한 리뷰
                        review.user.id.eq(userId),
                        // 동적 조건 1: 가게명 필터링
                        storeNameEq(condition.getStoreName()),
                        // 동적 조건 2: 별점 범위 필터링
                        ratingBucketEq(condition.getRatingBucket())
                )
                // 기본 정렬: 최신 리뷰 순
                .orderBy(review.createdAt.desc())
                .offset(condition.getPageable().getOffset())
                .limit(condition.getPageable().getPageSize())
                .fetch();

        // 2. Count 쿼리 (페이징을 위해 필요하며, 데이터 쿼리와 동일한 WHERE 절을 사용)
        JPAQuery<Long> countQuery = queryFactory
                .select(review.count())
                .from(review)
                .where(
                        review.user.id.eq(userId),
                        storeNameEq(condition.getStoreName()),
                        ratingBucketEq(condition.getRatingBucket())
                );

        // 3. Page 객체 반환 (PageableExecutionUtils는 Count 쿼리 최적화를 돕습니다.)
        return PageableExecutionUtils.getPage(content, condition.getPageable(), countQuery::fetchOne);
    }

    // --- BooleanExpression (동적 조건) 정의 ---

    // 1. 가게명 조건: 값이 있을 경우에만 where 절에 추가
    private BooleanExpression storeNameEq(String storeName) {
        // StringUtils.hasText()는 null, 빈 문자열, 공백 문자열 모두 체크
        if (!StringUtils.hasText(storeName)) {
            return null;
        }
        // 정확히 일치하는 가게명으로 필터링
        return store.name.eq(storeName);
    }

    // 2. 별점 범위 조건: "5", "4" 등을 받아 [min, max] 범위로 변환
    private BooleanExpression ratingBucketEq(String ratingBucket) {
        if (!StringUtils.hasText(ratingBucket)) {
            return null;
        }

        double minScore;
        double maxScore;

        try {
            int score = Integer.parseInt(ratingBucket);

            if (score == 5) {
                minScore = 5.0;
                maxScore = 5.0; // 5점
            } else if (score >= 1 && score <= 4) {
                // 4점대 (4.0 ~ 4.999), 3점대 등
                minScore = (double) score;
                // 5.0은 5점에서 처리되도록 4.9999로 설정
                maxScore = (double) score + 0.9999;
            } else {
                return null; // 유효하지 않은 값
            }
        } catch (NumberFormatException e) {
            return null; // 숫자로 변환 불가
        }

        return review.rating.between(minScore, maxScore);
    }
}