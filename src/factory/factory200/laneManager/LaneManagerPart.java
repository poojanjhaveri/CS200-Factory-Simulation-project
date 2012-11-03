package factory.factory200.laneManager;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This class has information of parts on lane and nest.
 * Each part has its image and location of x and y. 
 * 
 * @brief Part On Lane and Nest
 * @author Dongyoung Jung
 */
public class LaneManagerPart extends JLabel{

	private int limit = 0;	///< If nest if full, this variables plays a role to stand behind in a line.
	private int xCoor = 410;	///< Current X location of part 
	private final int yCoor = 10;	///< Current Y location of part
	private int partNum;	///< part number
	private static ImageIcon partImage0 = new ImageIcon(".//pics//part_pic0.png");	///< Part Image of part number 1
	private static ImageIcon partImage1 = new ImageIcon(".//pics//part_pic1.png");	///< Part Image of part number 2
	private static ImageIcon partImage2 = new ImageIcon(".//pics//part_pic2.png");	///< Part Image of part number 3
	private static ImageIcon partImage3 = new ImageIcon(".//pics//part_pic3.png");	///< Part Image of part number 4
	private static ImageIcon partImage4 = new ImageIcon(".//pics//part_pic4.png");	///< Part Image of part number 5
	private static ImageIcon partImage5 = new ImageIcon(".//pics//part_pic5.png");	///< Part Image of part number 6
	private static ImageIcon partImage6 = new ImageIcon(".//pics//part_pic6.png");	///< Part Image of part number 7
	private static ImageIcon partImage7 = new ImageIcon(".//pics//part_pic7.png");	///< Part Image of part number 8
	private ArrayList<ImageIcon> partImages = new ArrayList<ImageIcon>();	///< ArrayList of part images
	
	/**
	 * This sets up the image and the default location.
	 * 
	 * @brief Part Constructor
	 * @param partNum : part number
	 */
	public LaneManagerPart( int partNum ){
		this.partNum = partNum;
		
		if( partNum == 0 ){ setIcon(partImage0); }
		else if( partNum == 1 ){ setIcon(partImage1); }
		else if( partNum == 2 ){ setIcon(partImage2); }
		else if( partNum == 3 ){ setIcon(partImage3); }
		else if( partNum == 4 ){ setIcon(partImage4); }
		else if( partNum == 5 ){ setIcon(partImage5); }
		else if( partNum == 6 ){ setIcon(partImage6); }
		else if( partNum == 7 ){ setIcon(partImage7); }
				
		setSize(20,20);
		setLocation(xCoor ,yCoor);
	}
	
	/**
	 * The default limit is 0 for all parts.
	 * If the nest is full, the limit of all parts on lane is changed in order that they line up on the lane.
	 * If the current X location is larger than the limit, the part does not move.
	 * 
	 * @brief Part Movements
	 */
	public void partMove(){
		if( xCoor > limit ){
			xCoor -= 2;
			setLocation(xCoor, yCoor);
		}
	}
	
	/**
	 * If the nest is full, the lane runs this function to reset the limits of all parts on lane.
	 * 
	 * @brief Setter
	 * @param newLimit : New Limit Setup
	 */
	public void setLimit(int newLimit){
		limit = newLimit;
	}
	
	/**
	 * @brief Getter
	 * @return Instance of 'ImageIcon' of the part
	 */
	public ImageIcon getImageIconOfPart(){
		if( partNum == 0 ){ return partImage0; }
		else if( partNum == 1 ){ return partImage1; }
		else if( partNum == 2 ){ return partImage2; }
		else if( partNum == 3 ){ return partImage3; }
		else if( partNum == 4 ){ return partImage4; }
		else if( partNum == 5 ){ return partImage5; }
		else if( partNum == 6 ){ return partImage6; }
		else if( partNum == 7 ){ return partImage7; }
		return null;
	}
	
	/**
	 * If the part reaches the end of lane, it disappears.
	 * 
	 * @brief Part Image Disabler
	 */
	public void setDisappear(){
		setIcon(null);
	}
	
	/**
	 * @brief Getter
	 * @return part number
	 */
	public int getPartNum(){
		return partNum;
	}
}
