package factory.general;

import factory.factory200.kitAssemblyManager.KitAssemblyManager;
import factory.factory201.feederManagement.FeederAgent;
import factory.factory201.feederManagement.GantryAgent;
import factory.factory201.feederManagement.LaneAgent;
import factory.factory201.kitManagement.CameraAgent;
import factory.factory201.kitManagement.ConveyorAgent;
import factory.factory201.kitManagement.KitRobotAgent;
import factory.factory201.partsManagement.NestAgent;
import factory.factory201.partsManagement.PartsAgent;

/**
 * @author Alex Young
 * @version 1
 */
public class AgentMain {

    private static final int FEEDER = 4;
    private static final int LANE = 8;

    public static void main(String[] args) {

        /*========== Declare all agents and etc. ==========*/

        // Misc
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
        GantryAgent gantry = new GantryAgent(8, "Gantry");
        LaneAgent[] lane = new LaneAgent[LANE];
        for (int i = 0; i < LANE; i++) {
            if (i < FEEDER) {
                feeder[i] = new FeederAgent("Feeder " + i, i);
            }
            lane[i] = new LaneAgent("Lane " + i);
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
        for (int i = 0; i < 8; i++) {
            nestAgent.getNest(i).setLane(lane[i]);
        }

        // Kevin
        for (int i = 0, j = 0; i < FEEDER; i++, j++) {
            
            feeder[i].setGantry(gantry);
            feeder[i].setLeftLane(lane[j]);
            feeder[i].setRightLane(lane[++j]);
            gantry.setFeeder(feeder[i], i);
        }

        for (int i = 0; i < LANE; i += 2) {
            lane[i].setFeeder(feeder[i / 2]);
            lane[i].setNest(nestAgent);
            lane[i + 1].setFeeder(feeder[i / 2]);
            lane[i + 1].setNest(nestAgent);
        }


        /*========== Start all of the threads ==========*/

        // Alex
        camera.startThread();
        conveyor.startThread();
        conveyor.generateKit(10);
        kitRobot.startThread();

        //Patrick
        partsAgent.startThread();
        nestAgent.startThread();

        // Kevin
        gantry.startThread();
        for (int i = 0; i < LANE; i++) {
            if (i < FEEDER) {
                feeder[i].startThread();
            }
            lane[i].startThread();
        }

        /*========== Misc. ==========*/

        // Patrick
        Kit kit = new Kit("Test Kit");
        for (int i = 1; i < 9; i++) {
            kit.addPart(new Part(i));
        }

        partsAgent.msgHereIsKit(kit); // The primary agent
            // The message here tells the parts agent to start - 
            // The parts agent requests parts from the nest, nest asks lanes, laneagent asks feederagent
            // Feederagent asks GantryAgent, GantryAgent gets parts from bins, gives back to feederagent
            // Eventually coming back to the PartsAgent who will put the parts into the empty kit, and the
            // PartsAgent requests from the empty kit from kitrobot agent who then asks conveyor agent
            // See Interaction Diagram for better description
        
        if(false) {
            for (int i = 0; i < LANE; i++) {
                if (i < FEEDER) {
                    feeder[i].print = false;
                }
                lane[i].print = false;
            }
            gantry.print = false;
            nestAgent.print = false;
            partsAgent.print = false;
        }

    } // END main
} // END AgentMain
