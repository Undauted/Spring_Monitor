package com.example.shdemo.service;

import java.util.List;


import com.example.shdemo.domain.Monitor;
import com.example.shdemo.domain.Firma;

public interface MonitorManager {
	
	void addMonitor(Monitor monitor);
	
	List<Monitor> getAllMonitor();
	
	void deleteMonitor(Monitor monitor);
	
	Monitor findMonitorById(Long id);
	
	List<Monitor> findMonitorByRodzaj(String rodzaj);
	
	void editMonitor(Monitor monitor);
	
//------------------------------------------------------	
	
	void addFirma(Firma firma);
	List<Firma> getAllFirma();
	
	void deleteFirma(Firma firma);
	Firma findFirmaById(Long id);
	List<Firma> findFirmaByMarka(String marka);
	void editFirma(Firma firma);
//-----------------------------------------------------	
	List<Monitor> getOwnedMonitor(Firma firma);

	

}
