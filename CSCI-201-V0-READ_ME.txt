Parts dumped into bins and fed down the lanes:
	Run src/factory/factory200/laneManager/V0LaneBinGantry.java

Notes: All the part types have been initialized to quantity=0. So, the lanes start requesting for parts. To change
the intialized quantity, access src/factory/factory201/feederManagement/LaneAgent.java and change the quantities in any
of the lines: 67-74. Ex- Changing parts.add(new myParts(p1,0,1)); to parts.add(new myParts(p1,9,1)); would initialize the
quantity of p1 to be over the threshold (8) , and so, the lane will not ask for part type p1 from the feeder.

The kit robot picking kits from the agv/conveyor, putting them on the stand, and the reverse. - includes vision (camera):
	Run src/factory/general/Server.java
	Run src/factory/factory201/kitManagement/V0KitRobotCameraConveyor.java

The parts robot picking up parts from the nests and putting them in the kit:
