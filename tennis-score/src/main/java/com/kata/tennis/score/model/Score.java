package com.kata.tennis.score.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Score {

	@Id	
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private int pointsPlayer1;
	private int pointsPlayer2; 

	public Score() {

	}
	
	public Score(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPointsPlayer1() {
		return pointsPlayer1;
	}

	public void setPointsPlayer1(int pointsPlayer1) {
		this.pointsPlayer1 = pointsPlayer1;
	}

	public int getPointsPlayer2() {
		return pointsPlayer2;
	}

	public void setPointsPlayer2(int pointsPlayer2) {
		this.pointsPlayer2 = pointsPlayer2;
	}


	@Override
	public String toString() {
		return "Game [id=" + id + ", pointsPlayer1=" + pointsPlayer1 + ", pointsPlayer2=" + pointsPlayer2
				+ "]";
	}

}

