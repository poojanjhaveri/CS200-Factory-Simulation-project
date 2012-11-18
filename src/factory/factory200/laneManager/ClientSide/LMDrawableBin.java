package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

/**
 * @brief Bin Drawings
 * @author Dongyoung Jung
 */
public class LMDrawableBin {

	private int binX, binY;
	private ImageIcon binImage;
	private static ImageIcon bin1Image = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox1.png") );
	private static ImageIcon bin2Image = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox2.png") );
	private static ImageIcon bin3Image = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox3.png") );
	private static ImageIcon bin4Image = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox4.png") );
	private static ImageIcon bin5Image = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox5.png") );
	private static ImageIcon bin6Image = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox6.png") );
	private static ImageIcon bin7Image = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox7.png") );
	private static ImageIcon bin8Image = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox8.png") );
	private static ImageIcon emptyBinImage = new ImageIcon( LMDrawableBin.class.getResource("./pics/emptybox.png") );
	
	private Boolean binExist = false;
		
	public LMDrawableBin(int binX, int binY){
		this.binX = binX;
		this.binY = binY;
	}
	
	public void setBinImage(int binNum){
		if( binNum == 0 ){ binImage = bin1Image; }
		else if( binNum == 1 ){ binImage = bin2Image; }
		else if( binNum == 2 ){ binImage = bin3Image; }
		else if( binNum == 3 ){ binImage = bin4Image; }
		else if( binNum == 4 ){ binImage = bin5Image; }
		else if( binNum == 5 ){ binImage = bin6Image; }
		else if( binNum == 6 ){ binImage = bin7Image; }
		else if( binNum == 7 ){ binImage = bin8Image; }
		binExist = true;
	}
	
	public void emptyBin(){
		binImage = emptyBinImage;
	}
	
	public void purgeBin(){
		binExist = false;
	}
	
	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
		if(binExist == true){
			binImage.paintIcon(panel, graphics, binX, binY);
		}
	}
}
