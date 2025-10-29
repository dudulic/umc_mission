package com.example.demo.domain.repository;

import com.example.demo.domain.user.User;
import com.example.demo.dto.MyPageResponseDto; // dto 패키지 import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 마이페이지 쿼리
    @Query("SELECT new com.example.demo.dto.MyPageResponseDto(u.name, u.email, u.phone, u.clearId) " +
            "FROM User u WHERE u.userId = :userId")
    Optional<MyPageResponseDto> findMyPageInfo(@Param("userId") Long userId);
}