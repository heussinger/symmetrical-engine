package com.example.demo.service;

import com.example.demo.repository.DozentRepository;
import com.example.demo.entity.Dozent;

public class DozentService {
    
    private final DozentRepository dozentRepository;

    public DozentService(DozentRepository dozentRepository) {
        this.dozentRepository = dozentRepository;
    }

    public void addDozent(Dozent dozent) {
        dozentRepository.save(dozent);
    }

    public void deleteDozent(int id) {
        dozentRepository.deleteById(id);
    }
}
