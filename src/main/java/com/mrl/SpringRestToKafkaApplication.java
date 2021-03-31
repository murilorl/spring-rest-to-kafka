package com.mrl;

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
		log.info("Spring Boot quote: {}", restService.getQuoteSync().getValue().getQuote());
	}

}
