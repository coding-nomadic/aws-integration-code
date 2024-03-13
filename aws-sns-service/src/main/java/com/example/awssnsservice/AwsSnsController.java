package com.example.awssnsservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sns")
@CrossOrigin("*")
public class AwsSnsController {
    private static final Logger logger = LogManager.getLogger(AwsSnsController.class);
    @Autowired
    private AwsSnsService service;

    @PostMapping(path = "/publish")
    public ResponseEntity<String> publishMail(@RequestBody Message message) throws JsonProcessingException {
        logger.info("Incoming message to be published : {}",new ObjectMapper().writeValueAsString(message));
        service.publishMessageToTopic(message.getSubject(),message.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
