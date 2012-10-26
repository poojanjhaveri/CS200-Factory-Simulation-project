
/**
The gantry robot has basic bin movement and emptying capabilities. 
In relation to the simulation GUI, the gantry robot has 
<ul>
<li>arm extension functionality (purely aesthetic)</li>
<li>bin picking functionality</li>
<li>bin dropping functionality</li>
<li>bin opening (dumping) functionality.</li>
</ul>
<img src=../img/image_03.png" alt="unused Gantry robot with arms retracted"/>
<img src=../img/image_07.png" alt="Gantry robot in use and carring a bin"/>
@brief shared Robot that manipulates part bins
@author YiWei Roy Zheng
*/

public class GantryRobot {
Bin bin;///<null if no bin, otherwise contains the information on the bin
Boolean extended;///<whether or not the robot has arms extended
Boolean hasbin///<whether or not the robot is carrying a bin
Integer movetoX;///<X-coordinate destination of the gantry robot
Integer movetoY;///<Y-coordinate destination of gantry robot

GantryRobot()
{

}

public void moveTo(Integer,Integer);///<sets a new destination coordinate for the gantry robot
public void moveToFeeder(Integer);///<sets a new feeder destination coordinate for gantry robot, the passed Integer specifies which feeder to head to
public void moveToDump();///<sets the new destination to the bin dump site
public void extend();///<extends the arm
public void retract();///<retracts the arm
}