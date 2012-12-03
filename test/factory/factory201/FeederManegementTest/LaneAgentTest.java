/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.FeederManegementTest;

import factory.factory200.gantryRobotManager.GantryRobotManager;
import factory.factory201.feederManagement.FeederAgent;
import factory.factory201.feederManagement.GantryAgent;
import factory.factory201.feederManagement.LaneAgent;
import factory.factory201.interfaces.Feeder;
import factory.factory201.test.mock.MockFeeder;
import factory.factory201.test.mock.MockGantry;
import factory.factory201.test.mock.MockLane;
import factory.factory201.test.mock.MockNest;
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
public class LaneAgentTest extends TestCase{

public LaneAgent lane;

public void testneedParts(){

	//1 is the lane index number

	//this constructor intitializes the lane with 0 quantity of all the 8 parts in its list
	lane=new LaneAgent("Lane1",1);
	MockFeeder feeder=new MockFeeder("Feeder");
	//feeder=new FeederAgent(1);
	MockGantry gantry=new MockGantry("Gantry");
	Part p=new Part(1);
	//MockLane leftLane=new MockLane("Left Lane");
	//MockLane rightLane=new MockLane("Right Lane");

	lane.setFeeder(feeder);
	lane.msgNeedPart(p);
	assertEquals(
			"Mock Feeder should have an empty event log before the Gantry scheduler is called. Instead, the mock Feeder's event log reads: "
					+ feeder.log.toString(), 0, feeder.log.size());
        lane.pickAndExecuteAnAction();

	assertTrue(
			"Mock feeder should have received message here are parts. Event log: "
					+ feeder.log.toString(), feeder.log
					.containsString("Need parts from lane event encountered"));

}

public void testsendPartsAfterRequest(){

	lane=new LaneAgent("Lane1",1);
	MockFeeder feeder=new MockFeeder("Feeder");
	MockNest nest=new MockNest("Nest");
	//feeder=new FeederAgent(1);
	MockGantry gantry=new MockGantry("Gantry");
	Part p=new Part(1);
	//MockLane leftLane=new MockLane("Left Lane");
	//MockLane rightLane=new MockLane("Right Lane");
         List<Part> parts=new ArrayList<Part>();
         for(int i=0;i<8;i++)
         parts.add(p);
	lane.setFeeder(feeder);
	lane.setNest(nest);
	//message sent by the nest
	lane.msgNeedPart(p);
	lane.msgHereAreParts(parts);
        
        assertEquals(
			"Mock Nest should have an empty event log before the Gantry scheduler is called. Instead, the mock Feeder's event log reads: "
					+ nest.log.toString(), 0, nest.log.size());
	lane.pickAndExecuteAnAction();

	assertTrue(
			"Mock nest should have received message here are parts. Event log: "
					+ nest.log.toString(), nest.log
					.containsString("msgHereAreParts"));
}

public void testLaneInitializedWithNoParts(){

	lane=new LaneAgent("Lane1",1);
	MockFeeder feeder=new MockFeeder("Feeder");
	MockNest nest=new MockNest("Nest");
	//feeder=new FeederAgent(1);
	MockGantry gantry=new MockGantry("Gantry");
	Part p=new Part(1);
	//MockLane leftLane=new MockLane("Left Lane");
	//MockLane rightLane=new MockLane("Right Lane");

	lane.setFeeder(feeder);
	lane.setNest(nest);
	//message sent by the nest
	lane.msgNeedPart(p);
	//lane.msgHereAreParts(p,12);
	System.out.println("before pick and execute");
	
        assertEquals(
			"Mock Feeder should have an empty event log before the Gantry scheduler is called. Instead, the mock Feeder's event log reads: "
					+ feeder.log.toString(), 0, feeder.log.size());
        lane.pickAndExecuteAnAction();

	assertTrue(
			"Mock feeder should have received message need parts. Event log: "
					+ feeder.log.toString(), feeder.log
					.containsString("Need parts from lane event encountered"));
}

public void testMessageHereAreParts(){

        lane=new LaneAgent("Lane1",1);
	MockFeeder feeder=new MockFeeder("Feeder");
	MockNest nest=new MockNest("Nest");
	//feeder=new FeederAgent(1);
	MockGantry gantry=new MockGantry("Gantry");
	Part p=new Part(1);
	//MockLane leftLane=new MockLane("Left Lane");
	//MockLane rightLane=new MockLane("Right Lane");

	lane.setFeeder(feeder);
	lane.setNest(nest);
	//message sent by the nest
        lane.msgNeedPart(p);

        assertEquals(
			"Mock Nest should have an empty event log before the Gantry scheduler is called. Instead, the mock Feeder's event log reads: "
					+ nest.log.toString(), 0, nest.log.size());
        lane.pickAndExecuteAnAction();
        assertEquals(
			"Mock Nest should still have an empty event log before the Gantry scheduler is called. Instead, the mock Feeder's event log reads: "
					+ nest.log.toString(), 0, nest.log.size());
        
        List<Part> parts=new ArrayList<Part>();
        for(int i=0;i<8;i++)
        parts.add(p);
        lane.msgHereAreParts(parts);
        
        assertEquals(
			"Mock Nest should still have an empty event log before the Gantry scheduler is called. Instead, the mock Feeder's event log reads: "
					+ nest.log.toString(), 0, nest.log.size());
        lane.pickAndExecuteAnAction();

        assertTrue(
			"Mock nest should have received message here are parts. Event log: "
					+ nest.log.toString(), nest.log
					.containsString("msgHereAreParts"));
       
}    

}
