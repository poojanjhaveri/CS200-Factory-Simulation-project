package factory.factory200.kitManager;

import factory.general.BlueprintKits;
import factory.general.BlueprintParts;
import factory.general.Kit;

import factory.general.Manager;
import factory.general.Message;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
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
          BlueprintKits bpkit;
          BlueprintParts bppart;///<contains list of kits for serialization
          JPanel mainpanel;
          JTabbedPane tabbedPane;
          
          JPanel createkit;
          JPanel updatekit;
          JPanel deletekit;
          JPanel ck_main;
          JPanel uk_main;
          JPanel dk_main;
          JTextField kitname;
          JComboBox create_combo;
          JComboBox delete_combo;
          
          private ArrayList partlist = new ArrayList();
          
    
    
	
         public KitManager()
         {
            this.bpkit = new BlueprintKits();
            this.bppart = new BlueprintParts();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             setBounds(100, 100, 600, 400);
            this.update();  
           
            prepareMainPane();
	//	this.mcon.out(Message.PULL_PARTS_LIST);
              
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

            
            JLabel kittitle = new JLabel("Kits Manager",JLabel.CENTER);
            kittitle.setPreferredSize(new Dimension(200,50));
            kittitle.setFont(new Font("Verdana", Font.BOLD, 18));
            mainpanel.add(kittitle, BorderLayout.NORTH);

            tabbedPane = new JTabbedPane(JTabbedPane.TOP);
            mainpanel.add(tabbedPane,BorderLayout.CENTER);
            
            // CREATE KIT PANEL
            
            createkit = new JPanel();
            ImageIcon i = new ImageIcon("kit/create.jpg");
            tabbedPane.addTab("Create Kit", i, createkit);
            
            ck_main = new JPanel();
            ck_main.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            
            c.ipady=10;
            c.ipadx=10;
            c.insets=new Insets(0, 0, 15, 0);;
          
            
            c.gridx=0;
            c.gridy=0;
            ck_main.add(new JLabel("Kit Name :"),c);
            
            c.gridx=1;
            c.gridy=0;
            kitname = new JTextField(15);
            ck_main.add(kitname,c);
            
            c.gridx=0;
            c.gridy=1;
            ck_main.add(new JLabel("Select part:"),c);
            
 
            c.gridx=1;
            c.gridy=1;
            
            
            create_combo = new JComboBox(); // parts list
            for(int j=0;j<this.bppart.getSize();j++){
    		 create_combo.addItem(this.bppart.getPartAt(j).getName()); 
               
            }
            ck_main.add(create_combo,c);
            
            c.gridx=0;
            c.gridy=2;
            c.gridwidth=2;
            
            JPanel partgrid = new JPanel();
            partgrid.setLayout(new GridLayout(2,4));
            
            JButton b1=new JButton();
            JButton b2=new JButton();
            JButton b3=new JButton();
            JButton b4=new JButton();
            JButton b5=new JButton();
            JButton b6=new JButton();
            JButton b7=new JButton();
            JButton b8=new JButton();
            
            partgrid.add(b1);
            partgrid.add(b2);
            partgrid.add(b3);
            partgrid.add(b4);
            partgrid.add(b5);
            partgrid.add(b6);
            partgrid.add(b7);
            partgrid.add(b8);
            ck_main.add(partgrid,c);
            
            c.gridx=0;
            c.gridy=3;
            c.gridwidth=2;
            JButton submitbutton = new JButton("Create Kit");
            ck_main.add(submitbutton,c);
            
            
    
            createkit.add(ck_main);
            
            
            
            updatekit = new JPanel();
            
            i = new ImageIcon("kit/edit.png");
            tabbedPane.addTab("Modify Kit", i, updatekit);
            
            
            
            deletekit = new JPanel();
            
            i = new ImageIcon("kit/delete.jpg");
            tabbedPane.addTab("Delete Kit", i, deletekit);
            
            delete_combo = new JComboBox();
            deletekit.add(delete_combo);
            
            JButton deletebutton = new JButton("Delete Kit");
            deletekit.add(deletebutton);
            
            
            
            
            
            
            
        }
        
        
        /**
     * @brief sends an update request which should result in the parts list being updated
     */
        public void update() {
            
            this.mcon.out(Message.PULL_PARTS_LIST);
            System.out.println("Updates parts list from the server");
            
            
            this.mcon.out(Message.PULL_KITS_LIST);
            System.out.println("Updates kits list from the server");
         }
        
        
         /**
     * @brief deletes the kit with the specified kit name and sends data to
     * the server and updates existing kits list
     */
        
         public void deletePart(Kit in) {
            bpkit.removeKit(in);
        //	updateComboBox();
    }

}
