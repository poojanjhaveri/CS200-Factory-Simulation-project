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

    
    /* Check if on receiving msgKitFull, the Camera sends a message "Kit Inspected" */
    public void testInspectNest(){
        Kit kit=new Kit("TestKit");
        kit.status=Kit.Status.full;
        camera.msgKitIsFull(kit);
        camera.pickAndExecuteAnAction();
        assertTrue("Mock kitRobot should have received Nest Inspected. Event log: "
						+ kitRobot.log.toString(), kitRobot.log
						.containsString("Received msgKitInspected"));
    
    }
    
    /* Check if on receiving message nest is full, it sends a status to Nest "Nest Inspected" */
    public void testInspectKit(){
        Nest nest=new Nest(1);
        nest.status=Nest.Status.gettingInspected;
        camera.msgNestIsFull(nest);
        camera.pickAndExecuteAnAction();
        assertTrue("Mock nestAgent should have received Nest Inspected. Event log: "
						+ kitRobot.log.toString(), kitRobot.log
						.containsString("Nest Inspected"));
    
    }
    
}
