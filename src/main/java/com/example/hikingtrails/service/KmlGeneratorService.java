package com.example.hikingtrails.service;

import com.example.hikingtrails.model.Trail;

import java.io.File;
import java.io.IOException;

public interface KmlGeneratorService {
    public File generateKml(String name);
}

