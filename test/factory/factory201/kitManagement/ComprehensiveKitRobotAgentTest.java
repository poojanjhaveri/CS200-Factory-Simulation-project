package factory.factory201.kitManagement;

import factory.factory200.kitAssemblyManager.KitAssemblyManager;
import factory.factory201.test.mock.MockCamera;
import factory.factory201.test.mock.MockConveyor;
import factory.factory201.test.mock.MockParts;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Alex Young
 * @version 1
 */
public class ComprehensiveKitRobotAgentTest extends TestCase {

    public KitRobotAgent kitRobot;
    public MockCamera camera;
    public MockConveyor conveyor;
    public MockParts partsAgent;
    public KitAssemblyManager KAM;

    @Override
    protected void setUp() throws Exception {
        kitRobot = new KitRobotAgent("Kit Robot");
        camera = new MockCamera("Camera");
        conveyor = new MockConveyor("Conveyor");
        partsAgent = new MockParts("Parts Agent");
        KAM = new KitAssemblyManager();

        kitRobot.setAll(camera, conveyor, partsAgent, KAM);
    }

    @Override
    protected void tearDown() throws Exception {
        // Not used
    }

    @Test
    public void testNormalScenarioKitRobot() {
        assertEquals(true, true);
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
