package com.example.shdemo.service;

import java.util.List;


import com.example.shdemo.domain.Prawnik;

public interface PrawnikManager {
	
	void addPrawnik(Prawnik prawnik);
	
	List<Prawnik> getAllPrawnik();
	
	void deletePrawnik(Prawnik prawnik);
	
	Prawnik findPrawnikById(Long id);
	
	void editPrawnik(Prawnik prawnik);

}
