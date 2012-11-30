/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.kitAssemblyManager;


import factory.factory200.gantryRobotManager.GUINonNormGAM;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Poojan Jhaveri
 * @brief : GUI class for non-normative scenario associated with KIT assembly manager
 * 
 * 
 * 
 */
public class GUINonNormKAM  extends JPanel {
    
    private JPanel mainpanel;
    JButton droppart1;
    JButton undo1;
    
    
    public GUINonNormKAM() {
             
           preparemainpanel();
           this.add(mainpanel);
        
            
         }

 
    private void preparemainpanel()
    {
        mainpanel = new JPanel();
        JPanel basepanel = new JPanel();
        basepanel.setLayout(new BorderLayout());
        
        JLabel p1 = new JLabel("Kit Assembly Manager Non-Normative Scenario",JLabel.CENTER);
        p1.setFont(new Font("Verdana", Font.BOLD, 16));
        p1.setPreferredSize(new Dimension(500,100));
        basepanel.add(p1,BorderLayout.NORTH);
        
        
        JPanel scenario = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
         c.ipady=10;
         c.ipadx=10;
        
        
        c.gridx=0;
        c.gridy=0;
        droppart1 = new JButton("Drop a Part");
        droppart1.addActionListener(new freezebutton());
        droppart1.setPreferredSize(new Dimension(150,50));
        scenario.add(droppart1);
        
        c.gridx=1;
        c.gridy=0;
        undo1 = new JButton("Revert Back");
        undo1.setEnabled(false);
        undo1.setPreferredSize(new Dimension(150,50));
        undo1.addActionListener(new undobutton());
        scenario.add(undo1);
        
        
        basepanel.add(scenario);
        mainpanel.add(basepanel,BorderLayout.CENTER);
    }
    
    public class freezebutton implements ActionListener
         {
    
         public void actionPerformed(ActionEvent e) {
             
             droppart1.setEnabled(false);
             System.out.println("KAM drop part");
             undo1.setEnabled(true);
            
            
            }
    
       }
    
    
    public class undobutton implements ActionListener
         {
    
         public void actionPerformed(ActionEvent e) {
             
             undo1.setEnabled(false);
             System.out.println("Undo drop part condition");
             droppart1.setEnabled(true);
            
            
            }
    
       }
    
    
    
    
}
    

