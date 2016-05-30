package com.example.restservicedemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Game {
	
	private long id;
	
	private String name;
	private String genre;
	
	public Game() {
	}

	public Game(String name, String genre) {
		this.name = name;
		this.genre = genre;
	}
	
	public Game(long id, String name, String genre) {
		super();
		this.id = id;
		this.name = name;
		this.genre = genre;
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
	
	public String getGenre(){
		return genre;
	}
	
	public void setGenre(String genre){
		this.genre = genre;
	}
}
