package com.example.hikingtrails.controller;

import com.example.hikingtrails.model.Trail;
import com.example.hikingtrails.service.TrailFilterService;
import com.example.hikingtrails.service.impl.TrailFilterServiceImpl;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filter")
@RequiredArgsConstructor
public class TrailFilterController {
    @Qualifier("main")
    @NonNull
    private final TrailFilterServiceImpl service;

    @GetMapping("/gamedo")
    public List<String> filterByCriteria(@RequestParam(required = false) String country,
                                        @RequestParam(required = false) String region,
                                        @RequestParam(required = false) String difficulty,
                                        @RequestParam(required = false) String transportation){

        return service.filterByCriteria(country, region, difficulty, transportation);
    }
}
