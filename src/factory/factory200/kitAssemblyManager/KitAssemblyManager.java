package factory.factory200.kitAssemblyManager;

import factory.general.BlueprintParts;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import factory.general.GUIPart;
import factory.general.Manager;
import factory.general.Message;
import factory.general.Part;
import java.util.ArrayList;

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
public class KitAssemblyManager extends Manager implements ActionListener {

    /**
     * To avoid unexpected InvalidClassExceptions during deserialization we
     * explicitly specify a UID, instead of relying on the compiler to generate
     * one
     */
    private static final long serialVersionUID = 4L;
    KAMGraphicPanel graphics;
    boolean test = false;
    JTabbedPane tabbedPane;
    GUINonNormKAM nonnorm;
    //private KitAssemblyManagerDeliveryStation kamdelivery;///<keeps track of all of the objects listed above and paints the objects according to a timer
    //private KitAssemblyManagerGUIPanel gui;///<keeps track of the GUI components and allows the manager to pick which components will be broken

    /**
     * changes the panel based on what the user clicks
     */
    public void processMessage(String msg) {
        super.processMessage(msg);
        if (msg == null) {
            return;
        }

        if (msg.contains(Message.KAM_DROP_OFF_FULL_KIT)) {
            this.graphics.deliveryStation = false;
            this.graphics.stationRun = true;
            this.graphics.kitbot.dropOffFullKit();
            //this.graphics.deliveryStation = true;
            //this.graphics.stationRun = false;
            //needs release DONE
        } else if (msg.contains(Message.KAM_MOVE_ACTIVE_KIT_TO_INSPECTION)) {
            this.graphics.kitbot.moveActiveKitToInspection();
            //needs to release DONE
        } else if (msg.contains(Message.KAM_PICK_UP_EMPTY_KIT_TO_ACTIVE)) {
            this.graphics.kitbot.pickUpEmptyKitToActive();
            //needs to release DONE
        } else if (msg.contains(Message.KAM_PICK_UP_EMPTY_KIT)) {
            this.graphics.deliveryStation = true;
            this.graphics.stationRun = true;
            this.graphics.kitbot.pickUpEmptyKit();
            //needs to release DONE
        } else if (msg.contains(Message.KAM_MOVE_EMPTY_KIT_TO_ACTIVE)) {
            this.graphics.kitbot.moveEmptyKitToActive();
            //needs to release DONE
        } else if (msg.contains(Message.KAM_FLASH_KIT_CAMERA)) {
            this.flashKitCamera();
        } else if (msg.contains(Message.KAM_FLASH_NEST_CAMERA)) {
            this.flashNestCamera(Integer.parseInt(this.grabParameter(msg)));
        } else if (msg.contains(Message.KAM_PARTS_MOVE_TO_NEST)) {
            this.getPartsRobot().moveToNestCommand(Integer.parseInt(this.grabParameter(msg)));
            //needs to release DONE
        } else if (msg.contains(Message.KAM_PARTS_PICK_PART)) {
            this.getPartsRobot().pickPartCommand(Integer.parseInt(this.grabParameter(msg)));
            super.sendToServer(Integer.parseInt(this.grabParameter(msg)) + "PART_TAKE_BY_PARTROBOT");
        } else if (msg.contains(Message.KAM_PARTS_DROP_OFF_PARTS)) {
            this.getPartsRobot().dropOffParts(Integer.parseInt(this.grabParameter(msg)));
        } else if (msg.contains(Message.KAM_FLASH_KIT_CAMERA)) {
            this.flashKitCamera();
        } else if (msg.contains(Message.KAM_FLASH_NEST_CAMERA)) {
            this.flashNestCamera(Integer.parseInt(this.grabParameter(msg)));
        } else if (msg.contains(Message.KAM_MOVE_FROM_0_TO_2)) {
            this.moveFrom0To2();
            //needs to release DONE
        } else if (msg.contains(Message.KAM_ADD_KIT)) {
            this.doAddEmptyKit();
        } else if (msg.contains(Message.LM_ADD_PART)) {
            int nest = msg.charAt(4) - 48;
            int partType = msg.charAt(6) - 48;
            this.doSetParts(nest, partType);
        } else if (msg.contains(Message.KAM_ACTION_STABILIZE_NEST)) {
            Integer i = Integer.parseInt(this.grabParameter(msg));

            this.graphics.toggleUnstable(i);
            this.nonnorm.toggleStabilizeColor(i);
            this.nonnorm.removeAll();
            this.nonnorm.preparemainpanel();
        } else if (msg.contains(Message.KAM_ACTION_UNPILE_NEST)) {
            Integer i = Integer.parseInt(this.grabParameter(msg));
            this.graphics.togglePiled(i);
            this.nonnorm.togglePiledColor(i);
            this.nonnorm.removeAll();
            this.nonnorm.preparemainpanel();
        } else if (msg.contains(Message.NEST_DOWN)) {
            this.nestDown(Integer.parseInt(this.grabParameter(msg)));
        } else if (msg.contains(Message.NEST_UP)) {
            this.nestUp(Integer.parseInt(this.grabParameter(msg)));

        } else if (msg.contains(Message.KAM_MOVE_FROM_2_TO_0)) {
            this.moveFrom2To0();
        } else if (msg.contains(Message.KAM_ACTION_ROBOT_IN_WAY)) {
            graphics.beginRobotInWayAction();
        } else if (msg.contains(Message.KAM_CHANGE_CONFIGURATION)) {
            BlueprintParts temp = new BlueprintParts();
            temp.recreate(this.grabParameter(msg));
System.out.println("DEBUGGING KAM_CHANGE_CONFIGURATION!!!!");
temp.debug();
            this.changeConfig(temp.getParts());
        }

        //todo - let me know what functions agent will call so I can process them here
    }
    //THIS METHOD MOVES KIT FROM POSITION 2 TO 0!!

