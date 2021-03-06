/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.factoryProductionManager.KitsAssemblyManager;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Deepa
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import factory.factory200.factoryProductionManager.GraphicsPanel;
import factory.factory200.kitAssemblyManager.GUIKitRobot;
import factory.factory200.kitAssemblyManager.GUIPartRobot;
import factory.factory200.kitAssemblyManager.KAMCamera;
import factory.factory200.kitAssemblyManager.KAMNest;
import factory.factory200.kitAssemblyManager.KitDeliveryStation;
import factory.factory200.kitAssemblyManager.KitStand;

/**
 *
 * @author Deepa Borkr, YiWei Roy Zheng
 */
public class KAMGraphicPanel {

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
    public static final int EMPTY_CONVEYERY = 375;
    public static final int FULL_CONVEYERX = 25;
    public static final int FULL_CONVEYERY = 224;
    public static final Integer LANE0Y = 75 / 2 + 0 * 75 - 20;///<y-coordinate of lane 0's nest
    public static final Integer LANE1Y = 75 / 2 + 1 * 75 - 20;///<y-coordinate of lane 1's nest
    public static final Integer LANE2Y = 75 / 2 + 2 * 75 - 20;///<y-coordinate of lane 2's nest
    public static final Integer LANE3Y = 75 / 2 + 3 * 75 - 20;///<y-coordinate of lane 3's nest
    public static final Integer LANE4Y = 75 / 2 + 4 * 75 - 20;///<y-coordinate of lane 4's nest
    public static final Integer LANE5Y = 75 / 2 + 5 * 75 - 20;///<y-coordinate of lane 5's nest
    public static final Integer LANE6Y = 75 / 2 + 6 * 75 - 20;///<y-coordinate of lane 6's nest
    public static final Integer LANE7Y = 75 / 2 + 7 * 75 - 20;///<y-coordinate of lane 7's nest
    public static final Integer RAILX = (75 / 2 + 8 * 75) - 150;///<fixed x-coordinate of the rail the parts robot traverses
    public static final Integer PARTS_ROBOT_KIT0X = KAMGraphicPanel.KITX + 20;
    public static final Integer PARTS_ROBOT_KIT0Y = KAMGraphicPanel.KIT0Y + 20;
    public static final Integer PARTS_ROBOT_KIT1X = KAMGraphicPanel.KITX + 20;
    public static final Integer PARTS_ROBOT_KIT1Y = KAMGraphicPanel.KIT1Y + 20;
    public static final Integer PARTSROBOT_VELOCITYX = 2;
    public static final Integer PARTSROBOT_VELOCITYY = 2;
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
    //JPanel panel;
    boolean partsrobotinwayaction;///<causes non norm parts robot in way to begin

    KitAssemblyManager kam;
    
