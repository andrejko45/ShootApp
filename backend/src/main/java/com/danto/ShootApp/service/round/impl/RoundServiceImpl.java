package com.danto.ShootApp.service.round.impl;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danto.ShootApp.dto.round.CreateRoundRequest;
import com.danto.ShootApp.dto.round.CreateRoundResponse;
import com.danto.ShootApp.entity.competition.CompetitionEntity;
import com.danto.ShootApp.entity.round.RoundEntity;
import com.danto.ShootApp.exceptions.competitionExceptions.CompetitionNotFoundException;
import com.danto.ShootApp.exceptions.roundExceptions.NameHasToBeUniqueInCompException;
import com.danto.ShootApp.exceptions.roundExceptions.OrderHasToBeUniqueException;
import com.danto.ShootApp.mapper.round.RoundMapper;
import com.danto.ShootApp.repository.competition.CompetitionRepository;
import com.danto.ShootApp.repository.round.RoundRepository;
import com.danto.ShootApp.service.round.RoundService;

@Service
public class RoundServiceImpl implements RoundService{

    private static final Logger logger = LoggerFactory.getLogger(RoundServiceImpl.class);

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private CompetitionRepository competitionRepository;
    
    @Autowired
    private RoundMapper roundMapper;


    public CreateRoundResponse createRound(CreateRoundRequest request) {
        logger.trace("Creating round {}", request);


        Optional<CompetitionEntity> competition = competitionRepository.findById(request.compId());
        if(competition.isEmpty()) {
          throw new CompetitionNotFoundException("Competition with ID: " + request.compId() + " not found");
        }

        if(roundRepository.existsByCompetition_IdAndName(request.compId(), request.name())) {
          throw new NameHasToBeUniqueInCompException("Name of the round has to be unique in the same competition !");
        }

        if(roundRepository.existsByCompetition_IdAndOrderIndex(request.compId(), request.orderIndex())) {
          throw new OrderHasToBeUniqueException("Order index has to unique in the same competition !");
        }

        RoundEntity newRound = new RoundEntity();
        newRound.setName(request.name());
        newRound.setCompetition(competition.get());
        newRound.setOrderIndex(request.orderIndex());
        newRound.setResults(null);
        newRound.setDescription(request.description());

        roundRepository.save(newRound);
        return roundMapper.toResponse(newRound);

    }


}
