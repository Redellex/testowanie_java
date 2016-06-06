package com.example.restservicedemo.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProducentResponse {
	private List<Producent> producent = new ArrayList<Producent>();

	public List<Producent> getProducent() {
		return producent;
	}

	public void setProducent(List<Producent> game) {
		this.producent = game;
	}
}
