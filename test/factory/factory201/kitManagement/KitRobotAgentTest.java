
package factory.factory201.kitManagement;

import factory.factory201.test.mock.MockCamera;
import factory.factory201.test.mock.MockConveyor;
import factory.factory201.test.mock.MockParts;
import factory.general.Kit;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Alex Young
 * @version 1
 */
public class KitRobotAgentTest extends TestCase {

    private KitRobotAgent kitRobot;
    private MockCamera camera;
    private MockConveyor conveyor;
    private MockParts partsAgent;

    public KitRobotAgentTest() {
        kitRobot = new KitRobotAgent("Kit Robot");
        camera = new MockCamera("Camera");
        conveyor = new MockConveyor("Conveyor");
        partsAgent = new MockParts("Parts Agent");

        kitRobot.setAll(camera, conveyor, partsAgent);
    }

    @Test
    public void testComprehensiveNormalScenarioKitRobot() {
        assertEquals(true, true);
    }
    
    /**
     * Test of msgNeedEmptyKit method, of class KitRobotAgent.
     */
    @Test
    public void testMsgNeedEmptyKit() {
        System.out.println("msgNeedEmptyKit");
        KitRobotAgent instance = null;
        instance.msgNeedEmptyKit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of msgHereIsEmptyKit method, of class KitRobotAgent.
     */
    @Test
    public void testMsgHereIsEmptyKit() {
        System.out.println("msgHereIsEmptyKit");
        Kit kit = null;
        KitRobotAgent instance = null;
        instance.msgHereIsEmptyKit(kit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of msgKitIsFull method, of class KitRobotAgent.
     */
    @Test
    public void testMsgKitIsFull() {
        System.out.println("msgKitIsFull");
        Kit kit = null;
        KitRobotAgent instance = null;
        instance.msgKitIsFull(kit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of msgKitInspected method, of class KitRobotAgent.
     */
    @Test
    public void testMsgKitInspected() {
        System.out.println("msgKitInspected");
        boolean result_2 = false;
        KitRobotAgent instance = null;
        instance.msgKitInspected(result_2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    public String getLogs() {
        StringBuilder sb = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        sb.append("-------Camera Log-------");
        sb.append(newLine);
        sb.append(camera.log.toString());
        sb.append(newLine);
        sb.append("-------End Camera Log-------");

        sb.append(newLine);

        sb.append("-------Conveyor Log-------");
        sb.append(newLine);
        sb.append(conveyor.log.toString());
        sb.append("-------End Conveyor Log-------");

        sb.append(newLine);

        sb.append("------Parts Agent Log------");
        sb.append(newLine);
        sb.append(partsAgent.log.toString());
        sb.append("-------End Parts Agent Log-------");

        return sb.toString();
    }
}
