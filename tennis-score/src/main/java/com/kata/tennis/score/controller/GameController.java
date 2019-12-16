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

import com.kata.tennis.score.dto.GameDTO;
import com.kata.tennis.score.service.GameService;

@RestController
@RequestMapping("/games")
public class GameController {
	@Autowired
	private GameService gameService;
	
    @PostMapping
    ResponseEntity<GameDTO> createGame() {
        return new ResponseEntity<GameDTO>(gameService.createGame(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}")
    ResponseEntity<GameDTO> getGame(@PathVariable long id) {
        return new ResponseEntity<GameDTO>(gameService.getGame(id), HttpStatus.OK);
    }
    
    
    @PutMapping(path = "/{id}/player1/win")
    ResponseEntity<GameDTO> addPointToPlayer1(@PathVariable long id) {
        return new ResponseEntity<GameDTO>(gameService.addPointToPlayer1(id), HttpStatus.OK);
    }
    

    @PutMapping(path = "/{id}/player2/win")
    ResponseEntity<GameDTO> addPointToPlayer2(@PathVariable long id) {
        return new ResponseEntity<GameDTO>(gameService.addPointToPlayer2(id), HttpStatus.OK);
    }

}
