package com.example.demo.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import model.Korisnik;

public class KorisnikDTO {

	private int id;
	@NotBlank(message = "Ime je obavezno")
	@Size(min = 2, max = 20, message = "Ime mora imati između 2 i 20 karaktera")
	private String ime;

	@NotBlank(message = "Prezime je obavezno")
	@Size(min = 2, max = 50, message = "Prezime mora imati između 2 i 50 karaktera")
	private String prezime;

	@NotBlank(message = "Korisničko ime je obavezno")
	@Size(min = 2, max = 20, message = "Korisničko ime mora imati između 2 i 20 karaktera")
	private String username;

	@NotBlank(message = "Lozinka je obavezna")
	@Size(min = 4, max = 20, message = "Lozinka mora imati između 4 i 20 karaktera")
	private String password;

	@NotBlank(message = "Pol je obavezan")
	private String pol;

	@NotNull(message = "Datum je obavezan")
	@PastOrPresent(message = "Datum ne može biti u budućnosti")
	private Date datumRodjenja;

	@NotBlank(message = "E-mail je obavezan")
	@Size(min = 2, max = 20, message = "Ime mora imati između 2 i 20 karaktera")
	private String email;
	
	public KorisnikDTO() {}
	
	public KorisnikDTO(Korisnik k) {
		this.setId(k.getId());
		this.setIme(k.getIme());
		this.setPrezime(k.getPrezime());
		this.setUsername(k.getUsername());
		this.setPassword(k.getPassword());
		this.setPol(k.getPol());
		this.setDatumRodjenja(k.getDatumRodjenja());
		this.setEmail(k.getEmail());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
