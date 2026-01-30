package com.cloudintroteam.member.exception;

public class FileUploadFailedException extends RuntimeException {
    public FileUploadFailedException(String message) {
        super(message);
    }
}
