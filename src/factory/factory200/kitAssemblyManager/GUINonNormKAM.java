
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
    JButton earlyflash;///<flash camera early
    JButton piled;
    JButton robotIn;
    //JButton earlyCameraFlash;
    ArrayList<JButton> unstables;
    ArrayList<JButton> piles;
    public ArrayList<Boolean> unstableColor;
    public ArrayList<Boolean> piledColor;
    JButton unstabilize;    
    ActionListener kam;
    

	public JButton getRobotInWayButton()
    {
	return this.robotIn;
    }
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
        mainpanel = new JPanel()
        {
            public void paintComponent(Graphics g) 
            {
                Image img = new ImageIcon("pics/background/mainbg.png").getImage();
           /*     Dimension size = new Dimension(550, 700);
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);*/
                
                g.drawImage(img, 0, 0, null);
            } 
            
            
            
        };
        
        
        JPanel basepanel = new JPanel();
        basepanel.setOpaque(false);
        basepanel.setLayout(new BorderLayout());
        
        JLabel p1 = new JLabel("Kit Assembly Manager Non-Normative Scenario",JLabel.CENTER);
        p1.setFont(new Font("Verdana", Font.BOLD, 16));
        p1.setPreferredSize(new Dimension(500,100));
        p1.setOpaque(false);
        basepanel.add(p1,BorderLayout.NORTH);
        
        
        JPanel scenario = new JPanel(new GridBagLayout());
        scenario.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        
         c.ipady=10;
         c.ipadx=10;
        
         
        c.gridy=1;
        scenario.add(new JLabel("   "),c);
        
       
        for (int j=0;j<2;j++){
        	for (int i=0;i<4;i++){
        		c.fill =GridBagConstraints.VERTICAL;
        		c.insets=new Insets(5,0,0,0);
        		c.gridx=i;
        		c.gridy=2+j;
        		this.unstabilize = new JButton("Unstabilize Nest "+(j*4+i));
        		if(!this.unstableColor.get(j*4+i)){
        	//		this.unstabilize.setBackground(Color.GREEN);
        		}
        		else{
        	//		this.unstabilize.setBackground(Color.RED);
        		}
        		this.unstabilize.setOpaque(false);
        		this.unstabilize.setPreferredSize(new Dimension(120,43));
        		this.unstabilize.addActionListener(this.kam);
        		scenario.add(unstabilize,c);        	
        		unstables.add(unstabilize);
        	}
        }
        
        
            
         c.gridy=4;
        scenario.add(new JLabel("   "),c);
         
         
        
              
        for(int j=0;j<2;j++){
        	for(int i=0;i<4;i++){
        		c.fill =GridBagConstraints.HORIZONTAL;
        		c.insets=new Insets(5,0,0,0);
        		c.gridx=i;
        		c.gridy=5+j;
        		this.piled = new JButton("Pile Nest "+(j*4+i));
        	/*	if(!this.piledColor.get(j*4+i))
        		this.piled.setBackground(Color.GREEN);
        		else
        			this.piled.setBackground(Color.RED); */
        		this.piled.setOpaque(true);
        		this.piled.setPreferredSize(new Dimension(120,45));
        		this.piled.addActionListener(this.kam);
        		piles.add(piled);
        		scenario.add(this.piled,c);
        	}
        }
        
        /*
         c.gridwidth=2;
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        droppart = new JButton("Drop Part");
        //droppart.setBackground(Color.GREEN);
        //droppart.setOpaque(true);
        droppart.addActionListener(this.kam);
        droppart.setPreferredSize(new Dimension(100,45));
        scenario.add(droppart,c);*/
        
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=2;
        c.gridy=0;
        c.gridwidth=2;
        earlyflash = new JButton("Early Camera Flash");
        earlyflash.addActionListener(this.kam);
        earlyflash.setPreferredSize(new Dimension(120,43));
        scenario.add(earlyflash,c);
        
        c.fill =GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        robotIn = new JButton("Parts Robot in Way");
        robotIn.addActionListener(this.kam);
        robotIn.setPreferredSize(new Dimension(120,43));
        robotIn.setOpaque(false);
        scenario.add(robotIn,c);
        
       

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
    public JButton getEarlyFlashButton()
    {
	return this.earlyflash;
    }
    public class droppartbutton implements ActionListener
         {
    
         public void actionPerformed(ActionEvent e) {
             
        
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
    

