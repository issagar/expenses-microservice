package com.project3.userservice.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("Recurso no encontrado");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
