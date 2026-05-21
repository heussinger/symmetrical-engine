package com.example.demo.service;

import com.example.demo.repository.DozentRepository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Dozent;

@Service
public class DozentService {
    
    private final DozentRepository dozentRepository;


    public DozentService(DozentRepository dozentRepository) {
        this.dozentRepository = dozentRepository;
    }

    public List<Dozent> getAllDozenten() {
        return dozentRepository.findAll();
    }

    public Dozent getDozentById(int id) {
        return dozentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
            "Dozent with id " + id + " does not exist"
        ));
    }

    public void addDozent(Dozent dozent) {
        dozentRepository.save(dozent);
        //System.out.println("Dozent " + dozent.getVorname() + " " + dozent.getNachname() + " wurde hinzugefügt");
    }

    public void deleteDozent(int id) {
        dozentRepository.deleteById(id);
    }
}
