package com.sparta.cloneslack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CloneSlackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloneSlackApplication.class, args);
    }

}