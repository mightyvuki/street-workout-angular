package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.KorisnikRepository;


import model.Korisnik;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private KorisnikRepository kr;

    public CustomUserDetailsService(KorisnikRepository kr2) {
		this.kr = kr2;
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik k = kr.findByUsername(username);

        if (k == null) {
            throw new UsernameNotFoundException("Korisnik sa korisničkim imenom " + username + " nije pronađen!");
        }
        
        UserDetails ud = new CustomUserDetail(k);
        return ud;
    }
}