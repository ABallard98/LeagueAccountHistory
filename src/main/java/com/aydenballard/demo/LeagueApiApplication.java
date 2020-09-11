package com.aydenballard.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class LeagueApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeagueApiApplication.class, args);
        System.out.println(FileReader.readApiKey());
    }

}
