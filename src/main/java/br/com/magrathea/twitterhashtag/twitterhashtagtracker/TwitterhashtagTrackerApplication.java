package br.com.magrathea.twitterhashtag.twitterhashtagtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TwitterhashtagTrackerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TwitterhashtagTrackerApplication.class, args);
	}
	
}
