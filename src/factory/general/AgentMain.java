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

    public static void main(String[] args) {
        /*========== Declare all agents and etc. ==========*/

        // Misc - pass in the appropriate KAM and GRM
        KitAssemblyManager KAM = new KitAssemblyManager(); // *
        GantryRobotManager GRM = new GantryRobotManager(); // *

        // Alex
        KitRobotAgent kitRobotAgent = new KitRobotAgent("Kit Robot");
        CameraAgent cameraAgent = new CameraAgent("Camera");
        ConveyorAgent conveyorAgent = new ConveyorAgent("Conveyor");

        // Patrick
        PartsAgent partsAgent = new PartsAgent("Parts Agent");
        NestAgent nestAgent = new NestAgent("Nest Agent");

        // Kevin
        FeederAgent[] feederAgents = new FeederAgent[FEEDER];
        GantryAgent gantryAgent = new GantryAgent(8, "Gantry");
        LaneAgent[] laneAgents = new LaneAgent[LANE];
        for (int i = 0; i < LANE; i++) {
            if (i < FEEDER) {
                feederAgents[i] = new FeederAgent("Feeder " + i, i);
            }
            laneAgents[i] = new LaneAgent("Lane " + i);
        }

        /*========== Pass proper agents to everyone (connect agents and managers) ==========*/

        // Alex
        kitRobotAgent.setAll(cameraAgent, conveyorAgent, partsAgent);
        cameraAgent.setAll(kitRobotAgent, nestAgent);
        conveyorAgent.setKitRobot(kitRobotAgent);

        // Patrick
        partsAgent.setCamera(cameraAgent);
//        partsAgent.setKitAssemblyManager(KAM);
        partsAgent.setKitRobot(kitRobotAgent);
        partsAgent.setNestInterface(nestAgent);
        nestAgent.setCamera(cameraAgent);
        nestAgent.setPartsAgent(partsAgent);
        for (int i = 0; i < 8; i++) {
            nestAgent.getNest(i).setLane(laneAgents[i]);
        }

        // Kevin
        gantryAgent.setGantryRobotManager(GRM);
        for (int i = 0, j = 0; i < FEEDER; i++, j++) {
            
            feederAgents[i].setGantry(gantryAgent);
            feederAgents[i].setLeftLane(laneAgents[j]);
            feederAgents[i].setRightLane(laneAgents[++j]);
            gantryAgent.setFeeder(feederAgents[i], i);
        }

        for (int i = 0; i < LANE; i += 2) {
            laneAgents[i].setFeeder(feederAgents[i / 2]);
            laneAgents[i].setNest(nestAgent);
            laneAgents[i + 1].setFeeder(feederAgents[i / 2]);
            laneAgents[i + 1].setNest(nestAgent);
        }


        /*========== Start all of the threads ==========*/

        // Alex
        cameraAgent.startThread();
        conveyorAgent.startThread();
        conveyorAgent.generateKit(10); // * This generates 10 new kits, among other things if you pass string... *
        kitRobotAgent.startThread();

        //Patrick
        partsAgent.startThread();
        nestAgent.startThread();

        // Kevin
        gantryAgent.startThread();
        for (int i = 0; i < LANE; i++) {
            if (i < FEEDER) {
                feederAgents[i].startThread();
            }
            laneAgents[i].startThread();
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
                    feederAgents[i].print = false;
                }
                laneAgents[i].print = false;
            }
            gantryAgent.print = false;
            nestAgent.print = false;
            partsAgent.print = false;
        }

    } // END main
} // END AgentMain
