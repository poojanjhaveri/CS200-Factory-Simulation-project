package factory.factory200.factoryProductionManager.LaneManager;

/**
 * @brief Put Bin, Purge Bin
 * @author Dongyoung Jung
 */
public class LMGantryRobotHandler{
	
	private LMApplication app;	///< Instance of class 'LMApplication'
	private int feederNum, binNum;

	public LMGantryRobotHandler(LMApplication app){
		this.app = app;
	}

	public void verify(String message){
		feederNum = message.charAt(0) - 48;
		if( message.contains("&Put&") ){
			binNum = message.charAt(1) - 48;
			app.getAllBin().getBin(feederNum).setBinImage(binNum);
		}
		else if( message.contains("&Purge&") ){
			app.getAllBin().getBin(feederNum).purgeBin();
		}
		else if( message.contains("&Empty&") ){
			app.getAllBin().getBin(feederNum).emptyBin();
		}
	}
}
