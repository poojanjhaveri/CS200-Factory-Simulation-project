/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.partsManagement;
import factory.factory201.test.mock.MockCamera;
import factory.factory201.test.mock.MockLane;
import factory.factory201.test.mock.MockParts;
import factory.general.Nest;
import factory.general.Part;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
//import org.junit.Test;



/**
 *
 * @author polarpatbear
 */

public class NestAgentTest extends TestCase{
       
       MockLane lane1;
       MockLane lane2;
       MockLane lane3;
       MockLane lane4;
       MockLane lane5;
       MockLane lane6;
       MockLane lane7;
       MockParts parts;
       MockCamera camera;
       MockLane lane0;

      //@Test
      public void testFirstNest(){
        NestAgent nest = new NestAgent("NEST AGENT");
        Part p = new Part(1);
        List<Part> nestParts = new ArrayList<Part>();
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        
        parts = new MockParts("MOCK PARTS");
        
       lane0 = new MockLane("MOCK LANE 0");
        
        camera = new MockCamera("MOCK CAMERA");
       
        nest.getNest(0).setLane(lane0);
       /* nest.getNest(0).setLane(lane1);
        nest.getNest(0).setLane(lane2);
        nest.getNest(0).setLane(lane3);
        nest.getNest(0).setLane(lane4);
        nest.getNest(0).setLane(lane5);
        nest.getNest(0).setLane(lane6);
        nest.getNest(0).setLane(lane7);*/
        
        nest.setCamera(camera);
        nest.setPartsAgent(parts);
        assertTrue("Nest 0 status should be empty", nest.myNests.get(0).status == Nest.Status.empty);
        nest.msgNeedPart(p);//tests msgNeedPart below
    
        assertTrue("Requests size and NeedParts size should be 1", nest.requests.size()==1 && nest.needParts.size()==1);
    
        nest.pickAndExecuteAnAction();
    
        assertTrue("Nest 0 status should be needPart", nest.myNests.get(0).status == Nest.Status.needPart);
        nest.pickAndExecuteAnAction();
        assertTrue("Lane should have gotten msgNeedPart" + getLogs(), lane0.log.containsString("msgNeedPart"));
        assertTrue("nest 0 status should be gettingPart", nest.myNests.get(0).status == Nest.Status.gettingPart);
        nest.msgHereAreParts(nestParts, 0);
        assertTrue("Nest parts array should contain 8 p1 parts", nest.myNests.get(0).parts.size()==8);
        assertTrue("Nest 0 status should be full", nest.myNests.get(0).status == Nest.Status.full);
        assertTrue("Parts in nest should have set its nestNum to 0", nest.myNests.get(0).parts.get(0).getNestNum()==0);
        assertTrue("The part type of nest 0 should be 1", nest.myNests.get(0).parts.get(0).type == 1);
        assertTrue("Requests size should be 1 and NeedParts size should be 0", nest.requests.size()==1 && nest.needParts.isEmpty());
        nest.pickAndExecuteAnAction();
        assertTrue("Camera should have gotten msgRequestInspection from nest" + getLogs(), camera.log.containsString("msgNestIsFull"));
        assertTrue("Nest 0 status should be full", nest.myNests.get(0).status == Nest.Status.gettingInspected);
        nest.msgNestInspected(nest.myNests.get(0), true);//camera verifies the nest
        assertTrue("Nest 0 status should be readyForKit", nest.myNests.get(0).status == Nest.Status.readyForKit);
        nest.pickAndExecuteAnAction();
        assertTrue("msgHereIsPart" + getLogs(), parts.log.containsString("msgHereIsPart"));
        assertTrue("Requests size and NeedParts size should be 0", nest.requests.isEmpty() && nest.needParts.isEmpty());
        assertTrue("Nest parts array should contain 7 p1 parts", nest.myNests.get(0).parts.size()==7);
        assertTrue("Nest 0 status still should be readyForKit", nest.myNests.get(0).status == Nest.Status.readyForKit);
    
    
}
      
