import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Part extends JLabel{

	private Lane lane;
	private int limit = 0;
	private int xCoor = 400;
	private final int yCoor = 15;
	private static ImageIcon partImage = new ImageIcon(".//pics//part_pic.png");
	
	public Part(Lane lane){
		this.lane = lane;
		setIcon(partImage);
		setSize(30,30);
		setLocation(xCoor ,yCoor);
	}
	
	public void partMove(){
		// keep moving through lane
		if( xCoor > limit ){
			xCoor -= 2;
			setLocation(xCoor, yCoor);
		}
	}
	
	public void setLimit(int newLimit){
		limit = newLimit;
	}
	
	public ImageIcon getImageIconOfPart(){
		return partImage;
	}
	
	public void setDisappear(){
		setIcon(null);
	}
}
