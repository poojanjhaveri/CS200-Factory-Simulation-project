package factory.factory200.laneManager;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;


public class GantryManagerBinWithParts extends JLabel{

	private int binNum;
	
	//-----------------------------------------------------------------------------------------------------------------------
	// Image version
	/*
	private static ImageIcon binBoxImage0 = new ImageIcon(".//pics//binBox1.jpg");
	private static ImageIcon binBoxImage1 = new ImageIcon(".//pics//binBox2.jpg");
	private static ImageIcon binBoxImage2 = new ImageIcon(".//pics//binBox3.jpg");
	private static ImageIcon binBoxImage3 = new ImageIcon(".//pics//binBox4.jpg");
	private static ImageIcon binBoxImage4 = new ImageIcon(".//pics//binBox5.jpg");
	private static ImageIcon binBoxImage5 = new ImageIcon(".//pics//binBox6.jpg");
	private static ImageIcon binBoxImage6 = new ImageIcon(".//pics//binBox7.jpg");
	private static ImageIcon binBoxImage7 = new ImageIcon(".//pics//binBox8.jpg");
	*/
	
	// No image version
	private static String binBoxString0 = new String("BIN1");
	private static String binBoxString1 = new String("BIN2");
	private static String binBoxString2 = new String("BIN3");
	private static String binBoxString3 = new String("BIN4");
	private static String binBoxString4 = new String("BIN5");
	private static String binBoxString5 = new String("BIN6");
	private static String binBoxString6 = new String("BIN7");
	private static String binBoxString7 = new String("BIN8");
	//-----------------------------------------------------------------------------------------------------------------------
	
	public GantryManagerBinWithParts( int xCoor, int yCoor, int binNum ){
		this.binNum = binNum;
		setSize(40,70);
		setLocation(xCoor, yCoor);
		
		//-----------------------------------------------------------------------
		// Image version
		/*
		if( binNum == 0 ){ setIcon(binBoxImage0); }
		else if( binNum == 1 ){ setIcon(binBoxImage1); }
		else if( binNum == 2 ){ setIcon(binBoxImage2); }
		else if( binNum == 3 ){ setIcon(binBoxImage3); }
		else if( binNum == 4 ){ setIcon(binBoxImage4); }
		else if( binNum == 5 ){ setIcon(binBoxImage5); }
		else if( binNum == 6 ){ setIcon(binBoxImage6); }
		else if( binNum == 7 ){ setIcon(binBoxImage7); }
		*/
		
		// No image version
		setOpaque(true);
		setBorder( new LineBorder(Color.black));
		if( binNum == 0 ){ setText(binBoxString0); setBackground(Color.red); }
		else if( binNum == 1 ){ setText(binBoxString1); setBackground(Color.blue); }
		else if( binNum == 2 ){ setText(binBoxString2); setBackground(Color.white);}
		else if( binNum == 3 ){ setText(binBoxString3); setBackground(Color.pink);}
		else if( binNum == 4 ){ setText(binBoxString4); setBackground(Color.green);}
		else if( binNum == 5 ){ setText(binBoxString5); setBackground(Color.cyan);}
		else if( binNum == 6 ){ setText(binBoxString6); setBackground(Color.yellow); }
		else if( binNum == 7 ){ setText(binBoxString7); setBackground(Color.darkGray);}
		//-----------------------------------------------------------------------
		
	}	
}
