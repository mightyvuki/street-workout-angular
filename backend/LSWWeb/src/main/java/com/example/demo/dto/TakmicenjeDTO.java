package com.example.demo.dto;

import java.util.Date;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TakmicenjeDTO {

	private int id;
	@NotBlank(message = "Naziv je obavezan")
	@Size(min = 3, max = 50, message = "Naziv mora imati između 3 i 50 karaktera")
	private String naziv;

	@NotBlank(message = "Lokacija je obavezna")
	@Size(min = 3, max = 50, message = "Lokacija mora biti između 3 i 50 karaktera")
	private String lokacija;

	private String opis;

	@NotNull(message = "Datum je obavezan")
	@Future(message = "Datum mora biti u budućnosti")
	private Date datum;
	
	private KorisnikDTO organizator;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public KorisnikDTO getOrganizator() {
		return organizator;
	}

	public void setOrganizator(KorisnikDTO organizator) {
		this.organizator = organizator;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

}
