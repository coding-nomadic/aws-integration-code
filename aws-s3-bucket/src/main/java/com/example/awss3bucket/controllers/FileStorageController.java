package com.example.awss3bucket.controllers;

import com.example.awss3bucket.services.FileStorageService;
import com.example.awss3bucket.utils.AwsS3Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/s3")
@CrossOrigin("*")
public class FileStorageController {
    private static final Logger logger = LogManager.getLogger(FileStorageController.class);
    @Autowired
    private FileStorageService service;

    @PostMapping(path = "/upload/file/{bucketName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("bucketName") String bucketName) throws Exception {
        return processFileOperation(AwsS3Constants.UPLOAD_FILE,bucketName,file,"");
    }

    @DeleteMapping(path = "/delete/file/{bucketName}/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable("bucketName") String bucketName, @PathVariable("fileName") String fileName) throws Exception {
        return processFileOperation(AwsS3Constants.DELETE_FILE, bucketName, null,fileName);
    }
    private ResponseEntity<String> processFileOperation(String operation, String bucketName,MultipartFile file,String fileName) throws Exception {
        logger.info("Making request to {} S3 bucket with file name : {}", operation, bucketName);
        return switch (operation) {
            case AwsS3Constants.UPLOAD_FILE -> {
                service.fileUpload(file,bucketName);
                yield new ResponseEntity<>(HttpStatus.OK);
            }
            case AwsS3Constants.DELETE_FILE -> {
                service.fileDelete(bucketName,fileName);
                yield new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            default -> new ResponseEntity<>("Invalid operation", HttpStatus.BAD_REQUEST);
        };
    }

}
