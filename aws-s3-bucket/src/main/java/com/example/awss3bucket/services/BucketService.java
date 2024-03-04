package com.example.awss3bucket.services;

import com.amazonaws.services.s3.AmazonS3;
import com.example.awss3bucket.exceptions.DuplicateBucketException;
import com.example.awss3bucket.utils.BucketOperationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketService {
    private static final Logger logger = LogManager.getLogger(BucketService.class);

    @Autowired
    private AmazonS3 amazonS3;

    public void createBucket(String bucketName) {
        BucketOperationUtils.handleBucketOperation(bucketName, (name) -> {
            if (!amazonS3.doesBucketExistV2(name)) {
                amazonS3.createBucket(name);
                logger.info("Created bucket with name : {}", name);
            } else {
                logger.warn("Bucket with name already existed in S3 : {}", name);
                throw new DuplicateBucketException("Bucket with name already existed in S3 : {}", name);
            }
        });
    }

    public void deleteBucket(String bucketName) {
        BucketOperationUtils.handleBucketOperation(bucketName, (name) -> {
            if (amazonS3.doesBucketExistV2(name)) {
                amazonS3.deleteBucket(name);
                logger.info("Deleted bucket with name : {}", name);
            } else {
                logger.warn("Bucket with name {} does not exist in S3 Bucket", name);
                throw new DuplicateBucketException("Bucket with name {} does not exist in S3 Bucket", name);
            }
        });
    }
}
