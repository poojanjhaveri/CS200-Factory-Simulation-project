package factory.factory200.laneManager;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 * This is a Graphics Panel for one nest.
 * 
 * @brief Graphics Panel For Lane
 * @author Dongyoung Jung
 */
public class LaneManagerNest extends JLabel{
	
	private int nestNum;	///< nest number
	private ArrayList<JLabel> nestCells = new ArrayList<JLabel>();	///< ArrayList of nestcells( Total 8 cells )
	private JLabel newNestCell;	///< Instance of JLabel to be saved in ArrayList 'nestCells'
	
	// Image version
	//private ArrayList<ImageIcon> partImages = new ArrayList<ImageIcon>();	///< ArrayList of ImageIcons( To be saved in each cell )
	//private static ImageIcon nestImage = new ImageIcon(".//pics/nest.png");	///< Image of nest
	
	// No image version
	private ArrayList<String> partStrings = new ArrayList<String>();	///< ArrayList of ImageIcons( To be saved in each cell )
	
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
		setLocation(xCoor, yCoor);
		setLayout(new GridLayout(4,2));
		
		//---------------------------------------------------------------------
		// Image version
		//setIcon(nestImage);
		
		// No image version
		setOpaque(true);
		setBackground( new Color(255,148,99) );
		setBorder(new LineBorder( Color.black ));
		//---------------------------------------------------------------------
		
		for(int i=0 ; i<8 ; i++){
			newNestCell = new JLabel();
			
			//------------------------------------------
			// No image version
			newNestCell.setOpaque(true);
			newNestCell.setBackground(new Color(255,148,99));
			//------------------------------------------
			
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
		//---------------------------------------------------------------------
		// Image version
		//partImages.add( newPart.getImageIconOfPart() );
		
		// No image version
		partStrings.add( newPart.getStringOfPart() );
		//---------------------------------------------------------------------
		
		update();
	} 

	/**
	 * If the part robot takes one, one part disappears from nest.
	 * 
	 * @brief Robot Takes One From Nest
	 */
	public void robotTakePart(){
		//---------------------------------------------------------------------
		// Image version
		/*
		if( partImages.size() != 0 ){
			partImages.remove(0);
			update();
		}
		*/
		
		// No image version
		if( partStrings.size() != 0 ){
			partStrings.remove(0);
			update();
		}
		//---------------------------------------------------------------------
	}
	
	/**
	 * When the nest is down, all parts fall down.
	 * 
	 * @brief Nest Clear
	 */
	public void clearImageArrayList(){
		//---------------------------------------------------------------------
		// Image version
		//partImages.clear();
		
		// No image version
		partStrings.clear();
		//---------------------------------------------------------------------
		update();
	}
	
	/**
	 * Nest composes itself. It is not putting images in layout.
	 * Since the 8 cells are fixed, this function uses the ArrayList of cells to put the ImageIcons into the 8 JLabels again.
	 * 
	 * @brief Nest Update
	 */
	public void update(){
		//---------------------------------------------------------------------
		// Image version
		/*
		for(int i=0 ; i<partImages.size() ; i++){
			nestCells.get(i).setIcon(partImages.get(i));
		}
		for(int i=partImages.size() ; i<nestCells.size(); i++){
			nestCells.get(i).setIcon(null);
		}
		*/
		
		// No image version
		for(int i=0 ; i<partStrings.size() ; i++){
			nestCells.get(i).setBackground(new Color(250,255,117));
			nestCells.get(i).setBorder(new LineBorder(Color.black));
			nestCells.get(i).setText(partStrings.get(i));
		}
		for(int i=partStrings.size() ; i<nestCells.size(); i++){
			nestCells.get(i).setBackground(new Color(255,148,99));
			nestCells.get(i).setBorder(null);
			nestCells.get(i).setText("");
		}
		//---------------------------------------------------------------------
	}
	
	/**
	 * @brief Getter
	 * @return Size of parts in nest
	 */
	public int getPartQuantityInNest(){
		//---------------------------------------------------------------------
		// Image version
		//return partImages.size();
		
		// No image version
		return partStrings.size();
		//---------------------------------------------------------------------
	}
}
