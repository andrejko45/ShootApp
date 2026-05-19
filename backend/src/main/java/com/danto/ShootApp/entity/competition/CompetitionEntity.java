package com.danto.ShootApp.entity.competition;

import com.danto.ShootApp.entity.participation.ParticipationEntity;
import com.danto.ShootApp.entity.round.RoundEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String place;

    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "competition")
    private List<ParticipationEntity> participations;

    @OneToMany(mappedBy = "competition")
    private List<RoundEntity> rounds;




}