    public void changeConfig(ArrayList<Part> parts) {
        ArrayList<Part> gah = new ArrayList<Part>();
//        for(int i=0;i<parts.size();i++){
//        ImageIcon tempPic = new ImageIcon();
//        if (parts.get(i).type == 0) {
//            tempPic = new ImageIcon("pics/parts/part1.png");
//        } else if (parts.get(i).type == 1) {
//            tempPic = new ImageIcon("pics/parts/part2.png");
//        } else if (parts.get(i).type == 2) {
//            tempPic = new ImageIcon("pics/parts/part3.png");
//        } else if (parts.get(i).type == 3) {
//            tempPic = new ImageIcon("pics/parts/part4.png");
//        } else if (parts.get(i).type == 4) {
//            tempPic = new ImageIcon("pics/parts/part5.png");
//        } else if (parts.get(i).type == 5) {
//            tempPic = new ImageIcon("pics/parts/part6.png");
//        } else if (parts.get(i).type == 6) {
//            tempPic = new ImageIcon("pics/parts/part7.png");
//        } else if (parts.get(i).type == 7) {
//            tempPic = new ImageIcon("pics/parts/part8.png");
//        } else if (parts.get(i).type == 8) { //ADD IN BAD PART LOGIC!!!!
//            tempPic = new ImageIcon("pics/parts/badpart.png"); //ADD IN MORE TO THIS FUNCTION? is signal sent to parts agent?
//        }
//            GUIPart temp=new GUIPart(this.graphics.kitstand.getKitPositions().get(2).getKit().getX(),this.graphics.kitstand.getKitPositions().get(2).getKit().getY(),0.0,tempPic);
//            Part tempPart = new Part(null, null);
//            tempPart.setGUIPart(temp);
//            //parts.get(i).setGUIPart(temp);
//            gah.add(tempPart);
//        }
        //while(this.graphics.kitstand.getKitPositions().get(2).getKit().getParts().size() != 0){
        //this.graphics.kitstand.getKitPositions().get(2).getKit().getParts().remove(0);
        //}
        for (Part p : parts) {
            ImageIcon tempPic = null;
            switch (p.type) {
                case 0:
                    tempPic = new ImageIcon("pics/parts/part1.png");
                    break;
                case 1:
                    tempPic = new ImageIcon("pics/parts/part2.png");
                    break;
                case 2:
                    tempPic = new ImageIcon("pics/parts/part3.png");
                    break;
                case 3:
                    tempPic = new ImageIcon("pics/parts/part4.png");
                    break;
                case 4:
                    tempPic = new ImageIcon("pics/parts/part5.png");
                    break;
                case 5:
                    tempPic = new ImageIcon("pics/parts/part6.png");
                    break;
                case 6:
                    tempPic = new ImageIcon("pics/parts/part7.png");
                    break;
                case 7:
                    tempPic = new ImageIcon("pics/parts/part8.png");
                    break;
            }
            if(tempPic == null)
                System.out.print("We kill alex");
            GUIPart temp = new GUIPart(this.graphics.kitstand.getKitPositions().get(2).getKit().getX(), this.graphics.kitstand.getKitPositions().get(2).getKit().getY(), 0.0, tempPic);
            Part lolpart = p.clone();
            lolpart.setGUIPart(temp);
            //parts.get(i).setGUIPart(temp);
            gah.add(lolpart);
        }
System.out.println("SIZE OF parts: " + parts.size());
System.out.println("SIZE OF gah: " + gah.size());
        //for(int i=0;i<this.graphics.kitstand.getKitPositions().get(2).getKit().getParts().size();i++){
        //this.graphics.kitstand.getKitPositions().get(2).getKit().getParts().remove(0);
        //}
        for(int i=0;i<gah.size();i++){
            System.out.println("DEEPA TESTING GFIEHDOSKLDS"+gah.get(i).type);
        }
        //for(int i=0;i<gah.size();i++){
        this.graphics.kitstand.getKitPositions().get(2).getKit().setParts(gah);
        //}
        this.graphics.kitstand.getKitPositions().get(2).getKit().updateParts();
//       this.graphics.repaint();
    }

