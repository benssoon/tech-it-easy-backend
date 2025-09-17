package nl.benzelinsky.techiteasybackend.controllers;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tvs")
public class TelevisionsController {

    //GET request all tvs
    @GetMapping
    public ResponseEntity<String> getAllTelevisions() {
        return ResponseEntity.ok("all televisions");
    }

    //GET request 1 tv
    @GetMapping("/{id}")
    public ResponseEntity<String> getTelevision() {
        return ResponseEntity.ok("television");
    }

    //POST request 1 tv
    @PostMapping
    public ResponseEntity<Object> createTelevision() {
        //return new ResponseEntity<>("created television", HttpStatus.CREATED);
        return ResponseEntity.created(null).body("created television");
    }

    //PUT request 1 tv
    @PutMapping
    public ResponseEntity<String> updateTelevision() {
        return ResponseEntity.ok("updated television");
    }

    //DELETE request 1 tv
    @DeleteMapping
    public ResponseEntity<Object> deleteTelevison() {
        return ResponseEntity.ok("deleted television");
    }
}
