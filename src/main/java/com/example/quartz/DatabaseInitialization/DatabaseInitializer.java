package com.example.quartz.DatabaseInitialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component("databaseInitializer")
@Order(1)
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Load the SQL script
        ClassPathResource resource = new ClassPathResource("script.sql");
        String sql = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        try {
            // Execute the SQL script
            jdbcTemplate.execute(sql);
            System.out.println("Database tables initialized successfully!");
        } catch (Exception e) {
            // Log more details about the error
            System.err.println("Error during database initialization: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }
}