package com.example.awss3bucket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateBucketException extends RuntimeException{
    public DuplicateBucketException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateBucketException(String message, String errorCode) {
        super(message);
    }
}
