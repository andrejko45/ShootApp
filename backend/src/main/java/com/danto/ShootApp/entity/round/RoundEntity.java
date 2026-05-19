package com.danto.ShootApp.entity.round;


import com.danto.ShootApp.entity.competition.CompetitionEntity;
import com.danto.ShootApp.entity.result.ResultEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rounds")
public class RoundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_competition")
    private CompetitionEntity competition;

    @OneToMany(mappedBy = "round")
    private List<ResultEntity> results;


}
