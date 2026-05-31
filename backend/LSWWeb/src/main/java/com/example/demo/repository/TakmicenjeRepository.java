package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Takmicenje;

public interface TakmicenjeRepository extends JpaRepository<Takmicenje, Integer> {

	List<Takmicenje> findByDatumAfter(Date danas);
	
	List<Takmicenje> findByDatumBefore(Date danas);
	
	@Query("SELECT p.takmicenje FROM Prijava p WHERE p.korisnik.id = :id AND p.takmicenje.datum > :danas")
	List<Takmicenje> findFutureTakmicenjaByKorisnikId(@Param("id") Integer id, @Param("danas") Date danas);
	
	@Query("SELECT p.takmicenje FROM Prijava p WHERE p.korisnik.id = :id AND p.takmicenje.datum <= :danas")
	List<Takmicenje> findPastTakmicenjaByKorisnikId(@Param("id") Integer id, @Param("danas") Date danas);
}
