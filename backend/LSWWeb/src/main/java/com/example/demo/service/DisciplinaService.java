package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DisciplinaDTO;
import com.example.demo.repository.DisciplinaRepository;

import model.Disciplina;
import model.Rezultat;

@Service
public class DisciplinaService {
	@Autowired
	DisciplinaRepository dr;
	
	@Autowired
	RezultatService rs;
	
	public List<Disciplina> sveDiscipline() {
		return dr.findAll();
	}
	
	public Map<Disciplina, Double> getProsecnoPonavljanja() {
        List<Rezultat> rezultati = rs.findAll();
        return rezultati.stream().collect(Collectors.groupingBy(Rezultat::getDisciplina, Collectors.averagingInt(Rezultat::getRezultat)));
    }
	
	public DisciplinaDTO mapToDTO(Disciplina r) {
	    return new DisciplinaDTO(
	        r.getId(),
	        r.getNaziv(),
	        r.getOpis()
	    );
	}
}
