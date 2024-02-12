package com.example.hikingtrails.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private  String text;
}
