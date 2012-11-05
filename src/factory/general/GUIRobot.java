package factory.general;

import factory.general.MovingDrawable;
import java.util.LinkedList;

/**
 * @brief a Robotic device that manipulates its surroundings
 * @author YiWei Roy Zheng
 * @version 0.1
 */
public class GUIRobot extends MovingDrawable {

    Boolean busy;///<whether or not the Robot has orders
    Boolean flag;///<if this is ever set to true something is wrong

    protected LinkedList<Integer> orders;///<a queue of orders for the robot
    /**
     * @param x x-coordinate of default robot position
     * @param y y-coordinate of default robot position
     */
    public GUIRobot(Integer x, Integer y, String i) {
        super(x, y, 0.0, i);
        this.flag = false;
	this.orders = new LinkedList<Integer>();
    }
public void update()
{
    super.update();
}
    /**
     * @brief if something went wrong
     */
    public Boolean bad() {
        return this.flag;
    }
    public Integer getOrder()
    {
return ((this.orders.isEmpty())?-1:this.orders.get(0));
    }
    public Integer popOrder()
    {
        return ((this.orders.isEmpty())?-1:this.orders.poll());
    }
}
