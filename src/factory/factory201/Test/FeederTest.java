package factory.factory201.Test;

import factory.factory201.feederManagement.*;
import factory.general.*;
import junit.framework.TestCase;

public class FeederTest extends TestCase{
	
	public FeederAgent feeder;
	
	//sent by the nestagent
	public void testneedParts(){
		
		//1 is the index of the feeder (feederNum)
		feeder=new FeederAgent(1);
		MockGantry gantry=new MockGantry("Gantry");
		Part p=new Part(1);
		MockLane leftLane=new MockLane("Left Lane");
		MockLane rightLane=new MockLane("Right Lane");
		feeder.setLeftLane(leftLane);
		feeder.setRightLane(rightLane);
		feeder.setGantry(gantry);
		
		feeder.msgNeedPart(p,leftLane);
		feeder.pickAndExecuteAnAction();
		
		assertTrue(
				"Mock feeder should have received message here are parts. Event log: "
						+ gantry.log.toString(), gantry.log
						.containsString("Need parts event encountered"));
	
	}

}
