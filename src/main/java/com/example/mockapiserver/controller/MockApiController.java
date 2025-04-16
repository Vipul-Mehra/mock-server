package com.example.mockapiserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MockApiController {

    private List<Map<String, Object>> externalDataStore; // To hold the data sent via POST

    @PostMapping("/data")
    public ResponseEntity<String> receiveExternalData(@RequestBody List<Map<String, Object>> data) {
        System.out.println("Mock API Server received data: " + data);
        this.externalDataStore = data; // Store the received data
        return ResponseEntity.ok("Data received successfully by Mock API Server.");
    }

    @GetMapping("/data")
    public ResponseEntity<List<Map<String, Object>>> getExternalData() {
        if (externalDataStore != null) {
            return new ResponseEntity<>(externalDataStore, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}