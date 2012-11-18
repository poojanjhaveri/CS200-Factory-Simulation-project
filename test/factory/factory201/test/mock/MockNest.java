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
        log.add(new LoggedEvent("Received msgNeedPart of part " + partType.getString()));
    }

    @Override
    public void msgHereAreParts(List<Part> parts) {
      log.add(new LoggedEvent("Received parts event encountered" + parts.get(0).getString()));   
    }

    @Override
    public void msgNestInspected(Nest n, boolean result) {
          log.add(new LoggedEvent("Nest Inspected"));
    }

    
}
