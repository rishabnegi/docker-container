package com.poc.spring.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue
    private int id;
    private String description;
    private String rating;
    @ManyToOne
    private Course course;
}
