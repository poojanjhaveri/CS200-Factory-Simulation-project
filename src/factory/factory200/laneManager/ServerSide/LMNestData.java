package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;
import factory.general.*;

public class LMNestData {
	
	private Server server;
	private LMServerMain serverMain;
	private String signal = "";
	
	private int nestNum;	///< Nest number
	private Boolean nestSwitch = true; // false : down, true : up
	
	public LMNestData( int nestNum, Server server, LMServerMain serverMain ){
		this.nestNum = nestNum;
		this.server = server;
		this.serverMain = serverMain;
	}
	
	public void setSwitchUp(){
		// Signal To Managers
		signal = nestNum + "&Nest&" + "Switch Up";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//------------------------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		nestSwitch = true;
	}
	
	public void setSwitchDown(){
		// Signal To Managers
		signal = nestNum + "&Nest&" + "Switch Down";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//-------------------------------------------------------------------------------------------------
		
		// Send To FPM

		// Server Data Task
		nestSwitch = false;
		serverMain.getPartData().nestDown(nestNum);
		serverMain.getForAgentLane().setSwitchOff(nestNum);
	}
}
