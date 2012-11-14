package factory.general;

import factory.factory200.kitAssemblyManager.KitAssemblyManager;
import factory.factory200.laneManager.ServerSide.LMServerMain;
import factory.factory201.feederManagement.FeederAgent;
import factory.factory201.feederManagement.GantryAgent;
import factory.factory201.feederManagement.LaneAgent;
import factory.factory201.kitManagement.CameraAgent;
import factory.factory201.kitManagement.ConveyorAgent;
import factory.factory201.kitManagement.KitRobotAgent;
import factory.factory201.partsManagement.NestAgent;
import factory.factory201.partsManagement.PartsAgent;

/**
 *
 * @author Alex
 */
public class AgentMain {

    private static final int FEEDER = 4;
    private static final int LANE = 8;

    public static void main(String args) {

        /*========== Declare all agents and etc. ==========*/
        
        // Misc
        LMServerMain serverMain = new LMServerMain();
        KitAssemblyManager KAM = new KitAssemblyManager();

        // Alex
        KitRobotAgent kitRobot = new KitRobotAgent("Kit Robot");
        CameraAgent camera = new CameraAgent("Camera");
        ConveyorAgent conveyor = new ConveyorAgent("Conveyor");
        
        // Patrick
        PartsAgent partsAgent = new PartsAgent("Parts Agent");
        NestAgent nestAgent = new NestAgent("Nest Agent");

        // Kevin
        FeederAgent[] feeder = new FeederAgent[FEEDER];
        GantryAgent gantry = new GantryAgent(8, "Gantry", serverMain);
        LaneAgent[] lane = new LaneAgent[LANE];
        for (int i = 0; i < FEEDER; i++) {
            if(i < LANE) { 
                lane[i] = new LaneAgent("Lane " + i, serverMain); 
            }
            feeder[i] = new FeederAgent("Feeder " + i, i, serverMain);
        }
        
        
        /*========== Pass proper agents to everyone ==========*/
        
        // Alex
        kitRobot.setAll(camera, conveyor, partsAgent, KAM);
        camera.setAll(KAM, kitRobot, nestAgent);
        conveyor.setAll(KAM, kitRobot);
        
        // Patrick
        partsAgent.setCamera(camera);
        partsAgent.setKitAssemblyManager(KAM);
        partsAgent.setKitRobot(kitRobot);
        partsAgent.setNestInterface(nestAgent);
        nestAgent.setCamera(camera);
        nestAgent.setPartsAgent(partsAgent);
        
        // Kevin
        for (int i = 0, j = 0; i < FEEDER; i++, j++) {
            feeder[i].setGantry(gantry);
            feeder[i].setLeftLane(lane[j]);
            feeder[i].setRightLane(lane[++j]);
            gantry.setFeeder(feeder[i], i);
            
        }
        for (int i = 0; i < LANE; i+=2) {
            lane[i].setFeeder(feeder[i/2]);
            lane[i+1].setFeeder(feeder[i/2]);
        }

        
        /*========== Start all of the threads ==========*/
        
        // Alex
        kitRobot.startThread();
        camera.startThread();
        conveyor.startThread();
        
        //Patrick
        partsAgent.startThread();
        nestAgent.startThread();
        
        // Kevin
        gantry.startThread();
        for (int i = 0; i < FEEDER; i++) {
            if(i < LANE) { 
                lane[i].startThread(); 
            }
            feeder[i].startThread();
        }
        Kit kit = new Kit("Kit 1");
        
        kit.addPart(new Part(1));
        kit.addPart(new Part(2));
        kit.addPart(new Part(3));
        kit.addPart(new Part(4));
        kit.addPart(new Part(5));
        kit.addPart(new Part(6));
        kit.addPart(new Part(7));
        kit.addPart(new Part(8));
        
        partsAgent.msgEmptyKitReady(kit);
    }
}