    public void moveFrom2To0() {
        this.graphics.kitbot.moveFrom2To0();
    }

    public void nestUp(int n) {
        this.graphics.nest.get(n).nestUp();
    }

    public void nestDown(int n) {
        this.graphics.nest.get(n).nestDown();
    }

    public void doSetParts(int n, int partType) {
        //create part based on part type given
        Part temp = new Part(null, null);
        ImageIcon tempPic = new ImageIcon();
        if (partType == 0) {
            tempPic = new ImageIcon("pics/parts/part1.png");
        } else if (partType == 1) {
            tempPic = new ImageIcon("pics/parts/part2.png");
        } else if (partType == 2) {
            tempPic = new ImageIcon("pics/parts/part3.png");
        } else if (partType == 3) {
            tempPic = new ImageIcon("pics/parts/part4.png");
        } else if (partType == 4) {
            tempPic = new ImageIcon("pics/parts/part5.png");
        } else if (partType == 5) {
            tempPic = new ImageIcon("pics/parts/part6.png");
        } else if (partType == 6) {
            tempPic = new ImageIcon("pics/parts/part7.png");
        } else if (partType == 7) {
            tempPic = new ImageIcon("pics/parts/part8.png");
        } else if (partType == 8) { //ADD IN BAD PART LOGIC!!!!
            tempPic = new ImageIcon("pics/parts/badpart.png"); //ADD IN MORE TO THIS FUNCTION? is signal sent to parts agent?
        }
        for (int i = 0; i < this.graphics.nest.size(); i++) {
            if (n == i) {
                int k = this.graphics.nest.get(i).getParts().size();
                //System.out.println("size: "+k);
                if (k < 4) {
                    GUIPart GUItemp = new GUIPart(this.graphics.nest.get(i).getX(), this.graphics.nest.get(i).getY() + 18 * k, 0.0, tempPic);
                    temp.setGUIPart(GUItemp);
                    this.graphics.nest.get(i).getParts().add(temp);
                } else if (k >= 4 && k <= 8) {
                    GUIPart GUItemp = new GUIPart(this.graphics.nest.get(i).getX() + 20, this.graphics.nest.get(i).getY() + 18 * (k - 4), 0.0, tempPic);
                    temp.setGUIPart(GUItemp);
                    this.graphics.nest.get(i).getParts().add(temp);
                }
                //this.graphics.nest.get(i).getParts().add(temp);
                break;
            }
        }

        //temp.setGUIPart(GUItemp);
        //parts1.add(temp);
        //this.graphics.nest.get(nest).getParts().add(CHANGE);
    }

