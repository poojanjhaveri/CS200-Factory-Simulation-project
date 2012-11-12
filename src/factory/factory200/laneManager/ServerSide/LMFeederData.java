package factory.factory200.laneManager.ServerSide;

/**
 * This class contains all data for feeders. Lane agent and lane manager use these data to process.
 * 
 * @brief Feeder data
 *	@author Dongyoung Jung
 */
public class LMFeederData {
	private LMServer server;
	private LMServerMain serverMain;
	private String signal = "";
	
	private Boolean feederSwitch = false;
	private Boolean partLowSensor = true;
	private Boolean feedPartsSwitch = false;
	private Boolean rearGateSwitch = false;
	private Boolean diversionSwitch = false; // false : right, true : left
	private Boolean purgeSwitch = false;
	
	private int feederNum;

	public LMFeederData( int feederNum, LMServer server, LMServerMain serverMain ){		
		this.feederNum = feederNum;
		this.server = server;
		this.serverMain = serverMain;
	}

	public Boolean getDiversion(){
		return diversionSwitch;
	}
	
	public Boolean getRearGateSwitch(){
		return rearGateSwitch;
	}
	
	public Boolean getFeederSwitch(){
		return feederSwitch;
	}
	
	public Boolean getFeedingSwitch(){
		return feedPartsSwitch;
	}
	
	//--------------------------------------------------------------------------------------------------
	public void setSwitchOn(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Feeder Switch On";
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		feederSwitch = true;
	}

	public void setSwitchOff(){
		// Send To Managers
		signal = feederNum + "&Feeder&" + "Feeder Switch Off";
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		feederSwitch = false;
	}

	public void setPartLowSensorOn(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Part Low Sensor On";
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		partLowSensor = true;
	}

	public void setPartLowSensorOff(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Part Low Sensor Off";
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		partLowSensor = false;
	}

	public void setFeedPartsSwitchOn(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Feed Part Switch On";
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		feedPartsSwitch = true;
	}

	public void setFeedPartsSwitchOff(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Feed Part Switch Off";
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		feedPartsSwitch = false;
	}

	public void setPartFedCounterIncrease(){
		signal = feederNum + "&Feeder&" + "Part Fed Counter";
		server.getClientHandler().sendToClient(signal);
	}

	public void setRearGateSwitchLower(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Rear Gate Lower";
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		rearGateSwitch = true;
		serverMain.getPartData().rearGateOpen(feederNum);
	}

	public void setRearGateSwitchRaise(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Rear Gate Raise";
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		rearGateSwitch = false;
	}

	public void setPurgeBinSwitchOn(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Purge On";
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		purgeSwitch = true;
	}

	public void setPurgeBinSwitchOff(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Purge Off";
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		purgeSwitch = false;
	}

	public void setDiverterSwitchLeft(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Divert To Left";
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		diversionSwitch = true;
	}

	public void setDiverterSwitchRight(){
		// Send To Managers
		signal = feederNum + "&Feeder&" + "Divert To Right";
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		diversionSwitch = false;
	}
}