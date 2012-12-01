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
    public ArrayList<Boolean> unstableColor;
    public ArrayList<Boolean> piledColor;
    JButton unstabilize;    
    ActionListener kam;
    
    public JButton getDropPartButton()
    {
        return this.droppart;
    }
    
    public GUINonNormKAM(ActionListener in) {
             this.kam = in;

           this.piledColor = new ArrayList<Boolean>();
           this.unstableColor = new ArrayList<Boolean>();

           for(int i = 0 ; i != 8; i++)
           {
           	this.piledColor.add(false);
           	this.unstableColor.add(false);
           }

           preparemainpanel();
         }

 
    public void preparemainpanel()
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
        droppart.setBackground(Color.GREEN);
        droppart.setOpaque(true);
        droppart.addActionListener(new droppartbutton());
        droppart.setPreferredSize(new Dimension(100,50));
        scenario.add(droppart,c);
        
        for (int j=0;j<2;j++){
        	for (int i=0;i<4;i++){
        		c.fill =GridBagConstraints.HORIZONTAL;
        		c.gridx=1;
        		c.gridy=j*4+i+1;
        		this.unstabilize = new JButton("Toggle stability "+(j*4+i));
        		if(!this.unstableColor.get(j*4+i)){
        			this.unstabilize.setBackground(Color.GREEN);
        		}
        		else{
        			this.unstabilize.setBackground(Color.RED);
        		}
        		this.unstabilize.setOpaque(true);
        		this.unstabilize.setPreferredSize(new Dimension(100,50));
        		this.unstabilize.addActionListener(this.kam);
        		scenario.add(unstabilize,c);        	
        		unstables.add(unstabilize);
        	}
        }
              
        for(int j=0;j<2;j++){
        	for(int i=0;i<4;i++){
        		c.fill =GridBagConstraints.HORIZONTAL;
        		c.gridx=0;
        		c.gridy=j*4+i+1;
        		this.piled = new JButton("Toggle piling"+(j*4+i));
        		if(!this.piledColor.get(j*4+i))
        		this.piled.setBackground(Color.GREEN);
        		else
        			this.piled.setBackground(Color.RED);
        		this.piled.setOpaque(true);
        		this.piled.setPreferredSize(new Dimension(100,50));
        		this.piled.addActionListener(this.kam);
        		piles.add(piled);
        		scenario.add(this.piled,c);
        	}
        }
        
       

        basepanel.add(scenario);
        mainpanel.add(basepanel,BorderLayout.CENTER);

        this.add(mainpanel);
        revalidate();
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
    
    public void toggleStabilizeColor(int i ){
    	this.unstableColor.set(i, !this.unstableColor.get(i));
    }
    
    public void togglePiledColor(int i){
    	this.piledColor.set(i,!this.piledColor.get(i));
    }
}
    

