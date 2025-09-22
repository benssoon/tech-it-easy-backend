package nl.benzelinsky.techiteasybackend.controllers;

import nl.benzelinsky.techiteasybackend.model.Television;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tvs")
public class TelevisionsController {

    //temp database
    Map<Integer, Television> televisionMap = new HashMap<Integer, Television>();

    //POST request 1 tv
    @PostMapping
    public ResponseEntity<Object> createTelevision(@RequestBody Television tv) {
        this.televisionMap.put(tv.getId(), tv);

        //return new ResponseEntity<>(tv, HttpStatus.CREATED);
        return ResponseEntity.created(null).body(tv);
    }

    //GET request all tvs
    @GetMapping
    public ResponseEntity<Map<Integer, Television>> getAllTelevisions() {
        return ResponseEntity.ok(this.televisionMap);
    }

    //GET request 1 tv
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTelevisionById(@PathVariable int id) {
        if (this.televisionMap.containsKey(id)) {
            return ResponseEntity.ok(this.televisionMap.get(id));
        }
        else {
            return ResponseEntity.notFound().build();
        }
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
