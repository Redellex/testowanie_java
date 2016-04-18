package com.example.mockdemo.app;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.example.mockdemo.app.Messenger;
import com.example.mockdemo.messenger.MessageServiceSimpleImpl;

public class MessageAppSteps 
{
	private Messenger messenger;
	private MessageServiceSimpleImpl messageService = new MessageServiceSimpleImpl();
	
	private String server;
	private String message;
	
	
	@Given("a messenger")
	public void messengerSetup(){
		messenger = new Messenger(messageService);
	}
	
	@When("set Messenger arguments $a $b")
	public void setSendMessage(String server, String message)
	{
		this.server = server;
		this.message = message;
	}
	
	@Then("testConnection should return $expected")
	public void checkTestConnection(int expected)
	{
		final int actual = messenger.testConnection(server);
		assertEquals(actual, expected);
	}
	
	/*
	@Then("testConnection should return $expected")
	public void checkTestConnection(int expected)
	{
		final int actual = messenger.sendMessage(server);
		assertEquals(actual, expected);
	}
	*/
}