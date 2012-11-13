package factory.factory200.gantryRobotManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import factory.factory200.kitAssemblyManager.KAMNest;
import factory.factory200.kitAssemblyManager.KAMGraphicPanel.DeliveryTimer;
import factory.general.Part;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;


public class GRMGraphicPanel extends JPanel implements ActionListener {
	 //TODO figure out what the cords are for the following constants
    public static final Integer FEED0X = 0;///<x-coordinate of feeder 0
    public static final Integer FEED0Y = 0;///<y-coordinate of feeder 0
    public static final Integer FEED1X = 0;///<x-coordinate of feeder 1
    public static final Integer FEED1Y = 0;///<y-coordinate of feeder 1
    public static final Integer FEED2X = 0;///<x-coordinate of feeder 2
    public static final Integer FEED2Y = 0;///<y-coordinate of feeder 2
    public static final Integer FEED3X = 0;///<x-coordinate of feeder 3
    public static final Integer FEED3Y = 0;///<y-coordinate of feeder 3

    public static final Integer DUMPX = 0;///<x-coordinate of dump 
    public static final Integer DUMPY = 0;///<y-coordinate of dump 

    public static final Integer BIN_X = 0;///<x coordinate of all bin locations
    public static final Integer BIN0Y = 0;///<y coordinate of bin0
    public static final Integer BIN1Y = 0;///<y coordinate of bin1
    public static final Integer BIN2Y = 0;///<y coordinate of bin2
    public static final Integer BIN3Y = 0;///<y coordinate of bin3
    public static final Integer BIN4Y = 0;///<y coordinate of bin4
    public static final Integer BIN5Y = 0;///<y coordinate of bin5
    public static final Integer BIN6Y = 0;///<y coordinate of bin6
    public static final Integer BIN7Y = 0;///<y coordinate of bin7

    public static final Integer ROBOT_INITIAL_X = 0;///<spawn x coordinate of gantrybot
    public static final Integer ROBOT_INITIAL_Y = 0;///<spawn y coordinate of gantrybot
    
    ArrayList<GUIBin> bins;
    Timer timer;
    GUIBin bin;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public GRMGraphicPanel(){
		//String n = "pics/binBox1.png";
		//bin = new GUIBin(450,0,0.0,n);
		bins = new ArrayList<GUIBin>();

        for (int i = 1; i <= 8; i++) {
        	bin=new GUIBin(450,(i*50-50),0.0, "pics/binBox1.png" );
            bins.add(bin);
        }

        timer = new Timer(20, this);
        timer.start();
        
	}
	
	public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D.Double backgroundRectangle = new Rectangle2D.Double(0, 0, 500, 700);
        g2.setColor(Color.GRAY.darker().darker());//dark dark green background
        g2.fill(backgroundRectangle);
        paintBins(this, g2);
        //kitstand.getKitStand().paintIcon(this, g2, kitstand.getX(), kitstand.getY());
        
	}
	
	public void paintBins(JPanel j,Graphics2D g){
		
		for (int i=1;i<=7;i++){
			bins.get(i).getImage().paintIcon(j, g, bin.getX(), bin.getY());
	}
		
	}
		
//		         boolean changed;
		 //
//		         public GraphicGantryPanel() {//set up a Timer	
//		         }
		 //
//		         /**
//		          * check if Server send any message or data back
//		          */
//		         public boolean checkServerMessage() {
//		         }// 
		 //
//		         /**
//		          * get all coordinates from GantryState class
//		          */
//		         public resetComponent() {
//		         }
		 //
//		         ;//
//		 		/**
		 //paint all components: bins, purge station, feeders and the gantry robot
//		 		 */
//		 		public paint(Graphics g) {
//		         }
		 //
//		         ;
//		 		public actionPerformed() {
//		             changed = checkServerMessage();
		 //
//		             if (changed) {
//		                 resetComponents();//get information of recently changedd
//		             }
//		             repaint();
//		         }
//		     }

}
