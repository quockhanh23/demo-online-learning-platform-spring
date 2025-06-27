package com.example.demoonlinelearningplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoOnlineLearningPlatformApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(DemoOnlineLearningPlatformApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