       public void testCameraFindsNoGoodParts(){//tests if camera returns msgNestInspected with false
        NestAgent nest = new NestAgent("NEST AGENT");
        Part p = new Part(1);
        List<Part> nestParts = new ArrayList<Part>();
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        
        parts = new MockParts("MOCK PARTS");
        
        lane0 = new MockLane("MOCK LANE 0");
        
        camera = new MockCamera("MOCK CAMERA");
       
        nest.getNest(0).setLane(lane0);
      
        nest.setCamera(camera);
        nest.setPartsAgent(parts);
        assertTrue("Nest 0 status should be empty", nest.myNests.get(0).status == Nest.Status.empty);
        nest.msgNeedPart(p);//tests msgNeedPart below
    
        assertTrue("Requests size and NeedParts size should be 1", nest.requests.size()==1 && nest.needParts.size()==1);
    
        nest.pickAndExecuteAnAction();
    
        assertTrue("Nest 0 status should be needPart", nest.myNests.get(0).status == Nest.Status.needPart);
        nest.pickAndExecuteAnAction();
        assertTrue("Lane should have gotten msgNeedPart" + getLogs(), lane0.log.containsString("msgNeedPart"));
        assertTrue("nest 0 status should be gettingPart", nest.myNests.get(0).status == Nest.Status.gettingPart);
        nest.msgHereAreParts(nestParts, 0);
        assertTrue("Nest parts array should contain 8 p1 parts", nest.myNests.get(0).parts.size()==8);
        assertTrue("Nest 0 status should be full", nest.myNests.get(0).status == Nest.Status.full);
        assertTrue("Parts in nest should have set its nestNum to 0", nest.myNests.get(0).parts.get(0).getNestNum()==0);
        assertTrue("The part type of nest 0 should be 1", nest.myNests.get(0).parts.get(0).type == 1);
        assertTrue("Requests size should be 1 and NeedParts size should be 0", nest.requests.size()==1 && nest.needParts.isEmpty());
        nest.pickAndExecuteAnAction();
        assertTrue("Camera should have gotten msgRequestInspection from nest" + getLogs(), camera.log.containsString("msgNestIsFull"));
        assertTrue("Nest 0 status should be full", nest.myNests.get(0).status == Nest.Status.gettingInspected);
        nest.msgNestInspected(nest.myNests.get(0), false);//camera doesn't verify the nest
        assertTrue("Nest 0 status should be empty", nest.myNests.get(0).status == Nest.Status.needPart);
       
        assertTrue("Requests size should be 1 still and NeedParts size should be 0", nest.requests.size()==1 && nest.needParts.isEmpty());
        assertTrue("Nest parts array should contain 0 p1 parts after purge", nest.myNests.get(0).parts.isEmpty());
        nest.pickAndExecuteAnAction();
        assertTrue("Lane should have gotten msgNeedPart twice now" + getLogs(), lane0.log.size()==2);
        assertTrue("nest 0 status should be gettingPart", nest.myNests.get(0).status == Nest.Status.gettingPart);
        nest.msgHereAreParts(nestParts, 0);
        
        assertTrue("nest 0 status should be gettingPart", nest.myNests.get(0).status == Nest.Status.full);
        nest.msgNestInspected(nest.myNests.get(0), true);
        assertTrue("Nest 0 status should be readyForKit", nest.myNests.get(0).status == Nest.Status.readyForKit);
        nest.pickAndExecuteAnAction();
        assertTrue("msgHereIsPart should only have been recieved once" + getLogs(), parts.log.containsString("msgHereIsPart") && parts.log.size()==1);
        assertTrue("Requests size and NeedParts size should be 0", nest.requests.isEmpty() && nest.needParts.isEmpty());
        assertTrue("Nest parts array should contain 7 p1 parts", nest.myNests.get(0).parts.size()==7);
}
  
