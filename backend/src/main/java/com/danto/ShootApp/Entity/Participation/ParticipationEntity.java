package com.danto.ShootApp.Entity.Participation;

import com.danto.ShootApp.Entity.Competition.CompetitionEntity;
import com.danto.ShootApp.Entity.Role.RoleEntity;
import com.danto.ShootApp.Entity.User.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "participations")
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

}
