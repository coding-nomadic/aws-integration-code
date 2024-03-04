package com.example.awss3bucket.utils;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.example.awss3bucket.exceptions.InternalBucketException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BucketOperationUtils {
    private static final Logger logger = LogManager.getLogger(BucketOperationUtils.class);
    private BucketOperationUtils(){
    }

    public static void handleBucketOperation(String bucketName, BucketOperation operation) {
        try {
            operation.perform(bucketName);
        } catch (AmazonS3Exception exception) {
            logger.error("Error occurred while {} bucket with this : {}", operation.getAction(), exception.getMessage());
            throw new InternalBucketException("Error occurred while " + operation.getAction() + " bucket", exception);
        }
    }
}
