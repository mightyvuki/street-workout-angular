package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.dto.TakmicenjeDTO;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.UlogaRepository;

import jakarta.validation.Valid;
import model.Korisnik;
import model.Takmicenje;
import model.Uloga;

@Service
public class KorisnikService {
	@Autowired
	private KorisnikRepository kr;
	
	@Autowired
	private UlogaRepository ur;
	
	@Autowired
    PasswordEncoder passwordEncoder;
    
	
	public boolean postojiUsername(String username) {
		if (kr.findByUsername(username) == null)
			return false;
		return true;
	}
	
	public boolean postojiEmail(String email) {
		if (kr.findByEmail(email) == null)
			return false;
		return true;
	}
	
	public Korisnik register(KorisnikDTO dto) {
		Korisnik k = new Korisnik();
		k.setIme(dto.getIme());
		k.setPrezime(dto.getPrezime());
		k.setUsername(dto.getUsername());
		k.setEmail(dto.getEmail());
		k.setPassword(dto.getPassword());
		k.setPol(dto.getPol());
		k.setDatumRodjenja(dto.getDatumRodjenja());
		k.setDatumRegistracije(new Date());
		k.setUloga(ur.findByNaziv("user"));
		
		return kr.save(k);
	}
	
	
	public Korisnik update(Korisnik k) {
		Korisnik novi = kr.findById(k.getId()).orElse(null);
		if (novi == null)
			return null;
		novi.setIme(k.getIme());
		novi.setPrezime(k.getPrezime());
		novi.setUsername(k.getUsername());
		novi.setEmail(k.getEmail());
		if (k.getPassword() != null && !k.getPassword().isEmpty()) 
			novi.setPassword(k.getPassword());
		novi.setPol(k.getPol());
		novi.setDatumRodjenja(k.getDatumRodjenja());
		return kr.save(novi);
	}
	
	public Korisnik login(String username, String password) {
		Korisnik k = kr.findByUsername(username);
		if (k != null && k.getPassword().equals(password))
			return k;
		return null;
	}
	
	public int godine(int id) {
		return kr.findGodineById(id);
	}

	public List<Korisnik> findAllByUloga(String uloga) {
		return kr.findAllByUloga(ur.findByNaziv(uloga));
	}

	public Korisnik getKorisnik(int korisnikId) {
		return kr.findById(korisnikId).orElse(null);
	}
	
	public Korisnik findByUsername(String username) {
		return kr.findByUsername(username);
	}
	
	public KorisnikDTO mapToDTO(Korisnik k) {
		KorisnikDTO dto = new KorisnikDTO();
		dto.setId(k.getId());
		dto.setIme(k.getIme());
		dto.setPrezime(k.getPrezime());
		dto.setUsername(k.getUsername());
		dto.setPassword(k.getPassword());
		dto.setPol(k.getPol());
		dto.setDatumRodjenja(k.getDatumRodjenja());
		dto.setEmail(k.getEmail());

	    return dto;
	}
	
}
