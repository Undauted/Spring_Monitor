package com.example.shdemo.service;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.example.shdemo.domain.Prawnik;

@Component
@Transactional
public class PrawnikManagerHibernat implements PrawnikManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addPrawnik(Prawnik prawnik) {
		prawnik.setId(null);
		sessionFactory.getCurrentSession().persist(prawnik);
	}
	
	@Override
	public void deletePrawnik(Prawnik prawnik) {
		prawnik = (Prawnik) sessionFactory.getCurrentSession().get(Prawnik.class,
				prawnik.getId());
		
		
		sessionFactory.getCurrentSession().delete(prawnik);
	}

	

	@Override
	@SuppressWarnings("unchecked")
	public List<Prawnik> getAllPrawnik() {
		return sessionFactory.getCurrentSession().getNamedQuery("prawnik.all")
				.list();
	}

	@Override
	public Prawnik findPrawnikById(Long id) {
		return (Prawnik) sessionFactory.getCurrentSession().getNamedQuery("prawnik.byId").setLong("id", id).uniqueResult();
	}

	public void editPrawnik(Prawnik prawnik)
	{
		if(prawnik.getId() != null)
		{
		sessionFactory.getCurrentSession().persist(prawnik);
		}
	}
	

}
