package com.example.demo.dto;

import java.io.Serializable;

public class RezultatDTO implements Serializable {
    private int id;
    private String nazivTakmicenja;
    private String nazivDiscipline;
    private String imeIPrezime;
    private int rezultat;

    public RezultatDTO() {}

    public RezultatDTO(int id, String imeIPrezime, String nazivTakmicenja, String nazivDiscipline, int rezultat) {
        this.id = id;
        this.nazivTakmicenja = nazivTakmicenja;
        this.nazivDiscipline = nazivDiscipline;
        this.imeIPrezime = imeIPrezime;
        this.rezultat = rezultat;
    }

    // getters i setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNazivTakmicenja() { return nazivTakmicenja; }
    public void setNazivTakmicenja(String nazivTakmicenja) { this.nazivTakmicenja = nazivTakmicenja; }
    
    public String getImeIPrezime() { return imeIPrezime; }
    public void setImeIPrezime(String imeIPrezime) { this.imeIPrezime = imeIPrezime; }

    public String getNazivDiscipline() { return nazivDiscipline; }
    public void setNazivDiscipline(String nazivDiscipline) { this.nazivDiscipline = nazivDiscipline; }

    public int getRezultat() { return rezultat; }
    public void setRezultat(int rezultat) { this.rezultat = rezultat; }
}
