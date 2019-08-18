package com.out.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main class.
 */
@SpringBootApplication
@EnableScheduling
public class OutApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutApplication.class, args);
    }

}
