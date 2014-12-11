package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
		@NamedQuery(name = "firma.all", query = "Select f from Firma f"),
		@NamedQuery(name = "firma.byId", query = "Select f from Firma f where f.id = :id"),
		@NamedQuery(name = "firma.byMarka", query = "Select f from Firma f where f.marka = :marka")
})
public class Firma {

	private Long id;
	private String marka;
	private int regon;
	private String szef;
	
	private List<Monitor> monitor = new ArrayList<Monitor>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public int getRegon() {
		return regon;
	}

	public void setRegon(int regon) {
		this.regon = regon;
	}

	public String getSzef() {
		return szef;
	}

	public void setSzef(String szef) {
		this.szef = szef;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Monitor> getMonitor() {
		return monitor;
	}
	public void setMonitor(List<Monitor> monitor) {
		this.monitor = monitor;
	}

	
}
