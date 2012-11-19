package factory.factory201.kitManagement;

import factory.factory201.interfaces.KitRobot;
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
    
    public ConveyorAgentTest() {
        conveyor = new ConveyorAgent("Conveyor");
        kitRobot = new MockKitRobot("Mock Kit Robot");
        conveyor.setKitRobot(kitRobot);
    }

    /**
     * Test of msgNeedEmptyKit method, of class ConveyorAgent.
     */
    @Test
    public void testMsgNeedEmptyKit() {
        conveyor.generateKit("Test Kit");
        conveyor.msgNeedEmptyKit();
        conveyor.pickAndExecuteAnAction();
        assertTrue("Kit should be given after 1 scheduler call." +
                getLogs(), kitRobot.log.containsString("msgHereIsEmptyKit"));
        assertTrue("KitRobot should have only received one message." +
                getLogs(), kitRobot.log.size() == 1);
        assertFalse("Calling the scheduler again should return false.",
                conveyor.pickAndExecuteAnAction());
        conveyor.msgNeedEmptyKit();
        conveyor.pickAndExecuteAnAction();
        assertTrue("KitRobot should have not received another message." +
                getLogs(), kitRobot.log.size() == 1);
        assertTrue("Scheduler should keep running as long as kit is needed.",
                conveyor.pickAndExecuteAnAction());
    }

    /**
     * Test of msgHereIsVerifiedKit method, of class ConveyorAgent.
     */
    @Test
    public void testMsgHereIsVerifiedKit() {
        assertTrue("Kit List should be empty.", conveyor.getKitList().isEmpty());
        conveyor.msgHereIsVerifiedKit(new Kit("Test Kit"));
        assertFalse("Accepting a verified kit does not use the scheduler. "
                + "The scheduler should return false.",
                conveyor.pickAndExecuteAnAction());
        assertTrue("Kit List size should be 1.", conveyor.getKitList().size() == 1);
    }
//
//    /**
//     * Test of pickAndExecuteAnAction method, of class ConveyorAgent.
//     */
//    @Test
//    public void testPickAndExecuteAnAction() {
//        System.out.println("pickAndExecuteAnAction");
//        ConveyorAgent instance = null;
//        boolean expResult = false;
//        boolean result = instance.pickAndExecuteAnAction();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateKit method, of class ConveyorAgent.
//     */
//    @Test
//    public void testGenerateKit_int() {
//        System.out.println("generateKit");
//        int num = 0;
//        ConveyorAgent instance = null;
//        instance.generateKit(num);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateKit method, of class ConveyorAgent.
//     */
//    @Test
//    public void testGenerateKit_String() {
//        System.out.println("generateKit");
//        String name = "";
//        ConveyorAgent instance = null;
//        instance.generateKit(name);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeKit method, of class ConveyorAgent.
//     */
//    @Test
//    public void testRemoveKit() {
//        System.out.println("removeKit");
//        String name = "";
//        ConveyorAgent instance = null;
//        instance.removeKit(name);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeAllVerifiedKits method, of class ConveyorAgent.
//     */
//    @Test
//    public void testRemoveAllVerifiedKits() {
//        System.out.println("removeAllVerifiedKits");
//        ConveyorAgent instance = null;
//        instance.removeAllVerifiedKits();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setKitRobot method, of class ConveyorAgent.
//     */
//    @Test
//    public void testSetKitRobot() {
//        System.out.println("setKitRobot");
//        KitRobot agent = null;
//        ConveyorAgent instance = null;
//        instance.setKitRobot(agent);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of testAddKit method, of class ConveyorAgent.
//     */
//    @Test
//    public void testTestAddKit() {
//        System.out.println("testAddKit");
//        Kit k = null;
//        ConveyorAgent instance = null;
//        instance.testAddKit(k);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
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
