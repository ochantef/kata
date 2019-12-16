package com.kata.tennis.score.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import com.kata.tennis.score.dto.SetDTO;
import com.kata.tennis.score.model.Game;
import com.kata.tennis.score.model.Set;
import com.kata.tennis.score.repository.SetRepository;

@ExtendWith(MockitoExtension.class)
public class SetServiceTest {

    @InjectMocks
	private SetService setService = new SetServiceImpl();
    
    @Mock
    private SetRepository setRepository; 
    
    @Mock
    private GameService gameService; 
    
    private Set set;
 
    @BeforeEach
    public void setup() {
		// mock Set with score 2-1
		set = new Set();	
		set.setId(1L);	  
    }
	
    @Test
	public void getSetTest() {
		Game game1 = new Game(1L);		  
		Game game2 = new Game(2L);
		Game game3 = new Game(3L);

		set.addGame(game1);
		set.addGame(game2);
		set.addGame(game3);	
		
		when(setRepository.findById(1L))
	      .thenReturn(Optional.of(set));
		
		// mock games dto on gameService
		GameDTO gameDto1 = new GameDTO(1L); 
		gameDto1.setWinner(1);
		
		GameDTO gameDto2 = new GameDTO(2L); 
		gameDto2.setWinner(2);
		
		GameDTO gameDto3 = new GameDTO(3L); 
		gameDto3.setWinner(1);

		when(gameService.getGame(1L)).thenReturn(gameDto1);
		when(gameService.getGame(2L)).thenReturn(gameDto2);
		when(gameService.getGame(3L)).thenReturn(gameDto3);
		
		SetDTO setDto = setService.getSet(1L);
		assertEquals(1L, setDto.getId());	
		assertEquals("2-1", setDto.getScore());	
		assertNull(setDto.getCurrentGame());
	}
    
    @Test
	public void createSetTest() {
		when(setRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

		SetDTO setDto = setService.createSet();
		assertEquals("0-0", setDto.getScore());	
		assertNull(setDto.getCurrentGame());
	}

    @Test
	public void addGameToSetTest() {
		when(setRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

		when(setRepository.findById(1L))
	      .thenReturn(Optional.of(set));
		
		GameDTO gameDto4 = new GameDTO(4L); 
		gameDto4.setScore("0-0");
		when(gameService.getGame(any())).thenReturn(gameDto4);
		
		SetDTO setDto = setService.addGame(1L);
		assertEquals(1L, setDto.getId());	
		assertEquals("0-0", setDto.getScore());	
		assertNotNull(setDto.getCurrentGame());
		assertEquals("0-0", setDto.getCurrentGame().getScore());
	}

    
}


