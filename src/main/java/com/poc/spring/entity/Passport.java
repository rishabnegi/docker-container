package com.poc.spring.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;


}
