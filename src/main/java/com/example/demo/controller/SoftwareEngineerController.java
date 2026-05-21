package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SoftwareEngineer;
import com.example.demo.service.SoftwareEngineerService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @GetMapping
    public List<SoftwareEngineer> getEngineers() {
        // Implementation to retrieve all software engineers
        return softwareEngineerService.getAllSoftwareEngineers();
    }
    
    @GetMapping("{id}")
    public SoftwareEngineer getEngineerById(@PathVariable Integer id) {
        // Implementation to retrieve a software engineer by Id
        return softwareEngineerService.getSoftwareEngineerById(id);
    }

    @GetMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String createNewEngineer(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String techStack) {
        
        SoftwareEngineer softwareEngineer = new SoftwareEngineer(name,techStack);
        softwareEngineerService.insertSoftwareEngineer(softwareEngineer);
        return "Created engineer with name: " + name + " and tech stack: " + techStack;
    }

    @PostMapping
    public void addNewSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer) {
        softwareEngineerService.insertSoftwareEngineer(softwareEngineer);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSoftwareEngineer(@PathVariable Integer id) {
        softwareEngineerService.deleteSoftwareEngineer(id);
    }

    @PutMapping("/update/{id}")
    public void updateSoftwareEngineer(@PathVariable Integer id, @RequestBody SoftwareEngineer softwareEngineer) {
        softwareEngineerService.updateSoftwareEngineer(id, softwareEngineer);
        }

}
