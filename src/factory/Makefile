BASE=agentGUI/Drawable.java agentGUI/MovingDrawable.java agentGUI/Coordinate.java agentGUI/GUIRobot.java
BASECTR=controlGUI/Manager.java controlGUI/ManagerConnection.java Message.java Printer.java
PARAM=-d .
PACKAGE=factory.agentGUI
PACKAGECTR=factory.controlGUI

all:
	javac $(PARAM) *.java controlGUI/*.java agentGUI/*.java
movingdrawable:
	javac $(PARAM) $(BASE) 
	java $(PACKAGE).MovingDrawable > _movingdrawable.txt
manager:
	javac $(PARAM) $(BASECTR) Message.java Printer.java Server.java
	java $(PACKAGECTR).Manager
kitrobot:
	javac $(PARAM) $(BASE) $(BASECTR) agentGUI/GUIKitRobot.java controlGUI/KitAssemblyManager.java Server.java
	java $(PACKAGECTR).KitAssemblyManager

