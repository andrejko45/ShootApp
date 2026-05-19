package com.danto.ShootApp.entity.result;


import com.danto.ShootApp.entity.participation.ParticipationEntity;
import com.danto.ShootApp.entity.round.RoundEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "results")
public class ResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_participation", nullable = false)
    private ParticipationEntity participation;

    @ManyToOne
    @JoinColumn(name = "id_round", nullable = false)
    private RoundEntity round;

    @Column(nullable = false)
    private Integer points;

}
