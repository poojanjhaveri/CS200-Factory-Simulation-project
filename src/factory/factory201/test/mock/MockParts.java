/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.Test.mock;

import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.PartsInterface;
import factory.factory201.test.mock.EventLog;
import factory.factory201.test.mock.LoggedEvent;
import factory.factory201.test.mock.MockAgent;
import factory.general.Kit;
import factory.general.Part;

/**
 *
 * @author polarpatbear
 */
public class MockParts extends MockAgent implements PartsInterface {

    public EventLog log = new EventLog();
    

    
    public MockParts(String name) {
        super(name);

    }

    @Override
    public void msgNeedPart(Part part) {
        
    }

    @Override
    public void msgHereIsPart(Part part) {
        log.add(new LoggedEvent("got part " + part + "from nest from msgHereIsPart"));
    }

    @Override
    public void msgEmptyKitReady(Kit kit) {
        
    }

    @Override
    public void msgHereAreParts(Part part, int quantity) {
        
    }

    @Override
    public void msgHereIsKit(Kit kit) {
        log.add(new LoggedEvent("got kit " + kit.name + " from nest"));
    }
}
