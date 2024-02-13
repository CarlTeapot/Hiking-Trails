package com.example.hikingtrails.repository;

import com.example.hikingtrails.model.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, Long> {
    public Optional<Landmark> getLandmarkById(Long id);

    public Optional<Landmark> getLandmarkByName(String name);

    public List<Landmark> getLandmarksByTrailsId (Long id);
}
