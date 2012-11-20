############################################
#Linux compile file for CS factory project #
#by Roy YiWei Zheng            v.1         #
############################################
SRC=src
F200=$(SRC)/factory/factory200
F201=$(SRC)/factory/factory201
FPM=$(F200)/factoryProductionManager
LM=$(F200)/laneManager

ALLPKGDIR= \
	$(SRC)/agent/*.java \
	$(FPM)/*.java \
	$(FPM)/GantryRobotManager/*.java \
	$(FPM)/KitsAssemblyManager/*.java \
	$(FPM)/LaneManager/*java \
	$(F200)/gantryRobotManager/*.java \
	$(F200)/kitAssemblyManager/*.java \
	$(F200)/kitManager/*.java \
	$(F200)/partsManager/*.java \
	$(LM)/ClientSide/*.java \
	$(LM)/ServerSide/*.java \
	$(F201)/feederManagement/*.java \
	$(F201)/gui/*.java \
	$(F201)/interfaces/*.java \
	$(F201)/kitManagement/*.java \
	$(F201)/partsManagement/*.java \
	$(SRC)/factory/general/*.java

ALLPKG=$(wildcard $(ALLPKGDIR))
ALLPKGCLASS=$(ALLPKG:.java=.class)

RUNPKG=factory.factory200

RUN=java
C=javac
FLAG=-d .

debug:
	echo $(ALLPKGDIR)
	echo $(ALLPKG)
	echo $(ALLPKGCLASS)

all:
	$(C) $(FLAG) $(ALLPKGDIR)

allslow: $(ALLPKGCLASS)

%.class: %.java
	@echo -n "Compiling" $< "..."
	@$(C) $(FLAG) $<
	@echo "DONE!"
run:
	@echo "STARTING SERVER"
	$(RUN) factory.general.Server &
	@sleep 4
	@echo "BEGINNING ALL MANAGERS"
	$(RUN) $(RUNPKG).partManager.PartManager &
	@sleep 2
	$(RUN) $(RUNPKG).kitManager.KitManager &
	@sleep 2
	$(RUN) $(RUNPKG).gantryRobotManager.GantryRobotManager &
	@sleep 2
	$(RUN) $(RUNPKG).laneManager.ClientSide.V1LaneManagerCompileClient &
	@sleep
	$(RUN) $(RUNPKG).kitAssemblyManager.KitAssemblyManager &
	@sleep
	$(RUN) $(RUNPKG).factoryProductionManager.FactoryProductionManager &
