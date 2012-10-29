package factory.interfaces;

import factory.Kit;

/**
@brief contains all of the messages for the KitRobotAgent
 * This interface contains all of the messages for the KitRobotAgent. Both the
 * KitRobotAgent and a MockKitRobot can implement this interface (useful for
 * unit testing).
 *
 * @author Alex Young
 * @version 0
 */
public interface KitRobot {
    public void msgHereIsEmptyKit(Kit kit, int loc);
    public void msgKitInspected(Kit kit, boolean result);
    public void msgKitIsComplete(Kit kit);
    public void msgNeedEmptyKit();
}
