package factory.factory200.kitManager;

import factory.general.BlueprintKits;
import factory.general.Manager;
import factory.general.Message;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;



/*
 * 
 * @author : Poojan Jhaveri
 * @brief : KitManager containing the parts for production of kits
 * 
 * 
 */



public class KitManager extends Manager {

	/**
	 * @param args
	 */
          BlueprintKits bp;///<contains list of kits for serialization
          JPanel mainpanel;
          JTabbedPane tabbedPane;
          
          JPanel createkit;
          JPanel updatekit;
          JPanel deletekit;
          JPanel ck_main;
          JPanel uk_main;
          JPanel dk_main;
          JTextField kitname;
          
    
    
	
         public KitManager()
         {
            this.bp = new BlueprintKits();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             setBounds(100, 100, 450, 300);

            prepareMainPane();
	//	this.mcon.out(Message.PULL_PARTS_LIST);
              this.update();
         }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

            KitManager frame = new KitManager();
            frame.setVisible(true);
            
	}
	
	//getters and setters as needed 
	
        private void prepareMainPane()
        {
            mainpanel = new JPanel();
            mainpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            mainpanel.setLayout(new BorderLayout(0, 0));
            setContentPane(mainpanel);

            mainpanel.add(new JLabel("Kits Manager"), BorderLayout.NORTH);

            tabbedPane = new JTabbedPane(JTabbedPane.TOP);
            mainpanel.add(tabbedPane,BorderLayout.CENTER);
            
            // CREATE KIT PANEL
            
            createkit = new JPanel();
            ImageIcon i = new ImageIcon("part1.jpg");
            tabbedPane.addTab("Create Kit", i, createkit);
            
            ck_main = new JPanel();
            ck_main.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            
            
            c.gridx=0;
            c.gridy=0;
            ck_main.add(new JLabel("Kit Name :"),c);
            
            c.gridx=1;
            c.gridy=0;
            kitname = new JTextField(15);
            ck_main.add(kitname,c);
            
            createkit.add(ck_main);
            
            
            
            
            
            
             updatekit = new JPanel();
            
            i = new ImageIcon("part2.jpg");
            tabbedPane.addTab("Modify Kit", i, updatekit);
            
            
            
             deletekit = new JPanel();
            
            i = new ImageIcon("part3.jpg");
            tabbedPane.addTab("Delete Kit", i, deletekit);
            
            
            
            
            
        }
        
        
        /**
     * @brief sends an update request which should result in the parts list being updated
     */
        public void update() {
            this.mcon.out(Message.PULL_KITS_LIST);
            System.out.println("Updates kits list from the server");
         }
        

}
