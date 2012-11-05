package factory.factory201.test.mock;

import factory.factory201.test.mock.LoggedEvent;
import factory.factory201.test.mock.EventLog;
import factory.factory201.interfaces.NestInterface;
import factory.general.Part;

public class MockNest extends MockAgent implements NestInterface {
	public EventLog log = new EventLog();

	public MockNest(String name) {
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
		log.add(new LoggedEvent(
				"Received parts event encountered"
						));	
	}

}
