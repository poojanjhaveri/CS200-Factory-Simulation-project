package factory.interfaces;

import factory.Part;

public interface Feeder extends FactoryBase {

    int capacity = 0;

    public void msgNeedPart(Part part, Lane lane);
}
