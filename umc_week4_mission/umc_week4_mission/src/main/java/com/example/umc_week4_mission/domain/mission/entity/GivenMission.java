package com.example.missionapp.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "given_mission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GivenMission {

    @EmbeddedId
    private GivenMissionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("missionId")
    @JoinColumn(name = "mission_id")
    private Mission mission;

    private Boolean isComplete;
}
