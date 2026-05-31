package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the uloga database table.
 * 
 */
@Entity
@NamedQuery(name="Uloga.findAll", query="SELECT u FROM Uloga u")
public class Uloga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String naziv;

	//bi-directional many-to-one association to Korisnik
	@OneToMany(mappedBy="uloga")
	private List<Korisnik> korisnici;

	public Uloga() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Korisnik> getKorisnici() {
		return this.korisnici;
	}

	public void setKorisnici(List<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}

	public Korisnik addKorisnici(Korisnik korisnici) {
		getKorisnici().add(korisnici);
		korisnici.setUloga(this);

		return korisnici;
	}

	public Korisnik removeKorisnici(Korisnik korisnici) {
		getKorisnici().remove(korisnici);
		korisnici.setUloga(null);

		return korisnici;
	}

}