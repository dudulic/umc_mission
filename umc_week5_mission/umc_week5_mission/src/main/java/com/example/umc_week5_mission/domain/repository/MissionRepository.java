package com.example.demo.domain.repository;

import com.example.demo.domain.mission.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Page<Mission> findByStoreStoreNameContaining(String regionName, Pageable pageable);
}