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

import com.example.restservicedemo.domain.Producent;
import com.example.restservicedemo.service.ProducentManager;

@Path("producent")
public class ProducentRESTService {
	
	private ProducentManager pm = new ProducentManager();
	
	@GET
	@Path("/{ProducentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Producent getProducent(@PathParam("ProducentId") Long id){
		Producent p = pm.getProducent(id);
		return p;
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producent> getAllProducent(){
		List<Producent> Producents = pm.getAllProducents();
		return Producents;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProducent(Producent Producent){
		pm.addProducent(Producent);
		return Response.status(201).entity("Producent").build(); 
	}
	
	@DELETE
	@Path("/{ProducentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteProducent(@PathParam("ProducentId") long id){
		pm.deleteProducent(id);
		return Response.status(200).entity("Producent").build(); 
	}
	
	@PUT
	@Path("/{ProducentId}/{ProducentName}/{ProducentGameId}")
	public Response updateProducent(@PathParam("ProducentId") long id, @PathParam("ProducentName") String name, @PathParam("ProducentGameId") long gameId){
		pm.updateProducent(id, name, gameId);
		return Response.status(200).entity("Producent").build();
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_HTML)
	public String test(){
		return "REST API /Producent is running";
	}
	
	@DELETE
	public Response clearProducents(){
		pm.clearProducents();
		return Response.status(200).build();
	}

}
