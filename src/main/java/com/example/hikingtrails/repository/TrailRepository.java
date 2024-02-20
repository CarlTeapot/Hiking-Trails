package com.example.hikingtrails.repository;

import com.example.hikingtrails.model.Trail;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Query("SELECT t FROM Trail t WHERE (:country IS NULL OR t.country = :country) AND (:region IS NULL OR t.region = :region) AND (:difficulty IS NULL OR t.difficulty = :difficulty) AND (:transportation IS NULL OR t.transportation = :transportation)")
    List<Trail> findByCriteria(@RequestParam(required = false) String country,
                               @RequestParam(required = false) String region,
                               @RequestParam(required = false) String difficulty,
                               @RequestParam(required = false) String transportation);
}
