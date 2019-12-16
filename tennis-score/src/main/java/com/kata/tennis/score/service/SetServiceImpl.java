package com.kata.tennis.score.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kata.tennis.score.dto.GameDTO;
import com.kata.tennis.score.dto.SetDTO;
import com.kata.tennis.score.dto.TiebreakDTO;
import com.kata.tennis.score.model.Game;
import com.kata.tennis.score.model.Set;
import com.kata.tennis.score.model.Tiebreak;
import com.kata.tennis.score.repository.SetRepository;

@Service
public class SetServiceImpl implements SetService {
	@Autowired
	private SetRepository setRepository;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private TiebreakService tiebreakService;
	
	@Override
	public SetDTO createSet() {
		return convertToSetDTO(setRepository.save(new Set()));
	}

	@Override
	public SetDTO getSet(Long setId) {
		Set set = setRepository.findById(setId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return convertToSetDTO(set);
	}
	
	private SetDTO convertToSetDTO(Set set) {
		SetDTO setDto = new SetDTO(set.getId());
		int gamesPlayer1 = 0;
		int gamesPlayer2 = 0;
		GameDTO currentGame = null;
		
		for(Game game: set.getGames()) {
			GameDTO gameDTO = gameService.getGame(game.getId());
			int winner = gameDTO.getWinner();
			if(winner == 0) {
				currentGame = gameDTO;
			}
			else if(winner == 1) {
				gamesPlayer1++;
			}
			else if(winner == 2) {
				gamesPlayer2++;
			}			
		}
		
		setDto.setScore(gamesPlayer1 + "-" + gamesPlayer2);
		setDto.setCurrentGame(currentGame);
		
		if(gamesPlayer1 == 7 || gamesPlayer1 == 6 && gamesPlayer1 > gamesPlayer2 + 1) {
			setDto.setWinner(1);
		}
		else if(gamesPlayer2 == 7 || gamesPlayer2 == 6 && gamesPlayer2 > gamesPlayer1 + 1) {
			setDto.setWinner(2);
		}
		
		if(set.getTiebreak() != null) {
			TiebreakDTO tiebreakDTO = tiebreakService.getTiebreak(set.getTiebreak().getId());
			setDto.setTiebreak(tiebreakDTO);
			setDto.setWinner(tiebreakDTO.getWinner());
		}
		
		return setDto;
	}

	@Override
	public SetDTO addGame(Long setId) {
		Set set = setRepository.findById(setId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		if(set.getTiebreak() != null ) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Set has a tie-break");
		}
		
		SetDTO setDto = convertToSetDTO(set);		
		if(setDto.getWinner() != 0) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Set alredy won");
		}
		if("6-6".equals(setDto.getScore())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Set score is 6-6 please start a tiebreak");
		}
		if(setDto.getCurrentGame() != null) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Set aldready has a game in progress");
		}
		
		set.addGame(new Game());
		return convertToSetDTO(setRepository.save(set));
	}
	
	@Override
	public SetDTO addTiebreak(Long setId) {
		Set set = setRepository.findById(setId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		if(set.getTiebreak() != null) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Set aldready has a tie-break");
		}
		
		SetDTO setDto = convertToSetDTO(set);		
		if(!"6-6".equals(setDto.getScore())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The set score must be 6-6 to add tie-break");
		}
		
		set.setTiebreak(new Tiebreak());
		return convertToSetDTO(setRepository.save(set));
	}

}
