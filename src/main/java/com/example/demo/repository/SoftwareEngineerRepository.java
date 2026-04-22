package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SoftwareEngineer;

public interface SoftwareEngineerRepository extends JpaRepository<SoftwareEngineer, Integer> {}