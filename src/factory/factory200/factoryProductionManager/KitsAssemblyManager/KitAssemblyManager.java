package factory.factory200.factoryProductionManager.KitsAssemblyManager;

import factory.factory200.factoryProductionManager.GraphicsPanel;
import factory.factory200.kitAssemblyManager.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import factory.general.Manager;
import java.awt.Button;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class keeps track of everything that will be visible to the Kit Assembly
 * Manager. This includes the graphics panel and the GUI panel. The graphics
 * panel will show the kit stand, the camera, the kit delivery station, the
 * parts robot, the kit robot, and the completed/empty kits. The GUI panel will
 * allow the Kit Assembly Manager to break any of the components that are
 * contained in this window. <img url="addthis.jpg" /> <img url="addthis.jpg" />
 * <img src="../img/image11.png" /> <img src="../img/image16.png" />
 *
 * @brief keeps track of everything that will be visible to the Kit Assembly
 * Manager
 * @author Deepa Borkar, YiWei Roy Zheng
 */
//need to extend manager
public class KitAssemblyManager extends JPanel implements ActionListener {

    KAMGraphicPanel graphics;
    //private KitAssemblyManagerDeliveryStation kamdelivery;///<keeps track of all of the objects listed above and paints the objects according to a timer
    //private KitAssemblyManagerGUIPanel gui;///<keeps track of the GUI components and allows the manager to pick which components will be broken

    /**
     * changes the panel based on what the user clicks
     */
    /*public void processMessage(String msg) {
        super.processMessage(msg);

        if (msg.contains(Message.KAM_DROP_OFF_FULL_KIT)) {
            this.graphics.kitbot.dropOffFullKit();
        } else if (msg.contains(Message.KAM_MOVE_ACTIVE_KIT_TO_INSPECTION)) {
            this.graphics.kitbot.moveActiveKitToInspection();
        } else if (msg.contains(Message.KAM_PICK_UP_EMPTY_KIT_TO_ACTIVE)) {
            this.graphics.kitbot.pickUpEmptyKitToActive();
        } else if (msg.contains(Message.KAM_PICK_UP_EMPTY_KIT)) {
            this.graphics.kitbot.pickUpEmptyKit();
        } else if (msg.contains(Message.KAM_MOVE_EMPTY_KIT_TO_ACTIVE)) {
            this.graphics.kitbot.moveEmptyKitToActive();

        } else if (msg.contains(Message.KAM_FLASH_KIT_CAMERA)) {
            this.flashKitCamera();
        } else if (msg.contains(Message.KAM_FLASH_NEST_CAMERA)) {
            this.flashNestCamera(Integer.parseInt(this.grabParameter(msg)));
        } else if (msg.contains(Message.KAM_PARTS_MOVE_TO_NEST)) {
            this.getPartsRobot().moveToNestCommand(Integer.parseInt(this.grabParameter(msg)));
        } else if (msg.contains(Message.KAM_PARTS_PICK_PART)) {
            this.getPartsRobot().pickPartCommand(Integer.parseInt(this.grabParameter(msg)));
        } else if (msg.contains(Message.KAM_PARTS_DROP_OFF_PARTS)) {
            this.getPartsRobot().dropOffParts(Integer.parseInt(this.grabParameter(msg)));
        } else if (msg.contains(Message.KAM_FLASH_KIT_CAMERA)) {
            this.flashKitCamera();
        } else if (msg.contains(Message.KAM_FLASH_NEST_CAMERA)) {
            this.flashNestCamera(Integer.parseInt(this.grabParameter(msg)));
        } else if (msg.contains(Message.KAM_MOVE_FROM_0_TO_2)) {
            this.moveFrom0To2();
        }

        //todo - let me know what functions agent will call so I can process them here
    }*/

    public GUIPartRobot getPartsRobot() {
        return graphics.kitter;
    }

    public GUIKitRobot getKitRobot() {
        return graphics.kitbot;
    }

    public KitDeliveryStation getDeliveryStation() {
        return graphics.delivery;
    }

    public Boolean getStationRun() {
        return graphics.stationRun;
    }

    public KitStand getKitStand() {
        return graphics.kitstand;
    }

    public void KAMdropOffFullKit() {
        this.graphics.deliveryStation = false;
        this.graphics.stationRun = true;
        this.graphics.kitbot.dropOffFullKit();
    }
    //input needs to be nest 0-7

    public void pickUpPart(int nest) {
        this.graphics.kitter.moveToNest(nest);
        this.graphics.kitter.pickPartCommand(nest);
    }

    public void dropOffPart(Integer i) {
        this.graphics.kitter.dropOffParts(i);
    }

    public void flashKitCamera() {
        this.graphics.camera.setX(KAMGraphicPanel.KITX);
        this.graphics.camera.setY(KAMGraphicPanel.KIT2Y);
        this.graphics.camera.setVisible(true);
        this.graphics.repaint();

    }

