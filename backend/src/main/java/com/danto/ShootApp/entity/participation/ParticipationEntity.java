package com.danto.ShootApp.entity.participation;

import com.danto.ShootApp.entity.competition.CompetitionEntity;
import com.danto.ShootApp.entity.result.ResultEntity;
import com.danto.ShootApp.entity.role.RoleEntity;
import com.danto.ShootApp.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "participations", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {
                        "id_user",
                        "id_competition"
                }
        )
})
public class ParticipationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_competition")
    private CompetitionEntity competition;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    private RoleEntity role;

    @OneToMany(mappedBy = "participation")
    private List<ResultEntity> results;

}
