/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.gantryRobotManager;

/**
 *
 * @author Poojan Jhaveri
 * @brief : GUI class for non-normative scenario associated with KIT assembly manager
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class GUINonNormGAM extends JPanel {
    
    private JPanel mainpanel;
    private JButton freeze1;
    private JButton undo1;
    
    
    public GUINonNormGAM() {
             
           preparemainpanel();
           this.add(mainpanel);
        
            
         }

 
    private void preparemainpanel()
    {
        mainpanel = new JPanel();
        JPanel basepanel = new JPanel();
        basepanel.setLayout(new BorderLayout());
        
        JLabel p1 = new JLabel("Gantry Robot Manager Non-Normative Scenario",JLabel.CENTER);
        p1.setFont(new Font("Verdana", Font.BOLD, 16));
        p1.setPreferredSize(new Dimension(500,100));
        basepanel.add(p1,BorderLayout.NORTH);
        
        
        JPanel scenario = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx=0;
        c.gridy=0;
        freeze1 = new JButton("Freeze Gantry Robot");
        freeze1.addActionListener(new freezebutton());
        freeze1.setPreferredSize(new Dimension(200,100));
        scenario.add(freeze1);
        
        c.gridx=1;
        c.gridy=0;
        undo1 = new JButton("Undo");
        undo1.setEnabled(false);
        undo1.setPreferredSize(new Dimension(200,100));
        undo1.addActionListener(new undobutton());
        scenario.add(undo1);
        
        
        basepanel.add(scenario);
        mainpanel.add(basepanel,BorderLayout.CENTER);
    }
    
    public class freezebutton implements ActionListener
         {
    
         public void actionPerformed(ActionEvent e) {
             
             freeze1.setEnabled(false);
             System.out.println("Gantry Robot Freeze");
             undo1.setEnabled(true);
            
            
            }
    
       }
    
    
    public class undobutton implements ActionListener
         {
    
         public void actionPerformed(ActionEvent e) {
             
             undo1.setEnabled(false);
             System.out.println("Undo Gantry Robot Freeze");
             freeze1.setEnabled(true);
            
            
            }
    
       }
    
    
    
    
}
    
