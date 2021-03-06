package factory.factory200.gantryRobotManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import factory.general.GUIPart;
import factory.general.Global;
import factory.general.Message;
import factory.general.Part;

/**
 * @brief GRM animations are drawn here
 * @author Yuting Liu
 */
public class GRMGraphicPanel extends JPanel implements ActionListener {
    
    ArrayList<GUIBin> bins;
    ArrayList<Part> parts;
    ArrayList<GUIFeeder> feeders;
    
    Timer timer;
    GantryRobotManager grm;
    
    GUIBin bin;
    Part newPart;
    GUIPart GUItemp;
    GUIFeeder feedertemp; 
    PurgeStation ps;
	GUIGantryRobot gbot;
	Integer carriedBinIndex;
    
	public void actionPerformed(ActionEvent arg0) {
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
	    	}else if(this.gbot.getOrder() > 29 && this.gbot.getOrder() < 33)
			    {
				switch(this.gbot.getOrder())
				    {
				    case 30:
					this.grm.sendToServer(Message.GRM_FINISH_MOVE_TO_BIN);
					break;
				    case 31:
					this.grm.sendToServer(Message.GRM_FINISH_MOVE_TO_FEEDER);
					break;
				    case 32:
					this.grm.sendToServer(Message.GRM_FINISH_MOVE_TO_DUMP);
					break;
				    
				    }
				this.gbot.popOrder();
			    }
			
			}
		}	
		if(this.gbot.getBreakState()==false){
			gbot.update();
		}
		repaint();
	}

	public GRMGraphicPanel(GantryRobotManager g){
		ps = new PurgeStation();
		carriedBinIndex = 0;
		gbot = new GUIGantryRobot();
		bin = new GUIBin(450,0,0.0,"",0);
		bins = new ArrayList<GUIBin>();
		parts = new ArrayList<Part>();
		grm=g;
		newPart = new Part(null,null);
		feeders = new ArrayList<GUIFeeder>();	
		feedertemp = new GUIFeeder((Integer)0,(Integer)0,(Double)0.0,"");
		
		///<Initialize all 8 bins, 8 parts within its bins
        for (int i = 1; i <= 8; i++) {
           bin=new GUIBin(400,(i*80-50),0.0, "pics/emptybox.png",i-1);//"pics/binBox"+i+".png",i);
           bins.add(bin);
           GUItemp=new GUIPart(bins.get(i-1).getX()+15, bins.get(i-1).getY()+20, 0.0, new ImageIcon("pics/parts/part"+i+".png"));
           newPart = new Part(null,null);
           newPart.setGUIPart(GUItemp);
           parts.add(newPart);
        }
        ///<Initialize all 4 feeders
        for(int i = 1;i<5; i++){
        	feedertemp = new GUIFeeder(0,i*150-100,0.0,"pics/feeder"+i+".png");
        	feeders.add(feedertemp);
        }
        
        ///<Set the timer
        timer = new Timer(Global.STANDARD_TIMER_SPEED, this);
        timer.start();
        
	}
	
	/**
	 * getter for GUIGantryRobot
	 * @return an instance of GUIGantryRobot
	 */
	public GUIGantryRobot getGantryRobot(){
		return gbot;
	}
	
	/**
	 * paint method with Graphics params
	 */
	public void paint(Graphics g) {
            
        Graphics2D g2 = (Graphics2D) g;
        Image img = new ImageIcon("pics/background/mainbg.png").getImage();
        g2.drawImage(img, -1350+500, 0, null);
        //Rectangle2D.Double backgroundRectangle = new Rectangle2D.Double(0, 0, 500, 700);
        //g2.setColor(Color.GRAY.darker().darker());//dark dark green background
        //g2.fill(backgroundRectangle);
        paintPurge(this,g2);
        paintBinsWithParts(this, g2);
        paintFeeders(this,g2);
	    gbot.paintMe(this,g2);
	}
	
	public void binIsCarried(Integer binIndex){
		this.bins.get(binIndex).setFullStatus(false);
		carriedBinIndex = binIndex;
		//this.bins.g
	}
	
	public Integer getBinCarriedIndex(){
		return carriedBinIndex;
	}
	public void binIsPurged(Integer binIndex){
		this.bins.get(binIndex).setFullStatus(true);
	}
	
	public void paintPurge(JPanel j,Graphics2D g){
		ps.getImage().paintIcon(j, g, 260, 600);
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