  public void testNestPurge(){//when nest doesn't have a part the kit needs and has to get new parts     
         NestAgent nest = new NestAgent("NEST AGENT");
        Part p = new Part(1);
        Part purgePart = new Part(2);
        List<Part> nestParts = new ArrayList<Part>();
        List<Part> nestPartsPurge = new ArrayList<Part>();
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestParts.add(p);
        nestPartsPurge.add(purgePart);
        nestPartsPurge.add(purgePart);
        nestPartsPurge.add(purgePart);
        nestPartsPurge.add(purgePart);
        nestPartsPurge.add(purgePart);
        nestPartsPurge.add(purgePart);
        nestPartsPurge.add(purgePart);
        nestPartsPurge.add(purgePart);
        
        
        parts = new MockParts("MOCK PARTS");
        
       lane0 = new MockLane("MOCK LANE 0");
        
        camera = new MockCamera("MOCK CAMERA");
       
        nest.getNest(0).setLane(lane0);
        nest.getNest(1).status = Nest.Status.none;//set all other nests to none so scheduler only acts on nest0
        nest.getNest(2).status = Nest.Status.none;
        nest.getNest(3).status = Nest.Status.none;
        nest.getNest(4).status = Nest.Status.none;
        nest.getNest(5).status = Nest.Status.none;
        nest.getNest(6).status = Nest.Status.none;
        nest.getNest(7).status = Nest.Status.none;
        nest.setCamera(camera);
        nest.setPartsAgent(parts);
        
        nest.msgHereAreParts(nestParts, 0);
        nest.myNests.get(0).status = Nest.Status.readyForKit;
        nest.myNests.get(0).setPart(p);
        assertTrue("Nest parts array should contain 8 p1 parts", nest.myNests.get(0).parts.size()==8);
        assertTrue("Parts in nest should have set its nestNum to 0", nest.myNests.get(0).parts.get(0).getNestNum()==0);
        assertTrue("The part type of nest 0 should be 1", nest.myNests.get(0).parts.get(0).type == 1);
        
        nest.msgNeedPart(p);
        assertTrue("Nest 0 status should be readyForKit", nest.myNests.get(0).status == Nest.Status.readyForKit);
        nest.pickAndExecuteAnAction();
        assertTrue("msgHereIsPart" + getLogs(), parts.log.containsString("msgHereIsPart"));
        assertTrue("Requests size and NeedParts size should be 1", nest.requests.isEmpty() && nest.needParts.size()==1);
        assertTrue("Nest parts array should contain 7 p1 parts", nest.myNests.get(0).parts.size()==7);
        assertTrue("Nest 0 status still should be readyForKit", nest.myNests.get(0).status == Nest.Status.readyForKit);
        nest.needParts.clear();
        nest.msgNeedPart(purgePart);
        
        assertTrue("Requests size should be 1 still and NeedParts size should be 2", nest.requests.size()==1 && nest.needParts.size()==1);
        nest.pickAndExecuteAnAction();
        assertTrue("Requests size should be 1  and NeedParts size should be 1", nest.requests.size()==1 && nest.needParts.size()==0);
        assertTrue("The part type of nest 0 should be 2 [changed after purge]", nest.myNests.get(0).part.type == 2);//after purge nest part is 2 now
        assertTrue("Nest status should be needPart", nest.myNests.get(0).status == Nest.Status.needPart);
        assertTrue("Nest parts array should contain no parts after purge", nest.myNests.get(0).parts.size()==0);
        
        nest.msgHereAreParts(nestPartsPurge, 0);
  
  }
public String getLogs() {
		StringBuilder sb = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		sb.append("-------Lane 0 Log-------");
		sb.append(newLine);
		sb.append(lane0.log.toString());
		sb.append(newLine);
		sb.append("-------End Lane 0 Log-------");

		sb.append(newLine);

		sb.append("-------Camera Log-------");
		sb.append(newLine);
		sb.append(camera.log.toString());
		sb.append("-------End Camera Log-------");
		
		sb.append(newLine);
		
		sb.append("------Parts Log------");
		sb.append(newLine);
		sb.append(parts.log.toString());
		sb.append("-------End Parts Log-------");
		
		

		return sb.toString();

	}

}
