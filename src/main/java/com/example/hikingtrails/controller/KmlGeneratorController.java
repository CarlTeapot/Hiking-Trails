package com.example.hikingtrails.controller;

import com.example.hikingtrails.service.KmlGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/generate")
@RequiredArgsConstructor
public class KmlGeneratorController {
    @Qualifier("main")
    private final KmlGeneratorService service;

    @GetMapping("/file/{filename}")
    public File generateFile(@PathVariable("filename") String trailName) throws IOException {
        return service.generateKml(trailName);
    }
}
