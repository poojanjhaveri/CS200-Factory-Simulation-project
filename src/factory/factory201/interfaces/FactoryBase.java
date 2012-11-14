package factory.factory201.interfaces;

import factory.general.Part;
import java.util.List;

public interface FactoryBase {
//public enum Part {};

    public void msgNeedPart(Part part);

    public void msgHereAreParts(List<Part> parts);
}
