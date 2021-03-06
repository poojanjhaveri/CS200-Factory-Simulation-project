package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;

/**
 * @brief Feeder Functions for Agent
 * @author Dongyoung Jung
 */
public class LMFeederForAgent {
	
	private LMServerMain serverMain;
	private LMFeederData newFeeder;
	private ArrayList<LMFeederData> feeders = new ArrayList<LMFeederData>();

	public LMFeederForAgent(LMServerMain serverMain){
		this.serverMain = serverMain;
	
		for(int i=0 ; i<4 ; i++){
			newFeeder = new LMFeederData( i, serverMain );
			feeders.add(newFeeder);
		}
	}

	public void setSwitchOn( int feederNum ){
		feeders.get( feederNum ).setSwitchOn();
	}

	public void setSwitchOff( int feederNum ){
		feeders.get( feederNum ).setSwitchOff();
	}

	public void setPartLowSensorOn( int feederNum ){
		feeders.get( feederNum ).setPartLowSensorOn();
	}

	public void setPartLowSensorOff( int feederNum ){
		feeders.get( feederNum ).setPartLowSensorOff();
	}

	public void setFeedPartsSwitchOn( int feederNum ){
		feeders.get( feederNum ).setFeedPartsSwitchOn();
	}

	public void setFeedPartsSwitchOff( int feederNum ){
		feeders.get( feederNum ).setFeedPartsSwitchOff();
	}

	public void setPartFedCounterIncrease( int feederNum ){
		feeders.get( feederNum ).setPartFedCounterIncrease();
	}

	public void setRearGateSwitchLower( int feederNum ){
		feeders.get( feederNum ).setRearGateSwitchLower();
		serverMain.getForAgentFeeder().getFeeder(feederNum).setFeedPartsSwitchOff();
	}

	public void setRearGateSwitchRaise( int feederNum ){
		feeders.get( feederNum ).setRearGateSwitchRaise();
	}

	public void setPurgeBinSwitchOn( int feederNum ){
		feeders.get( feederNum ).setPurgeBinSwitchOn();
	}

	public void setPurgeBinSwitchOff( int feederNum ){
		feeders.get( feederNum ).setPurgeBinSwitchOff();
	}

	public void setDiverterSwitchLeft( int feederNum ){
		feeders.get( feederNum ).setDiverterSwitchLeft();
	}

	public void setDiverterSwitchRight( int feederNum ){
		feeders.get( feederNum ).setDiverterSwitchRight();
	}

	public LMFeederData getFeeder(int feederNum){
		return feeders.get(feederNum);
	}	
}