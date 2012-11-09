/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.test.mock;

import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.PartsInterface;
import factory.general.Kit;
import factory.general.Part;

/**
 *
 * @author polarpatbear
 */
public class MockParts extends MockAgent implements PartsInterface {

    public EventLog log = new EventLog();
    
//    private KitRobot kitRobot;
    
    public MockParts(String name) {
        super(name);
//        this.kitRobot = kitRobot;
//        try {
//            Thread.currentThread().sleep(20000);
//        } catch (Exception e) {}
//        this.kitRobot.msgKitIsFull();
    }

    @Override
    public void msgNeedPart(Part part) {
        
    }

    @Override
    public void msgHereIsPart(Part part) {
        
    }

    @Override
    public void msgEmptyKitReady(Kit kit) {
        
    }

    @Override
    public void msgHereAreParts(Part part, int quantity) {
        
    }

    @Override
    public void msgHereIsKit(Kit kit) {
        
    }
}
