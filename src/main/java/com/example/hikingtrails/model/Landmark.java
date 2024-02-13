package com.example.hikingtrails.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Landmark {

    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String landmark;

    @ManyToMany(mappedBy = "trails")
    private Set<Trail> trails = new HashSet<>();
}
