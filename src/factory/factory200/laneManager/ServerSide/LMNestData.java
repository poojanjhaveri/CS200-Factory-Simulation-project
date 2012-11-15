package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;

public class LMNestData {
	
	private LMServer server;
	private LMServerMain serverMain;
	private String signal = "";
	
	private int nestNum;	///< Nest number
	private Boolean nestSwitch = true; // false : down, true : up
	
	public LMNestData( int nestNum, LMServer server, LMServerMain serverMain ){
		this.nestNum = nestNum;
		this.server = server;
		this.serverMain = serverMain;
	}
	
	public void setSwitchUp(){
		// Signal To Managers
		signal = nestNum + "&Nest&" + "Switch Up";
		
		//----------------------------------------------------------------------------------For Test
		for(int i=0 ; i<server.clients.size() ; i++){
			server.clients.get(i).sendToClient(signal);
		}
		
		//server.getClientHandler().sendToClient(signal);
		//------------------------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		nestSwitch = true;
	}
	
	public void setSwitchDown(){
		// Signal To Managers
		signal = nestNum + "&Nest&" + "Switch Down";
		
		//----------------------------------------------------------------------------------For Test
		for(int i=0 ; i<server.clients.size() ; i++){
			server.clients.get(i).sendToClient(signal);
		}
		
		//server.getClientHandler().sendToClient(signal);
		//-------------------------------------------------------------------------------------------------
		
		// Send To FPM

		// Server Data Task
		nestSwitch = false;
		serverMain.getPartData().nestDown(nestNum);
		serverMain.getForAgentLane().setSwitchOff(nestNum);
	}
}
