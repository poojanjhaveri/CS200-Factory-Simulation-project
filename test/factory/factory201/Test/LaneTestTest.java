/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.Test;

import factory.factory201.feederManagement.*;
import factory.factory201.feederManagement.*;
import factory.general.Part;
import junit.framework.TestCase;

/**
 *
 * @author Kevin
 */

public class LaneTestTest extends TestCase{
public LaneAgent lane;

public void testneedParts(){
	
	//1 is the lane index number
	
	//this constructor intitializes the lane with 0 quantity of all the 8 parts in its list
	lane=new LaneAgent(1);
	MockFeeder feeder=new MockFeeder("Feeder");
	//feeder=new FeederAgent(1);
	MockGantry gantry=new MockGantry("Gantry");
	Part p=new Part(1);
	//MockLane leftLane=new MockLane("Left Lane");
	//MockLane rightLane=new MockLane("Right Lane");
	
	lane.setFeeder(feeder);
	lane.msgNeedPart(p);
	lane.pickAndExecuteAnAction();
	
	assertTrue(
			"Mock feeder should have received message here are parts. Event log: "
					+ feeder.log.toString(), feeder.log
					.containsString("Need parts from lane event encountered"));

}

public void testsendPartsAfterRequest(){
	
	lane=new LaneAgent(1);
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
	lane.msgHereAreParts(p,12);
	lane.pickAndExecuteAnAction();
	
	assertTrue(
			"Mock nest should have received message here are parts. Event log: "
					+ nest.log.toString(), nest.log
					.containsString("Received parts event encountered"));
}

public void testLaneInitializedWithNoParts(){
	
	lane=new LaneAgent(1);
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
	//lane.msgNeedPart(p);
	//lane.msgHereAreParts(p,12);
	System.out.println("before pick and execute");
	lane.pickAndExecuteAnAction();
	
	assertTrue(
			"Mock feeder should have received message need parts. Event log: "
					+ feeder.log.toString(), feeder.log
					.containsString("Need parts from lane event encountered"));
}
	
}
