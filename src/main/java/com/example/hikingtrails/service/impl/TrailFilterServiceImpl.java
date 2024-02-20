package com.example.hikingtrails.service.impl;

import com.example.hikingtrails.model.Trail;
import com.example.hikingtrails.repository.TrailRepository;
import com.example.hikingtrails.service.TrailFilterService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("main")
@RequiredArgsConstructor
public class TrailFilterServiceImpl implements TrailFilterService {
    private final TrailRepository repository;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<String> filterByCriteria(String country, String region, String difficulty, String transportation) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Trail> cq = cb.createQuery(Trail.class);
//        Root<Trail> root = cq.from(Trail.class);
//
//        Predicate filters = cb.conjunction();
//
//        if (!country.equals("any")) {
//            filters = cb.and(filters, cb.equal(root.get("country"), country));
//        }
//        if (!region.equals("any")) {
//            filters = cb.and(filters, cb.equal(root.get("region"), region));
//        }
//        if (!difficulty.equals("any")) {
//            filters = cb.and(filters, cb.equal(root.get("difficulty"), difficulty));
//        }
//        if (!transportation.equals("any")) {
//            filters = cb.and(filters, cb.equal(root.get("transportation"), transportation));
//        }
//
//        cq.where(filters);
        List<Trail> trails= repository.findByCriteria(country, region, difficulty, transportation);
//        List<Trail> trails =  entityManager.createQuery(cq).getResultList();
        List<String> names = new ArrayList<>();
        for (Trail  trail : trails) {
            names.add(trail.getTrailName());
        }
        return names;
    }
    }


