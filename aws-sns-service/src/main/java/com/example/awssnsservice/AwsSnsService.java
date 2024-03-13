package com.example.awssnsservice;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
@Service
public class AwsSnsService {
    private static final Logger logger = LogManager.getLogger(AwsSnsService.class);
    @Autowired
    private AmazonSNS amazonSns;

    @Value("${topic.arn}")
    private String topicARN;

    public void publishMessageToTopic(String subject, String message) {
        try {
            final PublishRequest request = new PublishRequest(topicARN, message);
            request.setSubject(subject);
            amazonSns.publish(request);
            logger.info("Message published successfully !");
        } catch (Exception exception) {
            logger.error("Error publishing message to AWS topic : {}",exception.getLocalizedMessage());
        }
    }
}
