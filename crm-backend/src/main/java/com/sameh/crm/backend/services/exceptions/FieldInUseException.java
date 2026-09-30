package com.sameh.crm.backend.services.exceptions;

public class FieldInUseException extends RuntimeException {

    public FieldInUseException(String message){
        super(message);
    }

}
