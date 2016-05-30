package com.example.restservicedemo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGame(Game game){
		pm.addGame(game);
		return Response.status(201).entity("Game").build(); 
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteGame(int id){
		pm.deleteGame(id);
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
