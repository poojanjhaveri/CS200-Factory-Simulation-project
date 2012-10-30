package factory.agentGUI;
/**
@brief any drawable that is capable of movement
 */
public class MovingDrawable extends Drawable{

private Integer velocityX;///<pixels/s speed X direction of Drawable
    private Integer velocityY;///<pixels/s speed Y direction of Drawable
    private Double rotationSpeed;///<degrees/second rotation of Drawable
    private Double rotateto;///<angle the Drawable is turning to
    private Integer moveToX;///<where Drawable is heading in x-coordinate
    private Integer moveToY;///<where Drawable is heading in y-coordinate

    public MovingDrawable(Integer x, Integer y, Double a, String i)
    {
        super(x,y,a,i);
this.moveToX = x;
        this.moveToY=y;
	    
}    

/**
@brief sets the speed and rotational speed of the drawable
     */
public void setConstants(Integer x, Integer y,Double r)
    {
        this.velocityX = x;
        this.velocityY = y;
        this.rotationSpeed = r;
    }
    /**
@brief sets the destination of movement for the drawable
     */
public void moveTo(Integer x, Integer y)
    {
	this.moveToX = x;
	this.moveToY = y;
    }
    /**
@brief sets the next angle to rotate to for the drawable
     */
    public void rotateTo(Double in)
    {
	this.rotateto = in;
    }
    public void turnTo(Double in)
    {
	this.rotateto = in;

    }
    /**
@brief updates the position and rotation of the robot
     */
    public void update()
    {
	if(this.posX != this.moveToX)
        this.posX = (Math.abs(this.moveToX - this.posX) > this.velocityX) ? ((this.moveToX > this.posX) ? this.velocityX+posX : posX-this.velocityX) : this.moveToX;
	if(this.posY != this.moveToY)
this.posY = (Math.abs(this.moveToY - this.posY) > this.velocityY) ? ((this.moveToY > this.posY) ? this.velocityY+posY : posY-this.velocityY) : this.moveToY;

	if(this.rotateto-this.rotation < this.rotationSpeed){
	    this.rotation=this.rotateto;
	}else
	    {
		if(this.rotation > 180)
		    {
			this.rotation = (this.rotateto > 270)?this.rotation-this.rotationSpeed:this.rotation+this.rotationSpeed;
		    }else if(this.rotation < 180){
		this.rotation = (this.rotateto > 90)?this.rotation-this.rotationSpeed:this.rotation+this.rotationSpeed;
		}else{
		    this.rotation = (this.rotateto > 180)?this.rotation+this.rotationSpeed:this.rotation-this.rotationSpeed;
		}
	    }    
}
    /**
tostring
     */
    public String toString()
    {
	return "Position: ("+this.posX+","+this.posY+")\tVelocity: ("+this.velocityX+","+this.velocityY+") "+this.rotationSpeed+" angles/sec" + "\tFacing " + rotation + " degrees";
    }

    /**
test code for the class
     */
    public static void main(String args[])
    {
	GUIRobot robo = new GUIRobot(20,60,"lol.png");
	robo.setConstants(2,2,1.0);

	System.out.println("          --==[|Basic Test|]==--");
	robo.moveTo(40,40);
	robo.turnTo(10.0);
	System.out.println("Moving robot to 40,40 facing 10 degrees");	
System.out.println(robo+"\n=======================================");
	for(int i =0 ; i != 11; i++)
	    {
	       	robo.update();
		System.out.println("Movement " + (i+1));
		System.out.println(robo);
	    }

System.out.println("          --==[|Extended Rotation Test|]==--");
robo = new GUIRobot(0,0,"lol.png");
robo.setConstants(0,0,10.0);
robo.turnTo(180.0);
System.out.println("Turning to 180");
for(int i =0 ;i != 19; i++)
    {
	robo.update();
	System.out.println(robo);
    }
System.out.println("Turning to 360");
robo.turnTo(360.0);
for(int i =0 ;i != 19; i++)
    {
	robo.update();
	System.out.println(robo);
    }
System.out.println("Turning to 90");
robo.turnTo(90.0);
for(int i =0 ;i != 19; i++)
    {
	robo.update();
	System.out.println(robo);
    }
System.out.println("Turning to 270");
robo.turnTo(270.0);
for(int i =0 ;i != 19; i++)
    {
	robo.update();
	System.out.println(robo);
    }
System.out.println("Turning to 0");
robo.turnTo(0.0);
for(int i =0 ;i != 19; i++)
    {
	robo.update();
	System.out.println(robo);
    }

    }

}