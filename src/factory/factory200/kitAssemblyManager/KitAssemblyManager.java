package factory.factory200.kitAssemblyManager;

import factory.factory200.kitAssemblyManager.GUIPartRobot;
import factory.factory200.kitAssemblyManager.KitDeliveryStation;
import factory.factory200.kitAssemblyManager.KitStand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import factory.general.Manager;
import java.awt.Button;
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
    public void actionPerformed(ActionEvent ae) {
          if (ae.getSource() == cameraNest) {
            String choice = JOptionPane.showInputDialog("Please enter the nest number: ");
            Integer nest = Integer.parseInt(choice);
            System.out.println(nest);
            if(nest==1){
                this.graphics.camera.setX(this.graphics.nest.get(0).getX());
                System.out.println(this.graphics.nest.get(0).getX());
                this.graphics.camera.setX(this.graphics.nest.get(0).getY());
                System.out.println(this.graphics.nest.get(0).getY());
                this.graphics.camera.setVisible(true);
                this.graphics.repaint();
                
            }
            else if(nest==2){
                this.graphics.camera.setX(this.graphics.nest.get(1).getX());
                this.graphics.camera.setX(this.graphics.nest.get(1).getY());
                this.graphics.camera.setVisible(true);
                this.graphics.repaint();
            }
            else if(nest==3){
                this.graphics.camera.setX(this.graphics.nest.get(2).getX());
                this.graphics.camera.setX(this.graphics.nest.get(2).getY());
                this.graphics.camera.setVisible(true);
                this.graphics.repaint();
            }
            else if(nest==4){
                this.graphics.camera.setX(this.graphics.nest.get(3).getX());
                this.graphics.camera.setX(this.graphics.nest.get(3).getY());
                this.graphics.camera.setVisible(true);
                this.graphics.repaint();
            }
            else if(nest==5){
                this.graphics.camera.setX(this.graphics.nest.get(4).getX());
                this.graphics.camera.setX(this.graphics.nest.get(4).getY());
                this.graphics.camera.setVisible(true);
                this.graphics.repaint();
            }
            else if(nest==6){
                this.graphics.camera.setX(this.graphics.nest.get(5).getX());
                this.graphics.camera.setX(this.graphics.nest.get(5).getY());
                this.graphics.camera.setVisible(true);
                this.graphics.repaint();
            }
            else if(nest==7){
                this.graphics.camera.setX(this.graphics.nest.get(6).getX());
                this.graphics.camera.setX(this.graphics.nest.get(6).getY());
                this.graphics.camera.setVisible(true);
                this.graphics.repaint();
            }
            else if(nest==8){
                this.graphics.camera.setX(this.graphics.nest.get(7).getX());
                this.graphics.camera.setX(this.graphics.nest.get(7).getY());
                this.graphics.camera.setVisible(true);
                this.graphics.repaint();
            }
        }
        if (ae.getSource() == kitRobotEmpty) {
            //System.out.println("GOGOGO");
            this.graphics.deliveryStation=false;
            this.graphics.kitbot.pickUpEmptyKit(1);
            
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
      public JPanel TestPanel() {
        JPanel tester = new JPanel();
        tester.setLayout(new BoxLayout(tester, BoxLayout.Y_AXIS));
        partRobot = new JButton("Move Part Robot (Nest -> Kit Stand)");
        partRobot.addActionListener(this);
        tester.add(partRobot);
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
      
      public static void main(String[] args){
       KitAssemblyManager mgr = new KitAssemblyManager();   
      }
      
}
