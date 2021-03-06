package factory.factory200.factoryProductionManager.GantryRobotManager;

import factory.factory200.factoryProductionManager.*;
import factory.factory200.gantryRobotManager.GUIBin;
import factory.factory200.gantryRobotManager.GUIFeeder;
import factory.factory200.gantryRobotManager.PurgeStation;
import factory.general.GUIPart;
import factory.general.Part;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GRMGraphicPanel{
    
    ArrayList<GUIBin> bins;
    ArrayList<Part> parts;
    ArrayList<GUIFeeder> feeders;
    Integer carriedBinIndex;
    GUIBin bin;
    Part newPart;
    GUIPart GUItemp;
	GUIGantryRobot gbot;
	PurgeStation ps;
        GUIFeeder feedertemp; 
	public GRMGraphicPanel(){
		carriedBinIndex = 0;
		gbot = new GUIGantryRobot();
                
		bin = new GUIBin(450,0,0.0,"",0);
		bins = new ArrayList<GUIBin>();
		parts = new ArrayList<Part>();
		feeders = new ArrayList<GUIFeeder>();
		newPart = new Part(null,null);
                ps=new PurgeStation();
                feedertemp = new GUIFeeder((Integer)0,(Integer)0,(Double)0.0,"");
		
		///<Initialize all 8 bins, 8 parts within its bins
		
        for (int i = 1; i < 9; i++) {
           bin=new GUIBin(1280,(i*80-70),0.0, "pics/emptybox.png",i);
           bins.add(bin);
           GUItemp=new GUIPart(bins.get(i-1).getX()+15, bins.get(i-1).getY()+20, 0.0, new ImageIcon("pics/parts/part"+i+".png"));
           newPart = new Part(null,null);
           newPart.setGUIPart(GUItemp);
           parts.add(newPart);
        }
        for(int i = 1;i<5; i++){
        	feedertemp = new GUIFeeder(0,i*150-100,0.0,"pics/feeder"+i+".png");
        	feeders.add(feedertemp);
        }
	}
	
	/*
	 * timer actions called by every clock cycle
	 */
	public void timerAction() {
		// TODO Auto-generated method stub
		if(!gbot.moving()){
			if(!gbot.performOrder()){
			if(this.gbot.getOrder() < 16 && this.gbot.getOrder() > 7)
			{
				this.gbot.pickUpBin(this.gbot.getOrder()-8);
				this.gbot.popOrder();
			}else if(this.gbot.getOrder() < 25 && this.gbot.getOrder() > 20)
			{
				this.gbot.supplyPartOnFeeder(this.gbot.getOrder()-21);//this.gbot.getOrder()-21);
				this.gbot.popOrder();
			}
			else if(this.gbot.getOrder() == 26)
	    	{
	    		this.gbot.binPurged();	    		
	    		this.gbot.popOrder();
	    		Integer binIndex=this.getBinCarriedIndex();
				this.binIsPurged(binIndex);
	    	}
			
			}
		}	
		if(this.gbot.getBreakState()==false){
		gbot.update();
		}
		//repaint();
	}
	
	/*
	 * return carriedBinIndex
	 */
	
	private Integer getBinCarriedIndex() {
		// TODO Auto-generated method stub
		return carriedBinIndex;
	}

	/*
	 * set the bin invisible which is carried by the robot
	 */
	public void binIsCarried(Integer binIndex){
		this.bins.get(binIndex).setFullStatus(false);
		carriedBinIndex = binIndex;
		//this.bins.g
	}
	
	
	public void paint(GraphicsPanel graph, Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        Image img = new ImageIcon("pics/background/part3.png").getImage();
        g2.drawImage(img, -1350+500, 0, null);
        
        paintPurge(graph,g2);
        paintBinsWithParts(graph, g2);
       
	    gbot.paintMe(graph,g2);
	}
	
	public void binIsPurged(Integer binIndex){
		this.bins.get(binIndex).setFullStatus(true);
	}
	
	/*
	 * paint purge station
	 */
	public void paintPurge(JPanel j,Graphics2D g){
		ps.getImage().paintIcon(j, g, 1160, 600);
	}
	public void paintBinsWithParts(JPanel j,Graphics2D g){
		
		for (int i=0;i<8;i++){
			if(bins.get(i).binIsFull()==true){
				bins.get(i).getImage().paintIcon(j, g, bins.get(i).getX(), bins.get(i).getY());
				parts.get(i).getGUIPart().getImage().paintIcon(j, g, parts.get(i).getGUIPart().getX(), parts.get(i).getGUIPart().getY());
			}
			
		}
		
	}
	
	public void paintFeeders(JPanel j,Graphics2D g){
		for(int i = 0;i<4;i++){
			feeders.get(i).getImage().paintIcon(j, g, feeders.get(i).getX(), feeders.get(i).getY());
		}
	}
		

}
