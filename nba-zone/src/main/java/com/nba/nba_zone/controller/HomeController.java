package com.nba.nba_zone.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"http://localhost:8080", "https://main.d1gp0n5z2qrzt6.amplifyapp.com"})
public class HomeController {
    @GetMapping
    public String home() {
        return "Hello World";
    }
}
