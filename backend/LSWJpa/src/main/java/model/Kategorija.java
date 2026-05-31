package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the kategorija database table.
 * 
 */
@Entity
@NamedQuery(name="Kategorija.findAll", query="SELECT k FROM Kategorija k")
public class Kategorija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="max_godina")
	private int maxGodina;

	@Column(name="min_godina")
	private int minGodina;

	private String naziv;

	private String pol;

	//bi-directional many-to-one association to Rezultat
	@OneToMany(mappedBy="kategorija")
	private List<Rezultat> rezultati;

	public Kategorija() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxGodina() {
		return this.maxGodina;
	}

	public void setMaxGodina(int maxGodina) {
		this.maxGodina = maxGodina;
	}

	public int getMinGodina() {
		return this.minGodina;
	}

	public void setMinGodina(int minGodina) {
		this.minGodina = minGodina;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getPol() {
		return this.pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public List<Rezultat> getRezultati() {
		return this.rezultati;
	}

	public void setRezultati(List<Rezultat> rezultati) {
		this.rezultati = rezultati;
	}

	public Rezultat addRezultati(Rezultat rezultati) {
		getRezultati().add(rezultati);
		rezultati.setKategorija(this);

		return rezultati;
	}

	public Rezultat removeRezultati(Rezultat rezultati) {
		getRezultati().remove(rezultati);
		rezultati.setKategorija(null);

		return rezultati;
	}

}