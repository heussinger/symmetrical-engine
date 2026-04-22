package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Kurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "dozent_id",nullable = false)
    private Dozent dozent;

    public Kurs(){
        this.id = 0;
        this.name = "";
        }

    public Kurs(String name){
        this.id = id;
        this.name = name;
        }

}