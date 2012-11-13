package factory.factory200.gantryRobotManager;

import factory.general.Coordinate;
import javax.swing.ImageIcon;


public class GUIBin{
	
	String binPicture;	
	
	protected Integer x;
	protected Integer y;
    protected Double rotation;///<information on degrees of rotation. 0 degrees is due right

	 ImageIcon img;///<image of the drawable

	 public GUIBin(){
		 
	 }
		public GUIBin(Integer newx, Integer newy, Double r, String i) {  
			x=newx;
			y = newy;
			rotation = r;
			binPicture=i;
			img = new ImageIcon(i);
		}
		    
		   
		    public ImageIcon getImage()
		    {
		        return this.img;
		    }
		    public void setX(Integer newx)
		    {
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
		    public Integer getY()
		    {
		        return y;
		    }
		 

		
	}	
	
	//bin=new ImageIcon("pics/KAMkitStand.png");
    //kitPositions=new ArrayList<KitPosition>();


