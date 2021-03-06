package factory.general;

import factory.factory201.feederManagement.FeederAgent;
import factory.factory201.feederManagement.GantryAgent;
import factory.factory201.feederManagement.LaneAgent;
import factory.factory201.kitManagement.CameraAgent;
import factory.factory201.kitManagement.ConveyorAgent;
import factory.factory201.kitManagement.KitRobotAgent;
import factory.factory201.partsManagement.NestAgent;
import factory.factory201.partsManagement.PartsAgent;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Example usage of all the agents working together. Agents only depend
 * on having instances of KitAssemblyManager and GantryRobotManager.
 * @author Alex Young, David Zhang
 * @version 1
 */
public class AgentMain {

    private static final boolean PATRICK = true;
    private static final boolean KEVIN = false;
    private static final boolean ALEX = true;
    
    private static final int FEEDER = 4;
    private static final int LANE = 8;

    public static void main(String[] args) {
        /*========== Declare all agents and etc. ==========*/

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
            laneAgents[i] = new LaneAgent("Lane " + i,i);
        }

        
        
        
//        if (!PATRICK) {
//            nestAgent.print = false;
//            partsAgent.print = false;
//        }
//        if (!KEVIN) {
//            for (int i = 0; i < LANE; i++) {
//                if (i < FEEDER) {
//                    feederAgents[i].print = false;
//                }
//                laneAgents[i].print = false;
//            }
//            gantryAgent.print = false;
//        }
//        if (!ALEX) {
//            kitRobotAgent.print = false;
//            conveyorAgent.print = false;
//            cameraAgent.print = false;
//        }
        
        
        
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
//        conveyorAgent.generateKit(10); // * This generates 10 new kits, among other things if you pass string... *
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

        // Get kit from somewhere
        // * 
        Kit kit = new Kit("Test Kit");
        for (int i = 1; i < 9; i++) {

            //kit.addPart(new Part("Name", "Description")); // This is a kit that has actual parts...
        //testing agents
            kit.addPart(new Part("i", "i+1"));
        }
        conveyorAgent.msgGenerateKits(10);

        // Officially start the agent interaction sequence!
        ArrayList<Kit> kits = new ArrayList<Kit>();
        kits.add(kit);
        kits.add(kit);
        kits.add(kit);
        partsAgent.msgHereIsKit(kits); // The primary agent
        // The message here tells the parts agent to start - 
        // The parts agent requests parts from the nest, nest asks lanes, laneagent asks feederagent
        // Feederagent asks GantryAgent, GantryAgent gets parts from bins, gives back to feederagent
        // Eventually coming back to the PartsAgent who will put the parts into the empty kit, and the
        // PartsAgent requests from the empty kit from kitrobot agent who then asks conveyor agent
        // See Interaction Diagram for better description

        /*========== Turn on or off debugging (print statements) ==========*/
        // Just for debugging; put 'false' to turn off print statements

        if (PATRICK) {
            nestAgent.print = true;
            partsAgent.print = true;
        }
        if(KEVIN) {
            for (int i = 0; i < LANE; i++) {
                if (i < FEEDER) {
                    feederAgents[i].print = true;
                }
                laneAgents[i].print = true;
            }
            gantryAgent.print = true;
        }
        if(ALEX) {
            kitRobotAgent.print = true;
            conveyorAgent.print = true;
            cameraAgent.print = true;
        }


    } // END main
} // END AgentMain
