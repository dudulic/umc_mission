package com.example.umc_week6_mission.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private Double rating;
    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Review(User user, Store store, Double rating, String content) {
        this.user = user;
        this.store = store;
        this.rating = rating;
        this.content = content;
    }
}