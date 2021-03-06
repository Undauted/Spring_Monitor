package com.example.shdemo.domain;




import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;



@Entity
@Table(name="Monitor")
@NamedQueries({ 
	@NamedQuery(name = "monitor.all", query = "Select m from Monitor m"),
	@NamedQuery(name = "monitor.byId", query = "Select m from Monitor m where m.id = :id"),
	@NamedQuery(name = "monitor.byRodzaj", query = "Select m from Monitor m where m.rodzaj = :rodzaj")
})
public class Monitor {

	@Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     
    @Column(name="Nazwa")
    private String nazwa;
 
    @Column(name="Rodzaj")
    private String rodzaj;
    
    @Column(name="Przekatna")
    private int przekatna;
    
    @Column(name="Waga")
    private int waga;
    
    
    
    public Long getId() {
		return id;
	}
    
    public String getNazwa() {
		return nazwa;
	}
   
    public String getRodzaj() {
		return rodzaj;
	}
    
    public int getPrzekatna() {
		return przekatna;
	}
    
    public int getWaga() {
		return waga;
	}
    
    public void setId(Long id) {
		this.id = id;
	}
    
    public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
    
    public void setRodzaj(String rodzaj) {
		this.rodzaj = rodzaj;
	}
    
    public void setPrzekatna(int przekatna) {
		this.przekatna = przekatna;
	}
    
    public void setWaga(int waga) {
		this.waga = waga;
	}
    
   
}

	

