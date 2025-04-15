package com.example.mockapiserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MockApiController {

    private List<Map<String, Object>> receivedData;

    @PostMapping("/data")
    public ResponseEntity<String> receiveData(@RequestBody List<Map<String, Object>> data) {
        System.out.println("Mock API Server received data: " + data);
        this.receivedData = data;
        return ResponseEntity.ok("Data received successfully by Mock API Server.");
    }

    @GetMapping("/data")
    public ResponseEntity<List<Map<String, Object>>> getSampleData() {
        List<Map<String, Object>> sampleData = List.of(
                Map.of("createDate", "2025-04-15T18:00:00Z", "endurId", 501, "outcomeDescription", "Processed"),
                Map.of("createDate", "2025-04-15T19:15:00Z", "endurId", 502, "outcomeDescription", "Failed"),
                Map.of("createDate", "2025-04-15T20:30:00Z", "endurId", 503, "outcomeDescription", "Processed")
        );
        return new ResponseEntity<>(sampleData, HttpStatus.OK);
    }

    @GetMapping("/received-data")
    public ResponseEntity<List<Map<String, Object>>> getReceivedData() {
        if (receivedData != null) {
            return new ResponseEntity<>(receivedData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}