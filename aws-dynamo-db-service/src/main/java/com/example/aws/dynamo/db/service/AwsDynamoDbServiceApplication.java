package com.example.aws.dynamo.db.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.aws.dynamo.db.service"})
public class AwsDynamoDbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsDynamoDbServiceApplication.class, args);
	}

}
