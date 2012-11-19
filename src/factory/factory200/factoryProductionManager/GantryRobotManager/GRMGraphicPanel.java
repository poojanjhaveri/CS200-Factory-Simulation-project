package factory.factory200.factoryProductionManager.GantryRobotManager;

import factory.factory200.factoryProductionManager.*;
import factory.factory200.gantryRobotManager.PurgeStation;
import factory.general.GUIPart;
import factory.general.Part;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;


public class GRMGraphicPanel{
    
    ArrayList<GUIBin> bins;
    ArrayList<Part> parts;
    ArrayList<GUIFeeder> feeders;
        
    GUIBin bin;
    Part newPart;
    GUIPart GUItemp;
	GUIGantryRobot gbot;
	PurgeStation ps;
	public GRMGraphicPanel(){
		
		gbot = new GUIGantryRobot();
		bin = new GUIBin(450,0,0.0,"",0);
		bins = new ArrayList<GUIBin>();
		parts = new ArrayList<Part>();
		feeders = new ArrayList<GUIFeeder>();
		newPart = new Part(null,null);
		
		///<Initialize all 8 bins, 8 parts within its bins
		
        for (int i = 1; i < 9; i++) {
           bin=new GUIBin(1220,(i*80-60),0.0, "pics/binBox"+i+".png",i);
           bins.add(bin);
           GUItemp=new GUIPart(bins.get(i-1).getX()+15, bins.get(i-1).getY()+20, 0.0, new ImageIcon("pics/parts/part"+i+".png"));
           newPart = new Part(null,null);
           newPart.setGUIPart(GUItemp);
           parts.add(newPart);
        }
	}
	
	public void paint(GraphicsPanel panel, Graphics2D g) {
        paintBinsWithParts(panel, g);
        gbot.paintMe(panel,g);
       
	}
	
	public void paintBinsWithParts(GraphicsPanel panel,Graphics2D g){	
		for (int i=0;i<8;i++){
			if(bins.get(i).binIsFull()==true){
				bins.get(i).getImage().paintIcon(panel, g, bins.get(i).getX(), bins.get(i).getY());
			}
			parts.get(i).getGUIPart().getImage().paintIcon(panel, g, parts.get(i).getGUIPart().getX(), parts.get(i).getGUIPart().getY());
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
