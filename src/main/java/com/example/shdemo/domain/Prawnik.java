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
@Table(name="Prawnik")
@NamedQueries({ 
	@NamedQuery(name = "prawnik.all", query = "Select p from Prawnik p"),
	@NamedQuery(name = "prawnik.byId", query = "Select p from Prawnik p where p.id = :id")
})
public class Prawnik {

	@Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     
    @Column(name="Imie")
    private String imie;
 
    @Column(name="Nazwisko")
    private String nazwisko;
    
    @Column(name="Wiek")
    private int wiek;
    
    
    public Long getId() {
		return id;
	}
    
    public String getImie() {
		return imie;
	}
   
    public String getNazwisko() {
		return nazwisko;
	}
    
    public int getWiek() {
		return wiek;
	}
    
    public void setId(Long id) {
		this.id = id;
	}
    
    public void setImie(String imie) {
		this.imie = imie;
	}
    
    public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
    
    public void setWiek(int wiek) {
		this.wiek = wiek;
	}
    
}

	

