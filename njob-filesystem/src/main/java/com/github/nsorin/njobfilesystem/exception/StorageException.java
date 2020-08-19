package com.github.nsorin.njobfilesystem.exception;

public class StorageException extends RuntimeException {

    private static final long serialVersionUID = -2575725070195510950L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}