package factory.factory201.test.mock;

import factory.factory201.interfaces.KitRobot;
import factory.general.Kit;
import factory.general.Part;
import java.util.List;

/**
 * @author Alex Young
 */
public class MockKitRobot extends MockAgent implements KitRobot {
    
    public EventLog log = new EventLog();
    
    public MockKitRobot(String name) {
        super(name);
    }

    @Override
    public void msgHereIsEmptyKit(Kit kit) {
        log.add(new LoggedEvent("Received msgHereIsEmptyKit from the "
                + "conveyor to get empty kit: " + kit.name + "."));
    }
    
   @Override
    public void msgKitIsFull(Kit k) {
        log.add(new LoggedEvent("Received msgKitIsFull from parts agent "
                + "that the kit is full."));
    }

    @Override
    public void msgNeedEmptyKit() {
        log.add(new LoggedEvent("Received msgNeedEmptyKit from parts agent "
                + "that it needs an empty kit to put parts in."));
    }

    @Override
    public void msgKitInspectedNoError() {
        log.add(new LoggedEvent("Received msgKitInspected from the "
                + "camera that the kit is verified."));
    }

    @Override
    public void msgKitInspectedError(List<Part> missingParts) {
        log.add(new LoggedEvent("Received msgKitInspected from the "
                + "camera that the kit has an error."));
    }
}
