package com.example.awss3bucket.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.example.awss3bucket.exceptions.InternalBucketException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileStorageService {
    private static final Logger logger = LogManager.getLogger(FileStorageService.class);
    @Autowired
    private AmazonS3 amazonS3;
    public void fileUpload(MultipartFile multiPart, String bucketName) throws Exception {
        try {
            final File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + multiPart.getOriginalFilename());
            multiPart.transferTo(convFile);
            amazonS3.putObject(bucketName, convFile.getName(), convFile);
        } catch (Exception s3Exception) {
            logger.error("Error occurred while uploading file : {}", s3Exception.getMessage());
            throw new InternalBucketException("Error occurred while uploading file : {}", s3Exception.getMessage());
        }
    }

    public void fileDelete(String bucketName, String fileName) {
        try {
            amazonS3.deleteObject(bucketName, fileName);
        } catch (AmazonS3Exception s3Exception) {
            logger.error("Error occurred while deleting file : {}", s3Exception.getMessage());
            throw new InternalBucketException("Error occurred while deleting file : {}", s3Exception.getMessage());
        }
    }
}
