package com.example.awssecretmanager;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class AwsSecretManagerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AwsSecretManagerApplication.class, args);
	}

	public static AWSCredentials credentials() {
		return  new BasicAWSCredentials("test","test");
	}

	@Override
	public void run(String... args) throws Exception {
		AWSSecretsManager client=	AWSSecretsManagerClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials())).withRegion("ca-central-1").build();
		GetSecretValueRequest secretValueRequest=new GetSecretValueRequest().withSecretId("secretName");
		GetSecretValueResult secretValueResult=client.getSecretValue(secretValueRequest);
		String secretValue=secretValueResult.getSecretString();
		System.out.printf("secret value : "+secretValue);
	}
}
