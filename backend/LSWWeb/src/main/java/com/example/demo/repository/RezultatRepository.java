package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Rezultat;

public interface RezultatRepository extends JpaRepository<Rezultat, Integer> {
	List<Rezultat> findByKorisnik_Id(int id);
	
	// ovo je genijalno
	List<Rezultat> findByTakmicenje_IdAndKategorija_IdOrderByDisciplina_NazivAscRezultatDesc(int takmicenjeId, int kategorijaId);
	
	@Query("SELECT r.disciplina.naziv AS disciplina, AVG(r.rezultat) AS prosek " +
		       "FROM Rezultat r " +
		       "JOIN r.takmicenje t " +
		       "WHERE t.id = :tId " +
		       "GROUP BY r.disciplina.naziv " +
		       "ORDER BY r.disciplina.naziv")
	List<Object[]> findProsekPoDisciplinama(@Param("tId") int takmicenjeId);
}
