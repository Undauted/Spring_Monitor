package com.example.shdemo.service;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


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
	
	@Override
	public void addMonitor(Monitor monitor) {
		monitor.setId(null);
		session.getCurrentSession().persist(monitor);
	}
	
	@Override
	public void deleteMonitor(Monitor monitor) {
		monitor = (Monitor) session.getCurrentSession().get(Monitor.class,
				monitor.getId());
		
		
		session.getCurrentSession().delete(monitor);
	}

	

	@Override
	@SuppressWarnings("unchecked")
	public List<Monitor> getAllMonitor() {
		return session.getCurrentSession().getNamedQuery("monitor.all")
				.list();
	}

	@Override
	public Monitor findMonitorById(Long id) {
		return (Monitor) session.getCurrentSession().getNamedQuery("monitor.byId").setLong("id", id).uniqueResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Monitor> findMonitorByRodzaj(String rodzaj) {
		return session.getCurrentSession().getNamedQuery("monitor.byRodzaj").setString("rodzaj", rodzaj).list();
	}

	public void editMonitor(Monitor monitor)
	{
		if(monitor.getId() != null)
		{
			session.getCurrentSession().persist(monitor);
		}
	}
	

}

