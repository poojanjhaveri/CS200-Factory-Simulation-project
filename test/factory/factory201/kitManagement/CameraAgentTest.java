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

    /**
     * Test of msgNestIsFull method, of class CameraAgent.
     */
    @Test
    public void testMsgNestIsFull() {
        this.initialize();
        
        Nest n = new Nest(0);
        n.part = new Part(0);
        n.parts.add(new Part(0));
        n.status = Nest.Status.gettingInspected;
        
        camera.msgNestIsFull(n);
        camera.pickAndExecuteAnAction();
        assertTrue("Nest should be inspected after 1 scheduler call." + 
                getLogs(), nestMock.log.containsString("msgNestInspected"));
        assertTrue("Mock Nest Agent should have only received one message.",
                nestMock.log.size() == 1);
        assertFalse("Calling the scheduler again should return false.",
                camera.pickAndExecuteAnAction());
    }

    /**
     * Test of msgHereIsKitInfo method, of class CameraAgent.
     */
//    @Test
//    public void testMsgHereIsKitInfo() {
//        this.initialize();
//        
//        Kit kitInfo = new Kit("Kit Info");
//        for (int i = 0; i < 8; i++) {
//            kitInfo.addPart(new Part(i));
//        }
//        camera.msgHereIsKitInfo(kitInfo);
//        camera.pickAndExecuteAnAction();
//        assertTrue("Kit requirements should be full after 1 scheduler call.",
//                !camera.getKitRqmts().isEmpty());
//        assertFalse("Calling the scheduler again should return false.",
//                camera.pickAndExecuteAnAction());
//    }

    /**
     * Test of msgKitIsFull method, of class CameraAgent.
     */
    @Test
    public void testMsgKitIsFull() {
        this.initialize();
        
        Kit testKit = new Kit("Test Kit");
        for (int i = 7; i >= 0; i--) {
            testKit.addPart(new Part(i));
        }
        testKit.status = Kit.Status.full;
        camera.msgKitIsFull(testKit);
        camera.pickAndExecuteAnAction();
        assertTrue("Kit should be inspected after 1 scheduler call."
                + getLogs(), kitRobot.log.containsString("msgKitInspected"));
        assertTrue("Mock Kit Robot should have only received one message.",
                kitRobot.log.size() == 1);
        assertFalse("Calling the scheduler again should return false.",
                camera.pickAndExecuteAnAction());
    }

    private void initialize() {
        camera = new CameraAgent("Camera");
        kitRobot = new MockKitRobot("Mock Kit Robot");
        nestMock = new MockNest("Mock Nest Agent");
        camera.setAll(kitRobot, nestMock);
    }
    
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
