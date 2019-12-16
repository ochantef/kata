package com.kata.tennis.score.service;

import com.kata.tennis.score.dto.GameDTO;

public interface GameService {
	
	public GameDTO createGame();
	
	public GameDTO getGame(Long gameId);

	public GameDTO addPointToPlayer1(Long gameId);

	public GameDTO addPointToPlayer2(Long gameId);
}
