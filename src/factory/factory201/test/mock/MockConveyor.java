package factory.factory201.test.mock;

import factory.factory201.interfaces.Conveyor;
import factory.general.Kit;

/**
 * @author Alex Young
 */
public class MockConveyor extends MockAgent implements Conveyor {

    public EventLog log = new EventLog();

    public MockConveyor(String name) {
        super(name);
    }

    @Override
    public void msgNeedEmptyKit() {
        log.add(new LoggedEvent("Received msgNeedEmptyKit from kit robot."));
    }

    @Override
    public void msgHereIsVerifiedKit(Kit kit) {
        log.add(new LoggedEvent("Received msgHereIsVerifiedKit from kit robot "
                + "that kit: " + kit.name + " is ready to be put "
                + "on the conveyor."));
    }
}
