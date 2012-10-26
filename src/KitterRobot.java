
/**
The KitterRobot obtains parts from the nest and places it into the working kit. It takes orders from the Kit Assembly Manager.
It has pickup functionality to transport parts along its arm to the working kit
It moves along a rail and only stops at one of eight preset positions facing a lane
the robot arm rotates along the rail to face the lane at the optimal angle
<img src="../img/image_09.png" alt="KitterRobot waiting for nest to fill"/>
<img src="../img/image_08.png" alt=KitterRobot picking from the lane"/>
@brief Robot that creates kits using parts from the lane nests
@author YiWei Roy Zheng
*/
public class KitterRobot{

Inventory parts;///<current parts on the KitterRobot, en route to the Kit
Boolean busy;///<whether or not the KitterRobot has orders
Integer moveto;///<which lane the KitterRobot is heading to


public void moveTo(Integer x,Integer y);///<sets a new destination coordinate for the KitterRobot
public void moveToLane(Integer l);///<sets a new lane destination coordinate for KitterRobot, the passed Integer specifies which lane to head to
public void updateParts();///<updates the location of all parts in the inventory
}