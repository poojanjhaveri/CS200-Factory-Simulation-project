package factory.factory201.Test;

import factory.factory201.interfaces.Lane;
import factory.general.Part;

public class MockLane extends MockAgent implements Lane{

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
		
	}

}
