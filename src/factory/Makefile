BASE=agentGUI/Drawable.java agentGUI/MovingDrawable.java agentGUI/Coordinate.java agentGUI/GUIRobot.java
BASECTR=controlGUI/Manager.java controlGUI/ManagerConnection.java
PARAM=-d .
PACKAGE=factory.agentGUI
PACKAGECTR=factory.controlGUI

manager:
	javac -d . controlGUI/Manager.java controlGUI/ManagerConnection.java Message.java Printer.java Server.java


movingdrawable:
	javac $(PARAM) $(BASE) 
	java $(PACKAGE).MovingDrawable > _movingdrawable.txt
kitrobot:
	javac $(PARAM) $(BASE) $(BASECTR) agentGUI/GUIKitRobot.java controlGUI/KitAssemblyManager.java
	java $(PACKAGECTR).KitAssemblyManager

