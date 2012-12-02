package factory.factory200.laneManager.ServerSide;

/**
 * @brief Feeder Signal Sends(Server Side)
 * @author Dongyoung Jung
 */
public class LMFeederData {
	
	private LMServerMain serverMain;
	private String signal = "";
	
	private boolean feederSwitch = true;
	private boolean partLowSensor = true;
	private boolean feedPartsSwitch = true;
	private boolean rearGateSwitch = false;
	private boolean diversionSwitch = false; // false : right, true : left
	private boolean purgeSwitch = false;
	private boolean withBin = false;
	
	private int feederNum;

	public LMFeederData( int feederNum, LMServerMain serverMain ){		
		this.feederNum = feederNum;
		this.serverMain = serverMain;
	}

	public boolean getDiversion(){
		return diversionSwitch;
	}
	
	public boolean getRearGateSwitch(){
		return rearGateSwitch;
	}
	
	public boolean getFeederSwitch(){
		return feederSwitch;
	}
	
	public boolean getFeedingSwitch(){
		return feedPartsSwitch;
	}
	
	public boolean getWithBin(){
		return withBin;
	}
	
	public void setWithBin(boolean status){
		withBin = status;
	}
	
	// For Agent------------------------------------------------------------------------------------
	public void setSwitchOn(){
		// Send To LM & FPM
		signal = feederNum + "&Feeder&" + "Feeder Switch On";		
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		feederSwitch = true;
	}
	//----------------------------------------------------------------------------------------------------

	// For Agent------------------------------------------------------------------------------------
	public void setSwitchOff(){
		// Send To LM & FPM
		signal = feederNum + "&Feeder&" + "Feeder Switch Off";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		feederSwitch = false;
	}
	//----------------------------------------------------------------------------------------------------

	public void setPartLowSensorOn(){
		// Send To LM & FPM	
		signal = feederNum + "&Feeder&" + "Part Low Sensor On";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		partLowSensor = true;
	}
	
	public void setPartLowSensorOff(){
		// Send To LM & FPM
		signal = feederNum + "&Feeder&" + "Part Low Sensor Off";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		partLowSensor = false;
	}

	// For Agent------------------------------------------------------------------------------------
	public void setFeedPartsSwitchOn(){
		// Send To LM & FPM		
		signal = feederNum + "&Feeder&" + "Feed Part Switch On";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		feedPartsSwitch = true;
	}
	//----------------------------------------------------------------------------------------------------

	// For Agent------------------------------------------------------------------------------------
	public void setFeedPartsSwitchOff(){
		// Send To LM & FPM		
		signal = feederNum + "&Feeder&" + "Feed Part Switch Off";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		feedPartsSwitch = false;
	}
	//----------------------------------------------------------------------------------------------------
	
	public void setPartFedCounterIncrease(){
		// Send To LM
		signal = feederNum + "&Feeder&" + "Part Fed Counter";
		serverMain.sendToLM(signal);
	}

	// For Agent------------------------------------------------------------------------------------
	public void setRearGateSwitchLower(){
		// Send To LM & FPM		
		signal = feederNum + "&Feeder&" + "Rear Gate Lower";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		rearGateSwitch = true;
		serverMain.getPartData().rearGateOpen(feederNum);
	}
	//----------------------------------------------------------------------------------------------------
	
	// For Agent------------------------------------------------------------------------------------
	public void setRearGateSwitchRaise(){
		// Send To LM & FPM		
		signal = feederNum + "&Feeder&" + "Rear Gate Raise";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		rearGateSwitch = false;
	}
	//----------------------------------------------------------------------------------------------------

	// For Agent------------------------------------------------------------------------------------
	public void setPurgeBinSwitchOn(){
		// Send To LM & FPM	
		signal = feederNum + "&Feeder&" + "Purge On";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		purgeSwitch = true;
	}
	//----------------------------------------------------------------------------------------------------

	// For Agent------------------------------------------------------------------------------------
	public void setPurgeBinSwitchOff(){
		// Send To LM & FPM		
		signal = feederNum + "&Feeder&" + "Purge Off";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		purgeSwitch = false;
	}
	//----------------------------------------------------------------------------------------------------

	// For Agent------------------------------------------------------------------------------------
	public void setDiverterSwitchLeft(){
		// Send To LM & FPM
		signal = feederNum + "&Feeder&" + "Divert To Left";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		diversionSwitch = true;
	}
	//----------------------------------------------------------------------------------------------------

	// For Agent------------------------------------------------------------------------------------
	public void setDiverterSwitchRight(){
		// Send To LM & FPM
		signal = feederNum + "&Feeder&" + "Divert To Right";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		diversionSwitch = false;
	}
	//----------------------------------------------------------------------------------------------------
}