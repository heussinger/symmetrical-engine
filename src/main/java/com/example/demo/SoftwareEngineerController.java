package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {
    
    @GetMapping
    public List<SoftwareEngineer> getEngineers() {
        // Implementation to retrieve all software engineers
        return List.of(
            new SoftwareEngineer(1, "Alice", List.of("Java", "Spring")),
            new SoftwareEngineer(2, "Bob", List.of("Python", "Django"))
        );
    }

}
