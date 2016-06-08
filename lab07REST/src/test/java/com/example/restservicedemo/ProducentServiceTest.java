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
import com.example.restservicedemo.domain.GameResponse;
import com.example.restservicedemo.domain.Producent;
import com.example.restservicedemo.domain.ProducentResponse;
import com.jayway.restassured.RestAssured;

public class ProducentServiceTest {

	private static final String PRODUCENT_NAME = "Blizzard";
	private static final long GAME_ID = 10L;
	private static final String PRODUCENT_NAME_2 = "World of Warcraft";
	private static final long GAME_ID_2 = 20L;
	private static final String GAME_NAME = "DOOM";
	private static final String GAME_GENRE = "FPS";
	
	private Producent addProducentHelper(long id, String name, long gameId)
	{
		Producent producent = new Producent(id, name, gameId);
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(producent).
	    when().	     
	    post("/producent/").then().assertThat().statusCode(201);
		
		return producent;
	}
	
	private static void addGameHelper(long id, String gameName, String genre)
	{
		Game game = new Game(id, gameName, genre);
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(game).
	    when().	     
	    post("/game/").then().assertThat().statusCode(201);
	}
	
	@BeforeClass
    public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api"; 
    }
	
	@Test
	public void addProducent(){	
		addGameHelper(GAME_ID, GAME_NAME, GAME_GENRE);
		addGameHelper(GAME_ID_2, GAME_NAME, GAME_GENRE);
		Producent producent = new Producent(1L, PRODUCENT_NAME, GAME_ID);
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(producent).
	    when().	     
	    post("/producent/").then().assertThat().statusCode(201);
				
		Producent rProducent = get("/producent/1").as(Producent.class);
		
		assertThat(PRODUCENT_NAME, equalToIgnoringCase(rProducent.getName()));
		assertEquals(GAME_ID, rProducent.getIdGame());
	}
		
	@Test
	public void readProducent()
	{
		final int EXPECTED_SIZE = 2;
		
		addGameHelper(GAME_ID, GAME_NAME, GAME_GENRE);
		addGameHelper(GAME_ID_2, GAME_NAME, GAME_GENRE);
		addProducentHelper(1L, PRODUCENT_NAME_2, GAME_ID_2);
		addProducentHelper(2L, PRODUCENT_NAME, GAME_ID);
		
	    RestAssured.when().get("/producent/").then().assertThat().statusCode(200);
	    
	    ProducentResponse response = get("/producent/").as(ProducentResponse.class);
	    List<Producent> producents = response.getProducent();
	    assertEquals(EXPECTED_SIZE, producents.size());
	}
	
	@Test
	public void deleteProducent(){
		final int beforeDeleteCount = 3;
		final int afterDeleteCount = 2;
		
		addGameHelper(GAME_ID, GAME_NAME, GAME_GENRE);
		addGameHelper(GAME_ID_2, GAME_NAME, GAME_GENRE);
		Producent producent = addProducentHelper(1L, PRODUCENT_NAME, GAME_ID);
		addProducentHelper(2L, PRODUCENT_NAME, GAME_ID);
		addProducentHelper(3L, PRODUCENT_NAME_2, GAME_ID);
		
		ProducentResponse response = get("/producent/").as(ProducentResponse.class);
	    List<Producent> producents = response.getProducent();
	    assertEquals(beforeDeleteCount, producents.size());
		
		given().
		contentType(MediaType.APPLICATION_JSON).body(producent).
		when().
		delete("/producent/1").then().assertThat().statusCode(200);
		
		response = get("/producent/").as(ProducentResponse.class);
		producents = response.getProducent();
		assertEquals(afterDeleteCount, producents.size());
	}
	
	@Test
	public void updateProducent(){
		addGameHelper(GAME_ID, GAME_NAME, GAME_GENRE);
		addGameHelper(GAME_ID_2, GAME_NAME, GAME_GENRE);
		addProducentHelper(1L, PRODUCENT_NAME, GAME_ID);
		
		// Update
		given().
			pathParam("ProducentId", "1").
			pathParam("ProducentName", "Capcom").
			pathParam("ProducentGameId", GAME_ID_2).
		when().	
			put("/producent/{ProducentId}/{ProducentName}/{ProducentGameId}").then().assertThat().statusCode(200);
		
		Producent rProducent = get("/producent/1").as(Producent.class);
		
		assertThat("Capcom", equalToIgnoringCase(rProducent.getName()));
		assertEquals(GAME_ID_2, rProducent.getIdGame());
	}
	
	@After
	public void cleanUp(){
		delete("/producent/").then().assertThat().statusCode(200);
		delete("/game/").then().assertThat().statusCode(200);
	}	
}
