package com.danto.ShootApp.service.participation.impl;

import com.danto.ShootApp.dto.participation.CreateParticipationRequest;
import com.danto.ShootApp.dto.participation.CreateParticipationResponse;
import com.danto.ShootApp.entity.competition.CompetitionEntity;
import com.danto.ShootApp.entity.participation.ParticipationEntity;
import com.danto.ShootApp.entity.role.RoleEntity;
import com.danto.ShootApp.entity.user.UserEntity;
import com.danto.ShootApp.exceptions.competitionExceptions.CompetitionNotFoundException;
import com.danto.ShootApp.exceptions.roleExceptions.RoleNotFoundException;
import com.danto.ShootApp.exceptions.userExceptions.UserNotFoundException;
import com.danto.ShootApp.mapper.participation.ParticipationMapper;
import com.danto.ShootApp.repository.competition.CompetitionRepository;
import com.danto.ShootApp.repository.participation.ParticipationRepository;
import com.danto.ShootApp.repository.role.RoleRepository;
import com.danto.ShootApp.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Dokoncenie este Role CRUD nech mozme odskusat Postman na participation

@Service
public class ParticipationServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(ParticipationServiceImpl.class);

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ParticipationMapper participationMapper;

    public CreateParticipationResponse createParticipation(CreateParticipationRequest request) {
        logger.trace("Creating participation {}", request);

        Optional<UserEntity> user = userRepository.findById(request.userId());
        if(user.isEmpty()) {
            throw new UserNotFoundException("User with ID: " + request.userId() + " not found !");
        }

        Optional<CompetitionEntity> competition = competitionRepository.findById(request.compId());
        if(competition.isEmpty()) {
            throw new CompetitionNotFoundException("Competition with ID: " + request.compId() + " not found !");
        }

        Optional<RoleEntity> role = roleRepository.findById(request.roleId());
        if(role.isEmpty()) {
            throw new RoleNotFoundException("Role with ID: " + request.roleId() + " not found !");
        }

        ParticipationEntity newParticipation = new ParticipationEntity();
        newParticipation.setUser(user.get());
        newParticipation.setCompetition(competition.get());
        newParticipation.setRole(role.get());
        newParticipation.setResults(null);

        participationRepository.save(newParticipation);

        return participationMapper.toResponse(newParticipation);

    }


}
