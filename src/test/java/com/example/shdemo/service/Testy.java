package com.example.shdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


import com.example.shdemo.domain.Prawnik;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class Testy {

	@Autowired
	PrawnikManager prawnikManager;

	private final String IMIE = "Andrzej";

	private final int WIEK=12;
	private final String NAZWISKO = "Miły";
	
//-------------------DODAWANIE PRAWNIKA---------------------------
	@Test
	public void addPrawnikCheck() {


		Prawnik prawnik = new Prawnik();
		prawnik.setImie(IMIE);
		prawnik.setNazwisko(NAZWISKO);
		prawnik.setWiek(WIEK);
		
		prawnikManager.addPrawnik(prawnik);
		
		List<Prawnik> praw = prawnikManager.getAllPrawnik();
		for (Prawnik prawnika : praw) {
			if (prawnika.getId().equals(prawnik.getId())) {
				assertEquals(IMIE,prawnika.getImie());
				assertEquals(NAZWISKO,prawnika.getNazwisko());
				assertEquals(WIEK,prawnika.getWiek());
			}
		}
	}
//-------------------USUWANIE PRAWNIKA PO ID----------------------
	@Test
	public void deletePrawnikCheck() {
		
		
		Prawnik prawnik = new Prawnik();
		prawnik.setImie(IMIE);
		prawnik.setNazwisko(NAZWISKO);
		prawnik.setWiek(WIEK);
		
		prawnikManager.addPrawnik(prawnik);

		prawnikManager.deletePrawnik(prawnik);
		
	
		
		assertNull(prawnikManager.findPrawnikById(prawnik.getId()));
		
		List<Prawnik> praw = prawnikManager.getAllPrawnik();
		assertEquals(0, praw.size());
//----------------------------------------------------------------------------------	
		
		Prawnik prawnik1 = new Prawnik();
		prawnik1.setImie(IMIE);
		prawnik1.setNazwisko(NAZWISKO);
		prawnik1.setWiek(WIEK);
		
		prawnikManager.addPrawnik(prawnik1);
		
		Prawnik prawnik2 = new Prawnik();
		prawnik2.setImie(IMIE);
		prawnik2.setNazwisko(NAZWISKO);
		prawnik2.setWiek(WIEK);
		
		prawnikManager.addPrawnik(prawnik2);
		
		List<Prawnik> praw1 = prawnikManager.getAllPrawnik();
		
		assertEquals(2, praw1.size());
		
		prawnikManager.deletePrawnik(prawnik1);
		assertNull(prawnikManager.findPrawnikById(prawnik1.getId()));
		
		List<Prawnik> praw2 = prawnikManager.getAllPrawnik();
		assertEquals(1, praw2.size());
		}
//-------------------SZUKANIE JEDNEGO PRAWNIKA PO ID--------------
	@Test
	public void findOnePrawnikCheck() {
		
		Prawnik prawnik = new Prawnik();
		prawnik.setImie(IMIE);
		prawnik.setNazwisko(NAZWISKO);
		prawnik.setWiek(WIEK);
		
		prawnikManager.addPrawnik(prawnik);
		
		assertNotNull(prawnikManager.findPrawnikById(prawnik.getId()));		
	}
//-------------------SZUKANIE WSZYSTKICH PRAWNIKÓW----------------
	@Test
	public void findAllPrawnikCheck() {
		
		Prawnik prawnik = new Prawnik();
		prawnik.setImie(IMIE);
		prawnik.setNazwisko(NAZWISKO);
		prawnik.setWiek(WIEK);
		
		prawnikManager.addPrawnik(prawnik);
		
		List<Prawnik> praw = prawnikManager.getAllPrawnik();
		
		assertNotNull(praw);
		assertEquals(1, praw.size());
		
	}
//-------------------EDYCJA PRAWNIKA------------------------------
	@Test
	public void editPrawnikCheck() {
		
		Prawnik prawnik = new Prawnik();
		prawnik.setImie(IMIE);
		prawnik.setNazwisko(NAZWISKO);
		prawnik.setWiek(WIEK);
		
		prawnikManager.addPrawnik(prawnik);
		
		List<Prawnik> praw = prawnikManager.getAllPrawnik();
		for (Prawnik prawnika : praw) {
			if (prawnika.getId().equals(prawnik.getId())) {
				assertEquals(IMIE,prawnika.getImie());
				assertEquals(NAZWISKO,prawnika.getNazwisko());
				assertEquals(WIEK,prawnika.getWiek());
			}
		}
		
		prawnik.setImie(NAZWISKO);
		prawnik.setNazwisko(IMIE);
		prawnikManager.editPrawnik(prawnik);
		
		List<Prawnik> praw1 = prawnikManager.getAllPrawnik();
		for (Prawnik prawnika : praw1) {
			if (prawnika.getId().equals(prawnik.getId())) {
				assertEquals(NAZWISKO,prawnika.getImie());
				assertEquals(IMIE,prawnika.getNazwisko());
				assertEquals(WIEK,prawnika.getWiek());
			}
		}
		
		
	}
	
	}

	


