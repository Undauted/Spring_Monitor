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


import com.example.shdemo.domain.Monitor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class Testy {

	@Autowired
	MonitorManager monitorManager;

	private final String NAZWA = "Dell U2412M";
	private final String RODZAJ = "LED";
	private final int PRZEKATNA=22;
	private final int WAGA=3;
	static int Liczby=0;
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

		
		monitorManager.deleteMonitor(monitor);
		
		assertNull(monitorManager.findMonitorById(monitor.getId()));
		
		List<Monitor> monit2 = monitorManager.getAllMonitor();
		assertEquals(liczba, monit2.size());
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
		
		
		monitorManager.deleteMonitor(monitor1);
		assertNull(monitorManager.findMonitorById(monitor1.getId()));
		
		List<Monitor> monit4 = monitorManager.getAllMonitor();
		assertEquals(liczba+1, monit4.size());
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
		
		assertNotNull(monitorManager.findMonitorById(monitor.getId()));	
		
		monitorManager.deleteMonitor(monitor);
		
		assertNull(monitorManager.findMonitorById(monitor.getId()));
		
		List<Monitor> monit1 = monitorManager.getAllMonitor();
		assertEquals(liczba, monit1.size());
	}
//-------------------SZUKANIE WSZYSTKICH MONITORÓW----------------
	@Test
	public void findAllMonitorCheck() {
		
		int liczba = baza();
		Monitor monitor = new Monitor();
		monitor.setNazwa(NAZWA);
		monitor.setRodzaj(RODZAJ);
		monitor.setPrzekatna(PRZEKATNA);
		monitor.setWaga(WAGA);
		
		monitorManager.addMonitor(monitor);
		
		List<Monitor> monit = monitorManager.getAllMonitor();
		
		assertNotNull(monit);
		assertEquals(liczba+1, monit.size());
		
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

		for (Monitor monitory : monit2) {
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
		
		List<Monitor> monit3 = monitorManager.getAllMonitor();
		for (Monitor monitory : monit3) {
			if (monitory.getId().equals(monitor.getId())) {
				assertEquals(RODZAJ,monitory.getNazwa());
				assertEquals(NAZWA,monitory.getRodzaj());
				assertEquals(PRZEKATNA,monitory.getPrzekatna());
				assertEquals(WAGA,monitory.getWaga());
			}
		}
		
		assertNotSame(RODZAJ,monitor1.getNazwa());
		assertNotSame(NAZWA,monitor1.getRodzaj());
		
		List<Monitor> monit4 = monitorManager.getAllMonitor();
		assertEquals(liczba+2, monit4.size());
	}
	
	
//-------------------SZUKANIE JEDNEGO MONITORA PO ID--------------
	@Test
	public void findOneMonitorbyRodzajCheck() {
		int liczba = baza();;
		Monitor monitor = new Monitor();
		monitor.setNazwa(NAZWA);
		monitor.setRodzaj(RODZAJ);
		monitor.setPrzekatna(PRZEKATNA);
		monitor.setWaga(WAGA);
		
		monitorManager.addMonitor(monitor);
		
		assertNotNull(monitorManager.findMonitorByRodzaj(monitor.getRodzaj()));	
		
		List<Monitor> monit1 = monitorManager.getAllMonitor();
		assertEquals(liczba+1, monit1.size());
	}
	
}
	


