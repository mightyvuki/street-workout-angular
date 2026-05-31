package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Korisnik;
import model.Takmicenje;
import model.Uloga;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
	Korisnik findByUsername(String username);
	Korisnik findByEmail(String email);
	
	@Query(value = "SELECT YEAR(CURDATE()) - YEAR(datum_rodjenja) FROM korisnik WHERE id = :id", nativeQuery = true) // zbog year
	int findGodineById(@Param("id") Integer id);
	
	List<Korisnik> findAllByUloga(Uloga byNaziv);
	
	@Query("SELECT k FROM Korisnik k JOIN Prijava p ON k.id = p.korisnik.id WHERE p.takmicenje.id = :tId")
	List<Korisnik> prijavljeniNaTakmicenje(@Param("tId") int tId);
}
