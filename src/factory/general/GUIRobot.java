package factory.general;

import factory.general.MovingDrawable;

/**
 * @brief a Robotic device that manipulates its surroundings
 * @author YiWei Roy Zheng
 * @version 0.1
 */
public class GUIRobot extends MovingDrawable {


    Boolean busy;///<whether or not the Robot has orders
    Boolean flag;///<if this is ever set to true something is wrong

    /**
    @param x x-coordinate of default robot position
    @param y y-coordinate of default robot position
     */
    public GUIRobot(Integer x, Integer y, String i)
    {
        super(x,y,0.0,i);
        this.flag = false;
    }

    /**
    @brief if something went wrong
    */
    public Boolean bad()
    {
        return this.flag;
    }
}
