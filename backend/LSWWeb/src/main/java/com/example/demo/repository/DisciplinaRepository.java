package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer>{
	List<Disciplina> findAll();
}
