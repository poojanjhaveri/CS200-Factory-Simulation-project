package factory.factory200.laneManager.ServerSide;

/**
 * @brief Nest Data
 * @author Dongyoung Jung
 */
public class LMNestData {
	
	private LMServerMain serverMain;
	private String signal = "";
	
	private int nestNum;
	private Boolean nestSwitch = true; // false : down, true : up
	
	public LMNestData( int nestNum, LMServerMain serverMain ){
		this.nestNum = nestNum;
		this.serverMain = serverMain;
	}
	
	public void setSwitchUp(){
		// Signal To LM & FPM
		signal = nestNum + "&Nest&Switch Up";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		serverMain.sendToKAM(signal);
		
		// Server Data Task
		nestSwitch = true;
		serverMain.getForAgentLane().setSwitchOn(nestNum);
	}
	
	public void setSwitchDown(){
		// Signal To LM  & FPM
		signal = nestNum + "&Nest&Switch Down";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		serverMain.sendToKAM(signal);

		// Server Data Task
		nestSwitch = false;
		serverMain.getForAgentNestCamera().stopPiledPart(nestNum);
		serverMain.getPartData().nestDown(nestNum);
		serverMain.getForAgentLane().setSwitchOff(nestNum);
	}
}
