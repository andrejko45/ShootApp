package com.danto.ShootApp.controller.round;

import com.danto.ShootApp.dto.DeleteResponse;
import org.springframework.web.bind.annotation.*;

import com.danto.ShootApp.dto.round.CreateRoundRequest;
import com.danto.ShootApp.dto.round.CreateRoundResponse;
import com.danto.ShootApp.service.round.RoundService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rounds")
@RequiredArgsConstructor
public class RoundController {

    private final RoundService roundService;

    @PostMapping
    public CreateRoundResponse createRound(@Valid @RequestBody CreateRoundRequest request) {
      return roundService.createRound(request);
    }

    @GetMapping
    public List<CreateRoundResponse> getAllRounds() {
        return roundService.getAllRounds();
    }

    @GetMapping(path = "/search")
    public CreateRoundResponse getByCompIdAndName(@RequestParam Long compId, @RequestParam String name) {
        return roundService.getByCompIdAndName(compId, name);
    }

    @DeleteMapping(path = "/{id}")
    public DeleteResponse deleteById(@PathVariable Long id) {
        return roundService.deleteById(id);
    }

}
