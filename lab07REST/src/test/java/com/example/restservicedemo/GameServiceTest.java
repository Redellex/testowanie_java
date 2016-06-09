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
import com.jayway.restassured.RestAssured;

public class GameServiceTest {

	private static final String GAME_NAME = "DOOM";
	private static final String GAME_GENRE = "FPS";
	private static final String GAME_NAME_2 = "World of Warcraft";
	private static final String GAME_GENRE_2 = "MMORPG";
	
	//Funkcja skracajaca kod. Nie chce by testy byly od siebie zalezne...
	private Game addGameHelper(long id, String gameName, String genre)
	{
		Game game = new Game(id, gameName, genre);
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(game).
	    when().	     
	    post("/game/").then().assertThat().statusCode(201);
		
		return game;
	}
	
	@BeforeClass
    public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";   	
    }
	
	@Test
	public void addGames(){		
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
	public void readGames()
	{
		addGameHelper(1l, GAME_NAME, GAME_GENRE);
		addGameHelper(2l, GAME_NAME, GAME_GENRE_2);
		addGameHelper(3l, GAME_NAME_2, GAME_GENRE_2);
		addGameHelper(4l, GAME_NAME_2, GAME_GENRE);
		
	    RestAssured.when().get("/game/").then().assertThat().statusCode(200);
	    
	    GameResponse response = get("/game/").as(GameResponse.class);
	    List<Game> games = response.getGame();
	    assertEquals(4, games.size());
	}
	
	@Test
	public void deleteGames(){
		final int beforeDeleteCount = 3;
		final int afterDeleteCount = 2;
	
		Game game;
		game = addGameHelper(1l, GAME_NAME, GAME_GENRE);
		addGameHelper(2L, GAME_NAME, GAME_GENRE_2);
		addGameHelper(3L, GAME_NAME_2, GAME_GENRE);
		
		GameResponse response = get("/game/").as(GameResponse.class);
	    List<Game> games = response.getGame();
	    assertEquals(beforeDeleteCount, games.size());
		
		given().
			contentType(MediaType.APPLICATION_JSON).body(game).
		when().
		delete("/game/1").then().assertThat().statusCode(200);
		
		response = get("/game/").as(GameResponse.class);
		games = response.getGame();
		assertEquals(afterDeleteCount, games.size());
	}
	
	@Test
	public void updateGames(){
		addGameHelper(1l, GAME_NAME, GAME_GENRE);
		addGameHelper(2l, GAME_NAME, GAME_GENRE_2);
		addGameHelper(3l, GAME_NAME_2, GAME_GENRE_2);
		addGameHelper(4l, GAME_NAME_2, GAME_GENRE);
		// Update
		
		given().
			pathParam("gameId", "1").
			pathParam("name", "super").
			pathParam("genre", "FPSe").
		when().	
			put("/game/{gameId}/{name}/{genre}").then().assertThat().statusCode(200);
		
			Game rGame = get("/game/1").as(Game.class);
			assertEquals("super", rGame.getName());
	}
	
	@After
	public void cleanUp(){
		delete("/game/").then().assertThat().statusCode(200);
	}
}
