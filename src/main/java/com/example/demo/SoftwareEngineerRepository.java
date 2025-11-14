package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SoftwareEngineerRepository extends JpaRepository<SoftwareEngineer, Integer> {
    
}
