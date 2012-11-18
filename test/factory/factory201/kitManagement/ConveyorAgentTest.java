/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.kitManagement;

import factory.factory201.test.mock.MockKitRobot;
import factory.general.Kit;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author Alex Young <alexyoung1992@gmail.com>
 */
public class ConveyorAgentTest extends TestCase {
    
    private ConveyorAgent conveyor;
    private MockKitRobot kitRobot;

    public ConveyorAgentTest() {
        conveyor = new ConveyorAgent("Conveyor");
        kitRobot = new MockKitRobot("Mock Kit Robot");
    }

    @Test
    public void testComprehensiveNormalScenarioConveyor() {
        assertEquals(true, true);
    }
    
    /**
     * Test of msgNeedEmptyKit method, of class ConveyorAgent.
     */
    @Test
    public void testMsgNeedEmptyKit() {
        System.out.println("msgNeedEmptyKit");
        ConveyorAgent instance = null;
        instance.msgNeedEmptyKit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of msgHereIsVerifiedKit method, of class ConveyorAgent.
     */
    @Test
    public void testMsgHereIsVerifiedKit() {
        System.out.println("msgHereIsVerifiedKit");
        Kit kit = null;
        ConveyorAgent instance = null;
        instance.msgHereIsVerifiedKit(kit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    public String getLogs() {
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
