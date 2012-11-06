package factory.factory201.kitManagement;

import factory.factory200.kitAssemblyManager.KitAssemblyManager;
import factory.factory201.test.mock.MockNest;
import factory.factory201.test.mock.MockParts;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Alex Young
 */
public class V0KitRobotCameraConveyor {

    public static void main(String[] args) {
        
        final KitRobotAgent kitRobot = new KitRobotAgent("Kit Robot");
        CameraAgent camera = new CameraAgent("Camera");
        ConveyorAgent conveyor = new ConveyorAgent("Conveyor");
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
        conveyor.generateKit(10);
        
        kitRobot.startThread();
        camera.startThread();
        conveyor.startThread();
//        kitRobot.msgNeedEmptyKit();
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                kitRobot.msgKitIsFull();
            }
        }, 10000);
    }
}
