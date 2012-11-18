/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.kitManagement;

import factory.factory201.test.mock.MockKitRobot;
import factory.factory201.test.mock.MockNest;
import factory.general.Kit;
import factory.general.Nest;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Alex Young <alexyoung1992@gmail.com>
 */
public class CameraAgentTest {
    
    private CameraAgent camera;
    private MockKitRobot kitRobot;
    private MockNest nestAgent;
    
    public CameraAgentTest() {
        camera = new CameraAgent("Camera");
        kitRobot = new MockKitRobot("Mock Kit Robot");
        nestAgent = new MockNest("Mock Nest Agent");
    }

    @Test
    public void testComprehensiveNormalScenarioCamera() {
        assertEquals(true, true);
    }
    
    /**
     * Test of msgNestIsFull method, of class CameraAgent.
     */
    @Test
    public void testMsgNestIsFull() {
        System.out.println("msgNestIsFull");
        Nest nest = null;
        CameraAgent instance = null;
        instance.msgNestIsFull(nest);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of msgKitIsFull method, of class CameraAgent.
     */
    @Test
    public void testMsgKitIsFull() {
        System.out.println("msgKitIsFull");
        Kit kit = null;
        CameraAgent instance = null;
        instance.msgKitIsFull(kit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of msgHereIsKitInfo method, of class CameraAgent.
     */
    @Test
    public void testMsgHereIsKitInfo() {
        System.out.println("msgHereIsKitInfo");
        Kit kit = null;
        CameraAgent instance = null;
        instance.msgHereIsKitInfo(kit);
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

        sb.append(newLine);

        sb.append("-------Nest Agent Log-------");
        sb.append(newLine);
        sb.append(nestAgent.log.toString());
        sb.append("-------End Nest Agent Log-------");

        return sb.toString();
    }
}
