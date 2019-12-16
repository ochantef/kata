package com.kata.tennis.score.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.kata.tennis.score.model.Set;
import com.kata.tennis.score.repository.SetRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class SetIntegrationTest {
	  @Autowired
	  private MockMvc mockMvc;
	  
	  @Autowired
	  private SetRepository setRepository;
	  
	  private Set set;
		  
	  @BeforeEach
	  public void setup() {
		  // mock set with score 6-6
		  set = new Set();	
		  
		  Game game1 = new Game();
		  game1.setPointsPlayer1(4);
		  game1.setPointsPlayer2(0);
		  
		  Game game2 = new Game();
		  game2.setPointsPlayer1(5);
		  game2.setPointsPlayer2(3);	

		  Game game3 = new Game();
		  game3.setPointsPlayer1(1);
		  game3.setPointsPlayer2(4);
		  
		  Game game4 = new Game();
		  game4.setPointsPlayer1(5);
		  game4.setPointsPlayer2(3);
		  
		  Game game6 = new Game();
		  game6.setPointsPlayer1(5);
		  game6.setPointsPlayer2(3);
		  
		  Game game7 = new Game();
		  game7.setPointsPlayer1(5);
		  game7.setPointsPlayer2(3);
		  
		  Game game8 = new Game();
		  game8.setPointsPlayer1(1);
		  game8.setPointsPlayer2(4);
		  
		  Game game9 = new Game();
		  game9.setPointsPlayer1(1);
		  game9.setPointsPlayer2(4);
		  
		  Game game10 = new Game();
		  game10.setPointsPlayer1(2);
		  game10.setPointsPlayer2(4);

		  set.addGame(game1);
		  set.addGame(game2);
		  set.addGame(game3);
		  set.addGame(game4);
		  set.addGame(game6);
		  set.addGame(game7);
		  set.addGame(game8);
		  set.addGame(game9);
		  set.addGame(game10);
	  
		  set = setRepository.save(set);
	}
	  
	  
	  @Test
	  public void createSetTest() throws Exception {
		  mockMvc.perform(post("/sets")
		    	      .contentType(MediaType.APPLICATION_JSON))
		    	      .andExpect(status().isOk())
		    	      .andExpect(jsonPath("$.id").isNotEmpty())
		    	      .andExpect(jsonPath("$.score").value("0-0"))
					  .andExpect(jsonPath("$.currentGame").isEmpty());
	  }
	  
	  @Test
	  public void getSetTest() throws Exception {		  
		  Game game = new Game();
		  game.setPointsPlayer1(1);
		  game.setPointsPlayer2(1);
		  set.addGame(game);
		  set = setRepository.save(set);
		  
		  mockMvc.perform(get("/sets/{id}", set.getId())
		    	      .contentType(MediaType.APPLICATION_JSON))
		    	      .andExpect(status().isOk())
		    	      .andExpect(jsonPath("$.id").value(set.getId()))
		    	      .andExpect(jsonPath("$.score").value("5-4"))
		    	      .andExpect(jsonPath("$.winner").value(0))
					  .andExpect(jsonPath("$.currentGame.score").value("15-15"));
	  }
	  
	  @Test
	  public void addGameToSet() throws Exception {		 		  
		  mockMvc.perform(post("/sets/{id}/games", set.getId())
		    	      .contentType(MediaType.APPLICATION_JSON))
		    	      .andExpect(status().isOk())
		    	      .andExpect(jsonPath("$.id").value(set.getId()))
		    	      .andExpect(jsonPath("$.score").value("5-4"))
		    	      .andExpect(jsonPath("$.winner").value(0))
					  .andExpect(jsonPath("$.currentGame.score").value("0-0"));
	  }
	  
	  @Test
	  public void addTiebreakToSetForbiddenTest() throws Exception {		 		  
		  mockMvc.perform(post("/sets/{id}/tiebreaks", set.getId())
		    	      .contentType(MediaType.APPLICATION_JSON))
		    	      .andExpect(status().isForbidden());
	  }
	  
	  
	  @Test
	  public void addTiebreakToSetTest() throws Exception {	
		  // mock game 6-6
		  Game game1 = new Game();
		  game1.setPointsPlayer1(1);
		  game1.setPointsPlayer2(4);
		  
		  Game game2 = new Game();
		  game2.setPointsPlayer1(4);
		  game2.setPointsPlayer2(1);
		  
		  Game game3 = new Game();
		  game3.setPointsPlayer1(2);
		  game3.setPointsPlayer2(4);
		  
		  set.addGame(game1);		  
		  set.addGame(game2);
		  set.addGame(game3);
		  
		  set = setRepository.save(set);
		  
		  mockMvc.perform(post("/sets/{id}/tiebreaks", set.getId())
		    	      .contentType(MediaType.APPLICATION_JSON))
		    	      .andExpect(status().isOk())
		    	      .andExpect(jsonPath("$.id").value(set.getId()))
		    	      .andExpect(jsonPath("$.score").value("6-6"))
		    	      .andExpect(jsonPath("$.winner").value(0))
					  .andExpect(jsonPath("$.tiebreak.score").value("0-0"));
	  }
	  
	  
	  @Test
	  public void getSetNotWinTest() throws Exception {
		  Game game1 = new Game();
		  game1.setPointsPlayer1(1);
		  game1.setPointsPlayer2(4);
		  
		  Game game2 = new Game();
		  game2.setPointsPlayer1(4);
		  game2.setPointsPlayer2(1);
		  
		  set.addGame(game1);		  
		  set.addGame(game2);
		  set = setRepository.save(set);

		  mockMvc.perform(get("/sets/{id}", set.getId())
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk())
	    	      .andExpect(jsonPath("$.id").value(set.getId()))
	    	      .andExpect(jsonPath("$.score").value("6-5"))
	    	      .andExpect(jsonPath("$.winner").value(0))
				  .andExpect(jsonPath("$.currentGame").isEmpty());
		  
	  }
	  
	  @Test
	  public void getSet75WinTest() throws Exception {
		  Game game1 = new Game();
		  game1.setPointsPlayer1(1);
		  game1.setPointsPlayer2(4);
		  
		  Game game2 = new Game();
		  game2.setPointsPlayer1(4);
		  game2.setPointsPlayer2(1);
		  
		  Game game3 = new Game();
		  game3.setPointsPlayer1(4);
		  game3.setPointsPlayer2(1);
		  
		  set.addGame(game1);		  
		  set.addGame(game2);
		  set.addGame(game3);
		  set = setRepository.save(set);

		  mockMvc.perform(get("/sets/{id}", set.getId())
	    	      .contentType(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk())
	    	      .andExpect(jsonPath("$.id").value(set.getId()))
	    	      .andExpect(jsonPath("$.score").value("7-5"))
	    	      .andExpect(jsonPath("$.winner").value(1));
		  
	  }

	  
	  @Test
	  public void getSetWinTest() throws Exception {	 
		  Game game = new Game();
		  game.setPointsPlayer1(4);
		  game.setPointsPlayer2(1);
		  
		  set.addGame(game);
		  set = setRepository.save(set);

		  mockMvc.perform(get("/sets/{id}", set.getId())
		    	      .contentType(MediaType.APPLICATION_JSON))
		    	      .andExpect(status().isOk())
		    	      .andExpect(jsonPath("$.id").value(set.getId()))
		    	      .andExpect(jsonPath("$.score").value("6-4"))
		    	      .andExpect(jsonPath("$.winner").value(1))
					  .andExpect(jsonPath("$.currentGame").isEmpty());
	  }
	  
}
