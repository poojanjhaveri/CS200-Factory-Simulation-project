/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.partsManagement;

import factory.general.Part;
import junit.framework.TestCase;

/**
 *
 * @author Kevin
 */
public class NestAgentTest extends TestCase {
    
    public NestAgentTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of msgNeedPart method, of class NestAgent.
     */
    public void testMsgNeedPart() {
        System.out.println("msgNeedPart");
        Part p = null;
        NestInterface instance = null;
        instance.msgNeedPart(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of msgHereAreParts method, of class NestAgent.
     */
    public void testMsgHereAreParts() {
        System.out.println("msgHereAreParts");
        Part p = null;
        int quantity = 0;
        NestInterface instance = null;
        instance.msgHereAreParts(p, quantity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of msgNestInspected method, of class NestAgent.
     */
    public void testMsgNestInspected() {
        System.out.println("msgNestInspected");
        boolean result_2 = false;
        NestInterface instance = null;
        instance.msgNestInspected(result_2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pickAndExecuteAnAction method, of class NestAgent.
     */
    public void testPickAndExecuteAnAction() {
        System.out.println("pickAndExecuteAnAction");
        NestInterface instance = null;
        boolean expResult = false;
        boolean result = instance.pickAndExecuteAnAction();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPartsAgent method, of class NestAgent.
     */
    public void testSetPartsAgent() {
        System.out.println("setPartsAgent");
        PartsInterface parts = null;
        NestInterface instance = null;
        instance.setPartsAgent(parts);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
