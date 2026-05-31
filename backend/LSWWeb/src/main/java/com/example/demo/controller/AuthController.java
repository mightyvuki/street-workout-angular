package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.KorisnikService;

import jakarta.validation.Valid;
import model.Korisnik;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private KorisnikService korisnikService;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid KorisnikDTO korisnik) {

        Map<String, String> response = new HashMap<>();

        if (korisnikService.postojiUsername(korisnik.getUsername())) {
            response.put("error", "Korisničko ime već postoji!");
            return ResponseEntity.badRequest().body(response);
        }

        if (korisnikService.postojiEmail(korisnik.getEmail())) {
            response.put("error", "Email već postoji!");
            return ResponseEntity.badRequest().body(response);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        korisnik.setPassword(passwordEncoder.encode(korisnik.getPassword()));

        korisnikService.register(korisnik);

        response.put("message", "Uspešno ste se registrovali!");
        return ResponseEntity.ok(response);
    }
    
    

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequest) {
        Map<String, Object> response = new HashMap<>();

        Korisnik korisnik = korisnikService.findByUsername(loginRequest.getUsername());
        if (korisnik == null) {
            response.put("error", "Ne postoji korisnik sa tim korisničkim imenom");
            return ResponseEntity.status(401).body(response);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(loginRequest.getPassword(), korisnik.getPassword())) {
            response.put("error", "Pogrešna lozinka");
            return ResponseEntity.status(401).body(response);
        }

        String token = jwtUtil.generateToken(korisnik.getUsername());

        Map<String, Object> userData = new HashMap<>();
        userData.put("id", korisnik.getId());
        userData.put("username", korisnik.getUsername());
        userData.put("uloga", korisnik.getUloga().getNaziv());

        response.put("user", userData);
        response.put("token", token); 
        response.put("message", "Uspešno ste se ulogovali");

        return ResponseEntity.ok(response);
    }
}
