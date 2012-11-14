package factory.factory201.test.mock;

import factory.factory201.test.mock.LoggedEvent;
import factory.factory201.test.mock.EventLog;
import factory.factory201.interfaces.Lane;
import factory.factory201.partsManagement.NestAgent;
import factory.general.Part;

public class MockLane extends MockAgent implements Lane{

	public EventLog log = new EventLog();
	NestAgent nest;
	public MockLane(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void msgNeedPart(Part part) {
		// TODO Auto-generated method stub
		log.add(new LoggedEvent(
				"Received msgNeedPart from nest"
						));
                //nest.msgHereAreParts(List<Part> );
	}

	@Override
	public void msgHereAreParts(Part part, int quantity) {
		// TODO Auto-generated method stub
	
		System.out.println("Quantity received " + quantity);
		log.add(new LoggedEvent(
				"Received parts event encountered"
						));		
	
	}
        public void setNestAgent(NestAgent n){
            this.nest=n;
        }

}
