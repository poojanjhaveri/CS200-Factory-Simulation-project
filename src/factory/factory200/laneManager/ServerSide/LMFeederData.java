package factory.factory200.laneManager.ServerSide;

import factory.general.*;

/**
 * This class contains all data for feeders. Lane agent and lane manager use these data to process.
 * 
 * @brief Feeder data
 *	@author Dongyoung Jung
 */
public class LMFeederData {
	
	private Server server;
	private LMServerMain serverMain;
	private String signal = "";
	
	private Boolean feederSwitch = false;
	private Boolean partLowSensor = true;
	private Boolean feedPartsSwitch = false;
	private Boolean rearGateSwitch = false;
	private Boolean diversionSwitch = false; // false : right, true : left
	private Boolean purgeSwitch = false;
	
	private int feederNum;

	public LMFeederData( int feederNum, Server server, LMServerMain serverMain ){		
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
	
	// For Agent------------------------------------------------------------------------------------
	public void setSwitchOn(){
		// Send To Managers
		signal = feederNum + "&Feeder&" + "Feeder Switch On";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//----------------------------------------------------------------------------------		
		
		// Send To FPM 
		
		// Server Data Task
		feederSwitch = true;
	}
	//----------------------------------------------------------------------------------------------------

	// For Agent------------------------------------------------------------------------------------
	public void setSwitchOff(){
		// Send To Managers
		signal = feederNum + "&Feeder&" + "Feeder Switch Off";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//----------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		feederSwitch = false;
	}
	//----------------------------------------------------------------------------------------------------

	public void setPartLowSensorOn(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Part Low Sensor On";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//----------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		partLowSensor = true;
	}
	
	public void setPartLowSensorOff(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Part Low Sensor Off";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//----------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		partLowSensor = false;
	}

	// For Agent------------------------------------------------------------------------------------
	public void setFeedPartsSwitchOn(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Feed Part Switch On";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//----------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		feedPartsSwitch = true;
	}
	//----------------------------------------------------------------------------------------------------

	// For Agent------------------------------------------------------------------------------------
	public void setFeedPartsSwitchOff(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Feed Part Switch Off";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//----------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		feedPartsSwitch = false;
	}
	//----------------------------------------------------------------------------------------------------
	
	public void setPartFedCounterIncrease(){
		signal = feederNum + "&Feeder&" + "Part Fed Counter";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//----------------------------------------------------------------------------------
	}

	// For Agent------------------------------------------------------------------------------------
	public void setRearGateSwitchLower(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Rear Gate Lower";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//----------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		rearGateSwitch = true;
		serverMain.getPartData().rearGateOpen(feederNum);
	}
	//----------------------------------------------------------------------------------------------------
	
	// For Agent------------------------------------------------------------------------------------
	public void setRearGateSwitchRaise(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Rear Gate Raise";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//----------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		rearGateSwitch = false;
	}
	//----------------------------------------------------------------------------------------------------

	// For Agent------------------------------------------------------------------------------------
	public void setPurgeBinSwitchOn(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Purge On";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//----------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		purgeSwitch = true;
	}
	//----------------------------------------------------------------------------------------------------

	// For Agent------------------------------------------------------------------------------------
	public void setPurgeBinSwitchOff(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Purge Off";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//----------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		purgeSwitch = false;
	}
	//----------------------------------------------------------------------------------------------------

	// For Agent------------------------------------------------------------------------------------
	public void setDiverterSwitchLeft(){
		// Send To Managers		
		signal = feederNum + "&Feeder&" + "Divert To Left";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//----------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		diversionSwitch = true;
	}
	//----------------------------------------------------------------------------------------------------

	// For Agent------------------------------------------------------------------------------------
	public void setDiverterSwitchRight(){
		// Send To Managers
		signal = feederNum + "&Feeder&" + "Divert To Right";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//----------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		diversionSwitch = false;
	}
	//----------------------------------------------------------------------------------------------------
}