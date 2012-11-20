package factory.factory201.test.mock;

import factory.factory201.interfaces.Lane;
import factory.general.Part;
import java.util.List;

public class MockLane extends MockAgent implements Lane{

	public EventLog log = new EventLog();
	
	public MockLane(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void msgNeedPart(Part partType) {
		// TODO Auto-generated method stub
		log.add(new LoggedEvent(
				"Received msgNeedPart from nest"
						));
                //nest.msgHereAreParts(List<Part> );
	}

	@Override
	public void msgHereAreParts(List<Part> parts) {
		// TODO Auto-generated method stub
	
		System.out.println("Quantity received " + parts.size());
		log.add(new LoggedEvent(
				"Received parts event encountered"
						));		
	
	}
        

    @Override
    public int getIndex() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
