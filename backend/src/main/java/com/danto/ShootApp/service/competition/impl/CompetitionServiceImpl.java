package com.danto.ShootApp.service.competition.impl;

import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.competition.CreateCompetitionRequest;
import com.danto.ShootApp.dto.competition.CreateCompetitionResponse;
import com.danto.ShootApp.dto.competition.UpdateCompetitionRequest;
import com.danto.ShootApp.entity.competition.CompetitionEntity;
import com.danto.ShootApp.exceptions.competitionExceptions.CompAlreadyExists;
import com.danto.ShootApp.exceptions.competitionExceptions.CompHasParticipation;
import com.danto.ShootApp.exceptions.competitionExceptions.CompetitionNotFoundException;
import com.danto.ShootApp.exceptions.competitionExceptions.DateNotValidException;
import com.danto.ShootApp.mapper.competition.CompetitionMapper;
import com.danto.ShootApp.repository.competition.CompetitionRepository;
import com.danto.ShootApp.repository.participation.ParticipationRepository;
import com.danto.ShootApp.service.competition.CompetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class CompetitionServiceImpl implements CompetitionService {

    private static final Logger logger = LoggerFactory.getLogger(CompetitionServiceImpl.class);

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private CompetitionMapper competitionMapper;

    @Override
    public CreateCompetitionResponse createCompetition(CreateCompetitionRequest request) {
        logger.trace("Creating competition {}", request);


        if(competitionRepository.existsByName(request.name())) {
            throw new CompAlreadyExists("Competition with name " + request.name() + " already exists !");
        }

        if(request.date().isAfter(LocalDate.now())) {

            CompetitionEntity newCompetition = competitionMapper.toEntity(request);

            competitionRepository.save(newCompetition);

            return competitionMapper.toResponse(newCompetition);
        }
        else {
            throw new DateNotValidException("Date can't be in the past or now !");
        }
    }

    @Override
    public List<CreateCompetitionResponse> getCompetitions() {
        logger.trace("Finding all competitions !");

        List<CreateCompetitionResponse> competitionList = new ArrayList<>();
        for(CompetitionEntity c : competitionRepository.findAll()) {
            competitionList.add(competitionMapper.toResponse(c));
        }

        return competitionList;
    }

    @Override
    public CreateCompetitionResponse findCompByName(String name) {
        if(competitionRepository.existsByName(name)) {
            return competitionMapper.toResponse(competitionRepository.getCompetitionByName(name));
        }
        else {
            throw new CompetitionNotFoundException("Competition with name " + name + " not found !");
        }
    }



    @Override
    public CreateCompetitionResponse fullCompUpdate(UpdateCompetitionRequest request) {
        logger.trace("Updating competition !");

        Optional<CompetitionEntity> compToBeUpdated = competitionRepository.findById(request.id());

        if(compToBeUpdated.isPresent()) {

            compToBeUpdated.get().setName(request.name());
            compToBeUpdated.get().setPlace(request.place());
            compToBeUpdated.get().setDate(request.date());
            competitionRepository.save(compToBeUpdated.get());
            return competitionMapper.toResponse(compToBeUpdated.get());
        }
        else {
            throw new CompetitionNotFoundException("Competition " + request.name() + " not found !");
        }

    }

    @Override
    public DeleteResponse deleteCompetition(Long id) {
        logger.trace("Deleting a competition with id: " + id);


        if(participationRepository.existsByCompetition_Id(id)) {
            throw new CompHasParticipation("Competition with ID: " + id + " has at least one participation ! Please delete the participation first !");
        }


        boolean exists = competitionRepository.existsById(id);
        if(exists) {
            competitionRepository.deleteById(id);
            return new DeleteResponse("Competition with ID: " + id + " deleted sucessfully !");
        }
        else {
            throw new CompetitionNotFoundException("Competition with ID: " + id + " not found !");
        }






    }


}