package factory.factory201.test.mock;

import factory.factory201.interfaces.NestInterface;
import factory.general.Nest;
import factory.general.Part;
import factory.general.Result;
import java.util.List;

public class MockNest extends MockAgent implements NestInterface {

    public EventLog log = new EventLog();

    public MockNest(String name) {
        super(name);
    }

    @Override
    public void msgNeedPart(Part partType) {
       log.add(new LoggedEvent("Received msgNeedPart of part "));
    }

    @Override
    public void msgHereAreParts(List<Part> parts) {
      log.add(new LoggedEvent("Received parts event encountered "));
    }

   
    @Override
    public void msgHereAreParts(List<Part> parts, int index) {
        log.add(new LoggedEvent("Got msgHereAreParts from Lane"+ index));
    }

    @Override
    public void msgNestInspected(Nest n, Result result) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

 
}
