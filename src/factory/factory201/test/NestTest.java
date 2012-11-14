/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.test;
import factory.factory201.test.mock.MockParts;
import factory.factory201.partsManagement.NestAgent;
import factory.factory201.test.mock.MockCamera;
import factory.factory201.test.mock.MockLane;
import factory.general.Nest;
import factory.general.Part;
import junit.framework.TestCase;
import static org.junit.Assert.*;
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
       List<Part> nestParts = new ArrayList<Part>();
       
   @Override
	protected void setUp() throws Exception {

        lane0 = new MockLane("LANE0");
        lane1 = new MockLane("LANE1");
        lane2 = new MockLane("LANE2");
        lane3 = new MockLane("LANE3");
        lane4 = new MockLane("LANE4");
        lane5 = new MockLane("LANE5");
        lane6 = new MockLane("LANE6");
        lane7 = new MockLane("LANE7");
        nestParts.add(new Part(1));
        nestParts.add(new Part(1));
        nestParts.add(new Part(1));
        nestParts.add(new Part(1));
        nestParts.add(new Part(1));
        nestParts.add(new Part(1));
        nestParts.add(new Part(1));
        nestParts.add(new Part(1));
        parts = new MockParts("MOCK PARTS");
       
        nest = new NestAgent("NEST AGENT");
        camera = new MockCamera("MOCK CAMERA");
       
        nest.getNest(0).setLane(lane0);
        nest.getNest(1).setLane(lane1);
        nest.getNest(2).setLane(lane2);
        nest.getNest(3).setLane(lane3);
        nest.getNest(4).setLane(lane4);
        nest.getNest(5).setLane(lane5);
        nest.getNest(6).setLane(lane6);
        nest.getNest(7).setLane(lane7);
        nest.setCamera(camera);
        nest.setPartsAgent(parts);
       
        //nest.startThread();

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
public void firstTest(){
    nest.msgNeedPart(null);
    /*nest.msgNeedPart(new Part(2));
    nest.msgNeedPart(new Part(3));
    nest.msgNeedPart(new Part(4));
    nest.msgNeedPart(new Part(5));
    nest.msgNeedPart(new Part(6));
    nest.msgNeedPart(new Part(7));
    nest.msgNeedPart(new Part(8));*/
    
    assertTrue("Nest 0 should have status of needPart", nest.myNests.get(0).status == Nest.Status.needPart);
    
    nest.pickAndExecuteAnAction();
    assertTrue("Nest 0 status should be gettingPart", nest.myNests.get(0).status == Nest.Status.gettingPart);
    assertTrue("Lane should have gotten msgNeedPart" + getLogs(), lane0.log.containsString("msgNeedPart"));
    nest.msgHereAreParts(nestParts);
    assertTrue("Nest parts array should contain 8 p1 parts", nest.myNests.get(0).parts.size()==8 && nest.myNests.get(0).parts.get(0).type==Part.Type.p1);
    nest.pickAndExecuteAnAction();
    assertTrue("Camera should have gotten msgRequestInspection from nest" + getLogs(), camera.log.containsString("msgNestIsFull"));
    nest.msgNestInspected(nest.myNests.get(0), true);
    nest.pickAndExecuteAnAction();
    assertTrue("msgHereIsPart" + getLogs(), parts.log.containsString("msgHereIsPart"));
    
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
