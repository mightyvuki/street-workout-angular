package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the prijava database table.
 * 
 */
@Entity
@NamedQuery(name="Prijava.findAll", query="SELECT p FROM Prijava p")
public class Prijava implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datum_prijave")
	private Date datumPrijave;

	//bi-directional many-to-one association to Takmicenje
	@ManyToOne
	@JoinColumn(name="takmicenje_id")
	private Takmicenje takmicenje;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="korisnik_id")
	private Korisnik korisnik;

	public Prijava() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatumPrijave() {
		return this.datumPrijave;
	}

	public void setDatumPrijave(Date datumPrijave) {
		this.datumPrijave = datumPrijave;
	}

	public Takmicenje getTakmicenje() {
		return this.takmicenje;
	}

	public void setTakmicenje(Takmicenje takmicenje) {
		this.takmicenje = takmicenje;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}