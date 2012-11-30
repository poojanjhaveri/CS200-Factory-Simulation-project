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
import javax.swing.*;



public class GUINonNormGAM extends JPanel {
    
    private JPanel mainpanel;
    
    
    public GUINonNormGAM() {
             
           preparemainpanel();
           this.add(mainpanel);
        
            
         }

 
    private void preparemainpanel()
    {
        mainpanel = new JPanel();
        JPanel basepanel = new JPanel();
        basepanel.setLayout(new BorderLayout());
        basepanel.add(new JLabel("Gantry Robot Manager Non-Normative Scenario"),BorderLayout.NORTH);
        
        
        JPanel scenario = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx=0;
        c.gridy=0;
        scenario.add(new JLabel("Scenario 1"));
        
        
        
        basepanel.add(scenario);
        mainpanel.add(basepanel);
    }
    
    
    
    
}
