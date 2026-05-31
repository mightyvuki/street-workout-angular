package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the rezultat database table.
 * 
 */
@Entity
@NamedQuery(name="Rezultat.findAll", query="SELECT r FROM Rezultat r")
public class Rezultat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int rezultat;

	//bi-directional many-to-one association to Disciplina
	@ManyToOne
	private Disciplina disciplina;

	//bi-directional many-to-one association to Kategorija
	@ManyToOne
	private Kategorija kategorija;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to Takmicenje
	@ManyToOne
	private Takmicenje takmicenje;

	public Rezultat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRezultat() {
		return this.rezultat;
	}

	public void setRezultat(int rezultat) {
		this.rezultat = rezultat;
	}

	public Disciplina getDisciplina() {
		return this.disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Kategorija getKategorija() {
		return this.kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Takmicenje getTakmicenje() {
		return this.takmicenje;
	}

	public void setTakmicenje(Takmicenje takmicenje) {
		this.takmicenje = takmicenje;
	}

}