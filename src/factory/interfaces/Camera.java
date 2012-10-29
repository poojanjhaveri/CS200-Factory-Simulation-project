
package factory.interfaces;

import factory.Kit;
import factory.Nest;

/**
 * @brief contains messages for CameraAgent This interface contains all of the
 * messages for the CameraAgent. Both the CameraAgent and a MockCamera can
 * implement this interface (useful for unit testing).
 *
 * @author Alex Young
 * @version 0
 */
public interface Camera {

    public void msgNestIsFull(Nest nest, int nestNum);

    public void msgKitIsFull(Kit kit, int kitNum);
}
