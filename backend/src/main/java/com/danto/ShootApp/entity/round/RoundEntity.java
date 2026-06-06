package com.danto.ShootApp.entity.round;


import com.danto.ShootApp.entity.competition.CompetitionEntity;
import com.danto.ShootApp.entity.result.ResultEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "rounds", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {
                        "id_competition",
                        "order_index"
                }
        )
})
public class RoundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_competition")
    private CompetitionEntity competition;

    @OneToMany(mappedBy = "round")
    private List<ResultEntity> results;

    @NotNull
    @Column(name = "order_index",nullable = false)
    private Integer orderIndex;

    @Column(name = "description")
    private String description;


}
