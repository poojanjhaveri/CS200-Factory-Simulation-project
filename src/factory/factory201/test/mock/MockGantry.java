package factory.factory201.test.mock;

import factory.factory201.test.mock.LoggedEvent;
import factory.factory201.test.mock.EventLog;
import factory.factory201.interfaces.Feeder;
import factory.factory201.interfaces.Gantry;
import factory.general.Part;

public class MockGantry extends MockAgent implements Gantry{

	public EventLog log = new EventLog();
	
	public MockGantry(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void msgNeedPart(Part partType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgHereAreParts(List<Part> parts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgNeedPart(Part part, Feeder feeder) {
		// TODO Auto-generated method stub
		System.out.println("testing message reception");
		log.add(new LoggedEvent(
				"Need parts event encountered"
						));		
	}

}
