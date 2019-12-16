package com.kata.tennis.score.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kata.tennis.score.dto.GameDTO;
import com.kata.tennis.score.model.Game;
import com.kata.tennis.score.repository.GameRepository;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest { 

    @InjectMocks
    private GameService gameService = new GameServiceImpl();

    @Mock
    private GameRepository gameRepository;   
    
    @BeforeEach
    public void setup() {
		// mock game with score 30-30
		Game game = new Game();
		game.setId(1L);
		game.setPointsPlayer1(2);
		game.setPointsPlayer2(2);
		when(gameRepository.findById(1L))
	      .thenReturn(Optional.of(game));

		when(gameRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
    }
	

	@Test
	public void addPointToPlayer1Test() {		
		GameDTO game = gameService.addPointToPlayer1(1L);		
		assertEquals(game.getId(), 1L);	
		assertEquals(game.getScore(), "40-30");	
	}
	

	@Test
	public void addPointToPlayer2Test() {	
		GameDTO game = gameService.addPointToPlayer2(1L);
		assertEquals(game.getId(), 1L);	
		assertEquals(game.getScore(), "30-40");			
	}
	
	
	@Test
	public void computeScorePlayerWinTest() 
	{
		GameDTO game = gameService.addPointToPlayer1(1L);		
		game = gameService.addPointToPlayer1(1L);	
		assertEquals(game.getId(), 1L);	
		assertEquals("Win game player1", game.getScore());	
	}
	
	@Test
	public void computeScorePlayerDeuceTest() {
		GameDTO game = gameService.addPointToPlayer2(1L);		
		game = gameService.addPointToPlayer1(1L);	
		game = gameService.addPointToPlayer2(1L);
		game = gameService.addPointToPlayer1(1L);
		assertEquals(game.getId(), 1L);	
		assertEquals("Deuce", game.getScore());	
		
		game = gameService.addPointToPlayer1(1L);		
		game = gameService.addPointToPlayer2(1L);		
		assertEquals(game.getId(), 1L);	
		assertEquals("Deuce", game.getScore());
	}
	
	@Test
	public void computeScorePlayerAdvantageTest() {
		GameDTO game = gameService.addPointToPlayer1(1L);		
		game = gameService.addPointToPlayer2(1L);
		game = gameService.addPointToPlayer1(1L);	

		assertEquals("Advantage player1", game.getScore());
		
		game = gameService.addPointToPlayer2(1L);
		game = gameService.addPointToPlayer2(1L);
		assertEquals("Advantage player2", game.getScore());
	}
	
	
}
