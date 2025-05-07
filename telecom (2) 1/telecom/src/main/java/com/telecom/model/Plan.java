package com.telecom.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column
    private String validity;
    @Column
    private Double price;
    @Column
    private String dataAllowance;
    @Column
    private String talkTime;
    @Column
    private String smsAllowance;


}
