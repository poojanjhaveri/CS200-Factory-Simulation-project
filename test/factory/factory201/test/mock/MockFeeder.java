package factory.factory201.test.mock;

import factory.factory201.interfaces.*;
import factory.general.Part;
import java.util.*;

public class MockFeeder extends MockAgent implements Feeder {

    public EventLog log = new EventLog();

    public MockFeeder(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void msgNeedPart(Part partType) {
        // TODO Auto-generated method stub
        log.add(new LoggedEvent(
                "Need parts event encountered"));
    }

    @Override
    public void msgHereAreParts(List<Part> parts) {
        System.out.println("message received");
        log.add(new LoggedEvent(
                "Received parts event encountered"));
    }

    @Override
    public void msgNeedPart(Part part, Lane lane) {
        log.add(new LoggedEvent(
                "Need parts from lane event encountered"));

    }

    @Override
    public int getIndex() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
