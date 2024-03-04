package com.example.awss3bucket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalBucketException extends RuntimeException {
    public InternalBucketException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalBucketException(String message, String errorCode) {
        super(message);
    }
}
