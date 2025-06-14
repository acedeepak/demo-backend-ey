package com.example.demo.dto;

import lombok.*;
import java.time.LocalDateTime;

import com.example.demo.entity.Band;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequest {


    private Long id;


    private String name;

    private Long designationId;

    private Long bandId;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;


    private String emailId;


    private String phoneNumber;


    private String address;
    

    private String imageURL;
    
    private Double salary;
}
