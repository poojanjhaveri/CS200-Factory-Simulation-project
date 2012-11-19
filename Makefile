SRC=src
F200=$(SRC)/factory/factory200
F201=$(SRC)/factory/factory201
FPM=$(F200)/factoryProductionManager
ALLPKG=$(SRC)/agent/*.java $(FPM)/*.java $(FPM)/GantryRobotManager/*.java $(FPM)/KitsAssemblyManager/*.java $(FPM)/LaneManager/*java $(F200)/gantryRobotManager/*.java $(F200)/kitAssemblyManager/*.java $(F200)/kitManager/*.java $(F200)/laneManager/ClientSide/*.java $(F200)/laneManager/ServerSide/*.java $(F201)/feederManagement/*.java $(F201)/gui/*.java $(F201)/interfaces/*.java $(F201)/kitManagement/*.java $(F201)/partsManagement/*.java $(SRC)/factory/general/*.java

RUN=java
RUNPKG=factory.factory200

all:
	javac -d . $(ALLPKG)

run:
	@echo "STARTING SERVER"
	$(RUN) factory.general.Server &
	php sleeper.php
	@echo "BEGINNING ALL MANAGERS"
	$(RUN) $(RUNPKG).partsManager.PartsManager &
	$(RUN) $(RUNPKG).kitManager.KitManager &
	$(RUN) $(RUNPKG).gantryRobotManager.GantryRobotManager &
	$(RUN) factory.factory200.laneManager.ClientSide.V1LaneManagerCompileClient &
	$(RUN) factory.factory200.kitAssemblyManager.KitAssemblyManager &
	$(RUN) factory.factory200.factoryProductionManager.FactoryProductionManager &
