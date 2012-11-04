package factory.factory201.interfaces;

import factory.factory201.feederManagement.FeederAgent;
import factory.general.Part;

public interface Gantry extends FactoryBase {

    public void msgNeedPart(Part part, Feeder feeder);
}
