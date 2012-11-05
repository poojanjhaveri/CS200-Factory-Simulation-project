package factory.factory201.test.mock;

import factory.factory201.interfaces.Camera;
import factory.general.Kit;
import factory.general.Nest;

/**
 * @author Alex Young
 */
public class MockCamera extends MockAgent implements Camera {

    public EventLog log = new EventLog();

    public MockCamera(String name) {
        super(name);
    }

    @Override
    public void msgNestIsFull(Nest nest) {
        log.add(new LoggedEvent("Received msgNestIsFull from nest agent that "
                + "nest: " + nest.name + " is full."));
    }

    @Override
    public void msgKitIsFull(Kit kit) {
        log.add(new LoggedEvent("Received msgKitIsFull from kit agent that "
                + "kit: " + kit.name + " is full."));
    }
}
