package factory.agentGUI;
/**
   @brief interprets signals from server and take an action 
		Signals from server
        Lane manager application bootup setting
        Feeder
            Part box is dumped into feeder
            Lane is fed one part by feeder
            Any hardware status change
        Lane
            Lane movement and vibration - controlled by server since server has Timer and Thread
            Any hardware status change
        Nest
            Part is put on nest
            Gantry robot takes part from nest
            Any hardware status change
	@author Dongyoung Jung
 */
class VerifySignalsFromServer{}
