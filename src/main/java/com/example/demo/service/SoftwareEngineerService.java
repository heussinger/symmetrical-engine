package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.SoftwareEngineer;
import com.example.demo.repository.SoftwareEngineerRepository;

@Service
public class SoftwareEngineerService {

    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(
    SoftwareEngineerRepository softwareEngineerRepository)
    {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    public SoftwareEngineer getSoftwareEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id).orElseThrow(() -> new IllegalStateException(
            "Software Engineer with id " + id + " does not exist"
        ));
    }

    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.save(softwareEngineer);
    }

    public void deleteSoftwareEngineer(Integer id) {
        boolean exists = softwareEngineerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                "Software Engineer with id " + id + " does not exist"
            );
        }
        else {
            softwareEngineerRepository.deleteById(id);
        }
    }

    public void updateSoftwareEngineer(Integer id, SoftwareEngineer softwareEngineer) {
        SoftwareEngineer existingEngineer = softwareEngineerRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException(
                "Software Engineer with id " + id + " does not exist"
            ));

        existingEngineer.setName(softwareEngineer.getName());
        existingEngineer.setTechStack(softwareEngineer.getTechStack());

        softwareEngineerRepository.save(existingEngineer);
    }

}
