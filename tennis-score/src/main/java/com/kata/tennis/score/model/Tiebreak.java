package com.kata.tennis.score.model;

import javax.persistence.Entity;

@Entity
public class Tiebreak extends Score {
	public Tiebreak() {
		super();
	}
	
	public Tiebreak(Long id) {
		super(id);
	}

}

