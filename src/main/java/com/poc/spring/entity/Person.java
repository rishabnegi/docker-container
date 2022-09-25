package com.poc.spring.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String city;


}
