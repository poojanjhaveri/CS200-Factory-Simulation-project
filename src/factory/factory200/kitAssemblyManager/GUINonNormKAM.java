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
 * @author Poojan Jhaveri, Yuting Liu
 * @brief : GUI class for non-normative scenario associated with KIT assembly manager
 * 
 * 
 * 
 */
public class GUINonNormKAM  extends JPanel {
    
    private JPanel mainpanel;
    JButton droppart;
    JButton undoDroppart;
    JButton piled;
   
    JButton unstabilize; 
    JButton undoUnstabilize;
    JButton undoPiled;
    ActionListener kam;
    
    public GUINonNormKAM(ActionListener in) {
             this.kam = in;
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
        
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        droppart = new JButton("Drop a Part");
        droppart.addActionListener(new droppartbutton());
        droppart.setPreferredSize(new Dimension(150,50));
        scenario.add(droppart,c);
        
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=0;
        undoDroppart = new JButton("Revert Back");
        //undo1.setEnabled(false);
        undoDroppart.setPreferredSize(new Dimension(150,50));
        undoDroppart.addActionListener(new undobutton());
        scenario.add(undoDroppart,c);
        
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        this.unstabilize = new JButton("Toggle nest stability");
        //this.unstabilize.setEnabled(false);
        this.unstabilize.setPreferredSize(new Dimension(150,50));
        this.unstabilize.addActionListener(this.kam);
        scenario.add(this.unstabilize,c);
        
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=1;
        this.undoUnstabilize = new JButton("UnToggle nest stability");
        //this.unstabilize.setEnabled(false);
        this.undoUnstabilize.setPreferredSize(new Dimension(150,50));
        this.undoUnstabilize.addActionListener(this.kam);
        scenario.add(this.undoUnstabilize,c);
        
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=2;
        this.piled = new JButton("Toggle nest piling");
        //this.unstabilize.setEnabled(false);
        this.piled.setPreferredSize(new Dimension(150,50));
        this.piled.addActionListener(this.kam);
        scenario.add(this.piled,c);
        
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=2;
        this.undoPiled = new JButton("Toggle nest piling");
        //this.unstabilize.setEnabled(false);
        this.undoPiled.setPreferredSize(new Dimension(150,50));
        this.undoPiled.addActionListener(this.kam);
        scenario.add(this.undoPiled,c);        

        basepanel.add(scenario);
        mainpanel.add(basepanel,BorderLayout.CENTER);
    }
    public JButton getPilingButton()
    {
	return this.piled;
    }
    public JButton getStabilityButton()
    {
    	return this.unstabilize;
    	//return this.unstabilize;
    }
    
    public class droppartbutton implements ActionListener
         {
    
         public void actionPerformed(ActionEvent e) {
             
             droppart.setEnabled(false);
             System.out.println("KAM drop part");
             undoDroppart.setEnabled(true);
            
            
            }
    
       }
    
    
    public class undobutton implements ActionListener
         {
    
         public void actionPerformed(ActionEvent e) {
             
             undoDroppart.setEnabled(false);
             System.out.println("Undo drop part condition");
             droppart.setEnabled(true);
            
            
            }
    
       }
    
    
    
    
}
    

