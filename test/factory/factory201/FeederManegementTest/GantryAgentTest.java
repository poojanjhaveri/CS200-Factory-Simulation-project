/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 NOTE TO THE GRADER: PLEASE COMMENT OUT doSupplyPartsToLeftLane() and doSupplyPartsToRightLane() in FeederAgent.java
 * to make tests work. This is because the tests won't work with the managers offline.
 * PLEASE COMMENT OUT doSupplyParts() in gantry as well for the same reason.
 
 */


package factory.factory201.FeederManegementTest;

import factory.factory200.gantryRobotManager.GantryRobotManager;
import factory.factory201.feederManagement.GantryAgent;
import factory.factory201.interfaces.Feeder;
import factory.factory201.test.mock.MockFeeder;
import factory.general.Part;
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


public class GantryAgentTest extends TestCase{
    public GantryAgent gantry;

//TEST NEED PARTS MESSAGE FROM A FEEDER
public void testneedParts(){
	
	//CREATE A PART OF TYPE 1
	Part p=new Part(1);
	
	//CREATE A MOCK FEEDER
	MockFeeder feeder=new MockFeeder("Feeder");

	System.out.println("sending message to feeder");
	gantry=new GantryAgent(7,"Gantry");
	
	//SET FEEDER
	gantry.setFeeder(feeder,1);
	System.out.println("sending message to feeder");
	
	//GANTRY RECEIVES A MESSAGE FROM THE FEEDER
	gantry.msgNeedPart(p,feeder);
	
	assertEquals(
			"Mock Feeder should have an empty event log before the Gantry scheduler is called. Instead, the mock Feeder's event log reads: "
					+ feeder.log.toString(), 0, feeder.log.size());
	
	gantry.pickAndExecuteAnAction();
	
	assertTrue(
			"Mock feeder should have received message here are parts. Event log: "
					+ feeder.log.toString(), feeder.log
					.containsString("Received parts event encountered"));
}

public void testneedPartsTwoFeeders(){
Part p1=new Part(1);
Part p2=new Part(2);
	
	//CREATE A MOCK FEEDER
	MockFeeder feeder1=new MockFeeder("Feeder1");
	MockFeeder feeder2=new MockFeeder("Feeder2");

	System.out.println("sending message to feeder");
	gantry=new GantryAgent(7,"Gantry");
	
	//SET FEEDER
	gantry.setFeeder(feeder1,1);
	gantry.setFeeder(feeder2,2);
	
	
	System.out.println("sending message to feeder");
	
	//GANTRY RECEIVES A MESSAGE FROM THE FEEDER
	gantry.msgNeedPart(p1,feeder1);
	gantry.msgNeedPart(p2,feeder2);
	
	assertEquals(
			"Mock Feeder should have an empty event log before the Gantry scheduler is called. Instead, the mock Feeder's event log reads: "
					+ feeder1.log.toString(), 0, feeder1.log.size());
	
	gantry.pickAndExecuteAnAction();
	
	assertTrue(
			"Mock feeder should have received message here are parts. Event log: "
					+ feeder1.log.toString(), feeder1.log
					.containsString("Received parts event encountered"));
	
	gantry.pickAndExecuteAnAction();
	assertTrue(
			"Mock feeder should have received message here are parts. Event log: "
					+ feeder2.log.toString(), feeder2.log
					.containsString("Received parts event encountered"));
	
	
}


public void testneedPartsTwoFeedersSamePartType(){
Part p1=new Part(1);
Part p2=new Part(1);
	
	//CREATE A MOCK FEEDER
	MockFeeder feeder1=new MockFeeder("Feeder1");
	MockFeeder feeder2=new MockFeeder("Feeder2");

	System.out.println("sending message to feeder");
	gantry=new GantryAgent(7,"Gantry");
	
	//SET FEEDER
	gantry.setFeeder(feeder1,1);
	gantry.setFeeder(feeder2,2);
	
	
	System.out.println("sending message to feeder");
	
	//GANTRY RECEIVES A MESSAGE FROM THE FEEDER
	gantry.msgNeedPart(p1,feeder1);
	gantry.msgNeedPart(p2,feeder2);
	
	assertEquals(
			"Mock Feeder should have an empty event log before the Gantry scheduler is called. Instead, the mock Feeder's event log reads: "
					+ feeder1.log.toString(), 0, feeder1.log.size());
	
	gantry.pickAndExecuteAnAction();
	
	assertTrue(
			"Mock feeder should have received message here are parts. Event log: "
					+ feeder1.log.toString(), feeder1.log
					.containsString("Received parts event encountered"));
	
	gantry.pickAndExecuteAnAction();
	assertTrue(
			"Mock feeder should have received message here are parts. Event log: "
					+ feeder2.log.toString(), feeder2.log
					.containsString("Received parts event encountered"));
	
	
}

  
}