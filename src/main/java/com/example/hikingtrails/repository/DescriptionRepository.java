package com.example.hikingtrails.repository;

import com.example.hikingtrails.model.Description;
import com.example.hikingtrails.model.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {
}
