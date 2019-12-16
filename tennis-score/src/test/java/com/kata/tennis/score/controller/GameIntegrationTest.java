package com.kata.tennis.score.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.kata.tennis.score.model.Game;
import com.kata.tennis.score.repository.GameRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class GameIntegrationTest {
	  @Autowired
	  private MockMvc mockMvc;
	  
	  @Autowired
	  private GameRepository gameRepository;
	  
	  private Game game;
	  
	  @BeforeEach
	  public void setup() {
		  game = new Game();
		  game.setPointsPlayer1(1);
		  game.setPointsPlayer2(0);

		  game = gameRepository.save(game);
	}
	  
	  
	  @Test
	  public void createGameTest() throws Exception {
		  mockMvc.perform(post("/games")
		    	      .contentType(MediaType.APPLICATION_JSON))
		    	      .andExpect(status().isOk())
		    	      .andExpect(jsonPath("$.id").isNumber())
		    	      .andExpect(jsonPath("$.score").value("0-0"));
	  }
	  
	  @Test
	  public void getGameTest() throws Exception {
		  mockMvc.perform(get("/games/{id}", game.getId())
		    	      .contentType(MediaType.APPLICATION_JSON))
		    	      .andExpect(status().isOk())
		    	      .andExpect(jsonPath("$.id").value(game.getId()))
		    	      .andExpect(jsonPath("$.score").value("15-0"));
	  }
	  
	  @Test
	  public void addPointToPlayer1Test() throws Exception {
		  mockMvc.perform(put("/games/{id}/player1/win", game.getId())
		    	      .contentType(MediaType.APPLICATION_JSON))
	      			  .andExpect(status().isOk())
		    	      .andExpect(jsonPath("$.id").value(game.getId()))
				      .andExpect(jsonPath("$.score").value("30-0"));
	  }
	  
	  @Test
	  public void addPointToPlayer2Test() throws Exception {
		  mockMvc.perform(put("/games/{id}/player2/win", game.getId())
		    	      .contentType(MediaType.APPLICATION_JSON))
	      			  .andExpect(status().isOk())
		    	      .andExpect(jsonPath("$.id").value(game.getId()))
				      .andExpect(jsonPath("$.score").value("15-15"));
	  }

}
