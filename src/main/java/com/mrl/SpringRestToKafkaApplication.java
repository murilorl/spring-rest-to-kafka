package com.mrl;

import java.util.List;

import com.mrl.model.jsonplaceholder.User;
import com.mrl.service.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringRestToKafkaApplication implements CommandLineRunner {

	@Autowired
	private RestService restService;

	public static void main(String[] args) {
		SpringApplication.run(SpringRestToKafkaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("---- App started");

		log.info("Get Spring Boot quote: {}", restService.getQuoteSync().getValue().getQuote());

		log.info("---- Get users synchronously");
		List<User> users = restService.getJsonPlaceholderUsers().collectList().block();
		users.forEach(user -> {
			log.info("Id: {}, Name: {}", user.getId(), user.getName());
		});
	}
}
