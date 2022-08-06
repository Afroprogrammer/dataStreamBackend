package com.example.datastreambackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataStreamBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataStreamBackendApplication.class, args);
    }

}
