package factory.factory201.kitManagement;

/**
 * @author Alex Young
 */
public class KitRobotMain {

    public static void main(String[] args) {
        
        KitRobotAgent kitRobot = new KitRobotAgent();
        CameraAgent camera = new CameraAgent();
        ConveyorAgent conveyor = new ConveyorAgent();
        
        kitRobot.setCamera(camera);
        kitRobot.setConveyor(conveyor);
        kitRobot.setPartsAgent(null);
        camera.setKitRobotAgent(kitRobot);
        camera.setNestAgent(null);
        conveyor.setKitRobotAgent(kitRobot);
        
        kitRobot.startThread();
        camera.startThread();
        conveyor.startThread();
    }
}
