package com.example.shdemo.service;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Firma;
import com.example.shdemo.domain.Monitor;

@Component
@Transactional
public class MonitorManagerHibernat implements MonitorManager {

	@Autowired
	private SessionFactory session;

	public SessionFactory getSessionFactory() {
		return session;
	}

	public void setSessionFactory(SessionFactory session) {
		this.session = session;
	}
//---------------DODAWANIE---------------------------	
	@Override
	public void addMonitor(Monitor monitor) {
		monitor.setId(null);
		session.getCurrentSession().persist(monitor);
	}
	
	@Override
	public void addFirma(Firma firma) {
		firma.setId(null);
		session.getCurrentSession().persist(firma);
	}
//---------------USUWANIE----------------------------	
	@Override
	public void deleteMonitor(Monitor monitor) {
		monitor = (Monitor) session.getCurrentSession().get(Monitor.class,
				monitor.getId());
		
		
		session.getCurrentSession().delete(monitor);
	}

	@Override
	public void deleteFirma(Firma firma) {
		firma = (Firma) session.getCurrentSession().get(Firma.class,
				firma.getId());
		
		for(Monitor monitor : firma.getMonitor())
		{
		session.getCurrentSession().delete(monitor);
		}
		
		session.getCurrentSession().delete(firma);
	}
//--------------POBIERANIE WSZYSTKICH----------------
	@Override
	@SuppressWarnings("unchecked")
	public List<Monitor> getAllMonitor() {
		return session.getCurrentSession().getNamedQuery("monitor.all")
				.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Firma> getAllFirma() {
		return session.getCurrentSession().getNamedQuery("firma.all")
				.list();
	}
//-------------POBIERANIE PO ID----------------------
	@Override
	public Monitor findMonitorById(Long id) {
		return (Monitor) session.getCurrentSession().getNamedQuery("monitor.byId").setLong("id", id).uniqueResult();
	}
	
	@Override
	public Firma findFirmaById(Long id) {
		return (Firma) session.getCurrentSession().getNamedQuery("firma.byId").setLong("id", id).uniqueResult();
	}
//------------POBIERANIE PO CZYMŚ--------------------	
	@Override
	@SuppressWarnings("unchecked")
	public List<Monitor> findMonitorByRodzaj(String rodzaj) {
		return session.getCurrentSession().getNamedQuery("monitor.byRodzaj").setString("rodzaj", rodzaj).list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Firma> findFirmaByMarka(String marka) {
		return session.getCurrentSession().getNamedQuery("firma.byMarka").setString("marka", marka).list();
	}
//-----------EDYCJA---------------------------------
	public void editMonitor(Monitor monitor)
	{
		if(monitor.getId() != null)
		{
			session.getCurrentSession().persist(monitor);
		}
	}
	
	public void editFirma(Firma firma)
	{
		if(firma.getId() != null)
		{
			session.getCurrentSession().persist(firma);
		}
	}
//---------------POBIERANIE MONITORÓW DANEJ FIRMY---------------------
	public List<Monitor> getOwnedMonitor(Firma firma)
	{
		firma = (Firma) session.getCurrentSession().get(Firma.class,
				firma.getId());
		
		List<Monitor> monitor = new ArrayList<Monitor>(firma.getMonitor());
		return monitor;
	}
	

}

