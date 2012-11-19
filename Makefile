SRC=src
F200=$(SRC)/factory/factory200
F201=$(SRC)/factory/factory201
FPM=$(F200)/factoryProductionManager
ALLPKGDIR=$(SRC)/agent/*.java $(FPM)/*.java $(FPM)/GantryRobotManager/*.java $(FPM)/KitsAssemblyManager/*.java $(FPM)/LaneManager/*java $(F200)/gantryRobotManager/*.java $(F200)/kitAssemblyManager/*.java $(F200)/kitManager/*.java $(F200)/laneManager/ClientSide/*.java $(F200)/laneManager/ServerSide/*.java $(F201)/feederManagement/*.java $(F201)/gui/*.java $(F201)/interfaces/*.java $(F201)/kitManagement/*.java $(F201)/partsManagement/*.java $(SRC)/factory/general/*.java
ALLPKG=$(wildcard $(ALLPKGDIR))
ALLPKGCLASS=$(ALLPKG:.java=.class)

RUNPKG=factory.factory200

RUN=java
C=javac
CFLAG=-d .

debug:
	echo $(ALLPKGDIR)
	echo $(ALLPKG)
	echo $(ALLPKGCLASS)

allfast:
	$(C) $(CFLAG) $(ALLPKGDIR)

allslow: $(ALLPKGCLASS)


%.class: %.java
	@echo -n "Compiling " $< "..."
	@javac -d . $<
	@echo "DONE"
run:
	@echo "STARTING SERVER"
	$(RUN) factory.general.Server &
	@sleep 4
	@echo "BEGINNING ALL MANAGERS"
	$(RUN) $(RUNPKG).partsManager.PartsManager &
	$(RUN) $(RUNPKG).kitManager.KitManager &
	$(RUN) $(RUNPKG).gantryRobotManager.GantryRobotManager &
	$(RUN) $(RUNPKG).laneManager.ClientSide.V1LaneManagerCompileClient &
	$(RUN) $(RUNPKG).kitAssemblyManager.KitAssemblyManager &
	$(RUN) $(RUNPKG).factoryProductionManager.FactoryProductionManager &
