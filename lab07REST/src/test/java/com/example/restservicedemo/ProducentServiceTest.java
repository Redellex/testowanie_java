package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.*;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Game;
import com.example.restservicedemo.domain.Producent;
import com.jayway.restassured.RestAssured;

public class ProducentServiceTest {
	
	private static final String PRODUCENT_NAME = "Blizzard";
	private static final long GAME_ID = 10L;
	private static final String PRODUCENT_NAME_2 = "World of Warcraft";
	private static final long GAME_ID_2 = 20L;
	private static final String GAME_NAME = "DOOM";
	private static final String GAME_GENRE = "FPS";
	
	@BeforeClass
    public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";   	
    }
	/*
	@After
	public void cleanUp(){
		delete("/producent/").then().assertThat().statusCode(200);
	}*/

	
	@Test
	public void addProducent(){		
		Producent producent = new Producent(1L, PRODUCENT_NAME, GAME_ID);
		Game game = new Game(10L, GAME_NAME, GAME_GENRE);
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(game).
	    when().	     
	    post("/game/").then().assertThat().statusCode(201);
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(producent).
	    when().	     
	    post("/producent/").then().assertThat().statusCode(201);
				
		Producent rProducent = get("/producent/1").as(Producent.class);
		
		assertThat(PRODUCENT_NAME, equalToIgnoringCase(rProducent.getName()));
	}
}
