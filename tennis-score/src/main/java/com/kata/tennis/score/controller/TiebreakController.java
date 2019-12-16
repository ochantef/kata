package com.kata.tennis.score.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.tennis.score.dto.TiebreakDTO;
import com.kata.tennis.score.service.TiebreakService;

@RestController
@RequestMapping("/tiebreaks")
public class TiebreakController {
	@Autowired
	private TiebreakService tiebreakService;
	
    @PostMapping
    ResponseEntity<TiebreakDTO> createTiebreak() {
        return new ResponseEntity<TiebreakDTO>(tiebreakService.createTiebreak(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}")
    ResponseEntity<TiebreakDTO> getTiebreak(@PathVariable long id) {
        return new ResponseEntity<TiebreakDTO>(tiebreakService.getTiebreak(id), HttpStatus.OK);
    }
    
    
    @PutMapping(path = "/{id}/player1/win")
    ResponseEntity<TiebreakDTO> addPointToPlayer1(@PathVariable long id) {
        return new ResponseEntity<TiebreakDTO>(tiebreakService.addPointToPlayer1(id), HttpStatus.OK);
    }
    

    @PutMapping(path = "/{id}/player2/win")
    ResponseEntity<TiebreakDTO> addPointToPlayer2(@PathVariable long id) {
        return new ResponseEntity<TiebreakDTO>(tiebreakService.addPointToPlayer2(id), HttpStatus.OK);
    }

}
