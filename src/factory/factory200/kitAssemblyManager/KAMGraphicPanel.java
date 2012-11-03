/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.kitAssemblyManager;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *
 * @author Deepa
 */
public class KAMGraphicPanel extends JPanel {
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
    public GUIPartRobot kitter;///<declares an object that keeps track of the parts robot animation and graphics
    public GUIKitRobot kitbot;///<declares an object that keeps track of the kit robot animation and graphics
    public KitStand kitstand;///<declares an object that keeps track of what is happening with the kit stand
    public KitDeliveryStation delivery;///<declares an object that keeps track of the delivery station
    
    public KAMGraphicPanel(){
        
    }
    
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        Rectangle2D.Double backgroundRectangle = new Rectangle2D.Double( 0, 0, 700,700 );
        g2.setColor( Color.GRAY.darker().darker() );//dark dark green background
	g2.fill( backgroundRectangle );
    }
    
    public static void main(String[] args){
        JFrame frame=new JFrame();
        KAMGraphicPanel graphicPanel=new KAMGraphicPanel();
        frame.add(graphicPanel);
        frame.setVisible(true);
        frame.setSize(700,700);
        graphicPanel.setVisible(true);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
