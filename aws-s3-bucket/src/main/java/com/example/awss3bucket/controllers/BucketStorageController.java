package com.example.awss3bucket.controllers;

import com.example.awss3bucket.services.BucketService;
import com.example.awss3bucket.utils.AwsS3Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/s3")
@CrossOrigin("*")
public class BucketStorageController {
    private static final Logger logger = LogManager.getLogger(BucketStorageController.class);
    @Autowired
    private BucketService service;

    @GetMapping("/add/bucket/{bucketName}")
    public ResponseEntity<String> createBucket(@PathVariable("bucketName") String bucketName) {
        return processBucketOperation(AwsS3Constants.CREATE, bucketName);
    }

    @DeleteMapping("/delete/bucket/{bucketName}")
    public ResponseEntity<String> deleteBucket(@PathVariable("bucketName") String bucketName) {
        return processBucketOperation(AwsS3Constants.DELETE, bucketName);
    }

    private ResponseEntity<String> processBucketOperation(String operation, String bucketName) {
        logger.info("Making request to {} S3 bucket with name : {}", operation, bucketName);
        return switch (operation) {
            case AwsS3Constants.CREATE -> {
                service.createBucket(bucketName);
                yield new ResponseEntity<>(HttpStatus.OK);
            }
            case AwsS3Constants.DELETE -> {
                service.deleteBucket(bucketName);
                yield new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            default -> new ResponseEntity<>("Invalid operation", HttpStatus.BAD_REQUEST);
        };
    }
}
