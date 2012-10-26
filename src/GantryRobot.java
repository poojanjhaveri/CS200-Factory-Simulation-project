Inner Class: Gantry Robot
  Roy Zheng
General description

Data
velocityX (static final Integer) - pixels/s speed X direction of gantry robot
velocityY (static final Integer) - pixels/s speed Y direction of gantry robot
bin (Bin) - null if no bin, otherwise contains the information on the bin
extended (Boolean) - whether or not the robot has arms extended
hasbin (Boolean) - whether or not the robot is carrying a bin
movetoX - X-coordinate destination of the gantry robot
movetoY - Y-coordinate destination of gantry robot
posX - current X-coordinate of the gantry robot
posY - current Y-coordinate of the gantry robot
Methods
main method to individually run this class
moveTo(Integer,Integer) [void] - sets a new destination coordinate for the gantry robot
moveToFeeder(Integer) [void] - sets a new feeder destination coordinate for gantry robot, the passed Integer specifies which feeder to head to
moveToDump() [void] - sets the new destination to the bin dump site
extend() [void] - extends the arm
retract() [void] - retracts the arm

/**
The gantry robot has basic bin movement and emptying capabilities. 
In relation to the simulation GUI, the gantry robot has 
<ul>
<li>arm extension functionality (purely aesthetic)</li>
<li>bin picking functionality</li>
<li>bin dropping functionality</li>
<li>bin opening (dumping) functionality.</li>
</ul>
<img src=
@brief
@author YiWei Roy Zheng
*/