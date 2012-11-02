package factory.factory201.interfaces;

import factory.general.Part;

public interface Feeder extends FactoryBase {

    int capacity = 0;

    public void msgNeedPart(Part part, Lane lane);
}
