package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datum_registracije")
	private Date datumRegistracije;

	@Temporal(TemporalType.DATE)
	@Column(name="datum_rodjenja")
	private Date datumRodjenja;

	private String email;

	private String ime;

	private String password;

	private String pol;

	private String prezime;

	private String username;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="korisnik")
	private List<Komentar> komentari;

	//bi-directional many-to-one association to Uloga
	@ManyToOne
	private Uloga uloga;

	//bi-directional many-to-one association to Rezultat
	@OneToMany(mappedBy="korisnik")
	private List<Rezultat> rezultati;

	//bi-directional many-to-one association to Takmicenje
	@OneToMany(mappedBy="organizator")
	private List<Takmicenje> organizovanaTakmicenja;

	//bi-directional many-to-one association to Prijava
	@OneToMany(mappedBy="korisnik")
	private List<Prijava> prijave;

	public Korisnik() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatumRegistracije() {
		return this.datumRegistracije;
	}

	public void setDatumRegistracije(Date datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}

	public Date getDatumRodjenja() {
		return this.datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPol() {
		return this.pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Komentar> getKomentari() {
		return this.komentari;
	}

	public void setKomentari(List<Komentar> komentari) {
		this.komentari = komentari;
	}

	public Komentar addKomentari(Komentar komentari) {
		getKomentari().add(komentari);
		komentari.setKorisnik(this);

		return komentari;
	}

	public Komentar removeKomentari(Komentar komentari) {
		getKomentari().remove(komentari);
		komentari.setKorisnik(null);

		return komentari;
	}

	public Uloga getUloga() {
		return this.uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public List<Rezultat> getRezultati() {
		return this.rezultati;
	}

	public void setRezultati(List<Rezultat> rezultati) {
		this.rezultati = rezultati;
	}

	public Rezultat addRezultati(Rezultat rezultati) {
		getRezultati().add(rezultati);
		rezultati.setKorisnik(this);

		return rezultati;
	}

	public Rezultat removeRezultati(Rezultat rezultati) {
		getRezultati().remove(rezultati);
		rezultati.setKorisnik(null);

		return rezultati;
	}

	public List<Takmicenje> getOrganizovanaTakmicenja() {
		return this.organizovanaTakmicenja;
	}

	public void setOrganizovanaTakmicenja(List<Takmicenje> organizovanaTakmicenja) {
		this.organizovanaTakmicenja = organizovanaTakmicenja;
	}

	public Takmicenje addOrganizovanaTakmicenja(Takmicenje organizovanaTakmicenja) {
		getOrganizovanaTakmicenja().add(organizovanaTakmicenja);
		organizovanaTakmicenja.setOrganizator(this);

		return organizovanaTakmicenja;
	}

	public Takmicenje removeOrganizovanaTakmicenja(Takmicenje organizovanaTakmicenja) {
		getOrganizovanaTakmicenja().remove(organizovanaTakmicenja);
		organizovanaTakmicenja.setOrganizator(null);

		return organizovanaTakmicenja;
	}

	public List<Prijava> getPrijave() {
		return this.prijave;
	}

	public void setPrijave(List<Prijava> prijave) {
		this.prijave = prijave;
	}

	public Prijava addPrijave(Prijava prijave) {
		getPrijave().add(prijave);
		prijave.setKorisnik(this);

		return prijave;
	}

	public Prijava removePrijave(Prijava prijave) {
		getPrijave().remove(prijave);
		prijave.setKorisnik(null);

		return prijave;
	}

}