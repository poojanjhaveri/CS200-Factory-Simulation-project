package factory.factory201.Test;

import factory.factory201.interfaces.*;
import factory.general.Part;
import java.util.*;
import agent.Agent;
import java.util.*;
import java.awt.Color;

import javax.swing.JOptionPane;
public class MockFeeder extends MockAgent implements Feeder {


	public EventLog log = new EventLog();
	public MockFeeder(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
	public void msgNeedPart(Part part) {
		// TODO Auto-generated method stub
		log.add(new LoggedEvent(
				"Need parts event encountered"
						));	
	}

	
	public void msgHereAreParts(Part part, int quantity) {
		System.out.println("message received");
		log.add(new LoggedEvent(
				"Received parts event encountered"
						));	
	}


	public void msgNeedPart(Part part, Lane lane) {
		log.add(new LoggedEvent(
				"Need parts from lane event encountered"
						));	
		
	}


}
