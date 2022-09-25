package com.poc.spring.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany
    @JoinTable(name = "student_courses",joinColumns = @JoinColumn(name="id"),inverseJoinColumns = @JoinColumn(name="course_id"))
    private List<Course> courses = new ArrayList<>();
}
