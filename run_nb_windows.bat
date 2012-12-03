::::::::::::::::::::::::::::::
::Factory run file			::
::Roy Zheng 2012  			::
::For Windows on NetBeans	::
::::::::::::::::::::::::::::::

echo "Beginning server"
START java -cp build\classes factory.general.Server
echo "Beginning managers"
ping -n 6 127.0.0.1 >nul
START java -cp build\classes factory.factory200.partsManager.PartsManager
ping -n 1 127.0.0.1 >nul
START java -cp build\classes factory.factory200.kitManager.KitManager
ping -n 1 127.0.0.1 >nul
START java -cp build\classes factory.factory200.gantryRobotManager.GantryRobotManager
ping -n 1 127.0.0.1 >nul
START java -cp build\classes factory.factory200.laneManager.ClientSide.V2LaneManager
ping -n 1 127.0.0.1 >nul
START java -cp build\classes factory.factory200.kitAssemblyManager.KitAssemblyManager
ping -n 1 127.0.0.1 >nul
START java -cp build\classes factory.factory200.factoryProductionManager.FactoryProductionManager
echo "All managers opened"
