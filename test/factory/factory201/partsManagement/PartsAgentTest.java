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
import junit.framework.TestCase;




/**
 *
 * @author polarpatbear
 */

public class PartsAgentTest extends TestCase{
	
	public MockKitRobot kitrobot; 
	public PartsAgent parts;
	public MockNest nest;
	public MockCamera camera;
	Kit kit, kit0, kit1;
	ArrayList<Kit> kits;
	Part p = new Part(1);
	
 
    /**
     *testFirst
     */
       // @Test
	public void testThreeKits(){
        kit = new Kit("Test Kit");
        kit0 = new Kit("Kit 0");
        kit1= new Kit("Kit 1");
        kit0.standNum=Kit.StandNum.zero;
        kit1.standNum=Kit.StandNum.one;
        
        for (int i = 0; i < 8; i++) {
            kit.addPart(new Part(i));
            kit.getPart(i).setNestNum(i);
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
	parts.msgHereIsKit(kits);
	assertTrue("Parts should have newKit size of 3 ", parts.newKit.size() == 3);
	parts.pickAndExecuteAnAction();
	parts.pickAndExecuteAnAction();
	assertTrue("Camera should have gotten msgHereIsKitInfo" + getLogs(), camera.log.containsString("msgHereIsKitInfo"));
	assertTrue("KitRobot should have gotten two messages" + getLogs(), kitrobot.log.size()==2);
	assertTrue("Kit0 and Kit1 needsParts list should both be of size 0", parts.kit0NeedsParts.size()==0 && parts.kit1NeedsParts.size()==0);
	assertTrue("Nest should have gotten 16 requests" + getLogs(), nest.log.size()==16);
	assertTrue("KitsStarted size should be 2", parts.kitsStarted.size()==2);
        assertTrue("KitZero and KitOne should be false, signifying neither kits are ready", parts.kitZero==false && parts.kitOne==false);
        parts.msgEmptyKitReady(kit1);
	assertTrue("Parts should have set kit1 status as ready " , parts.kitOne==true);
        parts.pickAndExecuteAnAction();
        assertTrue("KitOne should be false " , parts.kitOne==false);
        assertTrue("KitsStarted size should be 1", parts.kitsStarted.size()==1);
        assertTrue("Kit1needsparts size should be 8", parts.kit1NeedsParts.size()==8);
	parts.msgEmptyKitReady(kit0);
	assertTrue("kit0 status should be ready", parts.kitZero==true);
        parts.pickAndExecuteAnAction();
        assertTrue("KitZero should be false " , parts.kitZero==false);
        assertTrue("KitsStarted size should be 0", parts.kitsStarted.size()==0);
        assertTrue("Kit0needsparts size should be 8", parts.kit0NeedsParts.size()==8);
	for (int i = 0; i < 8; i++) {
			parts.msgHereIsPart(kit.getPart(i));
		}
	for (int i = 0; i < 8; i++) {
			parts.msgHereIsPart(kit.getPart(i));
		}
	assertTrue("Parts inventory size should be 16", parts.inventory.size()==16);
	parts.pickAndExecuteAnAction();
        assertTrue("Parts inventory size should be 16", parts.inventory.size()==15);
	assertTrue("Kit0NeedsParts size should be 7", parts.kit0NeedsParts.size()==7 && parts.kit1NeedsParts.size()==8);
	assertTrue("Grips should be 1", parts.grips.size()==1);
        assertTrue("Kit0 size should be 1", parts.kit0.parts.size()==1);
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        assertTrue("Grips should have no parts in it", parts.grips.isEmpty());
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
	assertTrue("Kit0NeedsParts size should be 0 and kit1needsparts should be 8", parts.kit0NeedsParts.isEmpty() && parts.kit1NeedsParts.size()==8);
	assertTrue("KitRobot should have recieved msgKitIsFull"+ getLogs(), kitrobot.log.containsString("msgKitIsFull") && kitrobot.log.size()==3);
	
	
        }
        
        public void testZeroKits(){//tests that the scheduler isn't executing actions if no kits are given
        kit = new Kit("Test Kit");
       
        kit1= new Kit("Kit 1");
        
        kit1.standNum=Kit.StandNum.one;
        
        for (int i = 0; i < 8; i++) {
            kit.addPart(new Part(i));
            kit.getPart(i).setNestNum(i);
        }
        kits = new ArrayList<Kit>();
        nest = new MockNest("MockNest");
        parts = new PartsAgent("PartsAgent");
        kitrobot = new MockKitRobot("MockKitRobot");
        camera = new MockCamera("MockCamera");
        
        parts.setKitRobot(kitrobot);
        parts.setNestInterface(nest);
        parts.setCamera(camera);
	parts.msgHereIsKit(kits);
	
        assertTrue("Parts should have newKit size of 0 ", parts.newKit.isEmpty());
	parts.pickAndExecuteAnAction();
	parts.pickAndExecuteAnAction();
	assertTrue("Camera should not have gotten a message" + getLogs(), camera.log.size()==0);
	assertTrue("KitRobot should not have gotten a message" + getLogs(), kitrobot.log.size()==0);
	assertTrue("Kit0 and Kit1 needsParts list should both be of size 0", parts.kit0NeedsParts.size()==0 && parts.kit1NeedsParts.size()==0);
	assertTrue("Nest should have gotten 0 requests" + getLogs(), nest.log.size()==0);
	assertTrue("KitsStarted size should be 0", parts.kitsStarted.isEmpty());
        assertTrue("KitZero and KitOne should be false, signifying neither kits are ready", parts.kitZero==false && parts.kitOne==false);
        
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        assertTrue("Grips should have no parts in it", parts.grips.isEmpty());
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
	assertTrue("Kit0NeedsParts size should be 0 and kit1needsparts should be 8", parts.kit0NeedsParts.isEmpty() && parts.kit1NeedsParts.isEmpty());
	assertTrue("KitRobot should not have recieved msgKitIsFull"+ getLogs(),  kitrobot.log.size()==0);
	assertTrue("All list sizes should be 0", parts.inventory.isEmpty() && parts.grips.isEmpty() && parts.newKit.isEmpty() && parts.kitsStarted.isEmpty() && parts.kits==0);
	
        }
        
        public void testOneKit(){//tests only 1 kit being passed into partsAgent and that it completes it
        kit = new Kit("Test Kit");
       
        kit1= new Kit("Kit 1");
        
        kit1.standNum=Kit.StandNum.one;
        
        for (int i = 0; i < 8; i++) {
            kit.addPart(new Part(i));
            kit.getPart(i).setNestNum(i);
        }
        kits = new ArrayList<Kit>();
        
        kits.add(kit);
        
        
        nest = new MockNest("MockNest");
        parts = new PartsAgent("PartsAgent");
        kitrobot = new MockKitRobot("MockKitRobot");
        camera = new MockCamera("MockCamera");
        parts.setKitRobot(kitrobot);
        parts.setNestInterface(nest);
        parts.setCamera(camera);
	parts.msgHereIsKit(kits);
	
        assertTrue("Parts should have newKit size of 1 ", parts.newKit.size() == 1);
	parts.pickAndExecuteAnAction();
	parts.pickAndExecuteAnAction();
	assertTrue("Camera should have gotten msgHereIsKitInfo" + getLogs(), camera.log.containsString("msgHereIsKitInfo") && camera.log.size()==1);
	assertTrue("KitRobot should have gotten one message" + getLogs(), kitrobot.log.size()==1);
	assertTrue("Kit0 and Kit1 needsParts list should both be of size 0", parts.kit0NeedsParts.size()==0 && parts.kit1NeedsParts.size()==0);
	assertTrue("Nest should have gotten 8 requests" + getLogs(), nest.log.size()==8);
	assertTrue("KitsStarted size should be 2", parts.kitsStarted.size()==1);
        assertTrue("KitZero and KitOne should be false, signifying neither kits are ready", parts.kitZero==false && parts.kitOne==false);
        parts.msgEmptyKitReady(kit1);
	assertTrue("Parts should have set kit1 status as ready " , parts.kitOne==true);
        parts.pickAndExecuteAnAction();
        assertTrue("KitOne should be false " , parts.kitOne==false);
        assertTrue("KitsStarted size should be 0", parts.kitsStarted.size()==0);
        assertTrue("Kit1needsparts size should be 8", parts.kit1NeedsParts.size()==8);
        assertTrue("Kit0needsparts size should be 0", parts.kit0NeedsParts.size()==0);
	for (int i = 0; i < 8; i++) {
			parts.msgHereIsPart(kit.getPart(i));
		}
	assertTrue("Parts inventory size should be 8", parts.inventory.size()==8);
	parts.pickAndExecuteAnAction();
        assertTrue("Parts inventory size should be 16", parts.inventory.size()==7);
	assertTrue("Kit0NeedsParts size should be 7", parts.kit1NeedsParts.size()==7);
	
        assertTrue("Grips should be 1", parts.grips.size()==1);
        assertTrue("Kit0 size should be 1", parts.kit1.parts.size()==1);
        
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        assertTrue("Grips should have no parts in it", parts.grips.isEmpty());
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
	assertTrue("Kit0NeedsParts size should be 0 and kit1needsparts should be 8", parts.kit0NeedsParts.isEmpty() && parts.kit1NeedsParts.isEmpty());
	assertTrue("KitRobot should have recieved msgKitIsFull"+ getLogs(), kitrobot.log.containsString("msgKitIsFull") && kitrobot.log.size()==2);
	}
        
        public void testDifferentKits(){//tests new arrays being passed into partsAgent and that it handles correctly
        kit = new Kit("Different Test Kit");
        Kit kitDif = new Kit("Third Kit");
        kit0 = new Kit("Kit 0");
        kit1= new Kit("Kit 1");
        kit0.standNum=Kit.StandNum.zero;
        kit1.standNum=Kit.StandNum.one;
        
        for (int i = 0; i < 4; i++) {
            kit.addPart(new Part(i));
            kit.getPart(i).setNestNum(i);
            kit.addPart(new Part(i));
            kit.getPart(i).setNestNum(i);
        }
        kits = new ArrayList<Kit>();
        
        kits.add(kit);
        kits.add(kit);
        kitDif.addPart(new Part(5));
        kitDif.addPart(new Part(7));
        kitDif.addPart(new Part(3));
        kitDif.addPart(new Part(5));
        kitDif.addPart(new Part(3));
        kitDif.addPart(new Part(7));
        kitDif.addPart(new Part(3));
        kitDif.addPart(new Part(3));
        kitDif.getPart(0).setNestNum(5);
        kitDif.getPart(1).setNestNum(7);
        kitDif.getPart(2).setNestNum(3);
        kitDif.getPart(3).setNestNum(5);
        kitDif.getPart(4).setNestNum(3);
        kitDif.getPart(5).setNestNum(7);
        kitDif.getPart(6).setNestNum(3);
        kitDif.getPart(7).setNestNum(3);
       // kits.add(kitDif);
        
        nest = new MockNest("MockNest");
        parts = new PartsAgent("PartsAgent");
        kitrobot = new MockKitRobot("MockKitRobot");
        camera = new MockCamera("MockCamera");
        parts.setKitRobot(kitrobot);
        parts.setNestInterface(nest);
        parts.setCamera(camera);
	parts.msgHereIsKit(kits);
	assertTrue("Parts should have newKit size of 3 ", parts.newKit.size() == 2);
	parts.pickAndExecuteAnAction();
	parts.pickAndExecuteAnAction();
	assertTrue("Camera should have gotten msgHereIsKitInfo" + getLogs(), camera.log.containsString("msgHereIsKitInfo"));
	assertTrue("KitRobot should have gotten two messages" + getLogs(), kitrobot.log.size()==2);
	assertTrue("Kit0 and Kit1 needsParts list should both be of size 0", parts.kit0NeedsParts.isEmpty() && parts.kit1NeedsParts.isEmpty());
	assertTrue("Nest should have gotten 16 requests" + getLogs(), nest.log.size()==16);
	assertTrue("KitsStarted size should be 2", parts.kitsStarted.size()==2);
        assertTrue("KitZero and KitOne should be false, signifying neither kits are ready", parts.kitZero==false && parts.kitOne==false);
        parts.msgEmptyKitReady(kit1);
	assertTrue("Parts should have set kit1 status as ready " , parts.kitOne==true);
        parts.pickAndExecuteAnAction();
        assertTrue("KitOne should be false " , parts.kitOne==false);
        assertTrue("KitsStarted size should be 1", parts.kitsStarted.size()==1);
        assertTrue("Kit1needsparts size should be 8", parts.kit1NeedsParts.size()==8);
	parts.msgEmptyKitReady(kit0);
	assertTrue("kit0 status should be ready", parts.kitZero==true);
        parts.pickAndExecuteAnAction();
        assertTrue("KitZero should be false " , parts.kitZero==false);
        assertTrue("KitsStarted size should be 0", parts.kitsStarted.isEmpty());
        assertTrue("Kit0needsparts size should be 8", parts.kit0NeedsParts.size()==8);
	for (int i = 0; i < 8; i++) {
			parts.msgHereIsPart(kit.getPart(i));
		}
	for (int i = 0; i < 8; i++) {
			parts.msgHereIsPart(kit.getPart(i));
		}
	assertTrue("Parts inventory size should be 16", parts.inventory.size()==16);
	parts.pickAndExecuteAnAction();
        assertTrue("Parts inventory size should be 16", parts.inventory.size()==15);
	assertTrue("Kit0NeedsParts size should be 7", parts.kit0NeedsParts.size()==7 && parts.kit1NeedsParts.size()==8);
	assertTrue("Grips should be 1", parts.grips.size()==1);
        assertTrue("Kit0 size should be 1", parts.kit0.parts.size()==1);
        kits.clear();
        kits.add(kitDif);
        parts.msgHereIsKit(kits);
        assertTrue("Kits size is 2 and newKit array is 1", parts.kits==2 && parts.newKit.size()==1);
        parts.pickAndExecuteAnAction();
        assertTrue("The new kit should stay in newKit array", parts.kitsStarted.isEmpty() && parts.newKit.size()==1);
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        assertTrue("Grips should have no parts in it", parts.grips.isEmpty());
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
        parts.pickAndExecuteAnAction();
	assertTrue("Kit0NeedsParts size should be 0 and kit1needsparts should be 8", parts.kit0NeedsParts.isEmpty() && parts.kit1NeedsParts.size()==8);
	assertTrue("KitRobot should have recieved msgKitIsFull"+ getLogs(), kitrobot.log.containsString("msgKitIsFull") && kitrobot.log.size()==3);
	parts.pickAndExecuteAnAction();
        assertTrue("The new kit should have moved to kitsStarted array", parts.kitsStarted.size()==1 && parts.newKit.isEmpty());
        
	
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