G=Drawable.java MovingDrawable.java Coordinate.java Manager.java ManagerConnection.java Message.java Printer.java GUIRobot.java Server.java
GENERAL=$(addprefix general/, $(G))
 
PARAM=-d .
PKGENERAL=factory.general

all:
	javac $(PARAM) *.java factory200/*.java general/*.java
movingdrawable:
	javac $(PARAM) $(GENERAL) 
	java $(PKGENERAL).MovingDrawable > _movingdrawable.txt
manager:
	javac $(PARAM) $(BASECTR) Message.java Printer.java Server.java
	java $(PACKAGECTR).Manager
kitrobot:
	javac $(PARAM) $(BASE) $(BASECTR) agentGUI/GUIKitRobot.java controlGUI/KitAssemblyManager.java Server.java
	java $(PACKAGECTR).KitAssemblyManager


debug:
	echo $(GENERAL)