package com.example.restservicedemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Producent {
	
	private long id;
	
	private String name;
	private long idGame;
	
	public Producent() {
	}

	public Producent(String name, long idGame) {
		this.name = name;
		this.idGame = idGame;
	}
	
	public Producent(long id, String name, long idGame) {
		super();
		this.id = id;
		this.name = name;
		this.idGame = idGame;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public long getIdGame(){
		return idGame;
	}
	
	public void setIdGame(long idGame){
		this.idGame = idGame;
	}
}
