package com.nba.nba_zone.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                .directory("./zone/.env") // specify the directory where your .env file is located
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
    }
}
