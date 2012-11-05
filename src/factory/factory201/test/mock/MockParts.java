/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.Test.mock;

import factory.factory201.interfaces.PartsInterface;
import factory.factory201.test.mock.EventLog;
import factory.factory201.test.mock.LoggedEvent;
import factory.factory201.test.mock.MockAgent;
import factory.general.Kit;
import factory.general.Part;

/**
 *
 * @author polarpatbear
 */
public class MockParts extends MockAgent implements PartsInterface{
   public EventLog log = new EventLog();
   
   public MockParts(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void msgNeedPart(Part part) {
		log.add(new LoggedEvent("Part " + part + " requested"));
		
	}

	@Override
	public void msgHereIsPart(Part part) {
		// TODO Auto-generated method stub
	
		System.out.println("Part recieved" + part);
		log.add(new LoggedEvent(
				"Received parts event encountered"
						));		
	
	}

    @Override
    public void msgEmptyKitReady(Kit kit) {
        log.add(new LoggedEvent("Got and empty kit"));
    }

    @Override
    public void msgHereAreParts(Part part, int quantity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void msgHereIsKit(Kit kit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
