package factory.factory200.laneManager.ServerSide;

public class LMCameraData {
	
	private LMServer server;
	private LMServerMain serverMain;
	private String signalToLM = "";	///< Signal to Lane Manager
	private int nestCameraNum;	///< Number of nest that the camera is taken at

	public LMCameraData( int nestCameraNum, LMServer server, LMServerMain serverMain ){
		this.nestCameraNum = nestCameraNum;
		this.server = server;
		this.serverMain = serverMain;
	}

	public void cameraShoot(){
		signalToLM = nestCameraNum + "&Camera&" + "Shoot";
		System.out.println(signalToLM);
		//-----------------------------------------------------For Test
		for(int i=0 ; i<server.clients.size() ; i++){
			server.clients.get(i).sendToClient("signalToLM");
		}
		
		// server.getClientHandler().sendToClient(signalToLM);
		//------------------------------------------------------------------
		
	}
}
