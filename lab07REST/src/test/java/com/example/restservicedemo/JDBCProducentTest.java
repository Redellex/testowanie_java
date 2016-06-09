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
import com.example.restservicedemo.domain.Producent;
import com.example.restservicedemo.service.GameManager;
import com.example.restservicedemo.service.ProducentManager;
import com.jayway.restassured.RestAssured;

public class JDBCProducentTest {
	
	private static IDatabaseConnection connection ;
	private static IDatabaseTester databaseTester;
	
	private static GameManager gm = new GameManager();
	private static ProducentManager pm = new ProducentManager();
	
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
				new FileInputStream(new File("src/test/resources/producentGameFullData.xml")));
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}
	
	@Test
	public void addProducent() throws Exception{
		Producent aProducent = new Producent(0l ,"Rockstar", 44l);
		given().contentType(MediaType.APPLICATION_JSON).body(aProducent)
				.when().post("/producent/").then().assertThat().statusCode(201);
		
		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("PRODUCENT");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"P_ID"});
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/producentAddData.xml"));
		ITable expectedTable = expectedDataSet.getTable("Producent");
		
		Assertion.assertEquals(expectedTable, filteredTable);
	}
	
	@Test
	public void deleteProducent() throws Exception{
		Producent aProducent = new Producent(10l ,"Activision", 42l);
		given().
		contentType(MediaType.APPLICATION_JSON).body(aProducent).
		when().
		delete("/producent/10").then().assertThat().statusCode(200);

		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("PRODUCENT");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"P_ID"});
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/producentDeleteData.xml"));
		ITable expectedTable = expectedDataSet.getTable("Producent");
		
		Assertion.assertEquals(expectedTable, filteredTable);
	}

	@AfterClass
	public static void tearDown() throws Exception{
		databaseTester.onTearDown();
	}
}
