::::::::::::::::::::
::Factory run file::
::Roy Zheng 2012  ::
::::::::::::::::::::


echo "Beginning server"
pause
START java -bin factory.general.Server
echo "Beginning managers"
pause
START java -cp bin factory.factory200.partsManager.PartsManager
START java -cp bin factory.factory200.kitManager.KitManager
START java -cp bin factory.factory200.gantryRobotManager.GantryRobotManager
START java -cp bin factory.factory200.laneManager.ClientSide.V2LaneManager
START java -cp bin factory.factory200.kitAssemblyManager.KitAssemblyManager
START java -cp bin factory.factory200.factoryProductionManager.FactoryProductionManager
echo "All managers opened"
pause