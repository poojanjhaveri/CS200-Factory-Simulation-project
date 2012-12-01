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
        
         c.ipady=5;
         c.ipadx=5;
        
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        droppart = new JButton("Drop a Part");
        droppart.setBackground(Color.GREEN);
        droppart.setOpaque(true);
        droppart.addActionListener(new droppartbutton());
        droppart.setPreferredSize(new Dimension(150,50));
        scenario.add(droppart,c);
        
        for (int j=0;j<2;j++){
        	for (int i=0;i<4;i++){
        		c.fill =GridBagConstraints.HORIZONTAL;
        		c.gridx=i;
        		c.gridy=j+1;
        		this.unstabilize = new JButton("Toggle stability "+(j*4+i));
        		this.unstabilize.setBackground(Color.GREEN);
        		this.unstabilize.setOpaque(true);
        		this.unstabilize.setPreferredSize(new Dimension(150,50));
        		this.unstabilize.addActionListener(this.kam);
        		scenario.add(unstabilize,c);        	
        		unstables.add(unstabilize);
        	}
        }
              
        for(int j=0;j<2;j++){
        	for(int i=0;i<4;i++){
        		c.fill =GridBagConstraints.HORIZONTAL;
        		c.gridx=i;
        		c.gridy=j+3;
        		this.piled = new JButton("Toggle piling"+(j*4+i));
        		this.piled.setBackground(Color.GREEN);
        		this.piled.setOpaque(true);
        		this.piled.setPreferredSize(new Dimension(150,50));
        		this.piled.addActionListener(this.kam);
        		piles.add(piled);
        		scenario.add(this.piled,c);
        	}
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
    

