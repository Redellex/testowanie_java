package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.ws.rs.core.MediaType;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.database.DatabaseConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;

import com.example.restservicedemo.domain.Game;
import com.example.restservicedemo.service.GameManager;
import com.jayway.restassured.RestAssured;

public class JDBCGameTest {
	
	private static IDatabaseConnection connection ;
	private static IDatabaseTester databaseTester;
	
	private static GameManager pm = new GameManager();
	
	@BeforeClass
	public static void setUp() throws Exception{
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";
		
		Connection jdbcConnection;
		jdbcConnection = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
		connection = new DatabaseConnection(jdbcConnection);
		DatabaseConfig dbConfig = connection.getConfig();
		System.out.println(jdbcConnection.getMetaData().getDatabaseProductName());
		dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
		
		databaseTester = new JdbcDatabaseTester(
				"org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
	}
	
	@Before
	public void insertFullData() throws Exception
	{		
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(
				new FileInputStream(new File("src/test/resources/fullData.xml")));
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}
	
	@Test
	public void addGame() throws Exception{
	
		Game aGame = new Game("Clastevania", "Platform game");
		given().contentType(MediaType.APPLICATION_JSON).body(aGame)
				.when().post("/game/").then().assertThat().statusCode(201);
		
		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("GAME");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"G_ID"});
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/addData.xml"));
		ITable expectedTable = expectedDataSet.getTable("Game");
		
		Assertion.assertEquals(expectedTable, filteredTable);
	}
	
	@Test
	public void deleteGame() throws Exception{
		Game game = new Game(42l, "Age of Empires", "RTS");
	given().
	contentType(MediaType.APPLICATION_JSON).body(game).
	when().
	delete("/game/42").then().assertThat().statusCode(200);
	
	IDataSet dbDataSet = connection.createDataSet();
	ITable actualTable = dbDataSet.getTable("GAME");
	ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
			(actualTable, new String[]{"G_ID"});
	
	IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
			new File("src/test/resources/deleteData.xml"));
	ITable expectedTable = expectedDataSet.getTable("Game");
	
	Assertion.assertEquals(expectedTable, filteredTable);
	}
	
	@Test
	public void updateGame() throws Exception{

	given().
		pathParam("gameId", "42").
		pathParam("name", "Ping'n'Pong").
		pathParam("genre", "Sport").
	when().	
		put("/game/{gameId}/{name}/{genre}").then().assertThat().statusCode(200);
	
	IDataSet dbDataSet = connection.createDataSet();
	ITable actualTable = dbDataSet.getTable("GAME");
	ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
			(actualTable, new String[]{"G_ID"});
	
	IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
			new File("src/test/resources/updateData.xml"));
	ITable expectedTable = expectedDataSet.getTable("Game");
	
	Assertion.assertEquals(expectedTable, filteredTable);
	}
	
	@AfterClass
	public static void tearDown() throws Exception{
		databaseTester.onTearDown();
	}
}
