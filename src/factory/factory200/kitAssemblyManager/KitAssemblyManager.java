package factory.factory200.kitAssemblyManager;

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
public class KitAssemblyManager extends Manager implements ActionListener {

    KAMGraphicPanel graphics;
    //private KitAssemblyManagerDeliveryStation kamdelivery;///<keeps track of all of the objects listed above and paints the objects according to a timer
    //private KitAssemblyManagerGUIPanel gui;///<keeps track of the GUI components and allows the manager to pick which components will be broken
    /**
     * changes the panel based on what the user clicks
     */
    public GUIPartRobot getPartsRobot()
    {
        return graphics.kitter;
    }
    public GUIKitRobot getKitRobot(){
	return graphics.kitbot;
    }
    public KitDeliveryStation getDeliveryStation(){
        return graphics.delivery;
    }
    public Boolean getStationRun(){
        return graphics.stationRun;
    }
    public KitStand getKitStand(){
        return graphics.kitstand;
    }
    public void KAMdropOffFullKit(){
          this.graphics.deliveryStation=false;
          this.graphics.stationRun=true;
          this.graphics.kitbot.dropOffFullKit();
    }
    //input needs to be nest 0-7
    public void pickUpPart(int nest){
        this.graphics.kitter.moveToNest(nest);
        this.graphics.kitter.pickPartCommand(nest);
    }
    public void dropOffPart(){
        this.graphics.kitter.dropOffParts();
    }

    public void flashKitCamera()
    {
                this.graphics.camera.setX(KAMGraphicPanel.KITX);
                //System.out.println(this.graphics.nest.get(0).getX());
                //System.out.println(this.graphics.camera.getX());
                this.graphics.camera.setY(KAMGraphicPanel.KIT2Y);
                //System.out.println(this.graphics.nest.get(0).getY());
                //System.out.println(this.graphics.camera.getY());
                this.graphics.camera.setVisible(true);
                this.graphics.repaint();

    }
    public void flashNestCamera(Integer i)
    {
                this.graphics.camera.setX(this.graphics.nest.get(i).getX());
                this.graphics.camera.setY(this.graphics.nest.get(i).getY());
                this.graphics.camera.setVisible(true);
                this.graphics.repaint();     
    }
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==cameraKitStand){
	    this.flashKitCamera();
        }
        
          if (ae.getSource() == cameraNest) {
            String choice = JOptionPane.showInputDialog("Please enter the nest number: ");
            Integer nest = Integer.parseInt(choice);
	    this.flashNestCamera(nest);
            //System.out.println(nest);
        }
        if(ae.getSource()==moveKit){
            this.graphics.kitbot.moveEmptyKitToActive();
        }  
        if(ae.getSource()==partRobot){
            //this.graphics.kitter.cheat();
            String choice = JOptionPane.showInputDialog("Please enter the nest number: ");
            Integer nest = Integer.parseInt(choice);
            this.graphics.kitter.moveToNest(nest);
            this.graphics.kitter.pickPartCommand(nest);
        }
        if(ae.getSource()==movePartRobotBack){
            this.graphics.kitter.dropOffParts();
        }
        if(ae.getSource()==kitRobotKitStand){
            this.graphics.kitbot.moveActiveKitToInspection();
        }
        
        if(ae.getSource()==kitRobotFull){
            this.graphics.deliveryStation=false;
            this.graphics.stationRun=true;
            this.graphics.kitbot.dropOffFullKit();
        }
          
        if (ae.getSource() == kitRobotEmpty) {
            //System.out.println("GOGOGO");
            //this.graphics.deliveryStation=false;
            this.graphics.kitbot.pickUpEmptyKit();
            //this.graphics.kitstand.getKitPositions().get(0).setFilled(true);
            //after robot goes back to kit stand
            
            //this.graphics.timer.start();
        }
    }

    /**
     * declares objects within the class and sets the states of each of the
     * objects
     */
    public KitAssemblyManager() {
        this.graphics= new KAMGraphicPanel();
        //tester lines
        this.setLayout(new GridLayout(1, 2));
        this.graphics = new KAMGraphicPanel();
        
        this.add(graphics);


        int x = 700;
        this.setSize(700 + x, 700);

        this.graphics.setVisible(true);

        this.add(TestPanel());
        //change TEST to just graphicPanel (above)

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        //tester variables
    JButton partRobot;
    JButton kitRobotKitStand;
    JButton kitRobotEmpty;
    JButton kitRobotFull;
    JButton cameraNest;
    JButton cameraKitStand;
    JButton moveKit;
    JButton movePartRobotBack;
      public JPanel TestPanel() {
        JPanel tester = new JPanel();
        tester.setLayout(new BoxLayout(tester, BoxLayout.Y_AXIS));
        partRobot = new JButton("Move Part Robot (Kit Stand -> Nest)");
        partRobot.addActionListener(this);
        tester.add(partRobot);
        movePartRobotBack=new JButton("Move Part Robot (Nest->KitStand)");
        movePartRobotBack.addActionListener(this);
        tester.add(movePartRobotBack);
        moveKit = new JButton("Move Kit (Position 0->1)");
        moveKit.addActionListener(this);
        tester.add(moveKit);
        kitRobotKitStand = new JButton("Move Kit Robot (Full Kit -> Camera Inspected)");
        kitRobotKitStand.addActionListener(this);
        tester.add(kitRobotKitStand);
        kitRobotEmpty = new JButton("Move Kit Robot (Empty Kit -> KitStand)");
        kitRobotEmpty.addActionListener(this);
        tester.add(kitRobotEmpty);
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

    public void processMessage(String msg)
    {
	super.processMessage(msg);
	//todo - let me know what functions agent will call so I can process them here
    }      

      public static void main(String[] args){
       KitAssemblyManager mgr = new KitAssemblyManager();   
      }
}
