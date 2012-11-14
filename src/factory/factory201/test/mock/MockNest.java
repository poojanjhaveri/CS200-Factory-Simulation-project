package factory.factory201.test.mock;

import factory.factory201.interfaces.NestInterface;
import factory.general.Nest;
import factory.general.Part;
import java.util.List;

public class MockNest extends MockAgent implements NestInterface {

    public EventLog log = new EventLog();

    public MockNest(String name) {
        super(name);
    }

    @Override
    public void msgNeedPart(Part partType) {
        
    }

    @Override
    public void msgHereAreParts(List<Part> parts) {
        
    }

    @Override
    public void msgNestInspected(Nest n, boolean result) {
        
    }
}
