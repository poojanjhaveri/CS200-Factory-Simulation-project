package factory.factory200.factoryProductionManager.LaneManager;

public class LMGantryRobotHandler{
	
	private LMApplication app;	///< Instance of class 'LMApplication'
	private int feederNum, binNum;

	public LMGantryRobotHandler(LMApplication app){
		this.app = app;
	}

	public void verify(String message){
		feederNum = message.charAt(0) - 48;
		if( message.indexOf("&Put&") != -1 ){
			binNum = message.charAt( message.length()-1 ) - 48;
			app.getAllBin().getBin(feederNum).setBinImage(binNum);
		}
		else if( message.indexOf("&Purge&") != -1 ){
			app.getAllBin().getBin(feederNum).purgeBin();
		}
	}
}
