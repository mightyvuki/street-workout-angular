package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.RezultatDTO;
import com.example.demo.dto.TakmicenjeDTO;
import com.example.demo.service.DisciplinaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.RezultatService;
import com.example.demo.service.TakmicenjeService;

import model.Korisnik;
import model.Rezultat;
import model.Takmicenje;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class KorisnikController {

    @Autowired
    private KorisnikService ks;

    @Autowired
    private RezultatService rs;

    @Autowired
    private DisciplinaService ds;

    @Autowired
    private TakmicenjeService ts;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getProfile(@PathVariable int id) {
        try {
            Korisnik k = ks.getKorisnik(id);
            if (k == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(Map.of("error", "Korisnik nije pronađen"));
            }
            return ResponseEntity.ok(k);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Greška na serveru"));
        }
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable int id, @RequestBody Korisnik noviPodaci) {
        try {
            Korisnik postojeci = ks.getKorisnik(id);
            if (postojeci == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(Map.of("error", "Korisnik nije pronađen"));
            }

            if (!noviPodaci.getUsername().equals(postojeci.getUsername()) && ks.postojiUsername(noviPodaci.getUsername())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body(Map.of("error", "Korisničko ime već postoji"));
            }

            if (!noviPodaci.getEmail().equals(postojeci.getEmail()) && ks.postojiEmail(noviPodaci.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body(Map.of("error", "Email već postoji"));
            }

            postojeci.setUsername(noviPodaci.getUsername());
            postojeci.setIme(noviPodaci.getIme());
            postojeci.setPrezime(noviPodaci.getPrezime());
            postojeci.setEmail(noviPodaci.getEmail());
            postojeci.setDatumRodjenja(noviPodaci.getDatumRodjenja());
            postojeci.setPol(noviPodaci.getPol());
            Korisnik azuriran = ks.update(postojeci);

            return ResponseEntity.ok(azuriran);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Greška pri ažuriranju profila"));
        }
    }

    // GET rezultati korisnika
    @GetMapping("/results/{id}")
    public ResponseEntity<?> getResults(@PathVariable int id) {
        try {
            Korisnik k = ks.getKorisnik(id);
            if (k == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(Map.of("error", "Korisnik nije pronađen"));
            }

            // vraćamo DTO
            List<RezultatDTO> rezultatiDTO = rs.getRezultatiKorisnika(id)
                                               .stream()
                                               .map(rs::mapToDTO)
                                               .toList();

            return ResponseEntity.ok(Map.of(
                "rezultati", rezultatiDTO,
                "discipline", ds.sveDiscipline().stream().map(ds::mapToDTO).toList()
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Greška pri dohvatu rezultata"));
        }
    }

    // GET najavljena takmičenja za korisnika
    @GetMapping("/upcoming/{id}")
    public ResponseEntity<?> getUpcoming(@PathVariable int id) {
        try {
            Korisnik k = ks.getKorisnik(id);
            if (k == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(Map.of("error", "Korisnik nije pronađen"));
            }
            List<TakmicenjeDTO> sledeca = ts.getNajavljenaTakmicenjaZaKorisnika(id).stream().map(ts::mapToDTO).toList();
            return ResponseEntity.ok(Map.of("sledeca", sledeca));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "Greška pri dohvatu najavljenih takmičenja"));
        }
    }
}
