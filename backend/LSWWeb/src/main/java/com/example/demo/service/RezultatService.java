package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RezultatDTO;
import com.example.demo.repository.RezultatRepository;

import model.Rezultat;

@Service
public class RezultatService {
	@Autowired
	RezultatRepository rr;
	
	public List<Rezultat> getRezultatiKorisnika(int id) {
		return rr.findByKorisnik_Id(id);
	}
	
	public List<Rezultat> getRezultatiKategorije(int tId, int kId) {
		return rr.findByTakmicenje_IdAndKategorija_IdOrderByDisciplina_NazivAscRezultatDesc(tId, kId);
	}

	public List<Rezultat> findAll() {
		// TODO Auto-generated method stub
		return rr.findAll();
	}
	
	public RezultatDTO mapToDTO(Rezultat r) {
	    return new RezultatDTO(
	        r.getId(),
	        r.getKorisnik().getIme() + " " + r.getKorisnik().getPrezime(),
	        r.getTakmicenje().getNaziv(),
	        r.getDisciplina().getNaziv(),
	        r.getRezultat()
	    );
	}
}
