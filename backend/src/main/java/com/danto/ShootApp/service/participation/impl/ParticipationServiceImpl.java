package com.danto.ShootApp.service.participation.impl;

import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.participation.CreateParticipationRequest;
import com.danto.ShootApp.dto.participation.CreateParticipationResponse;
import com.danto.ShootApp.entity.competition.CompetitionEntity;
import com.danto.ShootApp.entity.participation.ParticipationEntity;
import com.danto.ShootApp.entity.role.RoleEntity;
import com.danto.ShootApp.entity.user.UserEntity;
import com.danto.ShootApp.exceptions.competitionExceptions.CompetitionNotFoundException;
import com.danto.ShootApp.exceptions.participationExceptions.ParticipationNotFound;
import com.danto.ShootApp.exceptions.participationExceptions.UserAlreadyHasPartInComp;
import com.danto.ShootApp.exceptions.roleExceptions.RoleNotFoundException;
import com.danto.ShootApp.exceptions.userExceptions.UserNotFoundException;
import com.danto.ShootApp.mapper.participation.ParticipationMapper;
import com.danto.ShootApp.repository.competition.CompetitionRepository;
import com.danto.ShootApp.repository.participation.ParticipationRepository;
import com.danto.ShootApp.repository.role.RoleRepository;
import com.danto.ShootApp.repository.user.UserRepository;
import com.danto.ShootApp.service.participation.ParticipationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ParticipationServiceImpl implements ParticipationService {

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

    @Override
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

        if(participationRepository.existsByUser_IdAndCompetition_Id(request.userId(), request.compId())) {
            throw new UserAlreadyHasPartInComp("User with ID: " + request.userId() + " has participation in competition ID: " + request.compId());
        }

        ParticipationEntity newParticipation = new ParticipationEntity();
        newParticipation.setUser(user.get());
        newParticipation.setCompetition(competition.get());
        newParticipation.setRole(role.get());
        newParticipation.setResults(null);

        participationRepository.save(newParticipation);

        return participationMapper.toResponse(newParticipation);

    }

    @Override
    public List<CreateParticipationResponse> getParticipations() {
        logger.trace("Getting all participations");

        List<CreateParticipationResponse> allPart = new ArrayList<>();
        for(ParticipationEntity p : participationRepository.findAll()) {
            allPart.add(participationMapper.toResponse(p));
        }

        return allPart;

    }

    @Override
    public CreateParticipationResponse findById(Long id) {
        logger.trace("Finding participation by ID" + id);

        Optional<ParticipationEntity> partId = participationRepository.findById(id);
        if(partId.isPresent()) {
            return participationMapper.toResponse(partId.get());
        }
        else {
            throw new ParticipationNotFound("Participaton with ID: " + id + " not found !");
        }
    }

    @Override
    public DeleteResponse deleteById(Long id) {
        logger.trace("Deleting participation with ID: " + id);

        if(participationRepository.existsById(id)) {
            participationRepository.deleteById(id);
            return new DeleteResponse("Participation with ID: " + id + " deleted sucessfully !");
        }
        else {
            throw new ParticipationNotFound("Participation with ID: " + id + " not found !");
        }
    }

}
