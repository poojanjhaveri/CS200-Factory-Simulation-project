package factory.factory201.kitManagement;

import factory.factory200.kitAssemblyManager.KitAssemblyManager;
import factory.factory201.Test.mock.MockNest;
import factory.factory201.Test.mock.MockParts;

/**
 * @author Alex Young
 */
public class KitRobotMain {

    public static void main(String[] args) {
        
        KitRobotAgent kitRobot = new KitRobotAgent();
        CameraAgent camera = new CameraAgent();
        ConveyorAgent conveyor = new ConveyorAgent();
        MockParts parts = new MockParts("Mock Parts Robot");
        MockNest nest = new MockNest("Mock Nest Agent");
        KitAssemblyManager KAM = new KitAssemblyManager();
        
        kitRobot.setCamera(camera);
        kitRobot.setConveyor(conveyor);
        kitRobot.setPartsAgent(parts);
        kitRobot.setKitAssemblyManager(KAM);
        camera.setKitRobotAgent(kitRobot);
        camera.setNestAgent(nest);
        camera.setKitAssemblyManager(KAM);
        conveyor.setKitRobotAgent(kitRobot);
        conveyor.setKitAssemblyManager(KAM);
        
        kitRobot.startThread();
        camera.startThread();
        conveyor.startThread();
    }
}
