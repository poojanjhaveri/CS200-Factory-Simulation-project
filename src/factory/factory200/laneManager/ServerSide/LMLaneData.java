package factory.factory200.laneManager.ServerSide;

/**
 * @brief Lane Signal Sends(Server Side)
 * @author Dongyoung Jung
 */
public class LMLaneData {

	private LMServerMain serverMain;
	private String signal = "";
	
	private int laneNum;
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
		if( serverMain.getPartData().getLanePartSize(laneNum) == 0 ){
			signal = laneNum + "&Lane&" + "Amplitude Strong";
			serverMain.sendToLM(signal);
			serverMain.sendToFPM(signal);
		}
		
		for(int i=0 ; i<serverMain.getPartData().getLanePartSize(laneNum) ; i++){
			signal = laneNum + "&Lane&" + "Amplitude Strong";
			serverMain.sendToLM(signal);
			serverMain.sendToFPM(signal);
		}
	}
	
	public void setVibrationAmplitudeNormal(){
		// Signal To LM
		signal = laneNum + "&Lane&" + "Amplitude Normal";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
	}
}