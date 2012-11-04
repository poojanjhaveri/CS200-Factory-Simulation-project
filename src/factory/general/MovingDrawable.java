package factory.general;

/**
@brief any drawable that is capable of self-movement
@author YiWei Roy Zheng
@version 0.2
 */
public class MovingDrawable extends Drawable {

    private Integer velocityX;///<pixels/s speed X direction of Drawable
    private Integer velocityY;///<pixels/s speed Y direction of Drawable
    private Double rotationSpeed;///<degrees/second rotation of Drawable
    private Double rotateto;///<angle the Drawable is turning to
    private Integer moveToX;///<where Drawable is heading in x-coordinate
    private Integer moveToY;///<where Drawable is heading in y-coordinate

    private Boolean moving;///<whether or not the drawable is currently moving @deprecated

    /**
    Instantiate a MovingDrawable with position, rotation, and image
    @param x starting x-coordinate
    @param y starting y-coordinate
    @param a starting rotation
    @param i image file name
     */
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
        this.moving = true;
    }
    /**
    @brief sets the next angle to rotate to for the drawable
     */
    public void rotateTo(Double in)
    {
        while(in >= 360)in-=360;
        this.rotateto = in;
    }
    public void turnTo(Double in)
    {
        while(in >= 360)in-=360;
        this.rotateto = in;
    }
    /**
    @brief updates the position and rotation of the robot
     */
    public void update()
    {
        if(this.cords.getX() != this.moveToX) {
            if(Math.abs(this.moveToX - this.cords.getX()) < this.velocityX)
                this.cords.setX(this.moveToX);
            else {
                this.cords.setX((this.moveToX > this.cords.getX()) ? (this.velocityX+this.cords.getX()) : (cords.getX()-this.velocityX));
            }
        }

        if(this.cords.getY() != this.moveToY) {
            if(Math.abs(this.moveToY - this.cords.getY()) < this.velocityY)
                this.cords.setY(this.moveToY);
            else
            {
                this.cords.setY(((this.moveToY > this.cords.getY()) ? this.velocityY+this.cords.getY() : this.cords.getY()-this.velocityY));
            }
        }

        if(Math.abs(this.rotateto-this.rotation) < this.rotationSpeed) {
            this.rotation=this.rotateto;
        } else
        {
            Double i = (this.rotateto-this.rotation);
            if( i < 0) {
                if(360-Math.abs(i) >= 180)
                {
                    this.setRotation(this.rotation-this.rotationSpeed);
                }
                else {
                    this.setRotation(this.rotation+this.rotationSpeed);
                }
            } else {
                if(360-Math.abs(i) >= 180)
                {
                    this.setRotation(this.rotation+this.rotationSpeed);
                }
                else {
                    this.setRotation(this.rotation-this.rotationSpeed);
                }

            }

            //	    this.setRotation( ((Math.abs(360-(this.rotateto-this.rotation))) >= 180 ? )this.rotation-this.rotationSpeed:this.rotation+this.rotationSpeed);


            //this.setRotation((this.rotateto-this.rotation > this.rotation + Math.abs(this.rotateto-360))?this.rotation-this.rotationSpeed:this.rotation+this.rotationSpeed);
            //this.setRotation((this.rotation > this.rotateto && Math.abs(this.rotation - this.rotateto) < 180)?this.rotation-this.rotationSpeed:this.rotation+this.rotationSpeed);
            /*
                if(this.rotation > 180)
                {
                    this.rotation = (this.rotateto > 270)?this.rotation-this.rotationSpeed:this.rotation+this.rotationSpeed;
                } else if(this.rotation < 180) {
                    this.rotation = (this.rotateto > 90)?this.rotation-this.rotationSpeed:this.rotation+this.rotationSpeed;
                } else {
                    this.rotation = (this.rotateto > 180)?this.rotation+this.rotationSpeed:this.rotation-this.rotationSpeed;
            }*/
            while(this.rotation >= 360) {
                this.rotation -= 360;
            }
            if(this.rotation < 0)
            {
                this.rotation += 360;
            }
        }
    }
    /**
    @brief halts the drawable on the spot
     */
    public void halt() {
        this.moveToX = this.cords.getX();
        this.moveToY = this.cords.getY();
    }
    /**
    @brief whether or not the moving drawable is currently moving
     */
    public Boolean moving()
    {
        return (this.moveToX == this.cords.getX() && this.moveToY == this.cords.getY());
    }
    /**
    tostring
     */
    public String toString()
    {
        return "Position: ("+this.cords.getX()+","+this.cords.getY()+")\tVelocity: ("+this.velocityX+","+this.velocityY+") "+this.rotationSpeed+" angles/sec" + "\tFacing " + rotation + " degrees";
    }

    /**
    test code for the class
     */
    public static void main(String args[])
    {
        MovingDrawable robo = new MovingDrawable(20,60,0.0,"lol.png");
        robo.setConstants(2,2,1.0);

        System.out.println("              --==[|Basic Test|]==--");
        robo.moveTo(40,40);
        robo.turnTo(10.0);
        System.out.println("Moving drawable to 40,40 facing 10 degrees");
        System.out.println(robo+"\n=======================================");
        for(int i =0 ; i != 11; i++)
        {
            robo.update();
            System.out.println("Movement " + (i+1));
            System.out.println(robo);
        }
        System.out.println("------->"+((robo.getCoordinate().getX() == 40 && robo.getCoordinate().getY() == 40 && robo.getRotation() == 10)?"Pass":"FAILED"));

        System.out.println("              --==[|Extended Rotation Test|]==--");
        robo = new MovingDrawable(0,0,0.0,"lol.png");
        robo.setConstants(0,0,10.0);
        robo.turnTo(180.0);
        System.out.println("Turning to 180");
        for(int i =0 ; i != 20; i++)
        {
            robo.update();
            System.out.println(robo);
        }
        System.out.println("------->"+((robo.getRotation() == 180)?"Pass":"FAILED"));
        System.out.println("Turning to 359");
        robo.turnTo(359.0);
        for(int i =0 ; i != 20; i++)
        {
            robo.update();
            System.out.println(robo);
        }
        System.out.println("------->"+((robo.getRotation() == 359)?"Pass":"FAILED"));
        System.out.println("Turning to 270");
        robo.turnTo(270.0);
        for(int i =0 ; i != 10; i++)
        {
            robo.update();
            System.out.println(robo);
        }
        System.out.println("------->"+((robo.getRotation() == 270)?"Pass":"FAILED"));
        System.out.println("Turning to 359");
        robo.turnTo(359.0);
        for(int i =0 ; i != 20; i++)
        {
            robo.update();
            System.out.println(robo);
        }
        System.out.println("------->"+((robo.getRotation() == 359)?"Pass":"FAILED"));
        System.out.println("Turning to 90");
        robo.turnTo(90.0);
        for(int i =0 ; i != 10; i++)
        {
            robo.update();
            System.out.println(robo);
        }
        System.out.println("------->"+((robo.getRotation() == 90)?"Pass":"FAILED"));
        System.out.println("Turning to 270");
        robo.turnTo(270.0);
        for(int i =0 ; i != 20; i++)
        {
            robo.update();
            System.out.println(robo);
        }
        System.out.println("------->"+((robo.getRotation() == 270)?"Pass":"FAILED"));
        System.out.println("Turning to 77");
        robo.turnTo(77.0);
        for(int i =0 ; i != 18; i++)
        {
            robo.update();
            System.out.println(robo);
        }
        System.out.println("------->"+((robo.getRotation() == 77)?"Pass":"FAILED"));
        System.out.println("Turning to 270");
        robo.turnTo(270.0);
        for(int i =0 ; i != 18; i++)
        {
            robo.update();
            System.out.println(robo);
        }
        System.out.println("------->"+((robo.getRotation() == 270)?"Pass":"FAILED"));
        System.out.println("Turning to 111");
        robo.turnTo(111.0);
        for(int i =0 ; i != 17; i++)
        {
            robo.update();
            System.out.println(robo);
        }
        System.out.println("------->"+((robo.getRotation() == 111)?"Pass":"FAILED"));
        System.out.println("Turning to 270");
        robo.turnTo(270.0);
        for(int i =0 ; i != 17; i++)
        {
            robo.update();
            System.out.println(robo);
        }
        System.out.println("------->"+((robo.getRotation() == 270)?"Pass":"FAILED"));
        System.out.println("Turning to 180");
        robo.turnTo(180.0);
        for(int i =0 ; i != 20; i++)
        {
            robo.update();
            System.out.println(robo);
        }
        System.out.println("------->"+((robo.getRotation() == 180)?"Pass":"FAILED"));
        System.out.println("Turning to 0");
        robo.turnTo(0.0);
        for(int i =0 ; i != 20; i++)
        {
            robo.update();
            System.out.println(robo);
        }
        System.out.println("------->"+((robo.getRotation() == 0)?"Pass":"FAILED"));
        System.out.println("Turning to 360");
        robo.turnTo(360.0);
        for(int i =0 ; i != 5; i++)
        {
            robo.update();
            System.out.println(robo);
        }
        System.out.println("------->"+((robo.getRotation() == 0)?"Pass":"FAILED"));

    }

}