/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.FeederManegementTest;

import factory.factory200.gantryRobotManager.GantryRobotManager;
import factory.factory201.feederManagement.FeederAgent;
import factory.factory201.feederManagement.GantryAgent;
import factory.factory201.interfaces.Feeder;
import factory.factory201.test.mock.MockFeeder;
import factory.factory201.test.mock.MockGantry;
import factory.factory201.test.mock.MockLane;
import factory.general.Part;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kevin
 */
public class FeederTest extends TestCase{
	
	public FeederAgent feeder;
	
	//sent by the nestagent
	public void testneedParts(){
		
		//1 is the index of the feeder (feederNum)
		//this constructor initializes the feeder with 8 parts of 0 quantity
		feeder=new FeederAgent("Feeder1",1);
		MockGantry gantry=new MockGantry("Gantry");
		
		//assign a part index to the new part.
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
		feeder=new FeederAgent("Feeder1",1);
		MockGantry gantry=new MockGantry("Gantry");
		Part p=new Part(1);
                List<Part> parts=new ArrayList<Part>();
                for(int i=0;i<8;i++)
                    parts.add(p);
		MockLane leftLane=new MockLane("Left Lane");
		MockLane rightLane=new MockLane("Right Lane");
		feeder.setLeftLane(leftLane);
		feeder.setRightLane(rightLane);
		feeder.setGantry(gantry);
		feeder.msgHereAreParts(parts);
		feeder.msgNeedPart(p,leftLane);
		feeder.pickAndExecuteAnAction();
		
		assertTrue(
				"Mock feeder should have received message here are parts. Event log: "
						+ leftLane.log.toString(), leftLane.log
						.containsString("Received parts event encountered"));
	
	
	}

}
