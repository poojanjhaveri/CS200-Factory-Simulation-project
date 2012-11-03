/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.kitAssemblyManager;


/**
 *
 * @author Deepa
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Deepa
 */
public class KAMGraphicPanel extends JPanel implements ActionListener {
    public static final int ROBOT_INITIAL_X=0;
    public static final int ROBOT_INITIAL_Y=0;
    public static final int KITX=0;
    public static final int KIT0Y=0;
    public static final int KIT1Y=0;
    public static final int KIT2Y=0;
    public static final int CONVEYERX=0;
    public static final int CONVEYERY=0;
    
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
    //public GUIKitRobot kitbot;///<declares an object that keeps track of the kit robot animation and graphics
    
    public KitStand kitstand;///<declares an object that keeps track of what is happening with the kit stand
    public KitDeliveryStation delivery;///<declares an object that keeps track of the delivery station
    
    ArrayList<KAMNest> nest;
    ArrayList<KAMKit> kit;
    
    int emptyKits;
    
    public KAMGraphicPanel(){
       kitstand=new KitStand();
       kitstand.setX(275);
       kitstand.setY(150);
       
       //THIS NUMBER IS HARDCODED! should be from server => number of kits that should be made
       emptyKits=5;
       delivery=new KitDeliveryStation(emptyKits);
        
        kit = new ArrayList<KAMKit>();
        
        for(int i=1;i<=3;i++){
            kit.add(new KAMKit(i));
        }
        
        nest=new ArrayList<KAMNest>();
        
        for(int i=1;i<=8;i++){
            nest.add(new KAMNest(i));
            nest.get(i-1).setX(650);
        }
        
        int yNum=75/2;
        for(int i=0;i<8;i++){
        nest.get(i).setY(yNum+i*75);
        }
    }
    
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        Rectangle2D.Double backgroundRectangle = new Rectangle2D.Double( 0, 0, 700,700 );
        g2.setColor( Color.GRAY.darker().darker() );//dark dark green background
	g2.fill( backgroundRectangle );
        paintNests(this,g2);
        kitstand.getKitStand().paintIcon(this, g2, kitstand.getX(), kitstand.getY());
        
    }
    
    public void paintNests(JPanel j, Graphics2D g){
        for(int i=1;i<=8;i++){
            nest.get(i-1).getNest().paintIcon(j, g, nest.get(i-1).getX(), nest.get(i-1).getY());
        }
    }
    
    public static void main(String[] args){
        JFrame frame=new JFrame();
        //tester lines
        JPanel TEST=new JPanel(new GridLayout(1,2));
        
        KAMGraphicPanel graphicPanel=new KAMGraphicPanel();
        JPanel tester=graphicPanel.TestPanel();
        
        TEST.add(graphicPanel);
        TEST.add(tester);
        
        int x=700;
        frame.setSize(700+x,700);
        
        graphicPanel.setVisible(true);
        
        frame.add(TEST);
        //change TEST to just graphicPanel (above)
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //tester variables
    Button partRobot;
    Button kitRobotKitStand;
    Button kitRobotEmpty;
    Button kitRobotFull;
    Button cameraNest;
    Button cameraKitStand;
    
    public void actionPerformed(ActionEvent ae) {
        
    }
    
    
    public JPanel TestPanel(){
        JPanel tester=new JPanel();
        tester.setLayout(new BoxLayout(tester,BoxLayout.Y_AXIS));
        partRobot=new Button("Move Part Robot (Nest -> Kit Stand)");
        tester.add(partRobot);
        kitRobotKitStand=new Button("Move Kit Robot (Full Kit -> Camera Inspected)");
        tester.add(kitRobotKitStand);
        kitRobotEmpty=new Button("Move Kit Robot (Empty Kit -> KitStand)");
        tester.add(kitRobotEmpty);
        kitRobotFull=new Button("Move Kit Robot (Full Kit -> Conveyor)");
        tester.add(kitRobotFull);
        cameraNest=new Button("Take Picture of Nest");
        tester.add(cameraNest);
        cameraKitStand=new Button("Take Picture of Kit");
        tester.add(cameraKitStand);
        
        
        return tester;
}
}

