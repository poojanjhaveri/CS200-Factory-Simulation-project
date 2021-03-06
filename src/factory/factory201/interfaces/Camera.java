package factory.factory201.interfaces;

import factory.general.Kit;
import factory.general.Nest;

/**
 * @brief contains messages for CameraAgent This interface contains all of the
 * messages for the CameraAgent. Both the CameraAgent and a MockCamera can
 * implement this interface (useful for unit testing).
 *
 * @author Alex Young
 * @version 1
 */
public interface Camera {
    
    public void msgHereIsKitInfo(Kit kit);
    
    public void msgNestIsFull(Nest nest);

    public void msgKitIsFull(Kit kit);
}
