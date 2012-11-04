package factory.factory201.interfaces;

import factory.general.Kit;

/**
 * @brief contains all of the messages for the KitRobotAgent This interface
 * contains all of the messages for the KitRobotAgent. Both the KitRobotAgent
 * and a MockKitRobot can implement this interface (useful for unit testing).
 *
 * @author Alex Young
 * @version 0
 */
public interface KitRobot {

    public void msgHereIsEmptyKit(Kit kit);

    public void msgKitInspected(boolean result);

    public void msgKitIsFull();

    public void msgNeedEmptyKit();
}
