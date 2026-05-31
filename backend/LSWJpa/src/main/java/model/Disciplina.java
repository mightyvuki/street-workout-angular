package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the disciplina database table.
 * 
 */
@Entity
@NamedQuery(name="Disciplina.findAll", query="SELECT d FROM Disciplina d")
public class Disciplina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String naziv;

	@Lob
	private String opis;

	//bi-directional many-to-one association to Rezultat
	@OneToMany(mappedBy="disciplina")
	private List<Rezultat> rezultati;

	public Disciplina() {
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

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Rezultat> getRezultati() {
		return this.rezultati;
	}

	public void setRezultati(List<Rezultat> rezultati) {
		this.rezultati = rezultati;
	}

	public Rezultat addRezultati(Rezultat rezultati) {
		getRezultati().add(rezultati);
		rezultati.setDisciplina(this);

		return rezultati;
	}

	public Rezultat removeRezultati(Rezultat rezultati) {
		getRezultati().remove(rezultati);
		rezultati.setDisciplina(null);

		return rezultati;
	}

}