package com.kata.tennis.score.service;

import com.kata.tennis.score.dto.TiebreakDTO;

public interface TiebreakService {
	
	public TiebreakDTO createTiebreak();
	
	public TiebreakDTO getTiebreak(Long tiebreakId);

	public TiebreakDTO addPointToPlayer1(Long tiebreakId);

	public TiebreakDTO addPointToPlayer2(Long tiebreakId);
}
