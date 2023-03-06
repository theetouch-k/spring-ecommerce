package com.example.urnshop;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrnshopApplication implements ApplicationRunner {
// public class UrnshopApplication implements CommandLineRunner {
// Both of them provides the same functionality
// ButCommandLineRunner.run() accepts String array[]
// ApplicationRunner.run() accepts ApplicationArguments as argument.

    public static void main(String[] args) {
        SpringApplication.run(UrnshopApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        System.out.println("RUNNNNN!!!!");
    }
}
