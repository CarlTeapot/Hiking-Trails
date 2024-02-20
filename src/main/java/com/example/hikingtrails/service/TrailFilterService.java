package com.example.hikingtrails.service;

import com.example.hikingtrails.model.Trail;

import java.util.List;

public interface TrailFilterService {
    public List<String> filterByCriteria(String country, String region,
                                        String difficulty, String transportation);
}

