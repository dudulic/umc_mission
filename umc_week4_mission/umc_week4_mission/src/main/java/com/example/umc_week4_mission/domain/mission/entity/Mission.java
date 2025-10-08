package com.example.missionapp.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "mission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(length = 200)
    private String howTo;

    private String field;

    @OneToMany(mappedBy = "mission")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "mission")
    private List<GivenMission> givenMissions = new ArrayList<>();
}
