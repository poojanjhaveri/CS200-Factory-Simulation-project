#!/bin/bash
#################################
# Factory run file 		#
# Roy Zheng, David Zhang 2012   #
#################################

echo "STARTING SERVER"
java -cp bin factory.general.Server &
sleep 4
echo "BEGINNING ALL MANAGERS"
java -cp bin factory.factory200.partsManager.PartsManager &
sleep 2
java -cp bin factory.factory200.kitManager.KitManager &
sleep 2
java -cp bin factory.factory200.gantryRobotManager.GantryRobotManager &
sleep 2
java -cp bin factory.factory200.laneManager.ClientSide.V1LaneManagerCompileClient &
sleep 2
java -cp bin factory.factory200.kitAssemblyManager.KitAssemblyManager &
sleep 2
java -cp bin factory.factory200.factoryProductionManager.FactoryProductionManager &
