package factory.factory200.laneManager.ServerSide;

import factory.general.*;

/**
 * This class contains all data for lanes. Lane agent and lane manager use these data to process.
 * 
 * @brief Lane data
 *	@author Dongyoung Jung
 */
public class LMLaneData {

	private Server server;
	private LMServerMain serverMain;
	private String signal = "";	///< Signal to Lane Manager
	
	private int laneNum;	///< Lane number
	private int vibrationAmplitude;
	private Boolean laneSwitch = false;	///< Feeder switched on : true, Feeder switched off : false
	
	public LMLaneData( int laneNum, Server server, LMServerMain serverMain ){
		this.laneNum = laneNum;
		this.server = server;
		this.serverMain = serverMain;
	}	
	
	public Boolean getSwitch(){
		return laneSwitch;
	}
	
	public void switchOn(){
		// Signal To Managers
		signal = laneNum + "&Lane&" + "Switch On";
		
		//----------------------------------------------------------------------------------For Test
		server.signalToClient(signal);
		//-----------------------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		laneSwitch = true;
	}

	public void switchOff(){
		// Signal To Managers
		signal = laneNum + "&Lane&" + "Switch Off";
		
		//----------------------------------------------------------------------------------For Test
		server.signalToClient(signal);
		//---------------------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		laneSwitch = false;
	}
	
	public void setVibrationAmplitudeStrong(){
		// Signal To Managers
		signal = laneNum + "&Lane&" + "Amplitude Strong";
		
		//----------------------------------------------------------------------------------For Test
		server.signalToClient(signal);
		//-----------------------------------------------------------------------------------
		
		// Server Data Task
		vibrationAmplitude = 2;
	}
	
	public void setVibrationAmplitudeNormal(){
		// Signal To Managers
		signal = laneNum + "&Lane&" + "Amplitude Normal";
		
		//----------------------------------------------------------------------------------For Test
		server.signalToClient(signal);
		//------------------------------------------------------------------------------------------------
		
		// Server Data Task
		vibrationAmplitude = 1;
	}

	public void setVibrationAmplitudeWeak(){
		// Signal To Managers
		signal = laneNum + "&Lane&" + "Amplitude Weak";
		
		//----------------------------------------------------------------------------------For Test
		server.signalToClient(signal);
		//----------------------------------------------------------------------------------
		
		// Server Data Task
		vibrationAmplitude = 0;
	}
	
	public int getVibrationAmplitude(){
		return vibrationAmplitude;
	}
}