package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Game;
import com.jayway.restassured.RestAssured;

public class GameServiceTest {
	
	private static final String GAME_NAME = "DOOM";
	private static final String GAME_GENRE = "FPS";
	
	@BeforeClass
    public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";   	
    }
	
	@Test
	public void addGames(){		
		
		delete("/game/").then().assertThat().statusCode(200);
		
		Game game = new Game(1L, GAME_NAME, GAME_GENRE);
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(game).
	    when().	     
	    post("/game/").then().assertThat().statusCode(201);
				
		Game rGame = get("/game/1").as(Game.class);
		
		assertThat(GAME_NAME, equalToIgnoringCase(rGame.getName()));
		
	}
	
	@Test
	public void deleteGames(){
		delete("/game/").then().assertThat().statusCode(200);
		
		Game game = new Game(1L, GAME_NAME, GAME_GENRE);
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(game).
	    when().	     
	    post("/game/").then().assertThat().statusCode(201);
		
		given().
			contentType(MediaType.APPLICATION_JSON).body(game).
		when().
		delete("1").then().assertThat().statusCode(201);
	}
	

}
