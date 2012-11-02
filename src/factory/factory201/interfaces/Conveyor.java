package factory.factory201.interfaces;

import factory.Kit;

/**
 * @brief contains messages for ConveyerAgent This interface contains all of the
 * messages for the ConveyorAgent. Both the ConveyorAgent and a MockConveyor can
 * implement this interface (useful for unit testing).
 *
 * @author Alex Young
 * @version 0
 */
public interface Conveyor {

    public void msgNeedEmptyKit();

    public void msgHereIsVerifiedKit(Kit kit);
}
