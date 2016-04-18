package com.example;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class TelephoneSteps
{
	private TelephoneManager telephoneManager;
	private final Telephone element = new Telephone(111, "test", true);
	
	@Given("a telephone")
	public void telephoneSetup()
	{
		telephoneManager = new TelephoneManager();
	}
	
	@Given("a setupped telephone")
	public void telephoneSetupped()
	{
		telephoneManager = new TelephoneManager();
		telephoneManager.add(element);
		telephoneManager.add(element);
		telephoneManager.add(element);
		telephoneManager.add(element);
	}	
	
	@When("add one element")
	public void addTest()
	{
		telephoneManager.add(element);
	}
	
	@When("remove one element")
	public void addAndRemoveTest()
	{
		telephoneManager.remove(element);
	}
	
	@Then("telephone size should return $expected")
	public void checkAdd(int expected)
	{
		final int actual = telephoneManager.getSize();
		assertEquals(expected, actual);	
	}
}