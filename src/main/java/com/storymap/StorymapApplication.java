package com.storymap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class StorymapApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorymapApplication.class, args);
    }

}
