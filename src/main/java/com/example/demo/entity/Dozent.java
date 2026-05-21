package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Dozent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String vorname;
    private String nachname;
    
    public Dozent(){
        this.id = 0;
        this.vorname = "";
        this.nachname = "";
    }

    public Dozent(String vorname, String nachname){
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }
    public String getNachname() {
        return nachname;
    }

}