package factory.factory201.kitManagement;

import factory.factory201.test.mock.MockKitRobot;
import factory.factory201.test.mock.MockNest;
import factory.general.Kit;
import factory.general.Nest;
import factory.general.Part;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Alex Young <alexyoung1992@gmail.com>
 * @version 1
 * @brief Unit test for Camera Agent
 */
public class CameraAgentTest extends TestCase {

    private CameraAgent camera;
    private MockKitRobot kitRobot;
    private MockNest nestMock;

    public CameraAgentTest() {
        camera = new CameraAgent("Camera");
        kitRobot = new MockKitRobot("Mock Kit Robot");
        nestMock = new MockNest("Mock Nest Agent");
        camera.setAll(kitRobot, nestMock);
    }

    /**
     * Test of msgNestIsFull method, of class CameraAgent.
     */
    @Test
    public void testMsgNestIsFull() {
        Nest n = new Nest(0);
        n.part = new Part(0);
        n.parts.add(new Part(0));
        n.status = Nest.Status.gettingInspected;
        
        camera.msgNestIsFull(n);
        camera.pickAndExecuteAnAction();
        assertTrue("Nest should be inspected after 1 scheduler call." + 
                getLogs(), nestMock.log.containsString("Nest Inspected"));
        assertTrue("Mock Nest Agent should have only received one message." + 
                getLogs(), nestMock.log.size() == 1);
        assertFalse("Calling the scheduler again should return false.",
                camera.pickAndExecuteAnAction());
    }

    /**
     * Test of msgHereIsKitInfo method, of class CameraAgent.
     */
    @Test
    public void testMsgHereIsKitInfo() {
        Kit kitInfo = new Kit("Kit Info");
        for (int i = 0; i < 8; i++) {
            kitInfo.addPart(new Part(i));
        }
        camera.msgHereIsKitInfo(kitInfo);
        camera.pickAndExecuteAnAction();
        assertTrue("Kit requirements should be full after 1 scheduler call." +
                getLogs(), !camera.getKitRqmts().isEmpty());
        assertFalse("Calling the scheduler again should return false.",
                camera.pickAndExecuteAnAction());
    }

    /**
     * Test of msgKitIsFull method, of class CameraAgent.
     */
    @Test
    public void testMsgKitIsFull() {
        Kit testKit = new Kit("Test Kit");
        for (int i = 7; i >= 0; i--) {
            testKit.addPart(new Part(i));
        }
        testKit.status = Kit.Status.full;
        camera.msgKitIsFull(testKit);
        camera.pickAndExecuteAnAction();
        assertTrue("Kit should be inspected after 1 scheduler call."
                + getLogs(), kitRobot.log.containsString("msgKitInspected"));
        assertTrue("Mock Kit Robot should have only received one message."
                + getLogs(), kitRobot.log.size() == 1);
        assertFalse("Calling the scheduler again should return false.",
                camera.pickAndExecuteAnAction());
    }

//    /**
//     * Test of pickAndExecuteAnAction method, of class CameraAgent.
//     */
//    @Test
//    public void testPickAndExecuteAnAction() {
//        System.out.println("pickAndExecuteAnAction");
//        CameraAgent instance = null;
//        boolean expResult = false;
//        boolean result = instance.pickAndExecuteAnAction();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of inspectKit method, of class CameraAgent.
//     */
//    @Test
//    public void testInspectKit() {
//        System.out.println("inspectKit");
//        Kit kit = null;
//        CameraAgent instance = null;
//        instance.inspectKit(kit);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setKitRobot method, of class CameraAgent.
//     */
//    @Test
//    public void testSetKitRobot() {
//        System.out.println("setKitRobot");
//        KitRobot agent = null;
//        CameraAgent instance = null;
//        instance.setKitRobot(agent);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setNestAgent method, of class CameraAgent.
//     */
//    @Test
//    public void testSetNestAgent() {
//        System.out.println("setNestAgent");
//        NestInterface agent = null;
//        CameraAgent instance = null;
//        instance.setNestAgent(agent);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setAll method, of class CameraAgent.
//     */
//    @Test
//    public void testSetAll() {
//        System.out.println("setAll");
//        KitRobot kitRobot = null;
//        NestInterface nestAgent = null;
//        CameraAgent instance = null;
//        instance.setAll(kitRobot, nestAgent);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    private String getLogs() {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        sb.append("-------Mock Kit Robot Log-------");
        sb.append(newLine);
        sb.append(kitRobot.log.toString());
        sb.append(newLine);
        sb.append("-------End Mock Kit Robot Log-------");

        sb.append(newLine);

        sb.append("-------Mock Nest Agent Log-------");
        sb.append(newLine);
        sb.append(nestMock.log.toString());
        sb.append("-------End Mock Nest Agent Log-------");

        return sb.toString();
    }
}
