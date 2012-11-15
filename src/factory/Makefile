G=Drawable.java MovingDrawable.java Coordinate.java Manager.java ManagerConnection.java Message.java Printer.java GUIRobot.java Server.java Kit.java Part.java Inventory.java GUIPart.java Blueprint.java BlueprintKits.java BlueprintParts.java Util.java FactoryState.java
C201=factory201/feederManagement/*.java  factory201/partsManagement/*.java factory201/kitManagement/*.java
GENERAL=$(addprefix general/, $(G)) $(C201) 
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
partsmanager:
	javac $(PARAM) $(GENERAL) factory200/partsManager/*.java factory200/factoryProductionManager/*.java factory200/kitManager/*.java
	rm ../../factory -R
	mv factory ../..
factoryproduction:
	javac $(PARAM) $(GENERAL) factory200/factoryProductionManager/*.java
kitassembly:
	javac $(PARAM) $(GENERAL) factory200/kitAssemblyManager/*.java factory201/interfaces/KitRobot.java
	rm ../../factory -R
	mv factory ../..
lane:
	javac $(PARAM) factory200/laneManager/*.java
	java factory.factory200.laneManager.V0LaneBinGantry
debug:
	echo $(GENERAL)
