package com.nba.nba_zone.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"http://localhost:3000", "https://main.dmk56u49qaw4w.amplifyapp.com/"})
public class HomeController {
    @GetMapping
    public String home() {
        return "Hello World";
    }
}
