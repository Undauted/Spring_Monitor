package com.example.shdemo.service;

import java.util.List;


import com.example.shdemo.domain.Monitor;

public interface MonitorManager {
	
	void addMonitor(Monitor monitor);
	
	List<Monitor> getAllMonitor();
	
	void deleteMonitor(Monitor monitor);
	
	Monitor findMonitorById(Long id);
	
	List<Monitor> findMonitorByRodzaj(String rodzaj);
	
	void editMonitor(Monitor monitor);

}
