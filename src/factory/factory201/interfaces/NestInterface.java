package factory.factory201.interfaces;

import factory.general.Nest;
import factory.general.Part;
import java.util.List;

public interface NestInterface extends FactoryBase {

    int capacity = 9;
    @Override
     public void msgNeedPart(Part p);
    
    public void msgHereAreParts(List<Part> kitParts, int laneIndex);
    
    public void msgNestInspected(Nest n, boolean result);
    
}
