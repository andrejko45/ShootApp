package com.danto.ShootApp.entity.result;


import com.danto.ShootApp.entity.participation.ParticipationEntity;
import com.danto.ShootApp.entity.round.RoundEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "results", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {
                        "id_participation",
                        "id_round"
                }
        )
})
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

    @NotNull
    @Column(nullable = false)
    private Integer points;

}
