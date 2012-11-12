
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
        MockKitRobot kitrobot;
        CameraAgent camera;
        NestAgent nest;
        kam = new KitAssemblyManager();
        lane = new MockLane("LANE");
        parts = new PartsAgent();
        kitrobot = new MockKitRobot("KITROBOT");
        nest = new NestAgent();
        camera = new CameraAgent();
        
        lane.setNestAgent(nest);
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
