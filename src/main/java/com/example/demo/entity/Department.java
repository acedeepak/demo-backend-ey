package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    
    @Id
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 4000)
    private String description;
}
