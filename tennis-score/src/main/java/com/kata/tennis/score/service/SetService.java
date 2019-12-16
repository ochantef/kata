package com.kata.tennis.score.service;

import com.kata.tennis.score.dto.SetDTO;

public interface SetService {
	
	SetDTO createSet();
	
	SetDTO getSet(Long setId);

	SetDTO addGame(Long setId);

	SetDTO addTiebreak(Long setId);
}
