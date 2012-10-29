import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
	@brief creates nest image on graphic panel
	@author Dongyoung Jung
*/
public class Nest extends JPanel{
	
	private GridLayout layout = new GridLayout(4,2);
	private ArrayList<JLabel> nestCells = new ArrayList<JLabel>();
	private ArrayList<ImageIcon> partImages = new ArrayList<ImageIcon>();
	private JLabel newNestCell;
	
	public Nest(final int xCoor, final int yCoor){
		setSize(30, 60);
		setLocation(xCoor, yCoor);
		setLayout(layout);
		
		// 8 part stands(JPanel)
		for(int i=0 ; i<8 ; i++){
			newNestCell = new JLabel();
			newNestCell.setBorder(new LineBorder( Color. red ));
			nestCells.add(newNestCell);
			add(newNestCell);
		}
	}
	
	// Receives part from lane
	public void receivePartFromLane(Part newPart){
		partImages.add( newPart.getImageIconOfPart() );
		update();
	} 
	
	// Robot takes part from nest
	public void robotTakePart(){
		if( partImages.size() != 0 ){
			partImages.remove(0);
			update();
		}
	}
	
	// Update nest
	public void update(){
		for(int i=0 ; i<partImages.size() ; i++){
			nestCells.get(i).setIcon(partImages.get(i));
		}
		for(int i=partImages.size() ; i<nestCells.size(); i++){
			nestCells.get(i).setIcon(null);
		}
	}
	
	public int getPartQuantityInNest(){
		return partImages.size();
	}
}
