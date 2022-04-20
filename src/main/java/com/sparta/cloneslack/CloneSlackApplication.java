package com.sparta.cloneslack;

import com.sparta.cloneslack.model.ChatRoom;
import com.sparta.cloneslack.repository.ChatRoomRepository;
import com.sparta.cloneslack.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CloneSlackApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(CloneSlackApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);

    }

    @Bean
    public CommandLineRunner demo(ChatRoomRepository repository){
        return (args) -> {
            repository.save(new ChatRoom("전체 채팅방"));
        };
    }
}
