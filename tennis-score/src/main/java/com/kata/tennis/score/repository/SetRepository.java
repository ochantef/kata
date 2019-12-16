package com.kata.tennis.score.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kata.tennis.score.model.Set;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {

}
