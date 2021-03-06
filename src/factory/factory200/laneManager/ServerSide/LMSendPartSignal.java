package factory.factory200.laneManager.ServerSide;

/**
 * @brief Signal related to Server Sender
 * @author Dongyoung Jung
 */
public class LMSendPartSignal {
	
	private LMServerMain serverMain;
	private String signal = "";
	private int partNum;
	private int partStatus;
	
	public LMSendPartSignal(LMServerMain serverMain){
		this.serverMain = serverMain;
	}

	public void orderFeeding(){
		for(int i=0 ; i<4 ; i++){
			if(serverMain.getPartData().getFeederPartSize(i) != 0 && serverMain.getForAgentFeeder().getFeeder(i).getFeedingSwitch() == true){
				partNum = serverMain.getPartData().getFirstPartNum(i);
				partStatus = serverMain.getPartData().getFirstPartStatus(i);
				
				if(serverMain.getForAgentFeeder().getFeeder(i).getDiversion() == false && serverMain.getForAgentLane().getLane(2*i).getSwitch() == true){	//right
					serverMain.getPartData().addPartToLaneFromFeeder(2*i, i);
					signal = "" + (2*i) + partNum + partStatus +  "&Part&Add&";
				}
				else if(serverMain.getForAgentFeeder().getFeeder(i).getDiversion() == true && serverMain.getForAgentLane().getLane(2*i+1).getSwitch() == true){	//left
					serverMain.getPartData().addPartToLaneFromFeeder(2*i+1, i);
					signal = "" + (2*i+1) + partNum + partStatus + "&Part&Add&";
				}
				
				serverMain.sendToLM(signal);
				serverMain.sendToFPM(signal);
				
				serverMain.getForAgentFeeder().getFeeder(i).setPartFedCounterIncrease();
			}
		}
	}
}
