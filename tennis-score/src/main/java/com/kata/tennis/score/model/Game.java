package com.kata.tennis.score.model;

import javax.persistence.Entity;

@Entity
public class Game extends Score {
	
	public Game() {
		super();
	}
	
	public Game(Long id) {
		super(id);
	}
}

