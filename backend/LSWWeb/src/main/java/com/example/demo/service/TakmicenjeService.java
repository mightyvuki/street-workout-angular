package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.TakmicenjeDTO;
import com.example.demo.repository.TakmicenjeRepository;

import model.Korisnik;
import model.Prijava;
import model.Takmicenje;

@Service
public class TakmicenjeService {
	@Autowired
	TakmicenjeRepository tr;
	
	@Autowired
	KorisnikService ks;
	
	public List<Takmicenje> getOdrzanaTakmicenja() {
		return tr.findByDatumBefore(new Date());
	}
	
	public List<Takmicenje> getNajavljenaTakmicenja() {
		return tr.findByDatumAfter(new Date());
	}
	
	public List<Takmicenje> getNajavljenaTakmicenjaZaKorisnika(int id) {
		return tr.findFutureTakmicenjaByKorisnikId(id, new Date());
	}
	
	public Takmicenje getTakmicenje(int id) {
		return tr.findById(id).orElse(null);
	}

	public List<Takmicenje> findAll() {
		return tr.findAll();
	}
	
	public Takmicenje save(Takmicenje t) {
		return tr.save(t);
	}

	@Transactional
	public void delete(Takmicenje takmicenje) {
		tr.delete(takmicenje);
	}
	
	
	
	public TakmicenjeDTO mapToDTO(Takmicenje d) {
		TakmicenjeDTO dto = new TakmicenjeDTO();
	    dto.setId(d.getId());
	    dto.setNaziv(d.getNaziv());
	    dto.setLokacija(d.getLokacija());
	    dto.setOpis(d.getOpis());
	    dto.setDatum(d.getDatum());
	    dto.setOrganizator(ks.mapToDTO(d.getOrganizator()));

	    return dto;
	}
}
