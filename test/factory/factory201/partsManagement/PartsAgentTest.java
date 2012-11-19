/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.partsManagement;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.NestInterface;
import factory.factory201.test.mock.MockCamera;
import factory.factory201.test.mock.MockKitRobot;
import factory.factory201.test.mock.MockNest;
import factory.factory201.partsManagement.PartsAgent;
import factory.general.Kit;
import factory.general.Part;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



/**
 *
 * @author polarpatbear
 */

public class PartsAgentTest extends TestCase{
       
       public MockKitRobot kitrobot; 
       public PartsAgent parts;
       public MockNest nest;
       public MockCamera camera;
       Kit kit;
       ArrayList<Kit> kits;
       Part p = new Part(1);

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        kit = new Kit("Test Kit");
        for (int i = 0; i < 8; i++) {
            kit.addPart(new Part(i));
        }
        kits = new ArrayList<Kit>();
        
        kits.add(kit);
        kits.add(kit);
        kits.add(kit);
        
        
        nest = new MockNest("MockNest");
        parts = new PartsAgent("PartsAgent");
        kitrobot = new MockKitRobot("MockKitRobot");
        camera = new MockCamera("MockCamera");
        parts.setKitRobot(kitrobot);
        parts.setNestInterface(nest);
        parts.setCamera(camera);
    }

    @After
    public void tearDown() throws Exception {
    }
       

	
	
    /**
     *testFirst
     */
    @Test
