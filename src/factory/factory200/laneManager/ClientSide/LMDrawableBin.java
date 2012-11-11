package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class LMDrawableBin {

	private int binX, binY;
	private ImageIcon binImage;
	private static ImageIcon bin1 = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox1.jpg") );
	private static ImageIcon bin2 = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox2.jpg") );
	private static ImageIcon bin3 = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox3.jpg") );
	private static ImageIcon bin4 = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox4.jpg") );
	private static ImageIcon bin5 = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox5.jpg") );
	private static ImageIcon bin6 = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox6.jpg") );
	private static ImageIcon bin7 = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox7.jpg") );
	private static ImageIcon bin8 = new ImageIcon( LMDrawableBin.class.getResource("./pics/binBox8.jpg") );
	
	private Boolean binExist = false;
		
	public LMDrawableBin(int binX, int binY){
		this.binX = binX;
		this.binY = binY;
	}
	
	public void setBinImage(int binNum){
		if( binNum == 0 ){ binImage = bin1; }
		else if( binNum == 1 ){ binImage = bin2; }
		else if( binNum == 2 ){ binImage = bin3; }
		else if( binNum == 3 ){ binImage = bin4; }
		else if( binNum == 4 ){ binImage = bin5; }
		else if( binNum == 5 ){ binImage = bin6; }
		else if( binNum == 6 ){ binImage = bin7; }
		else if( binNum == 7 ){ binImage = bin8; }
		binExist = true;
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
