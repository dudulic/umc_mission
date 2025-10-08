package com.example.umc_week4_mission.domain.user.entity;

import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String password;

    @Column(length = 11, nullable = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(columnDefinition = "INT default 0")
    @Builder.Default
    private Integer clearMissionCount = 0;

    @Column(columnDefinition = "INT default 0")
    @Builder.Default
    private Integer point = 0;

    // Review와의 양방향 매핑
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}