    public KAMGraphicPanel(JPanel panel,KitAssemblyManager rofl) {
        this.kam = rofl;
this.partsrobotinwayaction = false;
        deliveryStation = true;

        stationRun = true;

        kitbot = new GUIKitRobot();

        kitter = new GUIPartRobot();

        camera = new KAMCamera();


        kitstand = new KitStand(275, 150);


        //THIS NUMBER IS HARDCODED! should be from server => number of kits that should be made
        emptyKits = 0;
        delivery = new KitDeliveryStation(emptyKits);

        nest = new ArrayList<KAMNest>();

        for (int i = 1; i <= 8; i++) {
            nest.add(new KAMNest());
            nest.get(i - 1).setX(650 - 150);
        }

        int yNum = 75 / 2;
        for (int i = 0; i < 8; i++) {
            nest.get(i).setY(yNum + i * 75 - 20);
        }


        //need above code from server

        counter = 0;
        cameraCounter = 0;
        panel = new JPanel();
        //timer = new Timer(Global.STANDARD_TIMER_SPEED, new DeliveryTimer(panel));
        //timer.start();

    }
public Integer getFullNestNumber(){
            int num=-1;
            for(int i=0;i<this.nest.size();i++){
                if(nest.get(i).isFull()){
                    num=i;
                    break;
                }
            }
            return num;
    }
    public void timerAction() {
        if(partsrobotinwayaction)
		{
		    int nestnum = getFullNestNumber();
		    if(nestnum != -1){
		    partsrobotinwayaction = false;
		    kitter.blockNestNonNorm(nestnum);
		    }
		}
        if (camera.isVisible()) {
            cameraCounter++;
        }
        int size = delivery.getPlaceholder().size();
        //PlaceHolder temp=new PlaceHolder();
        if (deliveryStation == true && delivery.getPlaceholder().size() > 0) {
            if (stationRun == true) {
                if (delivery.getPlaceholder().get(delivery.getPlaceholder().size() - 1).getY() > -150) {
                    for (int i = 0; i < delivery.getPlaceholder().size(); i++) {
                        int yPlace = delivery.getPlaceholder().get(i).getY();
                        int number = i * 150;

                        if (yPlace == KAMGraphicPanel.EMPTY_CONVEYERY && delivery.getPlaceholder().get(i).isShow() && !(delivery.getPlaceholder().get(i).getKit().isFinished())) {
                            stationRun = false;
                            break;
                        }
                        if (counter > number) {
                            delivery.getPlaceholder().get(i).setY(yPlace - 1);
                        }
                    }
                    counter++;
                } else {
                    for (int i = 0; i < delivery.getPlaceholder().size(); i++) {
                        //System.out.println(i+": "+delivery.getPlaceholder().get(i).getKit());
                        if (delivery.getPlaceholder().get(i).getKit() == null) {
                            delivery.getPlaceholder().get(i).setY(680);
                        } else if (delivery.getPlaceholder().get(i).getKit().isFinished()) {
                            delivery.getPlaceholder().remove(i);
                        } else {
                            delivery.getPlaceholder().get(i).setY(680);
                        }
                        
                    }
                    counter = 0;
                }
            }
            if (stationRun == false) {
                for (int i = 0; i < delivery.getPlaceholder().size(); i++) {
                    int yPlace = delivery.getPlaceholder().get(i).getY();
                    delivery.getPlaceholder().get(i).setY(yPlace);
                    if (yPlace == KAMGraphicPanel.EMPTY_CONVEYERY && !(delivery.getPlaceholder().get(i).isShow())) {
                        stationRun = true;
                        break;
                    }
                }
            }
        }
        //size=delivery.getPlaceholder().size();
        if (deliveryStation == false && delivery.getPlaceholder().size() > 0) {
            if (stationRun == true) {
                if (delivery.getPlaceholder().get(delivery.getPlaceholder().size() - 1).getY() > -150) {
                    for (int i = 0; i < delivery.getPlaceholder().size(); i++) {
                        //temp=delivery.getPlaceholder().get(i);
                        int yPlace = delivery.getPlaceholder().get(i).getY();
                        int number = i * 150;

                        if (yPlace == KAMGraphicPanel.FULL_CONVEYERY && !(delivery.getPlaceholder().get(i).isShow())) {
                            stationRun = false;
                            break;
                        }
                        if (counter > number) {
                            delivery.getPlaceholder().get(i).setY(yPlace - 1);
                        }
                    }
                    counter++;
                } else {
                    for (int i = 0; i < delivery.getPlaceholder().size(); i++) {
                        //System.out.println(i+": "+delivery.getPlaceholder().get(i).getKit());
                        if (delivery.getPlaceholder().get(i).getKit() == null) {
                            delivery.getPlaceholder().get(i).setY(680);
                        } else if (delivery.getPlaceholder().get(i).getKit().isFinished()) {
                            delivery.getPlaceholder().remove(i);
                        } else {
                            delivery.getPlaceholder().get(i).setY(680);
                        }
                        
                    }
                 
                    counter = 0;
                }
            }
            if (stationRun == false) {
                for (int i = 0; i < delivery.getPlaceholder().size(); i++) {
                    int yPlace = delivery.getPlaceholder().get(i).getY();
                    delivery.getPlaceholder().get(i).setY(yPlace);
                  
                    if (yPlace == KAMGraphicPanel.FULL_CONVEYERY && (delivery.getPlaceholder().get(i).isShow())) {
                        stationRun = true;
                        deliveryStation = true;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < nest.size(); i++) {
            nest.get(i).updateParts();
        }

        if (!kitbot.moving()) {
            Integer order = kitbot.getOrder();
            switch (order) {
                case 0:
                    if (delivery.inPosition()) {
                        kitbot.giveKit(delivery.giveKit());

                        //System.out.println("Picking up kit");
                    }
                    break;
                case 1:
                    kitbot.giveKit(kitstand.giveKit(0));
                    break;
                case 2:
                    kitbot.giveKit(kitstand.giveKit(1));
                    break;
                case 3:
                    kitbot.giveKit(kitstand.giveKit(2));
                    break;
                case 4:
                    //KAMKit k = kitbot.dropKit();
                    //System.out.println(k);
                    kitstand.takeKit(kitbot.dropKit());
                    break;
                case 5:
                    kitstand.takeKit(kitbot.dropKit());
                    break;
                case 6:
                    kitstand.takeKit(kitbot.dropKit());
                    break;
                case 7://how to drop kit onto placeholder?

                    if (delivery.inEmptyPostion()) {
                        //deliveryStation=true;
                        //stationRun=true;
                        delivery.takeKit(kitbot.dropKit());
                    }
                    break;
                default:
                    kitbot.performOrder();
                    break;
            }
        }
        if (!kitter.moving()) {
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
                case 8:
                    kitstand.getKitPositions().get(1).getKit().addPart(kitter.removePart());
                    kitter.popOrder();
                    kitter.checkDefault();
                    break;
                case 9:
                    kitstand.getKitPositions().get(0).getKit().addPart(kitter.removePart());
                    kitter.popOrder();
                    kitter.checkDefault();
                    break;
                    case 21:
		case 22:
		case 23:
		case 24:
		case 25:
		case 26:
		case 27:
		case 28:
		    kam.flashNestCamera(order-21);
            this.kam.parent.flashCamera(order-21);
		    kitter.popOrder();
		    break;
		case 49://this is the pause order
		    kitter.popOrder();
		    break;
                default:
                    kitter.performOrder();
            }

        }
        kitbot.update();
        kitter.update();
        //myPanel.repaint();

    }

    public void paint(GraphicsPanel graph, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //Rectangle2D.Double backgroundRectangle = new Rectangle2D.Double(0, 0, 700, 700);
        //g2.setColor(Color.GRAY.darker().darker());//dark dark green background
        //g2.fill(backgroundRectangle);
        //backgroundImage.paintIcon(this, g2, 500, 500);
        Image img = new ImageIcon("pics/background/mainbg.png").getImage();
        g2.drawImage(img, 0, 0, null);
        //paintNests(graph, g2);
        kitstand.getKitStand().paintIcon(graph, g2, kitstand.getX(), kitstand.getY());
        for (int i = 0; i < 3; i++) {

            if (kitstand.getKitPositions().get(i).isFilled()) {

                //kitstand.getKitPositions().get(i).getKit().getImage().paintIcon(j, g2, kitstand.getKitPositions().get(i).getX(), kitstand.getKitPositions().get(i).getY());
                kitstand.getKitPositions().get(i).getKit().setX(kitstand.getKitPositions().get(i).getX());
                kitstand.getKitPositions().get(i).getKit().setY(kitstand.getKitPositions().get(i).getY());
                kitstand.getKitPositions().get(i).getKit().paintMe(graph, g2, kitstand.getKitPositions().get(i).getKit().getX(), kitstand.getKitPositions().get(i).getKit().getY());

            }
        }
        for (int i = 0; i < delivery.getPlaceholder().size(); i++) {
            if (delivery.getPlaceholder().get(i).isShow()) {
                //delivery.getPlaceholder().get(i).getKit().getImage().paintIcon(j, g2, delivery.getPlaceholder().get(i).getX() + 10, delivery.getPlaceholder().get(i).getY() + 20);
                delivery.getPlaceholder().get(i).getKit().setX(delivery.getPlaceholder().get(i).getX() + 55);
                delivery.getPlaceholder().get(i).getKit().setY(delivery.getPlaceholder().get(i).getY() + 40);
                delivery.getPlaceholder().get(i).getKit().paintMe(graph, g2, delivery.getPlaceholder().get(i).getKit().getX(), delivery.getPlaceholder().get(i).getKit().getY());
            }
            delivery.getPlaceholder().get(i).getPlaceholder().paintIcon(graph, g2, delivery.getPlaceholder().get(i).getX(), delivery.getPlaceholder().get(i).getY());

        }
        kitbot.paintMe(graph, g2);

        if (camera.isVisible()) {
            camera.getCamera().paintIcon(graph, g2, camera.getX(), camera.getY());

        }
        if (camera.isVisible() && cameraCounter >= 20) {
            camera.setVisible(false);
            cameraCounter = 0;
        }
        kitter.paintMe(graph, g2);

        //for (int k = 0; k < this.nest.size(); k++) {
        //    for (int i = 0; i < this.nest.get(k).getParts().size(); i++) {
        //        //System.out.println(j.nest.get(0).getParts().get(i).getGUIPart());
        //        this.nest.get(k).getParts().get(i).getGUIPart().getImage().paintIcon(graph, g2, nest.get(k).getParts().get(i).getGUIPart().getX(), nest.get(k).getParts().get(i).getGUIPart().getY());
        //    }
        //}

    }

    public void paintNests(JPanel j, Graphics2D g) {
        for (int i = 1; i <= 8; i++) {
            nest.get(i - 1).getNest().paintIcon(j, g, nest.get(i - 1).getX(), nest.get(i - 1).getY());
        }
    }
    public void beginRobotInWayAction()
    {
	this.partsrobotinwayaction = true;
    }
}
