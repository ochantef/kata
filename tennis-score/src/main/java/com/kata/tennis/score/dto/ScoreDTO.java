package com.kata.tennis.score.dto;

public class ScoreDTO {

	private Long id;
	private String score;
	private int winner;
	
    public ScoreDTO() {
	}
	
    public ScoreDTO(Long id) {
    	this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}
    
    
}
