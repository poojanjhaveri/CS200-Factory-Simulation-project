/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.partsManagement;
import factory.factory201.test.mock.MockCamera;
import factory.factory201.test.mock.MockKitRobot;
import factory.factory201.test.mock.MockNest;
import factory.general.Kit;
import factory.general.Part;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
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
       List<Kit> kits = new ArrayList<Kit>();
      // Part p = new Part(1);
   @Override
	protected void setUp() throws Exception {
       
        kit = new Kit("Test Kit");
        for (int i = 1; i < 9; i++) {
            kit.addPart(new Part("i", "i"));
        }
        
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {

	}
@Test
public void testfirstTest(){
    parts.msgHereIsKit(kits);
    assertTrue("Parts should have newKit size of 1 ", parts.newKit.size() == 3);
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

}
