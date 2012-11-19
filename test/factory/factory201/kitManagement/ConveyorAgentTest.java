
package factory.factory201.kitManagement;

import factory.factory201.test.mock.MockKitRobot;
import factory.general.Kit;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Alex Young <alexyoung1992@gmail.com>
 * @version 1
 */
public class ConveyorAgentTest extends TestCase {
    
    private ConveyorAgent conveyor;
    private MockKitRobot kitRobot;

    public ConveyorAgentTest() {
        conveyor = new ConveyorAgent("Conveyor");
        kitRobot = new MockKitRobot("Mock Kit Robot");
    }

    
    public void testMsgNeedEmptyKit() {
        // TODO review the generated test code and remove the default call to fail.
    //conveyor.generateKit(1);
    conveyor.setKitRobot(kitRobot);
    Kit k = new Kit("Kit " + 1);
    System.out.println("kit status is " + k.status);
    conveyor.testAddKit(k);
    conveyor.msgNeedEmptyKit();
    conveyor.pickAndExecuteAnAction();
    assertTrue("Mock kitRobot should have received Nest Inspected. Event log: "
						+ kitRobot.log.toString(), kitRobot.log
						.containsString("Received msgHereIsEmptyKit"));
    }

    
}
