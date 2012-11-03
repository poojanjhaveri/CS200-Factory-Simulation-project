package factory.factory200.laneManager;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This is a Graphics Panel for one nest.
 * 
 * @brief Graphics Panel For Lane
 * @author Dongyoung Jung
 */
public class LaneManagerNest extends JLabel{
	
	private int nestNum;	///< nest number
	private ArrayList<JLabel> nestCells = new ArrayList<JLabel>();	///< ArrayList of nestcells( Total 8 cells )
	private ArrayList<ImageIcon> partImages = new ArrayList<ImageIcon>();	///< ArrayList of ImageIcons( To be saved in each cell )
	private JLabel newNestCell;	///< Instance of JLabel to be saved in ArrayList 'nestCells'
	private static ImageIcon nestImage = new ImageIcon(".//pics/nest.png");	///< Image of nest
	
	/**
	 * This function generates 8 cells for one nest.
	 * 
	 * @brief Constructor
	 * @param xCoor : X Coordinate
	 * @param yCoor : Y Coordinate
	 * @param nestNum : nest number
	 */
	public LaneManagerNest(int xCoor, int yCoor, int nestNum){
		this.nestNum = nestNum;
		setSize(40, 80);
		setIcon(nestImage);
		setLocation(xCoor, yCoor);
		setLayout(new GridLayout(4,2));
		
		for(int i=0 ; i<8 ; i++){
			newNestCell = new JLabel();
			nestCells.add(newNestCell);
			add(newNestCell);
		}
	}
	
	/**
	 * This function receives part it the part reaches the end of lane.
	 * 
	 * @brief Receive Part
	 * @param newPart : new part
	 */
	public void receivePartFromLane(LaneManagerPart newPart){
		partImages.add( newPart.getImageIconOfPart() );
		update();
	} 

	/**
	 * If the part robot takes one, one part disappears from nest.
	 * 
	 * @brief Robot Takes One From Nest
	 */
	public void robotTakePart(){
		if( partImages.size() != 0 ){
			partImages.remove(0);
			update();
		}
	}
	
	/**
	 * When the nest is down, all parts fall down.
	 * 
	 * @brief Nest Clear
	 */
	public void clearImageArrayList(){
		partImages.clear();
		update();
	}
	
	/**
	 * Nest composes itself. It is not putting images in layout.
	 * Since the 8 cells are fixed, this function uses the ArrayList of cells to put the ImageIcons into the 8 JLabels again.
	 * 
	 * @brief Nest Update
	 */
	public void update(){
		for(int i=0 ; i<partImages.size() ; i++){
			nestCells.get(i).setIcon(partImages.get(i));
		}
		for(int i=partImages.size() ; i<nestCells.size(); i++){
			nestCells.get(i).setIcon(null);
		}
	}
	
	/**
	 * @brief Getter
	 * @return Size of parts in nest
	 */
	public int getPartQuantityInNest(){
		return partImages.size();
	}
}
