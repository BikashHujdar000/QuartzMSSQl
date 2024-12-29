package com.example.quartz;


import com.example.quartz.Models.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuartzApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Database Connection Success");
		Test test = new Test();
		test.setName("BIkash");
		String man = test.getName();
		System.out.println("Lombok is correct"+man);


	}
}
