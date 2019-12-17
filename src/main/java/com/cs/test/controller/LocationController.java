package com.cs.test.controller;

import com.cs.test.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping(value = "/getLast5/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") String id) {
        if(locationService.getLast5Updates(id)==null){
            return new ResponseEntity<>("Vehicle Not found !", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(locationService.getLast5Updates(id), HttpStatus.OK);
        }

    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<Object> updateLocation(@PathVariable("id") String id, @RequestBody String location) {
        locationService.updateLocation(id, location);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
