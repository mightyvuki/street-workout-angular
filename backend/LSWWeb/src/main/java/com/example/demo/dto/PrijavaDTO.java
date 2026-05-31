package com.example.demo.dto;

import java.util.Date;

public class PrijavaDTO {

	private int id;
	private Date datumPrijave;
	private TakmicenjeDTO takmicenje;
	private KorisnikDTO korisnik;

	public PrijavaDTO() {

	}
	
	public PrijavaDTO(int id, Date datumPrijave, TakmicenjeDTO takmicenje, KorisnikDTO korisnik) {
		super();
		this.id = id;
		this.datumPrijave = datumPrijave;
		this.takmicenje = takmicenje;
		this.korisnik = korisnik;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatumPrijave() {
		return datumPrijave;
	}

	public void setDatumPrijave(Date datumPrijave) {
		this.datumPrijave = datumPrijave;
	}

	public TakmicenjeDTO getTakmicenje() {
		return takmicenje;
	}

	public void setTakmicenje(TakmicenjeDTO takmicenje) {
		this.takmicenje = takmicenje;
	}

	public KorisnikDTO getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KorisnikDTO korisnik) {
		this.korisnik = korisnik;
	}

}