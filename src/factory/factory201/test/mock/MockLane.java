package factory.factory201.test.mock;

import factory.factory201.test.mock.LoggedEvent;
import factory.factory201.test.mock.EventLog;
import factory.factory201.interfaces.Lane;
import factory.general.Part;

public class MockLane extends MockAgent implements Lane{

	public EventLog log = new EventLog();
	
	public MockLane(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void msgNeedPart(Part part) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgHereAreParts(Part part, int quantity) {
		// TODO Auto-generated method stub
	
		System.out.println("Quantity received " + quantity);
		log.add(new LoggedEvent(
				"Received parts event encountered"
						));		
	
	}

}
