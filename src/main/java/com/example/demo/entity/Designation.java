package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Designation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Designation {

	@Id
	private Long id;

	@Column(nullable = false, length = 100)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	@Column(length = 4000)
	private String description;
}
