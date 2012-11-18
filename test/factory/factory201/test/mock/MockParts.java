/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.test.mock;

import factory.factory201.interfaces.PartsInterface;
import factory.general.Kit;
import factory.general.Part;
import java.util.List;

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
    public void msgNeedPart(Part partType) {
        //Not used
    }

    @Override
    public void msgHereIsPart(Part part) {
        log.add(new LoggedEvent("Received msgHereIsPart from nest agent and "
                + "received part of type: " + part.type));
    }

    @Override
    public void msgEmptyKitReady(Kit kit) {
        log.add(new LoggedEvent("Received msgEmptyKitReady from kit robot "
                + "agent that empty kit: " + kit.name + " is ready."));
    }

    @Override
    public void msgHereAreParts(List<Part> parts) {
    }

   
    public void msgHereIsKit(Kit kit) {
        log.add(new LoggedEvent("got kit " + kit.name + " from nest"));
    }

    @Override
    public void msgHereIsKit(List<Kit> kit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
