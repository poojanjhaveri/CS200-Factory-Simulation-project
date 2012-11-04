package factory.factory201.interfaces;

import factory.general.Part;

public interface FactoryBase {
//public enum Part {};

    public void msgNeedPart(Part part);

    public void msgHereAreParts(Part part, int quantity);
}
