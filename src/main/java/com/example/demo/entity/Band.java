package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Band")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Band {
    
    @Id
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 4000)
    private String description;
}
