package com.kata.tennis.score.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kata.tennis.score.model.Tiebreak;

@Repository
public interface TiebreakRepository extends JpaRepository<Tiebreak, Long> {

}
