package com.example.hikingtrails.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Property;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Component
@Table(name = "trails")
public class Trail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("country")
    private String country;

    @Column(nullable = false)
    @JsonProperty("region")
    private String region;
    @Column(nullable = false)
    @JsonProperty("trailName")
    private String trailName;


    @Column(nullable = false)

    @JsonProperty("trailCode")
    private String trailCode;


    @Column(nullable = false)
    @JsonProperty("recommended")
    private String recommended;

    @Column(nullable = false)
    @JsonProperty("municipality")
    private String municipality;

    @Column(nullable = false)
    @JsonProperty("type")
    private String type;

    @Column(nullable = false)
    @JsonProperty("transportation")
    private String transportation;

    @Column(nullable = false)
    @JsonProperty("category")
    private String category;

    @Column(nullable = false)
    @JsonProperty("difficulty")
    private String difficulty;

    @Column(nullable = false)
    @JsonProperty("season")
    private String season;

    @Column(nullable = false)
    @JsonProperty("length")
    private String length;

    @Column(nullable = false)
    @JsonProperty("duration")
    private String duration;

    @Column(nullable = false)
    @JsonProperty("elevation")
    private String elevation;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "trail_landmarks",
            joinColumns = { @JoinColumn(name = "trail_id") },
            inverseJoinColumns = { @JoinColumn(name = "landmark_id") })
    @JsonIgnore
    private Set<LandMark> properties = new HashSet<>();

    @Column(nullable = false)
    @JsonProperty("infrastructure")
    private String infrastructure;

    @Column(nullable = false)
    @JsonProperty("marked")
    private String marked;

    @Column(nullable = false)
    @JsonProperty("crosses forestry agency")
    private String crossesAgency;

    @Column(nullable = false)
    @JsonProperty("organization")
    private String organization;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinColumn(nullable = false)
    @JsonProperty("description")
    private Description description;
    @Column(nullable = false)
    @JsonProperty("coordinates")
    private String coordinates;


}
