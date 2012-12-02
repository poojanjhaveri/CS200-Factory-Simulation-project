package factory.factory201.interfaces;

import factory.general.Kit;
import factory.general.Part;
import java.util.List;

/**
 * @brief contains all of the messages for the KitRobotAgent This interface
 * contains all of the messages for the KitRobotAgent. Both the KitRobotAgent
 * and a MockKitRobot can implement this interface (useful for unit testing).
 *
 * @author Alex Young
 * @version 1
 */
public interface KitRobot {

    public void msgHereIsEmptyKit(Kit kit);

    public void msgKitInspectedNoError();
    
    public void msgKitInspectedError(List<Part> missingParts);

    public void msgKitIsFull(Kit kit);

    public void msgNeedEmptyKit();
}
