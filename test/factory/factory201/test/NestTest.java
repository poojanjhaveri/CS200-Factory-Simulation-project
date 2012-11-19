/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.test;
import factory.factory201.partsManagement.NestAgent;
import factory.factory201.test.mock.MockCamera;
import factory.factory201.test.mock.MockLane;
import factory.factory201.test.mock.MockParts;
import factory.general.Nest;
import factory.general.Part;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;



/**
 *
 * @author polarpatbear
 */

public class NestTest extends TestCase{
       
       public MockLane lane;
       //public KitAssemblyManager kam; 
       public MockParts parts;     
       public MockCamera camera;
       public NestAgent nest;
       public MockLane lane0;
       public MockLane lane1;
       public MockLane lane2;
       public MockLane lane3;
       public MockLane lane4;
       public MockLane lane5;
       public MockLane lane6;
       public MockLane lane7;
       public Part p;
       public List<Part> nestParts;
       

      @Test
      public void testFirst(){
        lane0 = new MockLane("LANE0");
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
        
        MockParts parts = new MockParts("MOCK PARTS");
       
        NestAgent nest = new NestAgent("NEST AGENT");
        MockCamera camera = new MockCamera("MOCK CAMERA");
       
        nest.getNest(0).setLane(lane0);
        
        nest.setCamera(camera);
        nest.setPartsAgent(parts);
    assertTrue("Nest 0 status should be empty", nest.myNests.get(0).status == Nest.Status.empty);
    nest.msgNeedPart(p);
    
    assertTrue("Requests size and NeedParts size should be 1", nest.requests.size()==1 && nest.needParts.size()==1);
    
    nest.pickAndExecuteAnAction();
    
    assertTrue("Nest 0 status should be needPart", nest.myNests.get(0).status == Nest.Status.needPart);
    nest.pickAndExecuteAnAction();
    assertTrue("Lane should have gotten msgNeedPart" + getLogs(), lane0.log.containsString("msgNeedPart"));
    assertTrue("nest 0 status should be gettingPart", nest.myNests.get(0).status == Nest.Status.gettingPart);
    nest.msgHereAreParts(nestParts, 0);
    assertTrue("Nest parts array should contain 8 p1 parts", nest.myNests.get(0).parts.size()==8);
    assertTrue("Nest 0 status should be full", nest.myNests.get(0).status == Nest.Status.full);
    nest.pickAndExecuteAnAction();
    assertTrue("Camera should have gotten msgRequestInspection from nest" + getLogs(), camera.log.containsString("msgNestIsFull"));
    assertTrue("Nest 0 status should be full", nest.myNests.get(0).status == Nest.Status.gettingInspected);
    nest.msgNestInspected(nest.myNests.get(0), true);
    assertTrue("Nest 0 status should be full", nest.myNests.get(0).status == Nest.Status.readyForKit);
    nest.pickAndExecuteAnAction();
    assertTrue("msgHereIsPart" + getLogs(), parts.log.containsString("msgHereIsPart"));
    assertTrue("Requests size and NeedParts size should be 0", nest.requests.size()==0 && nest.needParts.size()==0);
    
    /* 
    nest.pickAndExecuteAnAction();
    nest.pickAndExecuteAnAction();
    nest.pickAndExecuteAnAction();
    nest.pickAndExecuteAnAction();
    nest.pickAndExecuteAnAction();
    nest.pickAndExecuteAnAction();
    nest.pickAndExecuteAnAction();*/
    
    
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
