package factory.factory201.kitManagement;

import factory.factory201.test.mock.MockCamera;
import factory.factory201.test.mock.MockConveyor;
import factory.factory201.test.mock.MockParts;
import factory.general.Kit;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Alex Young <alexyoung1992@gmail.com>
 * @version 1
 * @brief Unit test for Kit Robot Agent
 */
public class KitRobotAgentTest extends TestCase {
    
    private KitRobotAgent kitRobot;
    private MockConveyor conveyor;
    private MockCamera camera;
    private MockParts partsMock;

    /**
     * Test of msgNeedEmptyKit method, of class KitRobotAgent.
     */
    @Test
    public void testMsgNeedEmptyKit() {
        this.initalize();
        
        assertTrue("Kit stand should be empty.", kitRobot.getKitStand().isEmpty());
        kitRobot.getKitStand().addKit(new Kit("Test Kit 0"));
        kitRobot.getKitStand().addKit(new Kit("Test Kit 1"));
        kitRobot.msgNeedEmptyKit();
        kitRobot.pickAndExecuteAnAction();
        assertTrue("Empty kit should be given after 1 scheduler call." +
                getLogs(), partsMock.log.containsString("msgEmptyKitReady"));
        assertTrue("Mock Parts Agent should have only received one message.",
                partsMock.log.size() == 1);
        assertFalse("Calling the scheduler again should return false.",
                kitRobot.pickAndExecuteAnAction());
    }

    /**
     * Test of msgHereIsEmptyKit method, of class KitRobotAgent.
     */
    @Test
    public void testMsgHereIsEmptyKit() {
        this.initalize();
        
        assertTrue("Kit stand should be empty.", kitRobot.getKitStand().isEmpty());
        kitRobot.msgHereIsEmptyKit(new Kit("Test Kit"));
        assertFalse("Kit stand should no longer be empty.", kitRobot.getKitStand().isEmpty());
    }

    /**
     * Test of msgKitIsFull method, of class KitRobotAgent.
     */
    @Test
    public void testMsgKitIsFull() {
        this.initalize();
        
        Kit[] k = {new Kit("Test Kit 0"), new Kit("Test Kit 1"), new Kit("Test Kit 2")};
        kitRobot.getKitStand().addKit(k[0]);
        kitRobot.getKitStand().addKit(k[1]);
        kitRobot.msgKitIsFull(k[1]);
        kitRobot.pickAndExecuteAnAction();
        assertTrue("Camera should be notified after 1 scheduler call." +
                getLogs(), camera.log.containsString("msgKitIsFull"));
        assertTrue("Camera should have only received one message.",
                camera.log.size() == 1);
//        assertTrue("The scheduler should not return false.",
//                kitRobot.pickAndExecuteAnAction());
//        kitRobot.getKitStand().addKit(k[2]);
//        assertFalse("Calling the scheduler again should return false.",
//                kitRobot.pickAndExecuteAnAction());
    }

    /**
     * Test of msgKitInspectedNoError method, of class KitRobotAgent.
     */
    @Test
    public void testMsgKitInspected() {
        this.initalize();
        
        Kit[] k = {new Kit("Test Kit 0"), new Kit("Test Kit 1"), new Kit("Test Kit 2")};
        kitRobot.getKitStand().addKit(k[0]);
        kitRobot.getKitStand().addKit(k[1]);
        kitRobot.msgKitIsFull(k[1]);
        kitRobot.pickAndExecuteAnAction();
        
        k[1].status = Kit.Status.verified;
        kitRobot.msgKitInspectedNoError();
        kitRobot.pickAndExecuteAnAction();
        assertTrue("Verified kit should be given after 1 scheduler call." +
                getLogs(), conveyor.log.containsString("msgHereIsVerifiedKit"));
        assertTrue("Conveyor should have only received one message.",
                conveyor.log.size() == 1);
//        assertTrue("Calling the scheduler again should not return false.",
//                kitRobot.pickAndExecuteAnAction());
    }
    
    private void initalize() {
        kitRobot = new KitRobotAgent("Kit Robot Agent");
        conveyor = new MockConveyor("Mock Conveyor");
        camera = new MockCamera("Mock Camera");
        partsMock = new MockParts("Mock Parts Agent");
        kitRobot.setAll(camera, conveyor, partsMock);
    }
    
    private String getLogs() {
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
        sb.append(partsMock.log.toString());
        sb.append("-------End Parts Agent Log-------");

        return sb.toString();
    }
}
