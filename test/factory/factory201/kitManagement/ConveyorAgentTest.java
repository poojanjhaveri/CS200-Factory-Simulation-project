package factory.factory201.kitManagement;

import factory.factory201.test.mock.MockKitRobot;
import factory.general.Kit;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Alex Young <alexyoung1992@gmail.com>
 * @version 1
 * @brief Unit test for Conveyor Agent
 */
public class ConveyorAgentTest extends TestCase {
    
    private ConveyorAgent conveyor;
    private MockKitRobot kitRobot;

    /**
     * Test of msgNeedEmptyKit method, of class ConveyorAgent.
     */
    @Test
    public void testMsgNeedEmptyKit() {
        this.initialize();
        
        conveyor.msgGenerateKits(1);
        conveyor.pickAndExecuteAnAction();
        conveyor.msgNeedEmptyKit();
        conveyor.pickAndExecuteAnAction();
        assertTrue("Kit should be given after 1 scheduler call." +
                getLogs(), kitRobot.log.containsString("msgHereIsEmptyKit"));
        assertTrue("KitRobot should have only received one message.", 
                kitRobot.log.size() == 1);
        assertFalse("Calling the scheduler again should return false.",
                conveyor.pickAndExecuteAnAction());
        conveyor.msgNeedEmptyKit();
        conveyor.pickAndExecuteAnAction();
        assertTrue("KitRobot should have not received another message.", 
                kitRobot.log.size() == 1);
//        assertTrue("Scheduler should keep running as long as kit is needed.",
//                conveyor.pickAndExecuteAnAction());
    }

    /**
     * Test of msgHereIsVerifiedKit method, of class ConveyorAgent.
     */
    @Test
    public void testMsgHereIsVerifiedKit() {
        this.initialize();
        
        assertTrue("Kit List should be empty.", conveyor.getKitList().isEmpty());
        conveyor.msgHereIsVerifiedKit(new Kit("Test Kit"));
        assertFalse("Accepting a verified kit does not use the scheduler. "
                + "The scheduler should return false.",
                conveyor.pickAndExecuteAnAction());
        assertTrue("Kit List size should be 1.", conveyor.getKitList().size() == 1);
    }
    
    private void initialize() {
        conveyor = new ConveyorAgent("Conveyor");
        kitRobot = new MockKitRobot("Mock Kit Robot");
        conveyor.setKitRobot(kitRobot);
    }
    
    private String getLogs() {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        sb.append("-------Kit Robot Log-------");
        sb.append(newLine);
        sb.append(kitRobot.log.toString());
        sb.append(newLine);
        sb.append("-------End Kit Robot Log-------");

        return sb.toString();
    }
}
