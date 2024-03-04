package com.example.awss3bucket.utils;

public interface BucketOperation {
    void perform(String bucketName);

    default String getAction() {
        return this.getClass().getSimpleName();
    }
}