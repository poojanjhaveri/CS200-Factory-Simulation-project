
package factory.factory201.partsManagement;

import factory.factory200.kitAssemblyManager.KitAssemblyManager;
import factory.factory201.test.mock.MockKitRobot;
import factory.factory201.feederManagement.LaneAgent;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.kitManagement.CameraAgent;
import factory.factory201.kitManagement.KitRobotAgent;
import factory.factory201.test.mock.MockCamera;
import factory.factory201.test.mock.MockLane;

/**
 *
 * @author polarpatbear
 */
public class PartsAgentMain{
        
        
    
    public void PartsAgentMain(){
        MockLane lane;
        KitAssemblyManager kam; 
        PartsAgent parts;
        KitRobot kitrobot;
        CameraAgent camera;
        NestAgent nest;
        LaneAgent lane0;
        LaneAgent lane1;
        LaneAgent lane2;
        LaneAgent lane3;
        LaneAgent lane4;
        LaneAgent lane5;
        LaneAgent lane6;
        LaneAgent lane7;
        
        kam = new KitAssemblyManager();
        lane = new MockLane("LANE");
        lane0 = new LaneAgent("Lane0");
        lane1 = new LaneAgent("Lane1");
        lane2 = new LaneAgent("Lane2");
        lane3 = new LaneAgent("Lane3");
        lane4 = new LaneAgent("Lane4");
        lane5 = new LaneAgent("Lane5");
        lane6 = new LaneAgent("Lane6");
        lane7 = new LaneAgent("Lane7");
        
        parts = new PartsAgent("PartsAgent");
        kitrobot = new KitRobot("KITROBOT");
        nest = new NestAgent("NestAgent");
        camera = new CameraAgent();
        
        lane0.setNest(nest);
        lane1.setNest(nest);
        lane2.setNest(nest);
        lane3.setNest(nest);
        lane4.setNest(nest);
        lane5.setNest(nest);
        lane6.setNest(nest);
        lane7.setNest(nest);
        
        nest.getNest(0).setLane(lane0);
        nest.getNest(1).setLane(lane1);
        nest.getNest(2).setLane(lane2);
        nest.getNest(3).setLane(lane3);
        nest.getNest(4).setLane(lane4);
        nest.getNest(5).setLane(lane5);
        nest.getNest(6).setLane(lane6);
        nest.getNest(7).setLane(lane7);
        
        parts.setKitRobot(kitrobot);
        parts.setNestInterface(nest);
        //nest.setCameraAgent(camera);
        nest.setPartsAgent(parts);
        parts.setKitAssemblyManager(kam);
        
        parts.startThread();
        nest.startThread();
        
        camera.startThread();
        
        
    }
    /*
    reference
    * to move to a nest
    * kitassemblymanager = kam
    * kam.getPartsRobot().moveToNestCommand(integer for nest)
    * "" "".dropOffParts();
    * "" "" .pickPartCommand();//picks up a part at its current nest
    */
    public static void main(String[] args) {
       MockLane lane;
        KitAssemblyManager kam; 
        PartsAgent parts;
        MockKitRobot kitrobot;
        MockCamera camera;
        NestAgent nest;
        kam = new KitAssemblyManager();
        lane = new MockLane("LANE");
        parts = new PartsAgent();
        kitrobot = new MockKitRobot("KITROBOT");
        nest = new NestAgent();
        camera = new MockCamera("CAMERA");
        
        lane.setNestAgent(nest);
        parts.setKitRobot(kitrobot);
        parts.setNestInterface(nest);
        nest.setCameraAgent(camera);
        nest.setPartsAgent(parts);
        nest.setLane(lane);
        parts.setKitAssemblyManager(kam);
        kitrobot.setPartsAgent(parts);
        camera.setNestAgent(nest);
        
        parts.startThread();
        nest.startThread();
        
        
        kitrobot.msgNeedEmptyKit();
        
    }
    
    
    
    
}
