G=Drawable.java MovingDrawable.java Coordinate.java Manager.java ManagerConnection.java Message.java Printer.java GUIRobot.java Server.java Kit.java Part.java Inventory.java
GENERAL=$(addprefix general/, $(G))

PARAM=-d .
PKGENERAL=factory.general

all:
	javac $(PARAM) *.java factory200/*.java general/*.java
movingdrawable:
	javac $(PARAM) $(GENERAL) 
	java $(PKGENERAL).MovingDrawable > _movingdrawable.txt
manager:
	javac $(PARAM) $(GENERAL)
	java $(PKGENERAL).Manager
kitassembly:
	javac $(PARAM) $(GENERAL) factory200/kitAssemblyManager/*.java factory201/interfaces/KitRobot.java
	java $(PACKAGECTR).KitAssemblyManager

debug:
	echo $(GENERAL)