package com.nba.nba_zone.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        return "Hello World";
    }
}
