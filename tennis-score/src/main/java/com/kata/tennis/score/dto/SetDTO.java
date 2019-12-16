package com.kata.tennis.score.dto;

public class SetDTO extends ScoreDTO{
	private GameDTO currentGame;
	private TiebreakDTO tiebreak;

	public SetDTO() {}

	public SetDTO(Long id) {
		super(id);
	}
	
	public GameDTO getCurrentGame() {
		return currentGame;
	}
	
	public void setCurrentGame(GameDTO currentGame) {
		this.currentGame = currentGame;
	}

	public TiebreakDTO getTiebreak() {
		return tiebreak;
	}

	public void setTiebreak(TiebreakDTO tiebreak) {
		this.tiebreak = tiebreak;
	}

	@Override
	public String toString() {
		return "SetDTO [currentGame=" + currentGame + ", tiebreak=" + tiebreak + ", getScore()=" + getScore()
				+ ", getWinner()=" + getWinner() + "]";
	}
	
}
