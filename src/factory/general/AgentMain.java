
package factory.general;

import factory.factory200.kitAssemblyManager.KitAssemblyManager;
import factory.factory201.feederManagement.FeederAgent;
import factory.factory201.feederManagement.GantryAgent;
import factory.factory201.feederManagement.LaneAgent;
import factory.factory201.kitManagement.CameraAgent;
import factory.factory201.kitManagement.ConveyorAgent;
import factory.factory201.kitManagement.KitRobotAgent;
import factory.factory201.test.mock.MockNest;
import factory.factory201.test.mock.MockParts;

/**
 *
 * @author Alex
 */
public class AgentMain {    
    
    private static final int FEEDER = 4;
    private static final int LANE = 8;
    
    public static void main(String args) {
        
        KitRobotAgent kitRobot = new KitRobotAgent("Kit Robot");
        CameraAgent camera = new CameraAgent("Camera");
        ConveyorAgent conveyor = new ConveyorAgent("Conveyor");
        MockParts parts = new MockParts("Mock Parts Robot");
        MockNest nest = new MockNest("Mock Nest Agent");
        KitAssemblyManager KAM = new KitAssemblyManager();
        
//        FeederAgent[] feeder = new FeederAgent[FEEDER];
//        LaneAgent[] lane = new LaneAgent[LANE];
//	GantryAgent gantry;
//        
//        for(int i = 0; i < LANE; i++) {
//            lane[i] = new LaneAgent("Lane " + i, serverMain);
//        }
//        for(int i = 0; i < FEEDER; i++) {
//            feeder[i] = new FeederAgent("Feeder " + i, i+1, serverMain);
//        }
//        for(int i = 0; i < LANE; i++) {
//            lane[i].setFeeder(d);
//        }
        
//        FeederAgent feeder1;
//	FeederAgent feeder2;
//	FeederAgent feeder3;
//	FeederAgent feeder4;
//        LaneAgent lane1;
//	LaneAgent lane2;
//	LaneAgent lane3;
//	LaneAgent lane4;
//	LaneAgent lane5;
//	LaneAgent lane6;
//	LaneAgent lane7;
//	LaneAgent lane8;
//	
//        
//        gantry=new GantryAgent(8,"Gantry",serverMain);
//		
//		lane1=new LaneAgent("Lane 1",1,2,serverMain);
//		lane2=new LaneAgent("Lane 2",3,4,serverMain);
//		lane3=new LaneAgent("Lane 3",5,6,serverMain);
//		lane4=new LaneAgent("Lane 4",7,8,serverMain);
//                feeder1=new FeederAgent("Feeder 1",1,serverMain);
//		feeder2=new FeederAgent("Feeder 2",2,serverMain);
//		feeder3=new FeederAgent("Feeder 3",3,serverMain);
//		feeder4=new FeederAgent("Feeder 4",4,serverMain);
//                lane1.setFeeder(feeder1);
//		lane2.setFeeder(feeder2);
//
//		lane3.setFeeder(feeder3);
//		lane4.setFeeder(feeder4);
//                feeder1.setGantry(gantry);
//		feeder2.setGantry(gantry);
//		feeder3.setGantry(gantry);
//		feeder4.setGantry(gantry);
//                feeder1.setLane(lane1);
//		feeder2.setLane(lane2);
//		feeder3.setLane(lane3);
//		feeder4.setLane(lane4);
//                gantry.setFeeder(feeder1,1);
//		gantry.setFeeder(feeder2,2);
//		gantry.setFeeder(feeder3,3);
//		gantry.setFeeder(feeder4,4);
//		
//		gantry.startThread();
//		
//		feeder1.startThread();
//		feeder2.startThread();
//		feeder3.startThread();
//		feeder4.startThread();
//		
//		lane1.startThread();
//		lane2.startThread();
//		lane3.startThread();
//		lane4.startThread();

        kitRobot.startThread();
        camera.startThread();
        conveyor.startThread();
    }
}
