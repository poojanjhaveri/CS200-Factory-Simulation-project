package factory.factory201.test.mock;

import factory.factory201.test.mock.LoggedEvent;
import factory.factory201.test.mock.EventLog;
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

	
	public void msgNeedPart(Part partType) {
		// TODO Auto-generated method stub
		log.add(new LoggedEvent(
				"Need parts event encountered"
						));	
	}

	
	public void msgHereAreParts(List<Part> parts) {
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
