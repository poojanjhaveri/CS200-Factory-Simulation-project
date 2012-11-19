package factory.factory200.laneManager.ServerSide;

/**
 * @brief Lane Signal Sends(Server Side)
 * @author Dongyoung Jung
 */
public class LMLaneData {

	private LMServerMain serverMain;
	private String signal = "";
	
	private int laneNum;
	private int vibrationAmplitude;
	private Boolean laneSwitch = true;
	
	public LMLaneData( int laneNum, LMServerMain serverMain ){
		this.laneNum = laneNum;
		this.serverMain = serverMain;
	}	
	
	public Boolean getSwitch(){
		return laneSwitch;
	}
	
	public void switchOn(){
		// Signal To LM & FPM
		signal = laneNum + "&Lane&" + "Switch On";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		laneSwitch = true;
	}

	public void switchOff(){
		// Signal To LM & FPM
		signal = laneNum + "&Lane&" + "Switch Off";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		laneSwitch = false;
	}
	
	public void setVibrationAmplitudeStrong(){
		// Signal To LM
		signal = laneNum + "&Lane&" + "Amplitude Strong";
		serverMain.sendToLM(signal);
		
		// Server Data Task
		vibrationAmplitude = 2;
	}
	
	public void setVibrationAmplitudeNormal(){
		// Signal To LM
		signal = laneNum + "&Lane&" + "Amplitude Normal";
		serverMain.sendToLM(signal);
		
		// Server Data Task
		vibrationAmplitude = 1;
	}

	public void setVibrationAmplitudeWeak(){
		// Signal To LM
		signal = laneNum + "&Lane&" + "Amplitude Weak";
		serverMain.sendToLM(signal);
		
		// Server Data Task
		vibrationAmplitude = 0;
	}
	
	public int getVibrationAmplitude(){
		return vibrationAmplitude;
	}
}