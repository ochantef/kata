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

import com.kata.tennis.score.dto.TiebreakDTO;
import com.kata.tennis.score.model.Tiebreak;
import com.kata.tennis.score.repository.TiebreakRepository;

@ExtendWith(MockitoExtension.class)
public class TiebreakServiceTest { 

    @InjectMocks
    private TiebreakService tiebreakService = new TiebreakServiceImpl();

    @Mock
    private TiebreakRepository tiebreakRepository;   
    
    @BeforeEach
    public void setup() {
		// mock tiebreak with score 2-2
		Tiebreak tiebreak = new Tiebreak();
		tiebreak.setId(1L);
		tiebreak.setPointsPlayer1(4);
		tiebreak.setPointsPlayer2(4);
		when(tiebreakRepository.findById(1L))
	      .thenReturn(Optional.of(tiebreak));

		when(tiebreakRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
    }
	

	@Test
	public void addPointToPlayer1Test() {		
		TiebreakDTO tiebreak = tiebreakService.addPointToPlayer1(1L);		
		assertEquals(tiebreak.getId(), 1L);	
		assertEquals(tiebreak.getScore(), "5-4");	
	}
	

	@Test
	public void addPointToPlayer2Test() {	
		TiebreakDTO tiebreak = tiebreakService.addPointToPlayer2(1L);
		assertEquals(tiebreak.getId(), 1L);	
		assertEquals(tiebreak.getScore(), "4-5");			
	}
	
	
	@Test
	public void getPlayerWinTest() 
	{
		TiebreakDTO tiebreak = tiebreakService.addPointToPlayer1(1L);		
		tiebreak = tiebreakService.addPointToPlayer1(1L);	
		assertEquals(tiebreak.getId(), 1L);	
		assertEquals("6-4", tiebreak.getScore());
		assertEquals(1, tiebreak.getWinner());		
	}
	
	@Test
	public void getPlayerNotWinTest() {
		TiebreakDTO tiebreak = tiebreakService.addPointToPlayer2(1L);		
		tiebreak = tiebreakService.addPointToPlayer1(1L);	
		tiebreak = tiebreakService.addPointToPlayer1(1L);
		assertEquals(tiebreak.getId(), 1L);	
		assertEquals("6-5", tiebreak.getScore());
		assertEquals(0, tiebreak.getWinner());		
	}
	
	
}
