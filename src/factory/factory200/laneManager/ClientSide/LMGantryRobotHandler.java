package factory.factory200.laneManager.ClientSide;

/**
 * @brief Server Signal Handler(Put Bin & Purge Bin)
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
			app.getGraphicsPanel().getAllBin().getBin(feederNum).setBinImage(binNum);
		}
		else if( message.contains("&Purge&") ){
			app.getGraphicsPanel().getAllBin().getBin(feederNum).purgeBin();
		}
		else if( message.contains("&Empty&") ){
			app.getGraphicsPanel().getAllBin().getBin(feederNum).emptyBin();
		}
	}
}
