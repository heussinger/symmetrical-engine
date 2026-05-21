package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Dozent;
import com.example.demo.service.DozentService;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/dozent")
public class DozentController {
    
    private final DozentService dozentService;

    public DozentController(DozentService dozentService) {
        this.dozentService = dozentService;
    }

    @GetMapping
    public List<Dozent> getDozenten() {
        // Implementation to retrieve all Dozenten
        return dozentService.getAllDozenten();
    }

    @GetMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String addNewDozent(@RequestParam(defaultValue = "") String vorname, 
                            @RequestParam(defaultValue = "") String nachname) {
        Dozent dozent = new Dozent(vorname, nachname);
        dozentService.addDozent(dozent);
        return new String("neuer Dozent heißt " + vorname + " " + nachname);
    }
    
    @GetMapping("{id}")
    public Dozent getDozentById(@PathVariable Integer id) {
        // Implementation to retrieve a Dozent by Id
        return dozentService.getDozentById(id);
    }

    @PostMapping
    public String newDozent(@RequestBody Dozent dozent) {
        // Implementation to add a new Dozent
        dozentService.addDozent(dozent);    
        return "Added new Dozent: " + dozent.getVorname() + " " + dozent.getNachname(); 
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDozent(@PathVariable Integer id) {
        dozentService.deleteDozent(id);
        return new String("Dozent " + id + " wurde entfernt");
    }


}
