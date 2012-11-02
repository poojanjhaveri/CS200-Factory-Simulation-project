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
