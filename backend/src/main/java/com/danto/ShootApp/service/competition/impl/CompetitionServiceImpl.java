package com.danto.ShootApp.service.competition.impl;

import com.danto.ShootApp.dto.competition.CreateCompetitionRequest;
import com.danto.ShootApp.dto.competition.CreateCompetitionResponse;
import com.danto.ShootApp.entity.competition.CompetitionEntity;
import com.danto.ShootApp.exceptions.competitionExceptions.DateNotValidException;
import com.danto.ShootApp.mapper.competition.CompetitionMapper;
import com.danto.ShootApp.repository.competition.CompetitionRepository;
import com.danto.ShootApp.service.competition.CompetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Validated
public class CompetitionServiceImpl implements CompetitionService {

    private static final Logger logger = LoggerFactory.getLogger(CompetitionServiceImpl.class);

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private CompetitionMapper competitionMapper;

    @Override
    public CreateCompetitionResponse createCompetition(CreateCompetitionRequest request) {
        logger.trace("Creating competition {}", request);

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


}