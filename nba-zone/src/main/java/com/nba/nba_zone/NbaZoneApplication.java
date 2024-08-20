package com.nba.nba_zone;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class NbaZoneApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		SpringApplication.run(NbaZoneApplication.class, args);
	}
}
