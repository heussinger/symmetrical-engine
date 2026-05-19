package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Dozent;
import com.example.demo.service.DozentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/v1/dozent")
public class DozentController {
    
    private final DozentService dozentService;

    public DozentController(DozentService dozentService) {
        this.dozentService = dozentService;
    }

    @GetMapping("/new")
    public String newDozent(@RequestParam(defaultValue = "") String vorname, @RequestParam(defaultValue = "") String nachname) {
        return new String("neuer Dozent heißt" + vorname + " " + nachname);
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteDozent(@PathVariable Integer id) {
        return new String("Dozent " + id + " wurde entfernt");
    }


}
