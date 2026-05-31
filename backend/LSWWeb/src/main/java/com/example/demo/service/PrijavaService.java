package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.PrijavaDTO;
import com.example.demo.dto.TakmicenjeDTO;
import com.example.demo.repository.PrijavaRepository;
import com.example.demo.repository.TakmicenjeRepository;

import model.Korisnik;
import model.Prijava;
import model.Takmicenje;

@Service
public class PrijavaService {

	@Autowired
	PrijavaRepository pr;
	
	@Autowired
	TakmicenjeRepository tr;
	
	@Autowired
	KorisnikService ks;
	
	@Autowired
	TakmicenjeService ts;
	
	public List<Prijava> nadjiZaTakmicenje(int id) {
		return pr.findByTakmicenje_Id(id);
	}
	
	public Map<Takmicenje, List<Korisnik>> getTakmicariPoTakmicenjima() {
        List<Takmicenje> takmicenja = tr.findAll();
        Map<Takmicenje, List<Korisnik>> mapa = new HashMap<>();
        for (Takmicenje t : takmicenja) {
        	List<Prijava> prijave = findByTakmicenje(t);
        	List<Korisnik> takmicari = new ArrayList<Korisnik>();
        	for (Prijava p : prijave) {
        		 takmicari.add(p.getKorisnik());
        	}
            mapa.put(t, takmicari);
        }
        return mapa;
    }
	
	public Prijava prijaviSe(Korisnik k, Takmicenje t) {
		if (vecPrijavljen(k, t))
			return null;
		Prijava p = new Prijava();
		p.setDatumPrijave(new Date());
		p.setKorisnik(k);
		p.setTakmicenje(t);
		return pr.save(p);
	}
	
	public boolean vecPrijavljen(Korisnik k, Takmicenje t) {
		return pr.findByTakmicenje_IdAndKorisnik_Id(t.getId(), k.getId()) != null;
	}
	
	@Transactional
	public boolean odjaviSe(Korisnik k, Takmicenje t) {
	    Prijava p = pr.findByTakmicenje_IdAndKorisnik_Id(t.getId(), k.getId());
	    if (p != null) {
	        pr.delete(p);
	        return true;
	    }
	    return false;
	}

	public Prijava getPrijava(int id) {
		return pr.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Prijava p) {
		pr.delete(p);
	}

	public void save(Prijava prijava) {
		pr.save(prijava);
	}

	public List<Prijava> findByTakmicenje(Takmicenje t) {
		return pr.findByTakmicenje_Id(t.getId());
	}

	public List<Prijava> findAll() {
		return pr.findAll();
	}
	
	public Map<Takmicenje, Long> getBrojPrijavaIzmedju(Date odDatum, Date doDatum) {
        List<Prijava> prijave = pr.findByDatumPrijaveBetween(odDatum, doDatum);
        return prijave.stream().collect(Collectors.groupingBy(Prijava::getTakmicenje, Collectors.counting()));
    }
	
	public PrijavaDTO mapToDTO(Prijava d) {
		PrijavaDTO dto = new PrijavaDTO();
	    dto.setId(d.getId());
	    dto.setDatumPrijave(new Date());
	    dto.setKorisnik(ks.mapToDTO(d.getKorisnik()));
	    dto.setTakmicenje(ts.mapToDTO(d.getTakmicenje()));
	    return dto;
	}
}
