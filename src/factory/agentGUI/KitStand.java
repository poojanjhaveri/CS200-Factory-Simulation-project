    /**
The KitStand keeps track of what is on the kit stand
Objects that are placed on the kit stand are empty kits, partially full kits, and finished kits
the kit stand communicates with the parts robot, kit robot and the camera to see if a kit is full and should be sent off
@brief keeps track of what is on the kit stand
@author Deepa Broker
     */
    public class KitStand{
	KitRobot kitbot;///<declares a kit robot
	KitterRobot kitterbot;///<declares a parts robot
	ImageIcon kitStand;///<keeps an object for the kit stand
	ImageIcon camera;///<keeps an object for the camera
	/**
checks if a kit is empty
	*/
public Boolean isEmpty()
	{

	} 
	/**
when the camera takes a picture, checks if the kit is full of the correct parts
	*/
public Boolean isCorrect()
	{

	} 
	/**
positions kit
	*/
	public void setKitPos(Coordinate in)
	{

	} 
	/**
checks where the kit robot is located
	*/	
public Coordinate posKitRobot(){

	}
	/**
checks where the kitter robot is located
	*/
public Coordinate posKitterRobot()
	{

	} 
moves kit to position in front of camera
	public void moveKitCamera(Coordinate in){

	}
	/**
moves finished kit out of the factory
	 */
	public void moveKitOut(){


	}

}
