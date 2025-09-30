package nl.benzelinsky.techiteasybackend.controllers;

import nl.benzelinsky.techiteasybackend.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsController {

    //Record not found xception handler
    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<String> handleRecordNotFoundException(RecordNotFoundException ex) {
        // .status allows creating a body with .body
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
