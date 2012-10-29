package factory.agentGUI;

/**
 * @brief a Robotic device that manipulates its surroundings
 * @author YiWei Roy Zheng
 */
public class GUIRobot extends Drawable{

    private Integer velocityX;///<pixels/s speed X direction of Robot
    private Integer velocityY;///<pixels/s speed Y direction of Robot
    private Double rotationSpeed;///<degrees/second rotation
    Boolean busy;///<whether or not the KitterRobot has orders
    Double turnto;///<angle the GuiKitRobot is turning to
    private Integer moveToX;///<where robot is heading in x-coordinate
    private Integer moveToY;///<where robot is heading in y-coordinate

    /**
@param x x-coordinate of default robot position
@param y y-coordinate of default robot position
     */
    public GUIRobot(Integer x, Integer y, String i)
    {
	super(x,y,String i);
	this.moveToX = x;
	this.moveToY=y;
    }
    public void setConstants(Integer x, Integer y,Double r)
    {
	this.velocityX = x;
	this.velocityY = y;
	this.rotationSpeed = r;
    }
    public void update()
    {
	System.out.println("Attempting to call Robot.update(). Bad idea. Fix by overriding update() in your subclass.");
    }
}
