::::::::::::::::::::::::::
::Factory run file		::
::Roy Zheng 2012  		::
::For Windows on Eclipse::
::::::::::::::::::::::::::


echo "Beginning server"
START java -cp bin factory.general.Server
ping -n 5 127.0.0.1 >nul
echo "Beginning managers"
START java -cp bin factory.factory200.partsManager.PartsManager
START java -cp bin factory.factory200.kitManager.KitManager
START java -cp bin factory.factory200.gantryRobotManager.GantryRobotManager
START java -cp bin factory.factory200.laneManager.ClientSide.V2LaneManager
START java -cp bin factory.factory200.kitAssemblyManager.KitAssemblyManager
START java -cp bin factory.factory200.factoryProductionManager.FactoryProductionManager
