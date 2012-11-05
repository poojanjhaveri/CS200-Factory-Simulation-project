/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.Test;

import factory.factory201.test.mock.MockLane;
import factory.factory201.test.mock.MockGantry;
import factory.factory201.feederManagement.FeederAgent;
import factory.general.Part;
import junit.framework.TestCase;

/**
 *
 * @author Kevin
 */

public class FeederTestTest extends TestCase{
	
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
	
	public void testSendPartsToLane(){
		feeder=new FeederAgent(1);
		MockGantry gantry=new MockGantry("Gantry");
		Part p=new Part(1);
		MockLane leftLane=new MockLane("Left Lane");
		MockLane rightLane=new MockLane("Right Lane");
		feeder.setLeftLane(leftLane);
		feeder.setRightLane(rightLane);
		feeder.setGantry(gantry);
		feeder.msgHereAreParts(p,12);
		feeder.msgNeedPart(p,leftLane);
		feeder.pickAndExecuteAnAction();
		
		assertTrue(
				"Mock feeder should have received message here are parts. Event log: "
						+ leftLane.log.toString(), leftLane.log
						.containsString("Received parts event encountered"));
	
	
	}
}
