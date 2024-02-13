package org.example.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(YachtIsMissingException.class)
    public ResponseEntity<String> handleMissingYacht(YachtIsMissingException yachtIsMissingException) {

        return new ResponseEntity<String>("Yacht does not exits", HttpStatus.BAD_REQUEST);
    }
}
