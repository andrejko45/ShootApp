package com.danto.ShootApp.entity.competition;

import com.danto.ShootApp.entity.participation.ParticipationEntity;
import com.danto.ShootApp.entity.round.RoundEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "competitions")
public class CompetitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "competition")
    private List<ParticipationEntity> participations;

    @OneToMany(mappedBy = "competition")
    private List<RoundEntity> rounds;




}
