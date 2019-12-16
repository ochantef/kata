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

import com.kata.tennis.score.model.Tiebreak;
import com.kata.tennis.score.repository.TiebreakRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TiebreakIntegrationTest {
	  @Autowired
	  private MockMvc mockMvc;
	  
	  @Autowired
	  private TiebreakRepository tiebreakRepository;
	  
	  private Tiebreak tiebreak;
	  
	  @BeforeEach
	  public void setup() {
		  tiebreak = new Tiebreak();
		  tiebreak.setPointsPlayer1(1);
		  tiebreak.setPointsPlayer2(0);

		  tiebreak = tiebreakRepository.save(tiebreak);
	}
	  
	  
	  @Test
	  public void createTiebreakTest() throws Exception {
		  mockMvc.perform(post("/tiebreaks")
		    	      .contentType(MediaType.APPLICATION_JSON))
		    	      .andExpect(status().isOk())
		    	      .andExpect(jsonPath("$.id").isNumber())
		    	      .andExpect(jsonPath("$.score").value("0-0"));
	  }
	  
	  @Test
	  public void getTiebreakTest() throws Exception {
		  mockMvc.perform(get("/tiebreaks/{id}", tiebreak.getId())
		    	      .contentType(MediaType.APPLICATION_JSON))
		    	      .andExpect(status().isOk())
		    	      .andExpect(jsonPath("$.id").value(tiebreak.getId()))
		    	      .andExpect(jsonPath("$.score").value("1-0"));
	  }
	  
	  @Test
	  public void addPointToPlayer1Test() throws Exception {
		  mockMvc.perform(put("/tiebreaks/{id}/player1/win", tiebreak.getId())
		    	      .contentType(MediaType.APPLICATION_JSON))
	      			  .andExpect(status().isOk())
		    	      .andExpect(jsonPath("$.id").value(tiebreak.getId()))
				      .andExpect(jsonPath("$.score").value("2-0"));
	  }
	  
	  @Test
	  public void addPointToPlayer2Test() throws Exception {
		  mockMvc.perform(put("/tiebreaks/{id}/player2/win", tiebreak.getId())
		    	      .contentType(MediaType.APPLICATION_JSON))
	      			  .andExpect(status().isOk())
		    	      .andExpect(jsonPath("$.id").value(tiebreak.getId()))
				      .andExpect(jsonPath("$.score").value("1-1"));
	  }
	
}
