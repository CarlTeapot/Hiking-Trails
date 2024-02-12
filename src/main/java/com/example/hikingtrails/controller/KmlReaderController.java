package com.example.hikingtrails.controller;

import com.example.hikingtrails.service.KmlReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hiking")
public class KmlReaderController {
    @Qualifier("impl1")
    final KmlReaderService service;
    @GetMapping("/readFile")
    public void readKml() {
        service.read();
    }
}
