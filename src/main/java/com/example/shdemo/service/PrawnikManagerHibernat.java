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
	private SessionFactory session;

	public SessionFactory getSessionFactory() {
		return session;
	}

	public void setSessionFactory(SessionFactory session) {
		this.session = session;
	}
	
	@Override
	public void addPrawnik(Prawnik prawnik) {
		prawnik.setId(null);
		session.getCurrentSession().persist(prawnik);
	}
	
	@Override
	public void deletePrawnik(Prawnik prawnik) {
		prawnik = (Prawnik) session.getCurrentSession().get(Prawnik.class,
				prawnik.getId());
		
		
		session.getCurrentSession().delete(prawnik);
	}

	

	@Override
	@SuppressWarnings("unchecked")
	public List<Prawnik> getAllPrawnik() {
		return session.getCurrentSession().getNamedQuery("prawnik.all")
				.list();
	}

	@Override
	public Prawnik findPrawnikById(Long id) {
		return (Prawnik) session.getCurrentSession().getNamedQuery("prawnik.byId").setLong("id", id).uniqueResult();
	}

	public void editPrawnik(Prawnik prawnik)
	{
		if(prawnik.getId() != null)
		{
			session.getCurrentSession().persist(prawnik);
		}
	}
	

}

