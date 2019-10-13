package com.google.appinventor.web.services;

public class EncryptionException extends RuntimeException {
    public EncryptionException(String message) {
        super(message);
    }

    public EncryptionException(Exception e) {
        super(e);
    }
}
