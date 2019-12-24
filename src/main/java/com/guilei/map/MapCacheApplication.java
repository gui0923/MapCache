package com.guilei.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MapCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapCacheApplication.class, args);
    }

}
