package factory.factory201.interfaces;

import factory.general.Nest;
import factory.general.Part;
import java.util.List;

public interface NestInterface extends FactoryBase {

    int capacity = 9;
    public void msgNestInspected(Nest n, boolean result);

    public void setNestPurge(List<Part> parts);
    
    
}
