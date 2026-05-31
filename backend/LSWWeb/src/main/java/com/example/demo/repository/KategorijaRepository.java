package com.example.demo.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Kategorija;

public interface KategorijaRepository extends JpaRepository<Kategorija, Integer> {
	// nadji kategoriju za korisnika
	@Query("SELECT k FROM Kategorija k WHERE k.pol = :pol AND :godine BETWEEN k.minGodina AND k.maxGodina")
	Kategorija findByPolAndGodine(@Param("pol") String pol, @Param("godine") Integer godine);
}
