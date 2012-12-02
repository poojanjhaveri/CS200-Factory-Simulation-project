package factory.factory200.gantryRobotManager;
/**
 * @author Yuting Liu
 */
import factory.general.Coordinate;
import factory.general.GUIPart;
import factory.general.Part;

import javax.swing.ImageIcon;

/**
 * @brief a bin
 * @author Yuting Liu
 */
public class GUIBin{
	
	String binPictureName;
	Part part; ///<If the bin is not empty, it has its corresponding part
	GUIPart guiPart;
	private Integer x;
	private Integer y;
    private boolean full;///<boolean that tells if the bin is taken by a gantry robot
	 ImageIcon img;///<image of the drawable

	//public GUIBin(){ }
	
	public GUIBin(Integer newx, Integer newy, Double r, String i,Integer num) {  
			x=newx;
			y = newy;			
			binPictureName=i;
			full=true;
			img = new ImageIcon(i);
			ImageIcon partIma = new ImageIcon("pics/parts/part"+num+".png");
			guiPart = new GUIPart(newx, newy, r,partIma);
			part = new Part(num);
			part.setGUIPart(guiPart);
		}
	
	public void dumpPartOnFeeder(Integer x){
		
	}
		    
	public void setFullStatus(Boolean status){
		full=status;
	}
	public void setPartToNull(){
		this.part=null;
	}
	public Part getPart(){
		return part;
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


