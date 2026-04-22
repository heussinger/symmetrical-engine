package com.example.demo.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//import java.util.List;

@Entity
public class SoftwareEngineer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String techStack;

    public SoftwareEngineer() {
        this.id = 0;
        this.name = "";
        this.techStack = "";
    }

    public SoftwareEngineer(String name, String techStack) {
        this.id = id;
        this.name = name;
        this.techStack = techStack;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTechStack() {
        return techStack;
    }
    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

//equals and hashCode based on id and name
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SoftwareEngineer that = (SoftwareEngineer) o;
        return Objects.equals(name, that.name) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,techStack);
    }



}
