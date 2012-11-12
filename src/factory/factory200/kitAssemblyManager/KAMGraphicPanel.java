/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.kitAssemblyManager;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Deepa
 */
import factory.general.GUIPart;
import factory.general.Part;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 *
 * @author Deepa, YiWei Roy Zheng
 */
public class KAMGraphicPanel extends JPanel implements ActionListener {

    public static final int KITROBOT_INITIAL_X = 175;///<kit robot default position (x)
    public static final int KITROBOT_INITIAL_Y = 325;///<kit robot default position (y)
    public static final int KITROBOT_VELOCITYX = 2;
    public static final int KITROBOT_VELOCITYY = 2;
    public static final Double KITROBOT_ROTATION_SPEED = 20.0;
    public static final int KITX = 275 + 25;
    public static final int KIT0Y = 150 + 10;
    public static final int KIT1Y = 150 + 10 + 125;
    public static final int KIT2Y = 150 + 10 + 250;
    //took into consideration kit stand positioning
    public static final int EMPTY_CONVEYERX = 25;
    public static final int EMPTY_CONVEYERY = 300;
    public static final int FULL_CONVEYERX=25;
    public static final int FULL_CONVEYERY=300;
            
    public static final Integer LANE0Y = 75/2 + 0 * 75;///<y-coordinate of lane 0's nest
    public static final Integer LANE1Y = 75/2 + 1 * 75;///<y-coordinate of lane 1's nest
    public static final Integer LANE2Y = 75/2 + 2 * 75;///<y-coordinate of lane 2's nest
    public static final Integer LANE3Y = 75/2 + 3 * 75;///<y-coordinate of lane 3's nest
    public static final Integer LANE4Y = 75/2 + 4 * 75;///<y-coordinate of lane 4's nest
    public static final Integer LANE5Y = 75/2 + 5 * 75;///<y-coordinate of lane 5's nest
    public static final Integer LANE6Y = 75/2 + 6 * 75;///<y-coordinate of lane 6's nest
    public static final Integer LANE7Y = 75/2 + 7 * 75;///<y-coordinate of lane 7's nest
    public static final Integer RAILX = 75/2 + 8 * 75;///<fixed x-coordinate of the rail the parts robot traverses
    public static final Integer PARTS_ROBOT_KITX=KAMGraphicPanel.KITX+20;
    public static final Integer PARTS_ROBOT_KITY=KAMGraphicPanel.KIT1Y+20;
    public static final Integer PARTSROBOT_VELOCITYX=2;
    public static final Integer PARTSROBOT_VELOCITYY= 2;     
    public static final Integer PARTSROBOTINITIALX = 400;///<x coordinate for parts robot to spawn in
    public static final Integer PARTSROBOTINITIALY = 350;///<y coordinate for parts robot to spawn in
    public GUIPartRobot kitter;///<declares an object that keeps track of the parts robot animation and graphics
    public GUIKitRobot kitbot;///<declares an object that keeps track of the kit robot animation and graphics
    public KitStand kitstand;///<declares an object that keeps track of what is happening with the kit stand
    public KitDeliveryStation delivery;///<declares an object that keeps track of the delivery station
    
    public KAMCamera camera;
    ArrayList<KAMNest> nest;
    int emptyKits;
    int counter;
    int cameraCounter;
    boolean deliveryStation;
    Timer timer;
    boolean stationRun;

