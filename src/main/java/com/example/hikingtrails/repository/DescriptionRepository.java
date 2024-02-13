package com.example.hikingtrails.repository;

import com.example.hikingtrails.model.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {
    public Optional<Description> getDescriptionById(Long id);

}
