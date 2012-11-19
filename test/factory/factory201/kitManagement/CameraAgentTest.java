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
 */
public class CameraAgentTest extends TestCase {

    private CameraAgent camera;
    private MockKitRobot kitRobot;
    private MockNest nestAgent;

    public CameraAgentTest() {
        camera = new CameraAgent("Camera");
        kitRobot = new MockKitRobot("Mock Kit Robot");
        nestAgent = new MockNest("Mock Nest Agent");
        camera.setKitRobot(kitRobot);
    }

    /* Check if on receiving msgKitFull, the Camera sends a message "Kit Inspected" */
//    @Test
//    public void testInspectNest() {
//        Nest nest = new Nest(1);
//        nest.status = Nest.Status.gettingInspected;
//        camera.msgNestIsFull(nest);
//        camera.pickAndExecuteAnAction();
//        assertTrue("Mock kitRobot should have received Nest Inspected. Event log: "
//                + kitRobot.log.toString(), kitRobot.log
//                .containsString("Received msgKitInspected"));
//
//    }

    /* Check if on receiving message nest is full, it sends a status to Nest "Nest Inspected" */
    @Test
    public void testInspectKit() {
        Kit kit = new Kit("Test Kit");
        for (int i = 0; i < 8; i++) {
            kit.addPart(new Part(i));
        }
        camera.msgHereIsKitInfo(kit);
        camera.pickAndExecuteAnAction();
        Kit kit2 = new Kit("Test Kit2");
        for (int i = 7; i >= 0; i--) {
            kit2.addPart(new Part(i));
        }
        kit2.status = Kit.Status.full;
        camera.msgKitIsFull(kit2);
        camera.pickAndExecuteAnAction();
//        camera.inspectKit(kit);
//        assertTrue("Mock nestAgent should have received Nest Inspected. Event log: "
//                + kitRobot.log.toString(), kitRobot.log
//                .containsString("Nest Inspected"));
//        assertEquals(true, camera.TEST);


    }
}
