package com.example.demo.domain.repository;

import com.example.demo.domain.mission.GivenMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GivenMissionRepository extends JpaRepository<GivenMission, Long> {

    // 3. 내가 진행중/완료한 미션 쿼리 (상태 필터 + 페이징)
    Page<GivenMission> findByUserUserIdAndIsCompleteOrderByMissionMissionIdDesc(
            Long userId,
            boolean isComplete,
            Pageable pageable
    );
}