    public KAMGraphicPanel() {
        deliveryStation = true;
        
        stationRun=true;

        kitbot = new GUIKitRobot();
        
        kitter=new GUIPartRobot();

        camera = new KAMCamera();


        kitstand = new KitStand(275, 150);


        //THIS NUMBER IS HARDCODED! should be from server => number of kits that should be made
        emptyKits = 3;
        delivery = new KitDeliveryStation(emptyKits);

        nest = new ArrayList<KAMNest>();

        for (int i = 1; i <= 8; i++) {
            nest.add(new KAMNest());
            nest.get(i - 1).setX(650);
        }

        int yNum = 75 / 2;
        for (int i = 0; i < 8; i++) {
            nest.get(i).setY(yNum + i * 75);
        }
        
        //only for version 0
        ArrayList<Part> parts1 = new ArrayList<Part>();
        
        Part part1 = new Part(null, null);
        GUIPart guipart1=new GUIPart(nest.get(0).getX(),nest.get(0).getY(),0.0,new ImageIcon("pics/parts/part1.png"));
        part1.setGUIPart(guipart1);
        parts1.add(part1);
        
        Part part2 = new Part(null, null);
        GUIPart guipart2=new GUIPart(nest.get(0).getX(),nest.get(0).getY()+20,0.0,new ImageIcon("pics/parts/part1.png"));
        part2.setGUIPart(guipart2);
        parts1.add(part2);
        
        Part part3 = new Part(null, null);
        GUIPart guipart3=new GUIPart(nest.get(0).getX(),nest.get(0).getY()+40,0.0,new ImageIcon("pics/parts/part1.png"));
        part3.setGUIPart(guipart3);
        parts1.add(part3);
        
        Part part4 = new Part(null, null);
        GUIPart guipart4=new GUIPart(nest.get(0).getX(),nest.get(0).getY()+60,0.0,new ImageIcon("pics/parts/part1.png"));
        part4.setGUIPart(guipart4);
        parts1.add(part4);
        
        Part part5 = new Part(null, null);
        GUIPart guipart5=new GUIPart(nest.get(0).getX()+15,nest.get(0).getY(),0.0,new ImageIcon("pics/parts/part1.png"));
        part5.setGUIPart(guipart5);
        parts1.add(part5);
        
        Part part6 = new Part(null, null);
        GUIPart guipart6=new GUIPart(nest.get(0).getX()+15,nest.get(0).getY()+20,0.0,new ImageIcon("pics/parts/part1.png"));
        part6.setGUIPart(guipart6);
        parts1.add(part6);
        
        Part part7 = new Part(null, null);
        GUIPart guipart7=new GUIPart(nest.get(0).getX()+15,nest.get(0).getY()+40,0.0,new ImageIcon("pics/parts/part1.png"));
        part7.setGUIPart(guipart7);
        parts1.add(part7);
        
        Part part8 = new Part(null, null);
        GUIPart guipart8=new GUIPart(nest.get(0).getX()+15,nest.get(0).getY()+60,0.0,new ImageIcon("pics/parts/part1.png"));
        part8.setGUIPart(guipart8);
        parts1.add(part8);
        
        ArrayList<Part> parts2 = new ArrayList<Part>();
        
        Part part9 = new Part(null, null);
        GUIPart guipart9=new GUIPart(nest.get(1).getX(),nest.get(1).getY(),0.0,new ImageIcon("pics/parts/part2.png"));
        part9.setGUIPart(guipart9);
        parts2.add(part9);
        
        Part part10 = new Part(null, null);
        GUIPart guipart10=new GUIPart(nest.get(1).getX(),nest.get(1).getY()+20,0.0,new ImageIcon("pics/parts/part2.png"));
        part10.setGUIPart(guipart10);
        parts2.add(part10);
        
        Part part11 = new Part(null, null);
        GUIPart guipart11=new GUIPart(nest.get(1).getX(),nest.get(1).getY()+40,0.0,new ImageIcon("pics/parts/part2.png"));
        part11.setGUIPart(guipart11);
        parts2.add(part11);
        
        Part part12 = new Part(null, null);
        GUIPart guipart12=new GUIPart(nest.get(1).getX(),nest.get(1).getY()+60,0.0,new ImageIcon("pics/parts/part2.png"));
        part12.setGUIPart(guipart12);
        parts2.add(part12);
        
        Part part13 = new Part(null, null);
        GUIPart guipart13=new GUIPart(nest.get(1).getX()+15,nest.get(1).getY(),0.0,new ImageIcon("pics/parts/part2.png"));
        part13.setGUIPart(guipart13);
        parts2.add(part13);
        
        Part part14 = new Part(null, null);
        GUIPart guipart14=new GUIPart(nest.get(1).getX()+15,nest.get(1).getY()+20,0.0,new ImageIcon("pics/parts/part2.png"));
        part14.setGUIPart(guipart14);
        parts2.add(part14);
        
        Part part15 = new Part(null, null);
        GUIPart guipart15=new GUIPart(nest.get(1).getX()+15,nest.get(1).getY()+40,0.0,new ImageIcon("pics/parts/part2.png"));
        part15.setGUIPart(guipart15);
        parts2.add(part15);
        
        Part part16 = new Part(null, null);
        GUIPart guipart16=new GUIPart(nest.get(1).getX()+15,nest.get(1).getY()+60,0.0,new ImageIcon("pics/parts/part2.png"));
        part16.setGUIPart(guipart16);
        parts2.add(part16);
        
        //for(int i=0;i<parts1.size();i++){
            nest.get(0).setParts(parts1);
        //}
            nest.get(1).setParts(parts2);
        
        //need above code from server
        
        counter = 0;
        cameraCounter = 0;
        timer = new Timer(20, new DeliveryTimer(this));
        timer.start();

    }

    public class DeliveryTimer implements ActionListener {

        JPanel myPanel;

        public DeliveryTimer(JPanel jp) {
            myPanel = jp;
        }

