package com.kata.tennis.score.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kata.tennis.score.dto.TiebreakDTO;
import com.kata.tennis.score.model.Tiebreak;
import com.kata.tennis.score.repository.TiebreakRepository;

@Service
public class TiebreakServiceImpl implements TiebreakService {
	
	@Autowired
    private TiebreakRepository tiebreakRepository;
	

	@Override
	public TiebreakDTO createTiebreak() {
		return convertToTiebreakDTO(tiebreakRepository.save(new Tiebreak()));
	}

	@Override
	public TiebreakDTO getTiebreak(Long tiebreakId) {
		Tiebreak tiebreak = tiebreakRepository.findById(tiebreakId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return convertToTiebreakDTO(tiebreak);
	}

	@Override
	public TiebreakDTO addPointToPlayer1(Long tiebreakId) {
		Tiebreak tiebreak = tiebreakRepository.findById(tiebreakId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		tiebreak.setPointsPlayer1(tiebreak.getPointsPlayer1() + 1);
		tiebreak = tiebreakRepository.save(tiebreak);
		
		return convertToTiebreakDTO(tiebreak);
	}

	@Override
	public TiebreakDTO addPointToPlayer2(Long tiebreakId) {
		Tiebreak tiebreak = tiebreakRepository.findById(tiebreakId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		tiebreak.setPointsPlayer2(tiebreak.getPointsPlayer2() + 1);
		tiebreak = tiebreakRepository.save(tiebreak);
		
		return convertToTiebreakDTO(tiebreak);
	}
	
	private TiebreakDTO convertToTiebreakDTO(Tiebreak tiebreak) {
		String score = "0-0";
		int winner = 0;
		score = tiebreak.getPointsPlayer1() + "-" + tiebreak.getPointsPlayer2();
		
		if(tiebreak.getPointsPlayer1() > 5 && tiebreak.getPointsPlayer1() > tiebreak.getPointsPlayer2() + 1) {
			winner = 1;
		}
		else if(tiebreak.getPointsPlayer2() > 5 && tiebreak.getPointsPlayer2() > tiebreak.getPointsPlayer1() + 1) {
			winner = 2;
		}
		
		TiebreakDTO tiebreakDto = new TiebreakDTO(tiebreak.getId());
		tiebreakDto.setScore(score);
		tiebreakDto.setWinner(winner);
		
		return tiebreakDto;
	}
		
}
