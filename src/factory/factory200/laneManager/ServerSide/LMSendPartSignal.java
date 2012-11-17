package factory.factory200.laneManager.ServerSide;

import factory.general.*;

public class LMSendPartSignal {
	
	private Server server;
	private LMServerMain serverMain;
	private String message = "";
	private int partNum;
	
	public LMSendPartSignal(Server server, LMServerMain serverMain){
		this.server = server;
		this.serverMain = serverMain;
	}
	
	public void feederPartLowSensor(){
		for(int i=0 ; i<4 ; i++){
			if(serverMain.getPartData().getFeederPartSize(i) <= 4){
				serverMain.getForAgentFeeder().setPartLowSensorOn(i);
			}
			else if(serverMain.getPartData().getFeederPartSize(i) > 4){
				serverMain.getForAgentFeeder().setPartLowSensorOff(i);
			}
		}
	}

	public void orderFeeding(){
		for(int i=0 ; i<4 ; i++){
			if(serverMain.getPartData().getFeederPartSize(i) != 0 && serverMain.getForAgentFeeder().getFeeder(i).getFeedingSwitch() == true){
				partNum = serverMain.getPartData().getFirstPartNum(i);
				
				if(serverMain.getForAgentFeeder().getFeeder(i).getDiversion() == false && serverMain.getForAgentLane().getLane(2*i).getSwitch() == true){	//right
					serverMain.getPartData().addPartToLaneFromFeeder(2*i, i);
					message = "" + (2*i) + partNum + "&Part&Add&";
				}
				else if(serverMain.getForAgentFeeder().getFeeder(i).getDiversion() == true && serverMain.getForAgentLane().getLane(2*i+1).getSwitch() == true){	//left
					serverMain.getPartData().addPartToLaneFromFeeder(2*i+1, i);
					message = "" + (2*i+1) + partNum + "&Part&Add&";
				}
				
				//----------------------------------------------------------------------------------For Test
				server.signalToClient(message);
				//-------------------------------------------------------------------------------------------------
				
				serverMain.getForAgentFeeder().getFeeder(i).setPartFedCounterIncrease();
			}
		}
	}
}
