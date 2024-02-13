package com.example.hikingtrails.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class LandMark {

    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String landmark;
}
