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
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 *
 * @author Deepa
 */
public class KAMGraphicPanel extends JPanel implements ActionListener {

    public static final int KITROBOT_INITIAL_X = 175;
    public static final int KITROBOT_INITIAL_Y = 150;
    public static final int KITROBOT_VELOCITYX = 2;
    public static final int KITROBOT_VELOCITYY = 2;
    public static final Double KITROBOT_ROTATION_SPEED = 20.0;
    public static final int KITX = 275 + 25;
    public static final int KIT0Y = 150 + 10;
    public static final int KIT1Y = 150 + 10 + 125;
    public static final int KIT2Y = 150 + 10 + 250;
    //took into consideration kit stand positioning
    public static final int CONVEYERX = 25;
    public static final int CONVEYERY = 300;
    public static final Integer LANE0Y = 0;///<y-coordinate of lane 0's nest
    public static final Integer LANE1Y = 0;///<y-coordinate of lane 1's nest
    public static final Integer LANE2Y = 0;///<y-coordinate of lane 2's nest
    public static final Integer LANE3Y = 0;///<y-coordinate of lane 3's nest
    public static final Integer LANE4Y = 0;///<y-coordinate of lane 4's nest
    public static final Integer LANE5Y = 0;///<y-coordinate of lane 5's nest
    public static final Integer LANE6Y = 0;///<y-coordinate of lane 6's nest
    public static final Integer LANE7Y = 0;///<y-coordinate of lane 7's nest
    public static final Integer RAILX = 0;///<fixed x-coordinate of the rail the parts robot traverses
    public static final Integer PARTSROBOTINITIALX = 0;///<x coordinate for parts robot to spawn in
    public static final Integer PARTSROBOTINITIALY = 0;///<y coordinate for parts robot to spawn in
    //public GUIPartRobot kitter;///<declares an object that keeps track of the parts robot animation and graphics
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

    public KAMGraphicPanel() {
        deliveryStation = true;

        kitbot = new GUIKitRobot();

        camera = new KAMCamera();


        kitstand = new KitStand(275, 150);


        //THIS NUMBER IS HARDCODED! should be from server => number of kits that should be made
        emptyKits = 1;
        delivery = new KitDeliveryStation(emptyKits);

        nest = new ArrayList<KAMNest>();

        for (int i = 1; i <= 8; i++) {
            nest.add(new KAMNest(i));
            nest.get(i - 1).setX(650);
        }

        int yNum = 75 / 2;
        for (int i = 0; i < 8; i++) {
            nest.get(i).setY(yNum + i * 75);
        }
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
            if (delivery.getPlaceholder().get(delivery.getNumEmptyKits() - 1).getY() > -150) {
                for (int i = 0; i < delivery.getNumEmptyKits(); i++) {
                    if(delivery.getPlaceholder().get(i).getY()==300 && delivery.getPlaceholder().get(i).isShow()){
                        delivery.getPlaceholder().get(i).setY(300);
                    }
                    else{
                    int yPlace = delivery.getPlaceholder().get(i).getY();
                    int number = i * 200;
                    if (counter > number) {
                        delivery.getPlaceholder().get(i).setY(yPlace - 1);
                    }
                    }
                }
                counter++;
            } else {
                for (int i = 0; i < delivery.getNumEmptyKits(); i++) {
                    delivery.getPlaceholder().get(i).setY(680);
                    counter = 0;
                }
            }
            if (!kitbot.moving()) {
                Integer order = kitbot.getOrder();
                switch (order) {
                    case 0:
                        if (delivery.inPosition()) {
                            kitbot.giveKit(delivery.giveKit());
			    kitbot.popOrder();
                            //System.out.println("Picking up kit");
			}
                        break;
		case 1:this.kitbot.giveKit(this.kitstand.giveKit(0));
		    break;
		case 2:this.kitbot.giveKit(this.kitstand.giveKit(1));
		    break;
		case 3:this.kitbot.giveKit(this.kitstand.giveKit(2));
		    break;
		case 4:this.kitstand.takeKit(this.kitbot.dropKit());
		    break;
		case 5:this.kitstand.takeKit(this.kitbot.dropKit());
		    break;
		case 6:this.kitstand.takeKit(this.kitbot.dropKit());
		    break;
		case 7:this.kitstand.takeKit(this.kitbot.dropKit());
		    break;
		default:kitbot.performOrder();
		    break;
                }
            }
            kitbot.update();
            myPanel.repaint();
            if (deliveryStation == false) {
                for (int i = 0; i < delivery.getNumEmptyKits(); i++) {
                    if (delivery.getPlaceholder().get(i).getY() == 300) {
                        delivery.getPlaceholder().get(i).setY(300);
                        myPanel.repaint();
                    }
                }
            }
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
                kitstand.getKitPositions().get(i).getKit().getImage().paintIcon(this, g2, kitstand.getKitPositions().get(i).getX(), kitstand.getKitPositions().get(i).getY());
            }
        }
        for (int i = 0; i < delivery.getNumEmptyKits(); i++) {
            delivery.getPlaceholder().get(i).getPlaceholder().paintIcon(this, g2, delivery.getPlaceholder().get(i).getX(), delivery.getPlaceholder().get(i).getY());
            if (delivery.getPlaceholder().get(i).isShow()) {
                delivery.getPlaceholder().get(i).getKit().getImage().paintIcon(this, g2, delivery.getPlaceholder().get(i).getX() + 10, delivery.getPlaceholder().get(i).getY() + 20);
            }
        }

        //System.out.println(kitbot);
        kitbot.getImage().paintIcon(this, g2, kitbot.getCoordinate().getX(), kitbot.getCoordinate().getY());
        if (kitbot.hasKit()) {
            kitbot.getKit().getImage().paintIcon(this, g2, kitbot.getCoordinate().getX(), kitbot.getCoordinate().getY());
        }
        if (camera.isVisible()) {
            camera.getCamera().paintIcon(this, g2, camera.getX(), camera.getY());

        }
        if (camera.isVisible() && cameraCounter == 20) {
            camera.setVisible(false);
            cameraCounter = 0;
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
