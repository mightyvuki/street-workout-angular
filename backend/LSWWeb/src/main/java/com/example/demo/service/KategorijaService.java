package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.KategorijaRepository;
import com.example.demo.repository.KorisnikRepository;

import model.Kategorija;
import model.Korisnik;

@Service
public class KategorijaService {
	
	@Autowired
	KategorijaRepository kr;
	
	public Kategorija nadjiKategoriju(int id) {
		return kr.findById(id).orElse(null);
	}
	
	public List<Kategorija> sveKategorije() {
		return kr.findAll();
	}
	
	public Kategorija nadjiKategorijuZaKorisnika(Korisnik korisnik, int brGodina) {
        return kr.findByPolAndGodine(korisnik.getPol(), brGodina); // ako ne postoji odgovarajuća kategorija
    }

}
