package com.sameh.crm.backend.configs;


import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sameh.crm.backend.services.exceptions.FieldInUseException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    public static class ErrorResponse{
        
        String[] messages;

        public ErrorResponse(String[] messages){
            this.messages = messages;
        }

        public ErrorResponse(String message){
            this.messages = new String[]{message};
        }

        public String[] getMessages() {
            return messages;
        }

    }


    @ExceptionHandler(FieldInUseException.class)
    public ResponseEntity<ErrorResponse> handleFieldInUse(FieldInUseException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> argumentNotValid(MethodArgumentNotValidException exception){
        List<String> errorList = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(
            error->{
                String errorField = ((FieldError)error).getField();
                String errorMessage = error.getDefaultMessage();
                errorList.add(errorField + " : " + errorMessage);
            }
        );
        String[] errorArr = new String[errorList.size()];
        errorArr = errorList.toArray(errorArr);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorArr));
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Throwable thrw){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Internal error occurs !"));
    }


}
