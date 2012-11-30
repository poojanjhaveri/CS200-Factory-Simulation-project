#!/bin/bash
###################
#Factory run file #
#Roy Zheng 2012   #
###################

echo "STARTING SERVER"
java factory.general.Server &
sleep 4
echo "BEGINNING ALL MANAGERS"
java factory.factory200.partsManager.PartsManager &
sleep 1
java factory.factory200.kitManager.KitManager &
sleep 1
java factory.factory200.gantryRobotManager.GantryRobotManager &
sleep 1
java factory.factory200.laneManager.ClientSide.V1LaneManagerCompileClient &
sleep 1
java factory.factory200.kitAssemblyManager.KitAssemblyManager &
sleep 1
java factory.factory200.factoryProductionManager.FactoryProductionManager &
