package com.example.missionapp.domain.repository;

import com.example.missionapp.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, Long> {}
