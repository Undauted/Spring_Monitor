package com.example.shdemo.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Firma;
import com.example.shdemo.domain.Monitor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class Testy {

	@Autowired
	MonitorManager monitorManager;

	private final String NAZWA = "Dell U2412M";
	private final String RODZAJ = "LED";
	private final int PRZEKATNA=22;
	private final int WAGA=3;
	
	private final String MARKA = "SAMSUNG";
	private final int REGON=123456;
	private final String SZEF = "KOWALSKI";


	
public int baza()
{
	int liczba = 0;
	for(int i=0;i<5;i++)
	{
	Monitor monitor = new Monitor();
	monitor.setNazwa(NAZWA+i);
	monitor.setRodzaj(RODZAJ+i);
	monitor.setPrzekatna(PRZEKATNA+i);
	monitor.setWaga(WAGA+i);
	
	monitorManager.addMonitor(monitor);
	
	}
	
	List<Monitor> monit = monitorManager.getAllMonitor();
	liczba=monit.size();
	
	return liczba;
}

public int baza1()
{
	int liczba = 0;
	for(int j=0;j<5;j++)
	{
	List<Monitor> monitory = new ArrayList<Monitor>();
	for(int i=0;i<5;i++)
	{
		Monitor monitor = new Monitor();
		monitor.setNazwa(NAZWA+i);
		monitor.setRodzaj(RODZAJ+i);
		monitor.setPrzekatna(PRZEKATNA+i);
		monitor.setWaga(WAGA+i);
		
		monitorManager.addMonitor(monitor);
	
	
	monitory.add(monitor);
	
	}
	
	Firma firma = new Firma();
	firma.setMarka(MARKA+j);
	firma.setRegon(REGON+j);
	firma.setSzef(SZEF+j);
	firma.setMonitor(monitory);
	
	monitorManager.addFirma(firma);	
	
	}
	
	List<Firma> firm = monitorManager.getAllFirma();
	liczba=firm.size();
	return liczba;
}
	
	
//-------------------DODAWANIE MONITORA---------------------------
	@Test
	public void addMonitorCheck() {

		int liczba = baza();
		
		List<Monitor> monit1 = monitorManager.getAllMonitor();
		assertEquals(liczba, monit1.size());
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(NAZWA);
		monitor.setRodzaj(RODZAJ);
		monitor.setPrzekatna(PRZEKATNA);
		monitor.setWaga(WAGA);
		
		monitorManager.addMonitor(monitor);
		
		
		
		List<Monitor> monit = monitorManager.getAllMonitor();
		for (Monitor monitory : monit) {
			if (monitory.getId().equals(monitor.getId())) {
				assertEquals(NAZWA,monitory.getNazwa());
				assertEquals(RODZAJ,monitory.getRodzaj());
				assertEquals(PRZEKATNA,monitory.getPrzekatna());
				assertEquals(WAGA,monitory.getWaga());
				
			}
		}
		
		List<Monitor> monit2 = monitorManager.getAllMonitor();
		assertEquals(liczba+1, monit2.size());
	}
//-------------------USUWANIE MONITORA PO ID----------------------
	@Test
	public void deleteMonitorCheck() {
		
		int liczba = baza();
		Monitor monitor = new Monitor();
		monitor.setNazwa(NAZWA);
		monitor.setRodzaj(RODZAJ);
		monitor.setPrzekatna(PRZEKATNA);
		monitor.setWaga(WAGA);
		
		List<Monitor> monit = monitorManager.getAllMonitor();
		assertEquals(liczba, monit.size());
		
		monitorManager.addMonitor(monitor);
		
		List<Monitor> monit2 = monitorManager.getAllMonitor();
		assertEquals(liczba+1, monit2.size());
		
		monitorManager.deleteMonitor(monitor);
		
		assertNull(monitorManager.findMonitorById(monitor.getId()));
		
		List<Monitor> monit3 = monitorManager.getAllMonitor();
		assertEquals(liczba, monit3.size());
//----------------------------------------------------------------------------------	
		
		Monitor monitor1 = new Monitor();
		monitor1.setNazwa(NAZWA);
		monitor1.setRodzaj(RODZAJ);
		monitor1.setPrzekatna(PRZEKATNA);
		monitor1.setWaga(WAGA);
		
		monitorManager.addMonitor(monitor1);
		
		Monitor monitor2 = new Monitor();
		monitor2.setNazwa(NAZWA);
		monitor2.setRodzaj(RODZAJ);
		monitor2.setPrzekatna(PRZEKATNA);
		monitor2.setWaga(WAGA);
		
		monitorManager.addMonitor(monitor2);
		
		List<Monitor> monit4 = monitorManager.getAllMonitor();
		assertEquals(liczba+2, monit4.size());
		
		monitorManager.deleteMonitor(monitor1);
		
		assertNull(monitorManager.findMonitorById(monitor1.getId()));
		assertNotNull(monitorManager.findMonitorById(monitor2.getId()));
		
		List<Monitor> monit5 = monitorManager.getAllMonitor();
		assertEquals(liczba+1, monit5.size());
		}
//-------------------SZUKANIE JEDNEGO MONITORA PO ID--------------
	@Test
	public void findOneMonitorCheck() {
		int liczba = baza();;
		Monitor monitor = new Monitor();
		monitor.setNazwa(NAZWA);
		monitor.setRodzaj(RODZAJ);
		monitor.setPrzekatna(PRZEKATNA);
		monitor.setWaga(WAGA);
		
		monitorManager.addMonitor(monitor);
		
		List<Monitor> monit1 = monitorManager.getAllMonitor();
		assertEquals(liczba+1, monit1.size());
		
		assertNotNull(monitorManager.findMonitorById(monitor.getId()));	
		
		monitorManager.deleteMonitor(monitor);
		
		assertNull(monitorManager.findMonitorById(monitor.getId()));
		
		List<Monitor> monit2 = monitorManager.getAllMonitor();
		assertEquals(liczba, monit2.size());
	}
//-------------------SZUKANIE WSZYSTKICH MONITORÓW----------------
	@Test
	public void findAllMonitorCheck() {
		
		int liczba = baza();
		
		List<Monitor> monit = monitorManager.getAllMonitor();
		
		assertEquals(liczba, monit.size());
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(NAZWA);
		monitor.setRodzaj(RODZAJ);
		monitor.setPrzekatna(PRZEKATNA);
		monitor.setWaga(WAGA);
		
		monitorManager.addMonitor(monitor);
		
		List<Monitor> monit1 = monitorManager.getAllMonitor();
		
		assertNotNull(monit1);
		assertEquals(liczba+1, monit1.size());
		
	}
//-------------------EDYCJA MONITORA------------------------------
	@Test
	public void editMonitorCheck() {
		
		int liczba = baza();
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(NAZWA);
		monitor.setRodzaj(RODZAJ);
		monitor.setPrzekatna(PRZEKATNA);
		monitor.setWaga(WAGA);
		
		monitorManager.addMonitor(monitor);
		
		List<Monitor> monit1 = monitorManager.getAllMonitor();
		assertEquals(liczba+1, monit1.size());
		
		Monitor monitor1 = new Monitor();
		monitor1.setNazwa(NAZWA);
		monitor1.setRodzaj(RODZAJ);
		monitor1.setPrzekatna(PRZEKATNA);
		monitor1.setWaga(WAGA);
		
		monitorManager.addMonitor(monitor1);
		
		List<Monitor> monit2 = monitorManager.getAllMonitor();
		assertEquals(liczba+2, monit2.size());
		
		List<Monitor> monit3 = monitorManager.getAllMonitor();

		for (Monitor monitory : monit3) {
			if (monitory.getId().equals(monitor.getId())) {
				assertEquals(NAZWA,monitory.getNazwa());
				assertEquals(RODZAJ,monitory.getRodzaj());
				assertEquals(PRZEKATNA,monitory.getPrzekatna());
				assertEquals(WAGA,monitory.getWaga());
			}
		}
		
		monitor.setNazwa(RODZAJ);
		monitor.setRodzaj(NAZWA);
		monitorManager.editMonitor(monitor);
		
		List<Monitor> monit4 = monitorManager.getAllMonitor();
		for (Monitor monitory : monit4) {
			if (monitory.getId().equals(monitor.getId())) {
				assertEquals(RODZAJ,monitory.getNazwa());
				assertEquals(NAZWA,monitory.getRodzaj());
				assertEquals(PRZEKATNA,monitory.getPrzekatna());
				assertEquals(WAGA,monitory.getWaga());
			}
		}
		
		assertNotSame(RODZAJ,monitor1.getNazwa());
		assertNotSame(NAZWA,monitor1.getRodzaj());
		
		List<Monitor> monit5 = monitorManager.getAllMonitor();
		assertEquals(liczba+2, monit5.size());
	}
	
	
//-------------------SZUKANIE MONITORA PO RODZAJU-----------------
	@Test
	public void findOneMonitorbyRodzajCheck() {
		int liczba = baza();
		
		List<Monitor> monit = monitorManager.getAllMonitor();
		assertEquals(liczba, monit.size());
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(NAZWA);
		monitor.setRodzaj(RODZAJ);
		monitor.setPrzekatna(PRZEKATNA);
		monitor.setWaga(WAGA);
		
		monitorManager.addMonitor(monitor);
		
		List<Monitor> monit1 = monitorManager.getAllMonitor();
		assertEquals(liczba+1, monit1.size());
		
		assertNotNull(monitorManager.findMonitorByRodzaj(monitor.getRodzaj()));	
		
		List<Monitor> monit2 = monitorManager.getAllMonitor();
		assertEquals(liczba+1, monit2.size());
	}

//-------------------DODAWANIE FIRMY------------------------------
	
		@Test
		public void addFirmaCheck() {

			int liczba = baza1();
			int liczba2 = baza();
			
			
			List<Firma> firma3 = monitorManager.getAllFirma();
			assertEquals(liczba, firma3.size());
			
			List<Monitor> monit = monitorManager.getAllMonitor();
			assertEquals(liczba2, monit.size());
			
			List<Monitor> monitory = new ArrayList<Monitor>();
			
			Monitor monitor = new Monitor();
			monitor.setNazwa(NAZWA);
			monitor.setRodzaj(RODZAJ);
			monitor.setPrzekatna(PRZEKATNA);
			monitor.setWaga(WAGA);
			
			monitorManager.addMonitor(monitor);
			
			
			Monitor monitor1 = new Monitor();
			monitor1.setNazwa(NAZWA);
			monitor1.setRodzaj(RODZAJ);
			monitor1.setPrzekatna(PRZEKATNA);
			monitor1.setWaga(WAGA);
			
			monitorManager.addMonitor(monitor1);
			
			List<Monitor> monit1 = monitorManager.getAllMonitor();
			assertEquals(liczba2+2, monit1.size());
			
			monitory.add(monitor);
			monitory.add(monitor1);
			
			List<Firma> firm1 = monitorManager.getAllFirma();
			int liczba1=firm1.size();
			assertEquals(liczba, liczba1);
			
			Firma firma = new Firma();
			firma.setMarka(MARKA);
			firma.setRegon(REGON);
			firma.setSzef(SZEF);
			firma.setMonitor(monitory);
			
			
			monitorManager.addFirma(firma);
			
			List<Firma> firma4 = monitorManager.getAllFirma();
			assertEquals(liczba+1, firma4.size());
			
			List<Firma> firm = monitorManager.getAllFirma();
			for (Firma firmy : firm) {
				if (firmy.getId().equals(firma.getId())) {
					assertEquals(MARKA,firmy.getMarka());
					assertEquals(REGON,firmy.getRegon());
					assertEquals(SZEF,firmy.getSzef());
					assertEquals(monitory,firma.getMonitor());
					
					
				}
			}
			
			List<Firma> firma2 = monitorManager.getAllFirma();
			assertEquals(liczba+1, firma2.size());
		}
		
//-------------------USUWANIE FIRMY PO ID--------------------------
		
		@Test
		public void deleteFirmaCheck() {
			
			int liczba = baza1();
			int liczba1 = baza();
			
			List<Monitor> monit = monitorManager.getAllMonitor();
			assertEquals(liczba1, monit.size());
			
			List<Monitor> monitory = new ArrayList<Monitor>();
			
			Monitor monitor = new Monitor();
			monitor.setNazwa(NAZWA);
			monitor.setRodzaj(RODZAJ);
			monitor.setPrzekatna(PRZEKATNA);
			monitor.setWaga(WAGA);
			
			monitorManager.addMonitor(monitor);
			
			
			Monitor monitor1 = new Monitor();
			monitor1.setNazwa(NAZWA);
			monitor1.setRodzaj(RODZAJ);
			monitor1.setPrzekatna(PRZEKATNA);
			monitor1.setWaga(WAGA);
			
			monitorManager.addMonitor(monitor1);
			
			List<Monitor> monit1 = monitorManager.getAllMonitor();
			assertEquals(liczba1+2, monit1.size());
			
			monitory.add(monitor);
			monitory.add(monitor1);
			
			Firma firma = new Firma();
			firma.setMarka(MARKA);
			firma.setRegon(REGON);
			firma.setSzef(SZEF);
			firma.setMonitor(monitory);
			
			
			
			List<Firma> firm = monitorManager.getAllFirma();
			assertEquals(liczba, firm.size());
			
			monitorManager.addFirma(firma);
			
			assertNotNull(monitorManager.findFirmaById(firma.getId()));
			assertNotNull(monitorManager.findMonitorById(monitor.getId()));
			assertNotNull(monitorManager.findMonitorById(monitor1.getId()));
			
			monitorManager.deleteFirma(firma);
			
			assertNull(monitorManager.findFirmaById(firma.getId()));
			
			List<Firma> firm2 = monitorManager.getAllFirma();
			assertEquals(liczba, firm2.size());
			
			assertNull(monitorManager.findMonitorById(monitor.getId()));
			assertNull(monitorManager.findMonitorById(monitor1.getId()));


			
		}

		

//-------------------SZUKANIE FIRMY PO ID--------------------------
		
		@Test
		public void findOneFirmaCheck() {
			int liczba = baza1();
			int liczba1 = baza();
			
			List<Firma> firm2 = monitorManager.getAllFirma();
			assertEquals(liczba, firm2.size());
			
			List<Monitor> monit = monitorManager.getAllMonitor();
			assertEquals(liczba1, monit.size());
			
			List<Monitor> monitory = new ArrayList<Monitor>();
			
			Monitor monitor = new Monitor();
			monitor.setNazwa(NAZWA);
			monitor.setRodzaj(RODZAJ);
			monitor.setPrzekatna(PRZEKATNA);
			monitor.setWaga(WAGA);
			
			monitorManager.addMonitor(monitor);
			
			
			Monitor monitor1 = new Monitor();
			monitor1.setNazwa(NAZWA);
			monitor1.setRodzaj(RODZAJ);
			monitor1.setPrzekatna(PRZEKATNA);
			monitor1.setWaga(WAGA);
			
			monitorManager.addMonitor(monitor1);
			
			List<Monitor> monit1 = monitorManager.getAllMonitor();
			assertEquals(liczba1+2, monit1.size());
			
			monitory.add(monitor);
			monitory.add(monitor1);
			
			Firma firma = new Firma();
			firma.setMarka(MARKA);
			firma.setRegon(REGON);
			firma.setSzef(SZEF);
			firma.setMonitor(monitory);
			monitorManager.addFirma(firma);
			
			List<Firma> firm3 = monitorManager.getAllFirma();
			assertEquals(liczba+1, firm3.size());
			
			assertNotNull(monitorManager.findFirmaById(firma.getId()));	
			assertNotNull(monitorManager.findMonitorById(monitor.getId()));
			assertNotNull(monitorManager.findMonitorById(monitor1.getId()));
			
			monitorManager.deleteFirma(firma);
			
			assertNull(monitorManager.findFirmaById(firma.getId()));
			assertNull(monitorManager.findMonitorById(monitor.getId()));
			assertNull(monitorManager.findMonitorById(monitor1.getId()));
			
			List<Firma>firm1 = monitorManager.getAllFirma();
			assertEquals(liczba, firm1.size());
			
			List<Monitor> monit2 = monitorManager.getAllMonitor();
			assertEquals(liczba1, monit2.size());
		}
//-------------------SZUKANIE WSZYSTKICH FIRM----------------------
		
		@Test
		public void findAllFirmaCheck() {
			
			int liczba = baza1();
			int liczba1 = baza();
			
			List<Monitor> monitory = new ArrayList<Monitor>();
			
			Monitor monitor = new Monitor();
			monitor.setNazwa(NAZWA);
			monitor.setRodzaj(RODZAJ);
			monitor.setPrzekatna(PRZEKATNA);
			monitor.setWaga(WAGA);
			
			monitorManager.addMonitor(monitor);

			monitory.add(monitor);
			
			Firma firma = new Firma();
			firma.setMarka(MARKA);
			firma.setRegon(REGON);
			firma.setSzef(SZEF);
			firma.setMonitor(monitory);
			
			monitorManager.addFirma(firma);
			
			List<Firma> firm = monitorManager.getAllFirma();
			assertNotNull(firm);
			assertEquals(liczba+1, firm.size());
			
			List<Monitor> monit = monitorManager.getAllMonitor();
			assertNotNull(monit);
			assertEquals(liczba1+1, monit.size());
			
		}
//-------------------EDYCJA FIRMY----------------------------------
		@Test
		public void editFirmaCheck() {
			
			int liczba = baza1();
			int liczba1 = baza();
			
			List<Monitor> monit = monitorManager.getAllMonitor();
			assertEquals(liczba1, monit.size());
			
			List<Monitor> monitory = new ArrayList<Monitor>();
			
			Monitor monitor = new Monitor();
			monitor.setNazwa(NAZWA);
			monitor.setRodzaj(RODZAJ);
			monitor.setPrzekatna(PRZEKATNA);
			monitor.setWaga(WAGA);
			
			monitorManager.addMonitor(monitor);
			
			monitory.add(monitor);
			
			List<Monitor> monit1 = monitorManager.getAllMonitor();
			assertEquals(liczba1+1, monit1.size());
			
			Firma firma = new Firma();
			firma.setMarka(MARKA);
			firma.setRegon(REGON);
			firma.setSzef(SZEF);
			firma.setMonitor(monitory);
			monitorManager.addFirma(firma);
			
			
			
			Firma firma1 = new Firma();
			firma1.setMarka(MARKA);
			firma1.setRegon(REGON);
			firma1.setSzef(SZEF);
			firma.setMonitor(monitory);
			monitorManager.addFirma(firma1);
			
			List<Firma> firm1 = monitorManager.getAllFirma();
			assertEquals(liczba+2, firm1.size());
			
			List<Firma> firm2 = monitorManager.getAllFirma();

			for (Firma firmy : firm2) {
				if (firmy.getId().equals(firma.getId())) {
					assertEquals(MARKA,firmy.getMarka());
					assertEquals(REGON,firmy.getRegon());
					assertEquals(SZEF,firmy.getSzef());
					assertEquals(monitory,firmy.getMonitor());
					
				}
			}
			
			firma.setMarka(SZEF);
			firma.setSzef(MARKA);
			monitorManager.editFirma(firma);
			
			List<Firma> firm3 = monitorManager.getAllFirma();
			for (Firma firmy : firm3) {
				if (firmy.getId().equals(firma.getId())) {
					assertEquals(SZEF,firmy.getMarka());
					assertEquals(REGON,firmy.getRegon());
					assertEquals(MARKA,firmy.getSzef());
					assertEquals(monitory,firmy.getMonitor());
				}
			}
			
			assertNotSame(SZEF,firma1.getMarka());
			assertNotSame(MARKA,firma1.getSzef());
			
			List<Firma> firm4 = monitorManager.getAllFirma();
			assertEquals(liczba+2, firm4.size());
			
			List<Monitor> monit2 = monitorManager.getAllMonitor();
			assertEquals(liczba1+1, monit2.size());
		}
//-------------------SZUKANIE FIRMY PO MARCE-----------------------
		@Test
		public void findOneFirmabyMarkaCheck() {
			int liczba = baza1();
			int liczba1 = baza();
			
			List<Monitor> monit = monitorManager.getAllMonitor();
			assertEquals(liczba1, monit.size());
			
			List<Monitor> monitory = new ArrayList<Monitor>();
			
			Monitor monitor = new Monitor();
			monitor.setNazwa(NAZWA);
			monitor.setRodzaj(RODZAJ);
			monitor.setPrzekatna(PRZEKATNA);
			monitor.setWaga(WAGA);
			
			monitorManager.addMonitor(monitor);
			
			monitory.add(monitor);
			
			List<Monitor> monit1 = monitorManager.getAllMonitor();
			assertEquals(liczba1+1, monit1.size());
			
			Firma firma = new Firma();
			firma.setMarka(MARKA);
			firma.setRegon(REGON);
			firma.setSzef(SZEF);
			firma.setMonitor(monitory);
			
			monitorManager.addFirma(firma);
			
			
			assertNotNull(monitorManager.findFirmaByMarka(firma.getMarka()));	
			
			List<Firma> firm1 = monitorManager.getAllFirma();
			assertEquals(liczba+1, firm1.size());
		}
//-----------------SZUKANIE MONITORÓW PO FIRMIE--------------------
		@Test
		public void findMonitorbyFirma()
		{
			int liczba = baza1();
			int liczba1 = baza();
			
			List<Monitor> monit = monitorManager.getAllMonitor();
			assertEquals(liczba1, monit.size());
			
			List<Firma> firm = monitorManager.getAllFirma();
			assertEquals(liczba, firm.size());
			
			List<Monitor> monitory = new ArrayList<Monitor>();
			
			Monitor monitor = new Monitor();
			monitor.setNazwa(NAZWA);
			monitor.setRodzaj(RODZAJ);
			monitor.setPrzekatna(PRZEKATNA);
			monitor.setWaga(WAGA);
			
			monitorManager.addMonitor(monitor);
			
			Monitor monitor1 = new Monitor();
			monitor1.setNazwa(NAZWA);
			monitor1.setRodzaj(RODZAJ);
			monitor1.setPrzekatna(PRZEKATNA);
			monitor1.setWaga(WAGA);
			
			monitorManager.addMonitor(monitor1);
			
			List<Monitor> monit1 = monitorManager.getAllMonitor();
			assertEquals(liczba1+2, monit1.size());
			
			monitory.add(monitor);
			monitory.add(monitor1);
			
			Firma firma = new Firma();
			firma.setMarka(MARKA);
			firma.setRegon(REGON);
			firma.setSzef(SZEF);
			firma.setMonitor(monitory);
			
			monitorManager.addFirma(firma);
			
			List<Monitor> pobranie = monitorManager.getOwnedMonitor(firma);
			
			
			assertEquals(pobranie.get(0),monitorManager.findMonitorById(monitor.getId()));
			assertEquals(pobranie.get(1),monitorManager.findMonitorById(monitor1.getId()));
			
			assertEquals(pobranie.size(),2);
			
			List<Firma> firm1 = monitorManager.getAllFirma();
			assertEquals(liczba+1, firm1.size());
		}
}

	


