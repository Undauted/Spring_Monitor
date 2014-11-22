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
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class Testy {

	@Autowired
	MonitorManager monitorManager;

	private final String NAZWA = "Dell U2412M";
	private final String RODZAJ = "LED";
	private final int PRZEKATNA=22;
	private final int WAGA=3;
	
	
//-------------------DODAWANIE MONITORA---------------------------
	@Test
	public void addMonitorCheck() {


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
	}
//-------------------USUWANIE MONITORA PO ID----------------------
	@Test
	public void deleteMonitorCheck() {
		
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(NAZWA);
		monitor.setRodzaj(RODZAJ);
		monitor.setPrzekatna(PRZEKATNA);
		monitor.setWaga(WAGA);
		
		monitorManager.addMonitor(monitor);

		monitorManager.deleteMonitor(monitor);
		
		assertNull(monitorManager.findMonitorById(monitor.getId()));
		
		List<Monitor> praw = monitorManager.getAllMonitor();
		assertEquals(0, praw.size());
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
		
		List<Monitor> monit1 = monitorManager.getAllMonitor();
		
		assertEquals(2, monit1.size());
		
		monitorManager.deleteMonitor(monitor1);
		assertNull(monitorManager.findMonitorById(monitor1.getId()));
		
		List<Monitor> praw2 = monitorManager.getAllMonitor();
		assertEquals(1, praw2.size());
		}
//-------------------SZUKANIE JEDNEGO MONITORA PO ID--------------
	@Test
	public void findOneMonitorCheck() {
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(NAZWA);
		monitor.setRodzaj(RODZAJ);
		monitor.setPrzekatna(PRZEKATNA);
		monitor.setWaga(WAGA);
		
		monitorManager.addMonitor(monitor);
		
		assertNotNull(monitorManager.findMonitorById(monitor.getId()));	
		
		monitorManager.deleteMonitor(monitor);
		
		assertNull(monitorManager.findMonitorById(monitor.getId()));
	}
//-------------------SZUKANIE WSZYSTKICH MONITORÓW----------------
	@Test
	public void findAllMonitorCheck() {
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(NAZWA);
		monitor.setRodzaj(RODZAJ);
		monitor.setPrzekatna(PRZEKATNA);
		monitor.setWaga(WAGA);
		
		monitorManager.addMonitor(monitor);
		
		List<Monitor> monit = monitorManager.getAllMonitor();
		
		assertNotNull(monit);
		assertEquals(1, monit.size());
		
	}
//-------------------EDYCJA MONITORA------------------------------
	@Test
	public void editMonitorCheck() {
		
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
		
		List<Monitor> monit = monitorManager.getAllMonitor();
		assertEquals(2, monit.size());
		
		for (Monitor monitory : monit) {
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
		
		List<Monitor> monit1 = monitorManager.getAllMonitor();
		for (Monitor monitory : monit1) {
			if (monitory.getId().equals(monitor.getId())) {
				assertEquals(RODZAJ,monitory.getNazwa());
				assertEquals(NAZWA,monitory.getRodzaj());
				assertEquals(PRZEKATNA,monitory.getPrzekatna());
				assertEquals(WAGA,monitory.getWaga());
			}
		}
		
		assertNotSame(RODZAJ,monitor1.getNazwa());
		assertNotSame(NAZWA,monitor1.getRodzaj());
		
		
	}
	
	}

	


