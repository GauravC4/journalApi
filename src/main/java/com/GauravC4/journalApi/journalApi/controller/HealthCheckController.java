package com.GauravC4.journalApi.journalApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health-check")
    public String HealthCheck() {
        return "OK";
    }
}
