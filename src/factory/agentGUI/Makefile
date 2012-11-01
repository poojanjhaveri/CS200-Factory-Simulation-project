BASE=Drawable.java MovingDrawable.java Coordinate.java
PARAM=-d .
PACKAGE=factory.agentGUI

movingdrawable:
	javac $(PARAM) $(BASE) 
	java $(PACKAGE).MovingDrawable > _movingdrawable.txt
kitrobot:
	javac $(PARAM) $(BASE) GUIRobot.java GUIKitRobot.java
	java $(PACKAGE).GUIKitRobot > _guikitrobot.txt

