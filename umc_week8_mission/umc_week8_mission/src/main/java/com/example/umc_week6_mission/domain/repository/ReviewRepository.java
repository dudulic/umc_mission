package com.example.umc_week6_mission.repository;

import com.example.umc_week6_mission.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

}