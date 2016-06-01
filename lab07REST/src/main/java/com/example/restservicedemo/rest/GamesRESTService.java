package com.example.restservicedemo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE; //DELETE
import javax.ws.rs.GET; //READ
import javax.ws.rs.POST; //CREATE
import javax.ws.rs.PUT; //UPDATE
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import com.example.restservicedemo.domain.Game;
import com.example.restservicedemo.service.GameManager;

@Path("game")
public class GamesRESTService {	
	
	private GameManager pm = new GameManager();
	
	@GET
	@Path("/{gameId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Game getGame(@PathParam("gameId") Long id){
		Game p = pm.getGame(id);
		return p;
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Game> getAllGame(){
		List<Game> Games = pm.getAllGames();
		return Games;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGame(Game game){
		pm.addGame(game);
		return Response.status(201).entity("Game").build(); 
	}
	
	@DELETE
	@Path("/{gameId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteGame(@PathParam("gameId") long id){
		pm.deleteGame(id);
		return Response.status(201).entity("Game").build(); 
	}
	
	@PUT
	@Path("/{gameId}/{gameName}/{gameGenre}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateGame(@PathParam("gameId") long id, @PathParam("gameName") String name, @PathParam("gameGenre") String genre){
		pm.updateGame(id, name, genre);
		return Response.status(201).entity("Game").build();
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_HTML)
	public String test(){
		return "REST API /game is running";
	}
	
	@DELETE
	public Response clearGames(){
		pm.clearGames();
		return Response.status(200).build();
	}

}
