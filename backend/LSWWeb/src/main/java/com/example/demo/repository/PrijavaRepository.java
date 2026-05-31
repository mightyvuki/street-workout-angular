package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import model.Prijava;

public interface PrijavaRepository extends JpaRepository<Prijava, Integer> {
	
	List<Prijava> findByTakmicenje_Id(int id);
	Prijava findByTakmicenje_IdAndKorisnik_Id(int takId, int korId);
	
	List<Prijava> findByDatumPrijaveBetween(Date odDatum, Date doDatum);
}
