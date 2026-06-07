package com.danto.ShootApp.service.result.impl;

import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.result.CreateResultRequest;
import com.danto.ShootApp.dto.result.CreateResultResponse;
import com.danto.ShootApp.entity.participation.ParticipationEntity;
import com.danto.ShootApp.entity.result.ResultEntity;
import com.danto.ShootApp.entity.round.RoundEntity;
import com.danto.ShootApp.exceptions.participationExceptions.PartHasAlreadyResultInRound;
import com.danto.ShootApp.exceptions.participationExceptions.ParticipationNotFound;
import com.danto.ShootApp.exceptions.resultExceptions.ResultNotFoundException;
import com.danto.ShootApp.exceptions.roundExceptions.RoundNotFoundException;
import com.danto.ShootApp.mapper.result.ResultMapper;
import com.danto.ShootApp.repository.participation.ParticipationRepository;
import com.danto.ShootApp.repository.result.ResultRepository;
import com.danto.ShootApp.repository.round.RoundRepository;
import com.danto.ShootApp.service.result.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {

    private static final Logger logger = LoggerFactory.getLogger(ResultServiceImpl.class);

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private ResultMapper resultMapper;

    @Override
    public CreateResultResponse createResult(CreateResultRequest request) {
        logger.trace("Creating result {}", request);

        Optional<ParticipationEntity> participation = participationRepository.findById(request.partId());
        if(participation.isEmpty()) {
            throw new ParticipationNotFound("Participation with ID: " + request.partId() + " not found !");
        }

        Optional<RoundEntity> round = roundRepository.findById(request.roundId());
        if(round.isEmpty()) {
            throw new RoundNotFoundException("Round with ID: " + request.roundId() + " not found !");
        }

        if(resultRepository.existsByParticipation_IdAndRound_Id(request.partId(), request.roundId())) {
            throw new PartHasAlreadyResultInRound("Participation with ID: " + request.partId() + " has result in round ID: " + request.roundId());
        }

        ResultEntity newResult = new ResultEntity();
        newResult.setParticipation(participation.get());
        newResult.setRound(round.get());
        newResult.setPoints(request.points());
        resultRepository.save(newResult);

        return resultMapper.toResponse(newResult);
    }

    @Override
    public List<CreateResultResponse> getResults() {
        logger.trace("Getting all results !");

        List<CreateResultResponse> resultList = new ArrayList<>();
        for(ResultEntity r : resultRepository.findAll()) {
            resultList.add(resultMapper.toResponse(r));
        }

        return resultList;


    }

    @Override
    public CreateResultResponse getById(Long id) {
        logger.trace("Getting result with ID: " + id);

        Optional<ResultEntity> result = resultRepository.findById(id);
        if(result.isEmpty()) {
            throw new ResultNotFoundException("Result with ID: " + id + " not found !");
        }
        else {
            return resultMapper.toResponse(result.get());
        }
    }

    @Override
    public DeleteResponse deleteById(Long id) {
        logger.trace("Deleting result with ID: " + id);

        if(resultRepository.existsById(id)) {
            resultRepository.deleteById(id);
            return new DeleteResponse("Result with ID: " + id + " deleted sucessfully !");
        }
        else {
            throw new ResultNotFoundException("Result with ID: " + id + " not found");
        }

    }
}
