package factory.factory201.test.mock;

import factory.factory201.interfaces.NestInterface;
import factory.general.Nest;
import factory.general.Part;

public class MockNest extends MockAgent implements NestInterface {

    public EventLog log = new EventLog();

    public MockNest(String name) {
        super(name);
    }

    @Override
    public void msgNeedPart(Part part) {
        
    }

    @Override
    public void msgHereAreParts(Part part, int quantity) {
        
    }

    @Override
    public void msgNestInspected(Nest n, boolean result) {
        
    }
}
