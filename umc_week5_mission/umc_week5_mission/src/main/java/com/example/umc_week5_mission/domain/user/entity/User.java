package com.example.demo.domain.user;

import com.example.demo.domain.mission.GivenMission;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
    private String phone;
    private String clearId;

    @OneToMany(mappedBy = "users")
    private List<GivenMission> givenMissions = new ArrayList<>();
}