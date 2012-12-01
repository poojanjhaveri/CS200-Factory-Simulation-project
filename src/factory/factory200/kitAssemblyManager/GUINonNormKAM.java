/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.kitAssemblyManager;


import factory.factory200.gantryRobotManager.GUINonNormGAM;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
   
    JButton piled;
    ArrayList<JButton> unstables;
    ArrayList<JButton> piles;
    JButton unstabilize;    
    ActionListener kam;
    
    public GUINonNormKAM(ActionListener in) {
             this.kam = in;
           preparemainpanel();
           this.add(mainpanel);
           
         }

 
    private void preparemainpanel()
    {
    	unstables= new ArrayList<JButton>();
        piles = new ArrayList<JButton>();
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
        
        for (int i=0;i<8;i++){
        	c.fill =GridBagConstraints.HORIZONTAL;
        	c.gridx=0;
        	c.gridy=i+1;
        	this.unstabilize = new JButton("Toggle nest stability for 0");
        //this.unstabilize.setEnabled(false);
        	this.unstabilize.setPreferredSize(new Dimension(150,50));
        	this.unstabilize.addActionListener(this.kam);
        	scenario.add(unstabilize,c);
        	
        	unstables.add(unstabilize);
        	
        }
               
        for(int i=0;i<8;i++){
        	c.fill =GridBagConstraints.HORIZONTAL;
        	c.gridx=2;
        	c.gridy=i+1;
        	this.piled = new JButton("Toggle nest piling");
        	//this.unstabilize.setEnabled(false);
        	this.piled.setPreferredSize(new Dimension(150,50));
        	this.piled.addActionListener(this.kam);
        	piles.add(piled);
        	scenario.add(this.piled,c);
        }
        
       

        basepanel.add(scenario);
        mainpanel.add(basepanel,BorderLayout.CENTER);
    }
    public JButton getPilingButton(int i)
    {
	return this.piles.get(i);
    }
    public JButton getStabilityButton(int i)
    {
    	return this.unstables.get(i);
    	//return this.unstabilize;
    }
    
    public class droppartbutton implements ActionListener
         {
    
         public void actionPerformed(ActionEvent e) {
             
             droppart.setEnabled(false);
             System.out.println("KAM drop part");
           
            
            
            }
    
       }
    
    
    public class undobutton implements ActionListener
         {
    
         public void actionPerformed(ActionEvent e) {
             
             
             System.out.println("Undo drop part condition");
             droppart.setEnabled(true);
            
            
            }
    
       }
    
    
    
    
}
    

