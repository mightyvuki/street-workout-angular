package com.example.demo.controller;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.TakmicenjeDTO;
import com.example.demo.service.AdminService;
import com.example.demo.service.DisciplinaService;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.PrijavaService;
import com.example.demo.service.TakmicenjeService;

import model.Disciplina;
import model.Korisnik;
import model.Prijava;
import model.Takmicenje;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    @Autowired
    private PrijavaService prijavaService;

    @Autowired
    private TakmicenjeService takmicenjeService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private DisciplinaService disciplinaService;


    @GetMapping("/takmicenja")
    public ResponseEntity<List<TakmicenjeDTO>> getAllTakmicenja() {
        return ResponseEntity.ok(takmicenjeService.findAll().stream().map(takmicenjeService::mapToDTO).toList());
    }

    @GetMapping("/takmicenja/{id}")
    public ResponseEntity<?> getTakmicenje(@PathVariable int id) {
        Takmicenje t = takmicenjeService.getTakmicenje(id);
        if (t == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Takmičenje nije pronađeno."));
        return ResponseEntity.ok(t);
    }

    @PostMapping("/takmicenja")
    public ResponseEntity<?> addTakmicenje(@RequestBody TakmicenjeDTO takmicenjeDTO) {
        try {
            Takmicenje t = new Takmicenje();
            t.setNaziv(takmicenjeDTO.getNaziv());
            t.setDatum(takmicenjeDTO.getDatum());
            t.setLokacija(takmicenjeDTO.getLokacija());
            t.setOpis(takmicenjeDTO.getOpis());

            if (takmicenjeDTO.getOrganizator() != null) {
                t.setOrganizator(
                    korisnikService.findByUsername(takmicenjeDTO.getOrganizator().getUsername())
                );
            }

            takmicenjeService.save(t);

            return ResponseEntity.ok(Map.of("message", "Takmičenje je uspešno dodato."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Greška pri dodavanju takmičenja."));
        }
    }

    @PutMapping("/takmicenja/{id}")
    public ResponseEntity<?> updateTakmicenje(@PathVariable int id, @RequestBody Takmicenje updated) {
        Takmicenje postojeci = takmicenjeService.getTakmicenje(id);
        if (postojeci == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Takmičenje nije pronađeno."));

        postojeci.setNaziv(updated.getNaziv());
        postojeci.setDatum(updated.getDatum());
        postojeci.setLokacija(updated.getLokacija());
        postojeci.setOpis(updated.getOpis());

        takmicenjeService.save(postojeci);
        return ResponseEntity.ok(Map.of("message", "Takmičenje je uspešno izmenjeno."));
    }

    @DeleteMapping("/takmicenja/{id}")
    public ResponseEntity<?> deleteTakmicenje(@PathVariable int id) {
        Takmicenje t = takmicenjeService.getTakmicenje(id);
        if (t == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Takmičenje nije pronađeno."));

        takmicenjeService.delete(t);
        return ResponseEntity.ok(Map.of("message", "Takmičenje je obrisano."));
    }


    @GetMapping("/prijave")
    public ResponseEntity<?> getPrijave(@RequestParam(required = false) Integer takmicenjeId) {
        List<Prijava> prijave = (takmicenjeId != null)
                ? prijavaService.findByTakmicenje(takmicenjeService.getTakmicenje(takmicenjeId))
                : prijavaService.findAll();

        return ResponseEntity.ok(prijave);
    }

    @PostMapping("/prijave")
    public ResponseEntity<?> addPrijava(@RequestBody Map<String, Integer> body) {
        int korisnikId = body.get("korisnikId");
        int takmicenjeId = body.get("takmicenjeId");

        try {
            Prijava nova = new Prijava();
            nova.setDatumPrijave(new Date());
            nova.setKorisnik(korisnikService.getKorisnik(korisnikId));
            nova.setTakmicenje(takmicenjeService.getTakmicenje(takmicenjeId));
            prijavaService.save(nova);
            return ResponseEntity.ok(Map.of("message", "Prijava uspešno dodata."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Takmičar je već prijavljen."));
        }
    }

    @DeleteMapping("/prijave/{id}")
    public ResponseEntity<?> deletePrijava(@PathVariable int id) {
        Prijava p = prijavaService.getPrijava(id);
        if (p == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Prijava nije pronađena."));

        prijavaService.delete(p);
        return ResponseEntity.ok(Map.of("message", "Prijava obrisana."));
    }


    @GetMapping("/izvestaji/prijavljeniTakmicari/{takmicenjeId}")
    public ResponseEntity<byte[]> prijavljeniTakmicari(@PathVariable int takmicenjeId) {
        try {
            Takmicenje t = takmicenjeService.getTakmicenje(takmicenjeId);
            JasperPrint print = adminService.kreirajIzvestajPrijavljenih(t);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(print, out);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prijavljeniTakmicari.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(out.toByteArray());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/izvestaji/prosekPoDisciplinama/{takmicenjeId}")
    public ResponseEntity<byte[]> prosekDisciplina(@PathVariable int takmicenjeId) {
        try {
            Takmicenje t = takmicenjeService.getTakmicenje(takmicenjeId);
            JasperPrint print = adminService.kreirajIzvestajProsekPoDisciplinama(t);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(print, out);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=prosekPoDisciplinama.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(out.toByteArray());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
