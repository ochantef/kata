package com.kata.tennis.score.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Set {

	@Id	
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "set_id")
	private List<Game> games = new ArrayList<Game>();
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sets_id", referencedColumnName = "id")
	private Tiebreak tiebreak;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Game> getGames() {
		return games;
	}
	
	public void setGames(List<Game> games) {
		this.games = games;
	}

	public void addGame(Game game) {
		if(games == null) games = new ArrayList<Game>();
		games.add(game);		
	}
	
	public Tiebreak getTiebreak() {
		return tiebreak;
	}

	public void setTiebreak(Tiebreak tiebreak) {
		this.tiebreak = tiebreak;
	}

	@Override
	public String toString() {
		return "Set [id=" + id + ", games=" + games + ", tiebreak=" + tiebreak + "]";
	}

}