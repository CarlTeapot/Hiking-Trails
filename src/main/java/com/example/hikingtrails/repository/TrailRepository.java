package com.example.hikingtrails.repository;

import com.example.hikingtrails.model.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrailRepository extends JpaRepository<Trail, Long> {
    Optional<Trail> getTrailByTrailName(String trailName);

    List<Trail> getTrailsByRegion(String region);

    List<Trail> getTrailsByDifficulty(String difficulty);

    List<Trail> getTrailsByMunicipality(String municipality);

    Optional<Trail> findTrailById(Long id);

    Optional<Trail> findTrailByDescriptionId(Long id);
}
