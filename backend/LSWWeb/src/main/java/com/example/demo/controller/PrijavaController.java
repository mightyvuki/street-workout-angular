package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.KategorijaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PrijavaService;
import com.example.demo.service.TakmicenjeService;

import model.Kategorija;
import model.Korisnik;
import model.Prijava;
import model.Takmicenje;

@RestController
@RequestMapping("/api/prijava")
@CrossOrigin(origins = "http://localhost:4200")
public class PrijavaController {

    @Autowired
    private PrijavaService ps;

    @Autowired
    private TakmicenjeService ts;

    @Autowired
    private KategorijaService katS;

    @Autowired
    private KorisnikService korS;

    @PostMapping("/prijaviSe/{tId}")
    public ResponseEntity<?> prijaviSe(@PathVariable("tId") int tId, @RequestBody Map<String, Integer> body) {
        try {
            Integer korisnikId = body.get("korisnikId");
            if (korisnikId == null)
                return ResponseEntity.badRequest().body(Map.of("error", "Nedostaje ID korisnika"));

            Korisnik k = korS.getKorisnik(korisnikId);
            Takmicenje t = ts.getTakmicenje(tId);

            if (k == null || t == null)
                return ResponseEntity.badRequest().body(Map.of("error", "Korisnik ili takmičenje ne postoji"));

            Kategorija kat = katS.nadjiKategorijuZaKorisnika(k, korS.godine(k.getId()));

            Prijava p = ps.prijaviSe(k, t);
            if (p == null)
                return ResponseEntity.badRequest().body(Map.of("error", "Već ste prijavljeni na ovo takmičenje!"));

            return ResponseEntity.ok(Map.of("success", "Uspešno ste se prijavili u kategoriji " + kat.getNaziv()));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("error", "Greška pri prijavi"));
        }
    }

    @PostMapping("/odjaviSe/{tId}")
    public ResponseEntity<?> odjaviSe(@PathVariable("tId") int tId, @RequestBody Map<String, Integer> body) {
        try {
            Integer korisnikId = body.get("korisnikId");
            if (korisnikId == null)
                return ResponseEntity.badRequest().body(Map.of("error", "Nedostaje ID korisnika"));

            Korisnik k = korS.getKorisnik(korisnikId);
            Takmicenje t = ts.getTakmicenje(tId);

            if (k == null || t == null)
                return ResponseEntity.badRequest().body(Map.of("error", "Korisnik ili takmičenje ne postoji"));

            boolean success = ps.odjaviSe(k, t);
            if (!success)
                return ResponseEntity.badRequest().body(Map.of("error", "Niste bili prijavljeni na ovo takmičenje."));

            return ResponseEntity.ok(Map.of("success", "Uspešno ste se odjavili sa takmičenja."));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("error", "Greška pri odjavi"));
        }
    }
}
