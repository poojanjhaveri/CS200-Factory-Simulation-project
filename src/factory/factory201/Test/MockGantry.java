package factory.factory201.Test;

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
	public void msgNeedPart(Part part) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void msgHereAreParts(Part part, int quantity) {
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
