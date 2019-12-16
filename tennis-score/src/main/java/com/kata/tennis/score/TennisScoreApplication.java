package com.kata.tennis.score;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(scanBasePackages = {"com.kata.tennis.score"})
//@ComponentScan(basePackages={"com.kata.tennis.score"})
//@EnableJpaRepositories(basePackages =   "com.kata.tennis.score.repository")
//@EntityScan(basePackages = "com.kata.tennis.score.model")
@SpringBootApplication
@EnableJpaRepositories(basePackages =   "com.kata.tennis.score.repository")
@EntityScan(basePackages = "com.kata.tennis.score.model")
public class TennisScoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TennisScoreApplication.class, args);
	}

}
