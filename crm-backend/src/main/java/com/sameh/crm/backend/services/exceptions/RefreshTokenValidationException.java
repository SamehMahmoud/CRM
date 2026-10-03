package com.sameh.crm.backend.services.exceptions;

public class RefreshTokenValidationException extends RuntimeException{

    private String actualReson; // for logs

    public RefreshTokenValidationException(String msg, String actualReson){
        super(msg);
    }

    public String getActualReson() {
        return actualReson;
    }

}