public void testFirst(){
    parts.msgHereIsKit(kits);
    assertTrue("Parts should have newKit size of 3 ", parts.newKit.size() == 3);
    parts.pickAndExecuteAnAction();
    parts.pickAndExecuteAnAction();
    assertTrue("Camera should have gotten msgHereIsKitInfo" + getLogs(), camera.log.containsString("msgHereIsKitInfo"));
    assertTrue("KitRobot should have gotten two messages" + getLogs(), kitrobot.log.size()==2);
    assertTrue("Kit0 and Kit1 needsParts list should both be of size 8", parts.kit0NeedsParts.size()==8 && parts.kit1NeedsParts.size()==8);
    assertTrue("Nest should have gotten 16 requests" + getLogs(), nest.log.size()==16);
    parts.msgEmptyKitReady(kit);
    assertTrue("Parts should have set kit1 status as ready " , parts.kit1.status == Kit.Status.ready);
    parts.msgEmptyKitReady(kit);
    assertTrue("kit0 status should be ready", parts.kit0.status == Kit.Status.ready);
    for (int i = 0; i < 8; i++) {
    parts.msgHereIsPart(kit.getPart(i));
    }
    for (int i = 0; i < 8; i++) {
    parts.msgHereIsPart(kit.getPart(i));
    }
    assertTrue("Parts inventory size should be 16", parts.inventory.size()==16);
    parts.pickAndExecuteAnAction();
    assertTrue("Kit0NeedsParts size should be 7", parts.kit0NeedsParts.size()==7 && parts.kit1NeedsParts.size()==8);
    parts.pickAndExecuteAnAction();
    assertTrue("Kit0NeedsParts size should be 6", parts.kit0NeedsParts.size()==6 && parts.kit1NeedsParts.size()==8);
    parts.pickAndExecuteAnAction();
    parts.pickAndExecuteAnAction();
    assertTrue("Grips should be empty", parts.grips.isEmpty());
    parts.pickAndExecuteAnAction();
    parts.pickAndExecuteAnAction();
    parts.pickAndExecuteAnAction();
    parts.pickAndExecuteAnAction();
    parts.pickAndExecuteAnAction();
    assertTrue("Kit0NeedsParts size should be 0", parts.kit0NeedsParts.isEmpty() && parts.kit1NeedsParts.size()==8);
    assertTrue("KitRobot should have recieved msgKitIsFull"+ getLogs(), kitrobot.log.containsString("msgKitIsFull"));
    assertTrue("Kit0 status should be ready", parts.kit0.status== Kit.Status.empty);
    parts.pickAndExecuteAnAction();
    assertTrue("Parts should have newKit size of 1 ", parts.newKit.size() == 0);
    assertTrue("Camera should have gotten msgHereIsKitInfo" + getLogs(), camera.log.containsString("msgHereIsKitInfo"));
    assertTrue("KitRobot should have gotten one message" + getLogs(), kitrobot.log.size()==1);
    assertTrue("Kit0 and Kit1 needsParts list should both be of size 8", parts.kit0NeedsParts.size()==8 && parts.kit1NeedsParts.size()==8);
    assertTrue("Nest should have gotten 8 requests" + getLogs(), nest.log.size()==8);
    parts.msgEmptyKitReady(kit);
    assertTrue("kit0 status should be ready", parts.kit0.status == Kit.Status.ready);
    for (int i = 0; i < 8; i++) {
    parts.msgHereIsPart(kit.getPart(i));
    }
    parts.pickAndExecuteAnAction();
    parts.pickAndExecuteAnAction();
    parts.pickAndExecuteAnAction();
    parts.pickAndExecuteAnAction();
    parts.pickAndExecuteAnAction();
    parts.pickAndExecuteAnAction();
    parts.pickAndExecuteAnAction();
}
public String getLogs() {
		StringBuilder sb = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		sb.append("-------Nest Log-------");
		sb.append(newLine);
		sb.append(nest.log.toString());
		sb.append(newLine);
		sb.append("-------End Nest Log-------");

		sb.append(newLine);

		sb.append("-------KitRobot Log-------");
		sb.append(newLine);
		sb.append(kitrobot.log.toString());
		sb.append("-------End KitRobot Log-------");
		
		sb.append(newLine);
		
		sb.append("------Camera Log------");
		sb.append(newLine);
		sb.append(camera.log.toString());
		sb.append("-------End Camera Log-------");
		
		

		return sb.toString();

	}

    /**
     * Test of msgHereIsKit method, of class PartsAgent.
     */
    @Test
    public void testMsgHereIsKit() {
        System.out.println("msgHereIsKit");
        ArrayList<Kit> newKits = null;
        PartsAgent instance = null;
        instance.msgHereIsKit(newKits);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of msgHereIsPart method, of class PartsAgent.
     */
    @Test
    public void testMsgHereIsPart() {
        System.out.println("msgHereIsPart");
        Part p = null;
        PartsAgent instance = null;
        instance.msgHereIsPart(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of msgEmptyKitReady method, of class PartsAgent.
     */
    @Test
    public void testMsgEmptyKitReady() {
        System.out.println("msgEmptyKitReady");
        Kit k = null;
        PartsAgent instance = null;
        instance.msgEmptyKitReady(k);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pickAndExecuteAnAction method, of class PartsAgent.
     */
    @Test
    public void testPickAndExecuteAnAction() {
        System.out.println("pickAndExecuteAnAction");
        PartsAgent instance = null;
        boolean expResult = false;
        boolean result = instance.pickAndExecuteAnAction();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCamera method, of class PartsAgent.
     */
    @Test
    public void testSetCamera() {
        System.out.println("setCamera");
        Camera c = null;
        PartsAgent instance = null;
        instance.setCamera(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setKitRobot method, of class PartsAgent.
     */
    @Test
    public void testSetKitRobot() {
        System.out.println("setKitRobot");
        KitRobot k = null;
        PartsAgent instance = null;
        instance.setKitRobot(k);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNestInterface method, of class PartsAgent.
     */
    @Test
    public void testSetNestInterface() {
        System.out.println("setNestInterface");
        NestInterface n = null;
        PartsAgent instance = null;
        instance.setNestInterface(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of msgNeedPart method, of class PartsAgent.
     */
    @Test
    public void testMsgNeedPart() {
        System.out.println("msgNeedPart");
        Part partType = null;
        PartsAgent instance = null;
        instance.msgNeedPart(partType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of msgHereAreParts method, of class PartsAgent.
     */
    @Test
    public void testMsgHereAreParts() {
        System.out.println("msgHereAreParts");
        List<Part> parts = null;
        PartsAgent instance = null;
        instance.msgHereAreParts(parts);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DoMoveToNest method, of class PartsAgent.
     */
    @Test
    public void testDoMoveToNest() {
        System.out.println("DoMoveToNest");
        int nestNum = 0;
        PartsAgent instance = null;
        instance.DoMoveToNest(nestNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DoPickUpPart method, of class PartsAgent.
     */
    @Test
    public void testDoPickUpPart() {
        System.out.println("DoPickUpPart");
        int nestNum = 0;
        PartsAgent instance = null;
        instance.DoPickUpPart(nestNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DoPutInKit method, of class PartsAgent.
     */
    @Test
    public void testDoPutInKit() {
        System.out.println("DoPutInKit");
        int kitNum = 0;
        PartsAgent instance = null;
        instance.DoPutInKit(kitNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DoGiveKitsInAction method, of class PartsAgent.
     */
    @Test
    public void testDoGiveKitsInAction() {
        System.out.println("DoGiveKitsInAction");
        Kit k = null;
        PartsAgent instance = null;
        instance.DoGiveKitsInAction(k);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DoGiveKitsInQueue method, of class PartsAgent.
     */
    @Test
    public void testDoGiveKitsInQueue() {
        System.out.println("DoGiveKitsInQueue");
        ArrayList<Kit> kits1 = null;
        PartsAgent instance = null;
        instance.DoGiveKitsInQueue(kits1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}