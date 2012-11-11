package factory.factory200.laneManager.ServerSide;

/**
 * This class contains all data for lanes. Lane agent and lane manager use these data to process.
 * 
 * @brief Lane data
 *	@author Dongyoung Jung
 */
public class LMLaneData {

	private LMServer server;
	private LMServerMain serverMain;
	private String signal = "";	///< Signal to Lane Manager
	
	private int laneNum;	///< Lane number
	private int vibrationAmplitude;
	private Boolean laneSwitch = false;	///< Feeder switched on : true, Feeder switched off : false
	
	public LMLaneData( int laneNum, LMServer server, LMServerMain serverMain ){
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
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		laneSwitch = true;
	}

	public void switchOff(){
		// Signal To Managers
		signal = laneNum + "&Lane&" + "Switch Off";
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		laneSwitch = false;
	}
	
	public void setVibrationAmplitudeStrong(){
		// Signal To Managers
		signal = laneNum + "&Lane&" + "Amplitude Strong";
		server.getClientHandler().sendToClient(signal);
		
		// Server Data Task
		vibrationAmplitude = 2;
	}
	
	public void setVibrationAmplitudeNormal(){
		// Signal To Managers
		signal = laneNum + "&Lane&" + "Amplitude Normal";
		server.getClientHandler().sendToClient(signal);
		
		// Server Data Task
		vibrationAmplitude = 1;
	}

	public void setVibrationAmplitudeWeak(){
		// Signal To Managers
		signal = laneNum + "&Lane&" + "Amplitude Weak";
		server.getClientHandler().sendToClient(signal);
		
		// Server Data Task
		vibrationAmplitude = 0;
	}
}