        public void actionPerformed(ActionEvent ae) {
            if (camera.isVisible()) {
                cameraCounter++;
            }
            
            //PlaceHolder temp=new PlaceHolder();
            if(deliveryStation==true){
            if(stationRun==true){
            if (delivery.getPlaceholder().get(delivery.getNumEmptyKits() - 1).getY() > -150) {
            for(int i=0;i<delivery.getPlaceholder().size();i++){
                //temp=delivery.getPlaceholder().get(i);
                int yPlace=delivery.getPlaceholder().get(i).getY()-1;
                int number = i * 200;
                    if (counter > number) {
                        delivery.getPlaceholder().get(i).setY(yPlace);
                    }
                    if(yPlace==300 && delivery.getPlaceholder().get(i).isShow()){
                        stationRun=false;
                        break;
                    }
            }
                    counter++;
            } else {
                for (int i = 0; i < delivery.getNumEmptyKits(); i++) {
                    delivery.getPlaceholder().get(i).setY(680);
                    counter = 0;
                }
            }
            }
            if(stationRun==false){
                for(int i=0;i<delivery.getPlaceholder().size();i++){
                    int yPlace=delivery.getPlaceholder().get(i).getY();
                    delivery.getPlaceholder().get(i).setY(yPlace);
                    if(yPlace==300 && !(delivery.getPlaceholder().get(i).isShow())){
                        stationRun=true;
                        break;
                    }
                }
            }
            }
            
            if(deliveryStation==false){
            if(stationRun==true){
            if (delivery.getPlaceholder().get(delivery.getNumEmptyKits() - 1).getY() > -150) {
            for(int i=0;i<delivery.getPlaceholder().size();i++){
                //temp=delivery.getPlaceholder().get(i);
                int yPlace=delivery.getPlaceholder().get(i).getY()-1;
                int number = i * 200;
                    if (counter > number) {
                        delivery.getPlaceholder().get(i).setY(yPlace);
                    }
                    if(yPlace==300 && !(delivery.getPlaceholder().get(i).isShow())){
                        stationRun=false;
                        break;
                    }
            }
                    counter++;
            } else {
                for (int i = 0; i < delivery.getNumEmptyKits(); i++) {
                    delivery.getPlaceholder().get(i).setY(680);
                    counter = 0;
                }
            }
            }
            if(stationRun==false){
                for(int i=0;i<delivery.getPlaceholder().size();i++){
                    int yPlace=delivery.getPlaceholder().get(i).getY();
                    delivery.getPlaceholder().get(i).setY(yPlace);
                    if(yPlace==300 && (delivery.getPlaceholder().get(i).isShow())){
                        stationRun=true;
                        deliveryStation=true;
                        break;
                    }
                }
            }
            }
            
                        
//            if (delivery.getPlaceholder().get(delivery.getNumEmptyKits() - 1).getY() > -150) {
//                for (int i = 0; i < delivery.getNumEmptyKits(); i++) {
//                    int yPlace = delivery.getPlaceholder().get(i).getY();
//                    int number = i * 200;
//                    if (counter > number) {
//                        delivery.getPlaceholder().get(i).setY(yPlace - 1);
//                    }
//                    
//                }
//                counter++;
//            } else {
//                for (int i = 0; i < delivery.getNumEmptyKits(); i++) {
//                    delivery.getPlaceholder().get(i).setY(680);
//                    counter = 0;
//                }
//            }
//            if (deliveryStation == false) {
//                for (int i = 0; i < delivery.getNumEmptyKits(); i++) {
//                    if (delivery.getPlaceholder().get(i).getY() == 300) {
//                        delivery.getPlaceholder().get(i).setY(300);
//                        myPanel.repaint();
//                    }
//                }
//            }
            if (!kitbot.moving()) {
                Integer order = kitbot.getOrder();
                switch (order) {
                    case 0:      
                        if (delivery.inPosition()) {
                            kitbot.giveKit(delivery.giveKit());
			    
                            //System.out.println("Picking up kit");
			}
                        break;
		case 1:kitbot.giveKit(kitstand.giveKit(0));
		    break;
		case 2:kitbot.giveKit(kitstand.giveKit(1));
		    break;
		case 3:kitbot.giveKit(kitstand.giveKit(2));
		    break;
		case 4:
                    //KAMKit k = kitbot.dropKit();
                    //System.out.println(k);
                    kitstand.takeKit(kitbot.dropKit());
		    break;
		case 5:kitstand.takeKit(kitbot.dropKit());
		    break;
		case 6:kitstand.takeKit(kitbot.dropKit());
		    break;
		case 7://how to drop kit onto placeholder?
                    if(delivery.inEmptyPostion()){
                    delivery.takeKit(kitbot.dropKit());
                    }
		    break;
		default:kitbot.performOrder();
		    break;
                }
            }
	    if(!kitter.moving())
		{
                Integer order = kitter.getOrder();
                switch (order) {
		    //0-7 - pick up part nest 0-7
		    //8 - drop parts onto kit
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		    kitter.addPart(nest.get(order).getPart());
                    kitter.popOrder();
                    break;
		case 8:kitstand.getKitPositions().get(1).getKit().addPart(kitter.removePart());
                kitter.popOrder();
                    break;
                default:kitter.performOrder();
		}

		}
            kitbot.update();
            kitter.update();
            myPanel.repaint();
            
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D.Double backgroundRectangle = new Rectangle2D.Double(0, 0, 700, 700);
        g2.setColor(Color.GRAY.darker().darker());//dark dark green background
        g2.fill(backgroundRectangle);
        paintNests(this, g2);
        kitstand.getKitStand().paintIcon(this, g2, kitstand.getX(), kitstand.getY());
        for (int i = 0; i < 3; i++) {
            
            if (kitstand.getKitPositions().get(i).isFilled()) {
               
                //kitstand.getKitPositions().get(i).getKit().getImage().paintIcon(this, g2, kitstand.getKitPositions().get(i).getX(), kitstand.getKitPositions().get(i).getY());
                kitstand.getKitPositions().get(i).getKit().paintMe(this, g2, kitstand.getKitPositions().get(i).getX(), kitstand.getKitPositions().get(i).getY());
                //if(kitstand.getKitPositions().get(i).getKit().getParts().size()>0){
                    
                //    for(int j=0;j<kitstand.getKitPositions().get(i).getKit().getParts().size();i++){
                      
                //       kitstand.getKitPositions().get(i).getKit().getParts().get(j).getGUIPart().getImage().paintIcon(this, g2, kitstand.getKitPositions().get(i).getKit().getParts().get(j).getGUIPart().getX(), kitstand.getKitPositions().get(i).getKit().getParts().get(j).getGUIPart().getX()); 
                //    }
                //}
            }
        }
        for (int i = 0; i < delivery.getNumEmptyKits(); i++) {
            delivery.getPlaceholder().get(i).getPlaceholder().paintIcon(this, g2, delivery.getPlaceholder().get(i).getX(), delivery.getPlaceholder().get(i).getY());
            if (delivery.getPlaceholder().get(i).isShow()) {
                //delivery.getPlaceholder().get(i).getKit().getImage().paintIcon(this, g2, delivery.getPlaceholder().get(i).getX() + 10, delivery.getPlaceholder().get(i).getY() + 20);
                delivery.getPlaceholder().get(i).getKit().paintMe(this, g2, delivery.getPlaceholder().get(i).getX() + 10, delivery.getPlaceholder().get(i).getY() + 20);
            }
        }

        //System.out.println(kitbot);
//        if (kitbot.hasKit()) {
//            kitbot.getKit().getImage().paintIcon(this, g2, kitbot.getCoordinate().getX(), kitbot.getCoordinate().getY());
//        }
//        kitbot.getImage().paintIcon(this, g2, kitbot.getCoordinate().getX(), kitbot.getCoordinate().getY());
//        
        kitbot.paintMe(this, g2);
        
        if (camera.isVisible()) {
            camera.getCamera().paintIcon(this, g2, camera.getX(), camera.getY());

        }
        if (camera.isVisible() && cameraCounter == 20) {
            camera.setVisible(false);
            cameraCounter = 0;
        }
//        kitter.getImage().paintIcon(this, g2, kitter.getCoordinate().getX(), kitter.getCoordinate().getY());
//	LinkedList<Part> kitterparts= kitter.getPart();
//	for(int i = 0; i != kitterparts.size(); i++)
//	    {
//		kitterparts.get(i).getGUIPart().getImage().paintIcon(this, g2, kitterparts.get(i).getGUIPart().getX(), kitterparts.get(i).getGUIPart().getY());
//	    }
        kitter.paintMe(this, g2);
        
        for(int j=0;j<this.nest.size();j++){
        for(int i=0;i<this.nest.get(j).getParts().size();i++){
            //System.out.println(this.nest.get(0).getParts().get(i).getGUIPart());
            this.nest.get(j).getParts().get(i).getGUIPart().getImage().paintIcon(this, g2, nest.get(j).getParts().get(i).getGUIPart().getX(), nest.get(j).getParts().get(i).getGUIPart().getY());
        }
        }
        
    }

    public void paintNests(JPanel j, Graphics2D g) {
        for (int i = 1; i <= 8; i++) {
            nest.get(i - 1).getNest().paintIcon(j, g, nest.get(i - 1).getX(), nest.get(i - 1).getY());
        }
    }

    public void actionPerformed(ActionEvent ae) {
    }
}
