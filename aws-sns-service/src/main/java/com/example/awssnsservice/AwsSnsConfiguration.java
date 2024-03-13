package com.example.awssnsservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
@Configuration
public class AwsSnsConfiguration {
    @Value("${access_key}")
    private String accessKey;

    @Value("${secret_key}")
    private String secretKey;

    @Value("${region_name}")
    private String region;

    public AWSCredentials credentials() {
         return new BasicAWSCredentials(accessKey, secretKey);
    }
    
    @Bean
    public AmazonSNS amazonSNS() {
        return AmazonSNSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials())).withRegion(region)
                .build();
    }
}
