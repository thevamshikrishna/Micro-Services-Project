package com.telecom.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="user_plan")
public class UserPlan {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private User user;

        @ManyToOne
        private Plan plan;

        private LocalDate startDate;

        private String status;
        @Column(name = "expiry_date")
        private LocalDate expiryDate;


}


