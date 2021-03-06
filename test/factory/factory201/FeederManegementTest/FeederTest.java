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
		
                assertEquals(
		"Mock Gantry should have an empty event log before the feeder scheduler is called. Instead, the mock Lane's event log reads: "
					+ gantry.log.toString(), 0, gantry.log.size());

                feeder.pickAndExecuteAnAction();
		
		assertTrue(
				"Mock feeder should have received message here are parts. Event log: "
						+ gantry.log.toString(), gantry.log
						.containsString("Need parts event encountered"));
	}
	
        /* Incoming parts request from leftLane */
	public void testSendPartToLeftLane(){
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
                
                assertEquals(
		"Mock Lane should have an empty event log before the feeder scheduler is called. Instead, the mock Lane's event log reads: "
					+ leftLane.log.toString(), 0, leftLane.log.size());

		feeder.pickAndExecuteAnAction();
		
		assertTrue(
				"Mock lane should have received message here are parts. Event log: "
						+ leftLane.log.toString(), leftLane.log
						.containsString("Received parts event encountered"));
	
	
	}
        
        /* Send request through Right Lane */
        public void testSendPartToRighttLane(){
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
		feeder.msgNeedPart(p,rightLane);
                
                
                assertEquals(
		"Mock Lane should have an empty event log before the feeder scheduler is called. Instead, the mock Lane's event log reads: "
					+ leftLane.log.toString(), 0, leftLane.log.size());
                
                assertEquals(
		"Mock Lane should have an empty event log before the feeder scheduler is called. Instead, the mock Lane's event log reads: "
					+   rightLane.log.toString(), 0, rightLane.log.size());

		feeder.pickAndExecuteAnAction();
		
		assertTrue(
				"Mock lane should have received message here are parts. Event log: "
						+ rightLane.log.toString(), rightLane.log
						.containsString("Received parts event encountered"));
	
	
	}
        
        
        /* Send request through Right Lane */
        public void testSendPartToBothLanes(){
		feeder=new FeederAgent("Feeder1",1);
		MockGantry gantry=new MockGantry("Gantry");
		Part p=new Part(1);
                Part p1=new Part(2);
                List<Part> parts=new ArrayList<Part>();
                List<Part> parts1=new ArrayList<Part>();

                for(int i=0;i<8;i++)
                    parts.add(p);
                
                for(int i=0;i<8;i++)
                    parts1.add(p1);
		MockLane leftLane=new MockLane("Left Lane");
		MockLane rightLane=new MockLane("Right Lane");
		
                feeder.setLeftLane(leftLane);
		feeder.setRightLane(rightLane);
		feeder.setGantry(gantry);
		
                feeder.msgHereAreParts(parts);
		feeder.msgHereAreParts(parts1);
                
                feeder.msgNeedPart(p1,leftLane);
                feeder.msgNeedPart(p,rightLane);
		
                
                assertEquals(
		"Mock Lane should have an empty event log before the feeder scheduler is called. Instead, the mock Lane's event log reads: "
					+ leftLane.log.toString(), 0, leftLane.log.size());

                
                assertEquals(
		"Mock Lane should have an empty event log before the feeder scheduler is called. Instead, the mock Lane's event log reads: "
					+ rightLane.log.toString(), 0, rightLane.log.size());

                feeder.pickAndExecuteAnAction();
		
		assertTrue(
				"Mock rightLane should have received message here are parts. Event log: "
						+ rightLane.log.toString(), rightLane.log
						.containsString("Received parts event encountered"));

                feeder.pickAndExecuteAnAction();
                
		assertTrue(
				"Mock leftLane should have received message here are parts. Event log: "
						+ leftLane.log.toString(), leftLane.log
						.containsString("Received parts event encountered"));
	
	
	}

/* Incoming msg Here are parts from gantry , event log in the lane should be empty if the scheduler isn't called */
public void testMsgHereArePartsFromGantry(){
		feeder=new FeederAgent("Feeder1",1);
		MockGantry gantry=new MockGantry("Gantry");
		Part p=new Part(1);
                Part p1=new Part(2);
                List<Part> parts=new ArrayList<Part>();
                List<Part> parts1=new ArrayList<Part>();

                for(int i=0;i<8;i++)
                    parts.add(p);
                
                for(int i=0;i<8;i++)
                    parts1.add(p1);
		MockLane leftLane=new MockLane("Left Lane");
		MockLane rightLane=new MockLane("Right Lane");
		
                feeder.setLeftLane(leftLane);
		feeder.setRightLane(rightLane);
		feeder.setGantry(gantry);
		
                feeder.msgHereAreParts(parts);

                assertEquals(
		"Mock Lane should have an empty event log before the feeder scheduler is called. Instead, the mock Lane's event log reads: "
					+ leftLane.log.toString(), 0, leftLane.log.size());
                
}

}
