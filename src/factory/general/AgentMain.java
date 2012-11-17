package factory.general;

import factory.factory200.gantryRobotManager.GantryRobotManager;
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
 * @brief Example usage of all the agents working together.
 * Agents only depend on having instances of KitAssemblyManager and GantryRobotManager.
 * @author Alex Young, David Zhang
 * @version 1
 */
public class AgentMain {

    private static final boolean TEST_MODE = true;
    private static final int FEEDER = 4;
    private static final int LANE = 8;

    // TODO: This isn't ready yet because agents are still changing their interaction ways...
    // See main() for an update on how all the agents work.
    // Temporary: the * in comments means it is a place that the server coder will change depending on manager stuff
//    public AgentMain() {
//    	declareAgents();
//    	connectAgentsAndManagers();
//        startAgentThreads();
//        startInteractionSequence();
//
//        debugIfNecessary();
//    }
//    
//    private void declareAgents() {
//        /*========== Declare all agents and etc. ==========*/
//        // Misc - pass in the appropriate KAM and GRM
//        KitAssemblyManager KAM = new KitAssemblyManager(); // * Modify
//        GantryRobotManager GRM = new GantryRobotManager(); // * Modify
//
//        // Alex
//        KitRobotAgent kitRobot = new KitRobotAgent("Kit Robot");
//        CameraAgent camera = new CameraAgent("Camera");
//        ConveyorAgent conveyor = new ConveyorAgent("Conveyor");
//
//        // Patrick
//        PartsAgent partsAgent = new PartsAgent("Parts Agent");
//        NestAgent nestAgent = new NestAgent("Nest Agent");
//
//        // Kevin
//        FeederAgent[] feeder = new FeederAgent[FEEDER];
//        GantryAgent gantry = new GantryAgent(8, "Gantry");
//        LaneAgent[] lane = new LaneAgent[LANE];
//        for (int i = 0; i < LANE; i++) {
//            if (i < FEEDER) {
//                feeder[i] = new FeederAgent("Feeder " + i, i);
//            }
//            lane[i] = new LaneAgent("Lane " + i);
//        }
//    }
//
//    private void connectAgentsAndManagers() {
//        /*========== Pass proper agents to everyone ==========*/
//
//        // Alex
//        kitRobot.setAll(camera, conveyor, partsAgent, KAM);
//        camera.setAll(KAM, kitRobot, nestAgent);
//        conveyor.setAll(KAM, kitRobot);
//
//        // Patrick
//        partsAgent.setCamera(camera);
//        partsAgent.setKitAssemblyManager(KAM);
//        partsAgent.setKitRobot(kitRobot);
//        partsAgent.setNestInterface(nestAgent);
//        nestAgent.setCamera(camera);
//        nestAgent.setPartsAgent(partsAgent);
//        for (int i = 0; i < 8; i++) {
//            nestAgent.getNest(i).setLane(lane[i]);
//        }
//
//        // Kevin
//        gantry.setGantryRobotManager(GRM);
//        for (int i = 0, j = 0; i < FEEDER; i++, j++) {
//            
//            feeder[i].setGantry(gantry);
//            feeder[i].setLeftLane(lane[j]);
//            feeder[i].setRightLane(lane[++j]);
//            gantry.setFeeder(feeder[i], i);
//        }
//
//        for (int i = 0; i < LANE; i += 2) {
//            lane[i].setFeeder(feeder[i / 2]);
//            lane[i].setNest(nestAgent);
//            lane[i + 1].setFeeder(feeder[i / 2]);
//            lane[i + 1].setNest(nestAgent);
//        }
//    }
//
//    private void startAgentThreads() {
//        /*========== Start all of the threads ==========*/
//
//        // Alex
//        camera.startThread();
//        conveyor.startThread();
//        conveyor.generateKit(10); // * Modify
//        kitRobot.startThread();
//
//        //Patrick
//        partsAgent.startThread();
//        nestAgent.startThread();
//
//        // Kevin
//        gantry.startThread();
//        for (int i = 0; i < LANE; i++) {
//            if (i < FEEDER) {
//                feeder[i].startThread();
//            }
//            lane[i].startThread();
//        }
//    }
//
//    private void startInteractionSequence() {
//        // Get kit from somewhere
//        // * 
//        Kit kit = new Kit("Test Kit"); // This is required for...
//        for (int i = 1; i < 9; i++) {
//            kit.addPart(new Part(i)); // This is a kit that has actual parts...
//        }
//
//        // Officially start the agent interaction sequence!
//        partsAgent.msgHereIsKit(kit); // The primary agent
//            // The message here tells the parts agent to start - 
//            // The parts agent requests parts from the nest, nest asks lanes, laneagent asks feederagent
//            // Feederagent asks GantryAgent, GantryAgent gets parts from bins, gives back to feederagent
//            // Eventually coming back to the PartsAgent who will put the parts into the empty kit, and the
//            // PartsAgent requests from the empty kit from kitrobot agent who then asks conveyor agent
//            // See Interaction Diagram for better description
//    }
//
//    private void debugIfNecessary() {
//        /*========== Turn on or off debugging (print statements) ==========*/
//        // Just for debugging; put 'true' to turn off print statements
//        if (!TEST_MODE) {
//            for (int i = 0; i < LANE; i++) {
//                if (i < FEEDER) {
//                    feeder[i].print = false;
//                }
//                lane[i].print = false;
//            }
//            gantry.print = false;
//            nestAgent.print = false;
//            partsAgent.print = false;
//        }
//    }

    public static void main(String[] args) {

        /*========== Declare all agents and etc. ==========*/

        // Misc - pass in the appropriate KAM and GRM
        KitAssemblyManager KAM = new KitAssemblyManager(); // *
        GantryRobotManager GRM = new GantryRobotManager(); // *

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

        /*========== Pass proper agents to everyone (connect agents and managers) ==========*/

        // Alex
        kitRobot.setAll(camera, conveyor, partsAgent);
        camera.setAll(kitRobot, nestAgent);
        conveyor.setKitRobot(kitRobot);

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
        gantry.setGantryRobotManager(GRM);
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
        conveyor.generateKit(10); // * This generates 10 new kits, among other things if you pass string... *
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

        /*========== Start Agent Interaction Sequence based on a Kit ==========*/

        // Patrick

        // Get kit from somewhere
        // * 
        Kit kit = new Kit("Test Kit");
        for (int i = 1; i < 9; i++) {
            kit.addPart(new Part(i)); // This is a kit that has actual parts...
        }

        // Officially start the agent interaction sequence!
        partsAgent.msgHereIsKit(kit); // The primary agent
            // The message here tells the parts agent to start - 
            // The parts agent requests parts from the nest, nest asks lanes, laneagent asks feederagent
            // Feederagent asks GantryAgent, GantryAgent gets parts from bins, gives back to feederagent
            // Eventually coming back to the PartsAgent who will put the parts into the empty kit, and the
            // PartsAgent requests from the empty kit from kitrobot agent who then asks conveyor agent
            // See Interaction Diagram for better description
        
        /*========== Turn on or off debugging (print statements) ==========*/
        // Just for debugging; put 'true' to turn off print statements
        if (TEST_MODE) {
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
