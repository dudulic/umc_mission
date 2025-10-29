package com.example.demo.domain.mission;

import com.example.demo.domain.user.User;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Given_Mission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GivenMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // GivenMission의 고유 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Column(name = "is_complete")
    private boolean isComplete;
}