    public void doAddEmptyKit() {
        this.graphics.delivery.addKit();

    }

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
        for (int i = 0; i != 8; i++) {
            if (ae.getSource() == this.nonnorm.getPilingButton(i)) {
                if (!this.nonnorm.piledColor.get(i)) {
                    this.sendToServer(Message.KAM_NEST_PILED + ":" + i);
                }
                this.graphics.togglePiled(i);
                this.nonnorm.togglePiledColor(i);
                this.nonnorm.removeAll();
                this.nonnorm.preparemainpanel();
            } else if (ae.getSource() == this.nonnorm.getStabilityButton(i)) {
                if (!this.nonnorm.unstableColor.get(i)) {
                    this.sendToServer(Message.KAM_NEST_UNSTABLE + ":" + i);
                }
                this.graphics.toggleUnstable(i);
                this.nonnorm.toggleStabilizeColor(i);
                this.nonnorm.removeAll();
                this.nonnorm.preparemainpanel();
            }
        }
        if (ae.getSource() == this.nonnorm.getRobotInWayButton()) {
            this.sendToServer(Message.KAM_ACTION_ROBOT_IN_WAY);

        }
        if (ae.getSource() == this.nonnorm.getDropPartButton()) {


            Part p = this.graphics.kitter.dropPartOnGround();
            if (p != null) {
                p.debug();
                System.out.println(p.serialize());
                this.sendToServer(Message.KAM_BAD_KIT + ":" + p.serialize());
            }
        }
        if (ae.getSource() == this.nonnorm.getEarlyFlashButton()) {
            this.sendToServer(Message.EARLY_CAMERA_FLASH);
        }

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
            this.graphics.kitter.moveToNestCommand(nest);
            this.graphics.kitter.pickPartCommand(nest);
        }
        if (ae.getSource() == movePartRobotBack) {
            String choice = JOptionPane.showInputDialog("Please enter the kit number (0 or 1): ");
            Integer kit = Integer.parseInt(choice);
            this.graphics.kitter.dropOffParts(kit);
        }
        if (ae.getSource() == nestUp) {
            String choice = JOptionPane.showInputDialog("Please enter the nest number (0-7): ");
            Integer kit = Integer.parseInt(choice);
            nestUp(kit);
        }
        if (ae.getSource() == nestDown) {
            String choice = JOptionPane.showInputDialog("Please enter the nest number (0-7): ");
            Integer kit = Integer.parseInt(choice);
            nestDown(kit);
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
        if (ae.getSource() == addKit) {
            this.graphics.delivery.addKit();
        }
        if (ae.getSource() == moveBack) {
            this.moveFrom2To0();
        }
        if (ae.getSource() == addPart) {
            String choice = JOptionPane.showInputDialog("Please enter the nest number (0-7): ");
            Integer n = Integer.parseInt(choice);
            String c = JOptionPane.showInputDialog("Please enter the part type (0-7): ");
            Integer partType = Integer.parseInt(c);
            this.doSetParts(n, partType);
        }
    }

    /**
     * declares objects within the class and sets the states of each of the
     * objects
     */
    public KitAssemblyManager() {


        this.graphics = new KAMGraphicPanel(this);
        //tester lines
        this.setLayout(new GridLayout(1, 2));
        this.graphics = new KAMGraphicPanel(this);

        //@author : Poojan

        this.tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Animation", this.graphics);
        this.nonnorm = new GUINonNormKAM(this);

        tabbedPane.addTab("Non-normative", nonnorm);
        this.add(tabbedPane);

        //   this.add(graphics);



        int x = 0;
        if (test == true) {
            x = 550;
            this.add(TestPanel());
        } else if (test == false) {
            x = 0;
        }
        this.setSize(550 + x, 700);

        this.graphics.setVisible(true);

        //this.add(TestPanel());
        //change TEST to just graphicPanel (above)

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.sendToServer(Message.IDENTIFY_KITASSEMBLYMANAGER); //won't this only be called once because it is in the constructor; shouldn't this be in the timer class?
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
    JButton addKit;
    JButton addPart;
    JButton nestUp;
    JButton nestDown;
    JButton moveBack;

    public JPanel TestPanel() {
        JPanel tester = new JPanel();
        tester.setLayout(new BoxLayout(tester, BoxLayout.Y_AXIS));
        moveBack = new JButton("MOVE BACK!!");
        moveBack.addActionListener(this);
        tester.add(moveBack);
        nestUp = new JButton("NEST UP");
        nestUp.addActionListener(this);
        tester.add(nestUp);
        nestDown = new JButton("NEST DOWN");
        nestDown.addActionListener(this);
        tester.add(nestDown);
        addPart = new JButton("ADD PART");
        addPart.addActionListener(this);
        tester.add(addPart);
        addKit = new JButton("ADD KIT");
        addKit.addActionListener(this);
        tester.add(addKit);
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

    public static void main(String[] args) {
        KitAssemblyManager mgr = new KitAssemblyManager();


    }
}

/*
 * TO SEND ARRAYLIST
 ArrayList = array;
 BlueprintParts p = new BlueprintParts(array);
 this.sendToServer(Message.KAM_BAD_KIT+":"+p.serialize());
 */
