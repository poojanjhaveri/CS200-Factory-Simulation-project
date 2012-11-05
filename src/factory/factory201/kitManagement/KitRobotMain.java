package factory.factory201.kitManagement;

import factory.factory200.kitAssemblyManager.KitAssemblyManager;

/**
 * @author Alex Young
 */
public class KitRobotMain {

    public static void main(String[] args) {
        
        KitRobotAgent kitRobot = new KitRobotAgent();
        CameraAgent camera = new CameraAgent();
        ConveyorAgent conveyor = new ConveyorAgent();
        KitAssemblyManager KAM = new KitAssemblyManager();
        
        kitRobot.setCamera(camera);
        kitRobot.setConveyor(conveyor);
        kitRobot.setPartsAgent(null);
        kitRobot.setKitAssemblyManager(KAM);
        camera.setKitRobotAgent(kitRobot);
        camera.setNestAgent(null);
        camera.setKitAssemblyManager(KAM);
        conveyor.setKitRobotAgent(kitRobot);
        conveyor.setKitAssemblyManager(KAM);
        
        kitRobot.startThread();
        camera.startThread();
        conveyor.startThread();
    }
}
