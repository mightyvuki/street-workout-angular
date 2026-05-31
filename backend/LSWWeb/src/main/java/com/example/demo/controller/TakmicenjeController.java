package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PrijavaDTO;
import com.example.demo.dto.RezultatDTO;
import com.example.demo.dto.TakmicenjeDTO;
import com.example.demo.service.KategorijaService;
import com.example.demo.service.PrijavaService;
import com.example.demo.service.RezultatService;
import com.example.demo.service.TakmicenjeService;

import model.Kategorija;
import model.Prijava;
import model.Rezultat;
import model.Takmicenje;

@RestController
@RequestMapping("/api/takmicenje")
@CrossOrigin(origins = "http://localhost:4200") 
public class TakmicenjeController {

    @Autowired
    private TakmicenjeService ts;

    @Autowired
    private PrijavaService ps;

    @Autowired
    private KategorijaService ks;

    @Autowired
    private RezultatService rs;

    @GetMapping("/najavljeno/{id}")
    public Map<String, Object> prikaziNajavljeno(@PathVariable("id") int id) {
        Takmicenje t = ts.getTakmicenje(id);
        List<PrijavaDTO> p = ps.nadjiZaTakmicenje(id).stream().map(ps::mapToDTO).toList();

        Map<String, Object> response = new HashMap<>();
        response.put("takmicenje", ts.mapToDTO(t));
        response.put("prijave", p);
        response.put("odrzano", false);

        return response;
    }

    @GetMapping("/odrzano/{id}")
    public Map<String, Object> prikaziOdrzano(@PathVariable("id") int id) {
        Takmicenje t = ts.getTakmicenje(id);
        List<Kategorija> kategorije = ks.sveKategorije();
        Map<String, List<RezultatDTO>> rezultati = new HashMap<>();

        for (Kategorija k : kategorije) {
            List<RezultatDTO> r = rs.getRezultatiKategorije(id, k.getId()).stream().map(rs::mapToDTO).toList();
            rezultati.put(k.getNaziv(), r);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("takmicenje", ts.mapToDTO(t));
        response.put("rezultati", rezultati);
        response.put("odrzano", true);

        return response;
    }
    
    @GetMapping(value = "/getNajavljena", produces = "application/json")
    public ResponseEntity<?> getNajavljena() {
    	List<TakmicenjeDTO> najavljena = ts.getNajavljenaTakmicenja().stream().map(ts::mapToDTO).toList();
    	return ResponseEntity.ok(najavljena);
    }
    
    @GetMapping(value = "/getOdrzana", produces = "application/json")
    public ResponseEntity<?> getOdrzana() {
    	List<TakmicenjeDTO> odrzana = ts.getOdrzanaTakmicenja().stream().map(ts::mapToDTO).toList();
    	return ResponseEntity.ok(odrzana);
    }
   
    
}
