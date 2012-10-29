package factory.agentGUI;

/**
 * @brief a Robotic device that manipulates its surroundings
 * @author YiWei Roy Zheng
 */
public class GUIRobot extends Drawable {

    private Integer velocityX;///<pixels/s speed X direction of Robot
    private Integer velocityY;///<pixels/s speed Y direction of Robot
    private Double rotationSpeed;///<degrees/second rotation
    
    Boolean busy;///<whether or not the KitterRobot has orders
    Double turnto;///<angle the GuiKitRobot is turning to
    private Integer moveToX;///<where robot is heading in x-coordinate
    private Integer moveToY;///<where robot is heading in y-coordinate

    Boolean flag;///<if this is ever set to true something is wrong
    /**
    @param x x-coordinate of default robot position
    @param y y-coordinate of default robot position
     */
    public GUIRobot(Integer x, Integer y, String i)
    {
        super(x,y,i);
        this.moveToX = x;
        this.moveToY=y;
	this.flag = false;
    }
    public void setConstants(Integer x, Integer y,Double r)
    {
        this.velocityX = x;
        this.velocityY = y;
        this.rotationSpeed = r;
    }
    public void moveTo(Integer x, Integer y)
    {
	this.moveToX = x;
	this.moveToY = y;
    }
    public void rotateTo(Double in)
    {
	this.turnto = in;
    }
    /**
@brief updates the position and rotation of the robot
     */
    public void update()
    {
        this.posX = (Math.abs(this.moveToX - this.posX) > this.velocityX) ? ((this.moveToX > this.posX) ? this.velocityX : -1*this.velocityX) : this.moveToX;
    }
    public Boolean bad()
    {
	return this.flag;
    }
    public String toString()
    {
	return "Position: ("+this.posX+","+this.posY+")\nVelocity: ("+this.velocityX+","+this.velocityY+")";
    }
    /**
test code for the robot
     */
    public static void main(String args[])
    {
	GUIRobot robo = new GUIRobot(20,20,"lol.png");
	robo.setConstants(2,2,1);

	for(int i =0 ; i != 10; i++)
	    {
		robo.update();
		System.out.println(robo);
	    }
    }
}
