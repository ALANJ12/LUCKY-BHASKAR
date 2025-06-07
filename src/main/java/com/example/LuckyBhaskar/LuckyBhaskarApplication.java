package com.example.LuckyBhaskar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class LuckyBhaskarApplication {

	public static void main(String[] args) {
		System.out.println("hii");
//		"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwiaXNzIjoibXktYXBwIiwiaWF0IjoxNzQ4OTU2OTk5LCJleHAiOjE3NDg5NjA1OTl9.mVXxLvHI4SqnE5u2zEK0sHgLqj7M4p1uBEwFBb7O3-s"
		SpringApplication.run(LuckyBhaskarApplication.class, args)

		;
	}

}
