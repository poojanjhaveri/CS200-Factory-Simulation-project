package factory.controlGUI;

/**
 * The Manager plays a pivotal role in server interaction by standardizing the
 * methods used to send up data to the FactoryProductionManager simulation GUI.
 *
 * @brief used to standardize methods for all three managers
 * @author YiWei Roy Zheng
 */
public interface Manager {

    /**
     * @brief passes all relevant state information to the main Factory
     * Production Manager for painting
     */
    public void giveState();
}