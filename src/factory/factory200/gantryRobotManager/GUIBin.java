package factory.factory200.gantryRobotManager;

import factory.general.Coordinate;
import javax.swing.ImageIcon;


public class GUIBin{
	
	String binPictureName;		
	private Integer x;
	private Integer y;
    private boolean full;///<boolean that tells if the bin is taken by a gantry robot
	 ImageIcon img;///<image of the drawable

	public GUIBin(){
		 
	 }
	
	public GUIBin(Integer newx, Integer newy, Double r, String i) {  
			x=newx;
			y = newy;			
			binPictureName=i;
			full=true;
			img = new ImageIcon(i);
		}
		    
	public boolean binIsFull(){
		return full;
	}
	public ImageIcon getImage(){
		return this.img;
	}
	public void setX(Integer newx){
		x= newx;
	}
	public void setY(Integer newy)
	{
		y = newy;
	}
	public Integer getX()
	{
	   return x;
	}
	public Integer getY(){
		return y;
	}
		 

		
}	
	
	//bin=new ImageIcon("pics/KAMkitStand.png");
    //kitPositions=new ArrayList<KitPosition>();


