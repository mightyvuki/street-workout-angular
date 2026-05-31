package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.RezultatRepository;

import model.Korisnik;
import model.Takmicenje;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class AdminService {
	@Autowired
	KorisnikRepository kr;
	
	@Autowired
    RezultatRepository rr;


	public JasperPrint kreirajIzvestajPrijavljenih(Takmicenje t) throws IOException, JRException {
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(kr.prijavljeniNaTakmicenje(t.getId()));
		InputStream inputStream = this.getClass().getResourceAsStream("/reports/prijavljeniTakmicari.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imeTakmicenja", t.getNaziv());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		return jasperPrint;
	}
	
	

    public JasperPrint kreirajIzvestajProsekPoDisciplinama(Takmicenje t) throws IOException, JRException {
        List<Object[]> rawData = rr.findProsekPoDisciplinama(t.getId());
        List<Map<String, Object>> lista = new ArrayList<>();
        for (Object[] row : rawData) {
            Map<String, Object> map = new HashMap<>();
            map.put("disciplina", row[0]);
            map.put("prosek", row[1]);
            lista.add(map);
        }
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/prosekPoDisciplinama.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        Map<String, Object> params = new HashMap<>();
        params.put("imeTakmicenja", t.getNaziv());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        inputStream.close();
        return jasperPrint;
    }
}
