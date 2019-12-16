package com.kata.tennis.score.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kata.tennis.score.dto.GameDTO;
import com.kata.tennis.score.model.Game;
import com.kata.tennis.score.repository.GameRepository;

@Service
public class GameServiceImpl implements GameService {
	private static String[] scorePoints = {"0", "15", "30", "40"};
	
	@Autowired
    private GameRepository gameRepository;
	

	@Override
	public GameDTO createGame() {
		return convertToGameDTO(gameRepository.save(new Game()));
	}

	@Override
	public GameDTO getGame(Long gameId) {
		Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return convertToGameDTO(game);
	}

	@Override
	public GameDTO addPointToPlayer1(Long gameId) {
		Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		game.setPointsPlayer1(game.getPointsPlayer1() + 1);
		game = gameRepository.save(game);
		
		return convertToGameDTO(game);
	}

	@Override
	public GameDTO addPointToPlayer2(Long gameId) {
		Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		game.setPointsPlayer2(game.getPointsPlayer2() + 1);
		game = gameRepository.save(game);
		
		return convertToGameDTO(game);
	}
	
	private GameDTO convertToGameDTO(Game game) {
		String score = "0-0";
		int winner = 0;
		if(game.getPointsPlayer1() < 4 && game.getPointsPlayer2() < 4) {
			score = scorePoints[game.getPointsPlayer1()] + "-" + scorePoints[game.getPointsPlayer2()];
		}
		else if(game.getPointsPlayer1() == game.getPointsPlayer2()) {
			score = "Deuce";
		}
		else if(game.getPointsPlayer1() > game.getPointsPlayer2() + 1) {
			score = "Win game player1";
			winner = 1;
		}
		else if(game.getPointsPlayer2() > game.getPointsPlayer1() + 1) {
			score = "Win game player2";
			winner = 2;
		}
		else if(game.getPointsPlayer1() > game.getPointsPlayer2()) {
			score = "Advantage player1";
		}
		else if(game.getPointsPlayer2() > game.getPointsPlayer1()) {
			score = "Advantage player2";
		}
		
		GameDTO gameDto = new GameDTO(game.getId());
		gameDto.setScore(score);
		gameDto.setWinner(winner);
		
		return gameDto;
	}
		
}
