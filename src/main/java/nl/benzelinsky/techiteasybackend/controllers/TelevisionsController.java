package nl.benzelinsky.techiteasybackend.controllers;

import nl.benzelinsky.techiteasybackend.exceptions.RecordNotFoundException;
import nl.benzelinsky.techiteasybackend.model.Television;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/televisions")
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
    public ResponseEntity<Television> getTelevisionById(@PathVariable int id) {
        if (this.televisionMap.containsKey(id)) {
            return ResponseEntity.ok(this.televisionMap.get(id));
        }
        else {
            throw new RecordNotFoundException("ID cannot be found");
            //return ResponseEntity.notFound().build();
        }
    }

    //PUT request 1 tv
    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable int id, @RequestBody Television newTv) {
        Television tv = this.televisionMap.get(id);
        tv.setName(newTv.getName());
        return ResponseEntity.ok(tv);
    }

    //DELETE request 1 tv
    @DeleteMapping
    public ResponseEntity<String> deleteTelevison() {
        return ResponseEntity.ok("deleted television");
    }
}
