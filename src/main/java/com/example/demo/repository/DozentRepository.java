package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Dozent;

public interface DozentRepository extends JpaRepository<Dozent,Integer>{}

