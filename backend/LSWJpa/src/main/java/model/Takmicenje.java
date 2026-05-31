package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the takmicenje database table.
 * 
 */
@Entity
@NamedQuery(name="Takmicenje.findAll", query="SELECT t FROM Takmicenje t")
public class Takmicenje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="datum")
	private Date datum;

	private String lokacija;

	private String naziv;

	@Lob
	private String opis;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="takmicenje")
	private List<Komentar> komentari;

	//bi-directional many-to-one association to Rezultat
	@OneToMany(mappedBy="takmicenje")
	private List<Rezultat> rezultati;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik organizator;

	//bi-directional many-to-one association to Prijava
	@OneToMany(mappedBy="takmicenje")
	private List<Prijava> prijave;

	public Takmicenje() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getLokacija() {
		return this.lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Komentar> getKomentari() {
		return this.komentari;
	}

	public void setKomentari(List<Komentar> komentari) {
		this.komentari = komentari;
	}

	public Komentar addKomentari(Komentar komentari) {
		getKomentari().add(komentari);
		komentari.setTakmicenje(this);

		return komentari;
	}

	public Komentar removeKomentari(Komentar komentari) {
		getKomentari().remove(komentari);
		komentari.setTakmicenje(null);

		return komentari;
	}

	public List<Rezultat> getRezultati() {
		return this.rezultati;
	}

	public void setRezultati(List<Rezultat> rezultati) {
		this.rezultati = rezultati;
	}

	public Rezultat addRezultati(Rezultat rezultati) {
		getRezultati().add(rezultati);
		rezultati.setTakmicenje(this);

		return rezultati;
	}

	public Rezultat removeRezultati(Rezultat rezultati) {
		getRezultati().remove(rezultati);
		rezultati.setTakmicenje(null);

		return rezultati;
	}

	public Korisnik getOrganizator() {
		return this.organizator;
	}

	public void setOrganizator(Korisnik organizator) {
		this.organizator = organizator;
	}

	public List<Prijava> getPrijave() {
		return this.prijave;
	}

	public void setPrijave(List<Prijava> prijave) {
		this.prijave = prijave;
	}

	public Prijava addPrijave(Prijava prijave) {
		getPrijave().add(prijave);
		prijave.setTakmicenje(this);

		return prijave;
	}

	public Prijava removePrijave(Prijava prijave) {
		getPrijave().remove(prijave);
		prijave.setTakmicenje(null);

		return prijave;
	}

}