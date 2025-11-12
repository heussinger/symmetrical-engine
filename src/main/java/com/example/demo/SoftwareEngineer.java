package com.example.demo;

import java.util.Objects;
import java.util.List;

public class SoftwareEngineer {

    private Integer id;
    private String name;
    private List<String> techStack;

    public SoftwareEngineer(Integer id, String name, List<String> techStack) {
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
    public List<String> getTechStack() {
        return techStack;
    }
    public void setTechStack(List<String> techStack) {
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
