package com.example.hikingtrails.controller;

import com.example.hikingtrails.service.impl.KmlReaderServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import lombok.*;
@RestController
@RequestMapping("/hiking")
@RequiredArgsConstructor
public class KmlReaderController {
    @Qualifier("impl1")
    private final KmlReaderServiceImpl service;

    @GetMapping("/readFile")
    public void readKml() {
        service.read();
    }
}
