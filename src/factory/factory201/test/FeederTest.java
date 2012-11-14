package factory.factory201.test;

import factory.factory200.laneManager.ServerSide.LMServerMain;
import factory.factory201.feederManagement.*;
import factory.factory201.test.mock.MockGantry;
import factory.factory201.test.mock.MockLane;
import factory.general.*;
//import junit.framework.TestCase;

public class FeederTest{
	
	public FeederAgent feeder;
	
	//sent by the nestagent
	public void testneedParts(){
		
		//1 is the index of the feeder (feederNum)
		feeder=new FeederAgent("Feeder", 1, new LMServerMain());
		MockGantry gantry=new MockGantry("Gantry");
		Part p=new Part(1);
		MockLane leftLane=new MockLane("Left Lane");
		MockLane rightLane=new MockLane("Right Lane");
		feeder.setLeftLane(leftLane);
		feeder.setRightLane(rightLane);
		feeder.setGantry(gantry);
		
		feeder.msgNeedPart(p,leftLane);
		feeder.pickAndExecuteAnAction();
	/*	
		assertTrue(
				"Mock feeder should have received message here are parts. Event log: "
						+ gantry.log.toString(), gantry.log
						.containsString("Need parts event encountered"));
	*/
	}

}
