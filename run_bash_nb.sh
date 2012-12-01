#!/bin/bash
#####################
#Factory run file 	#
#Roy Zheng 2012   	#
#For Mac on NetBeans#
#####################

echo "STARTING SERVER"
java -cp build/classes factory.general.Server &
sleep 4
echo "BEGINNING ALL MANAGERS"
java -cp build/classes factory.factory200.partsManager.PartsManager &
sleep 1
java -cp build/classes factory.factory200.kitManager.KitManager &
sleep 1
java -cp build/classes factory.factory200.gantryRobotManager.GantryRobotManager &
sleep 1
java -cp build/classes factory.factory200.laneManager.ClientSide.V2LaneManager &
sleep 1
java -cp build/classes factory.factory200.kitAssemblyManager.KitAssemblyManager &
sleep 1
java -cp build/classes factory.factory200.factoryProductionManager.FactoryProductionManager &