    public void flashNestCamera(Integer i) {
        this.graphics.camera.setX(this.graphics.nest.get(i).getX());
        this.graphics.camera.setY(this.graphics.nest.get(i).getY());
        this.graphics.camera.setVisible(true);
        this.graphics.repaint();
    }

    public void moveFrom0To2() {
        this.graphics.kitbot.moveFrom0To2();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cameraKitStand) {
            this.flashKitCamera();
        }

        if (ae.getSource() == cameraNest) {
            String choice = JOptionPane.showInputDialog("Please enter the nest number: ");
            Integer nest = Integer.parseInt(choice);
            this.flashNestCamera(nest);
        }
        if (ae.getSource() == moveKit) {
            this.graphics.kitbot.moveEmptyKitToActive();
        }
        if (ae.getSource() == partRobot) {
            //this.graphics.kitter.cheat();
            String choice = JOptionPane.showInputDialog("Please enter the nest number: ");
            Integer nest = Integer.parseInt(choice);
            this.graphics.kitter.moveToNest(nest);
            this.graphics.kitter.pickPartCommand(nest);
        }
        if (ae.getSource() == movePartRobotBack) {
            String choice = JOptionPane.showInputDialog("Please enter the kit number (0 or 1): ");
            Integer kit = Integer.parseInt(choice);
            this.graphics.kitter.dropOffParts(kit);
        }
        if (ae.getSource() == kitRobotKitStand) {
            this.graphics.kitbot.moveActiveKitToInspection();
        }

        if (ae.getSource() == kitRobotFull) {
            this.graphics.deliveryStation = false;
            this.graphics.stationRun = true;
            this.graphics.kitbot.dropOffFullKit();
        }

        if (ae.getSource() == kitRobotEmpty) {

            this.graphics.kitbot.pickUpEmptyKit();

        }
        if (ae.getSource() == kitRobotEmpty0) {

            this.graphics.kitbot.pickUpEmptyKitToActive();

        }
        if (ae.getSource() == moveFrom0To2) {
            this.graphics.kitbot.moveFrom0To2();
        }
    }

    /**
     * declares objects within the class and sets the states of each of the
     * objects
     */
    public KitAssemblyManager() {
        this.graphics = new KAMGraphicPanel();
        //tester lines
        this.setLayout(new GridLayout(1, 2));
        this.graphics = new KAMGraphicPanel();

        this.add(graphics);


        int x = 700;
        this.setSize(700 + x, 700);

        this.graphics.setVisible(true);

        //this.add(TestPanel());
        //change TEST to just graphicPanel (above)

        //this.setVisible(true);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //this.mcon.out(Message.IDENTIFY_KITASSEMBLYMANAGER);
    }
    //tester variables
    JButton partRobot;
    JButton kitRobotKitStand;
    JButton kitRobotEmpty;
    JButton kitRobotEmpty0;
    JButton kitRobotFull;
    JButton cameraNest;
    JButton cameraKitStand;
    JButton moveKit;
    JButton movePartRobotBack;
    JButton moveFrom0To2;

    public JPanel TestPanel() {
        JPanel tester = new JPanel();
        tester.setLayout(new BoxLayout(tester, BoxLayout.Y_AXIS));
        partRobot = new JButton("Move Part Robot (Kit Stand -> Nest)");
        partRobot.addActionListener(this);
        tester.add(partRobot);
        movePartRobotBack = new JButton("Move Part Robot (Nest->KitStand)");
        movePartRobotBack.addActionListener(this);
        tester.add(movePartRobotBack);
        kitRobotEmpty = new JButton("Move Kit Robot (Empty Kit -> KitStand (0))");
        kitRobotEmpty.addActionListener(this);
        tester.add(kitRobotEmpty);
        kitRobotEmpty0 = new JButton("Move Kit Robot (Empty Kit -> KitStand (1))");
        kitRobotEmpty0.addActionListener(this);
        tester.add(kitRobotEmpty0);
        moveKit = new JButton("Move Kit (Position 0->1)");
        moveKit.addActionListener(this);
        tester.add(moveKit);
        kitRobotKitStand = new JButton("Move Kit Robot (Full Kit -> Camera Inspected (1->2))");
        kitRobotKitStand.addActionListener(this);
        tester.add(kitRobotKitStand);
        moveFrom0To2 = new JButton("Move Kit Robot (Full Kit -> Camera Inspected (0->2))");
        moveFrom0To2.addActionListener(this);
        tester.add(moveFrom0To2);
        kitRobotFull = new JButton("Move Kit Robot (Full Kit -> Conveyor)");
        kitRobotFull.addActionListener(this);
        tester.add(kitRobotFull);
        cameraNest = new JButton("Take Picture of Nest");
        cameraNest.addActionListener(this);
        tester.add(cameraNest);
        cameraKitStand = new JButton("Take Picture of Kit");
        cameraKitStand.addActionListener(this);
        tester.add(cameraKitStand);


        return tester;
    }

    public void paint(GraphicsPanel gpanel, Graphics2D g2) {
        this.graphics.paint(gpanel, g2);

    }
}
