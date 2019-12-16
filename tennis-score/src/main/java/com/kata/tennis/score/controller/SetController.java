package com.kata.tennis.score.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.tennis.score.dto.SetDTO;
import com.kata.tennis.score.service.SetService;

@RestController
@RequestMapping("/sets")
public class SetController {
	@Autowired
	private SetService setService;
	
    @PostMapping
    ResponseEntity<SetDTO> createSet() {
        return new ResponseEntity<SetDTO>(setService.createSet(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}")
    ResponseEntity<SetDTO> getSet(@PathVariable long id) {
        return new ResponseEntity<SetDTO>(setService.getSet(id), HttpStatus.OK);
    }
    
    
    @PostMapping(path = "/{id}/games")
    ResponseEntity<SetDTO> addGameToSet(@PathVariable long id) {
        return new ResponseEntity<SetDTO>(setService.addGame(id), HttpStatus.OK);
    }

    
    @PostMapping(path = "/{id}/tiebreaks")
    ResponseEntity<SetDTO> addTiebreakToSet(@PathVariable long id) {
        return new ResponseEntity<SetDTO>(setService.addTiebreak(id), HttpStatus.OK);
    }
}
