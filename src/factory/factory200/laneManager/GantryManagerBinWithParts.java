package factory.factory200.laneManager;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

//Bins class
public class GantryManagerBinWithParts extends JLabel{

	private int binNum;
	private static ImageIcon binBoxImage0 = new ImageIcon(".//pics//binBox1.jpg");
	private static ImageIcon binBoxImage1 = new ImageIcon(".//pics//binBox2.jpg");
	private static ImageIcon binBoxImage2 = new ImageIcon(".//pics//binBox3.jpg");
	private static ImageIcon binBoxImage3 = new ImageIcon(".//pics//binBox4.jpg");
	private static ImageIcon binBoxImage4 = new ImageIcon(".//pics//binBox5.jpg");
	private static ImageIcon binBoxImage5 = new ImageIcon(".//pics//binBox6.jpg");
	private static ImageIcon binBoxImage6 = new ImageIcon(".//pics//binBox7.jpg");
	private static ImageIcon binBoxImage7 = new ImageIcon(".//pics//binBox8.jpg");
	
	//GantryManagerBinWithParts constructor with coordinates parameters and binNumber
	public GantryManagerBinWithParts( int xCoor, int yCoor, int binNum ){
		this.binNum = binNum;
		
		if( binNum == 0 ){ setIcon(binBoxImage0); }
		else if( binNum == 1 ){ setIcon(binBoxImage1); }
		else if( binNum == 2 ){ setIcon(binBoxImage2); }
		else if( binNum == 3 ){ setIcon(binBoxImage3); }
		else if( binNum == 4 ){ setIcon(binBoxImage4); }
		else if( binNum == 5 ){ setIcon(binBoxImage5); }
		else if( binNum == 6 ){ setIcon(binBoxImage6); }
		else if( binNum == 7 ){ setIcon(binBoxImage7); }
		
		setSize(40,70);
		setLocation(xCoor, yCoor);
	}	
}
