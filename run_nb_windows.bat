::::::::::::::::::::
::Factory run file::
::Roy Zheng 2012  ::
::::::::::::::::::::

echo "Beginning server"
pause
START java -cp build\classes factory.general.Server
echo "Beginning managers"
pause
START java -cp build\classes factory.factory200.partManager.PartManager
START java -cp build\classes factory.factory200.kitManager.KitManager
START java -cp build\classes factory.factory200.gantryRobotManager.GantryRobotManager
START java -cp build\classes factory.factory200.laneManager.ClientSide.V1LaneManagerCompileClient
START java -cp build\classes factory.factory200.kitAssemblyManager.KitAssemblyManager
START java -cp build\classes factory.factory200.factoryProductionManager.FactoryProductionManager
echo "All managers opened"
pause