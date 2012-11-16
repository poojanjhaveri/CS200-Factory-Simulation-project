package factory.factory200.kitManager;

import factory.general.BlueprintKits;
import factory.general.BlueprintParts;
import factory.general.Kit;

import factory.general.Manager;
import factory.general.Message;
import factory.general.Part;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



/*
 * 
 * @author : Poojan Jhaveri
 * @brief : KitManager containing the parts for production of kits
 * 
 * 
 */



public class KitManager extends Manager  implements ActionListener {

	/**
	 * @param args
	 */
    BlueprintKits bpkit;///<contains list of kits modifiable
          BlueprintParts bppart;///<contains list of parts useable
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
          
          JButton createkitbutton;
          
          ArrayList<Part> partlist = new ArrayList();
          
    
    
	
         public KitManager()
         {
             
            this.bpkit = new BlueprintKits();
            this.bppart = new BlueprintParts();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             setBounds(100, 100, 600, 400);
           
           
            prepareMainPane();
             this.update();  
	//	this.mcon.out(Message.PULL_PARTS_LIST);
              
         }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

            KitManager frame = new KitManager();
            frame.setVisible(true);
            
	}
        
        
         public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createkitbutton) {
         System.out.println("kit Created");
            createKit();
            
        }
    }
        
        
	
	//getters and setters as needed 
	
        private void prepareMainPane()
        {
            mainpanel = new JPanel()
            {     
            public void paintComponent(Graphics g) 
            {
                Image img = new ImageIcon("pics/kitbackground.jpg").getImage();
                Dimension size = new Dimension(600, 400);
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
                
                g.drawImage(img, 0, 0, null);
            } 
        };
                
                
            
            mainpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            mainpanel.setLayout(new BorderLayout(0, 0));
            setContentPane(mainpanel);

            
            JLabel kittitle = new JLabel("Kits Manager",JLabel.CENTER);
            kittitle.setPreferredSize(new Dimension(200,50));
            kittitle.setFont(new Font("Verdana", Font.BOLD, 18));
            mainpanel.add(kittitle, BorderLayout.NORTH);

            tabbedPane = new JTabbedPane(JTabbedPane.TOP);
            
            
            tabbedPane.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                   processtabchange();
                    }
                });
            
            
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
            System.out.println("Size of part list is "+bppart.getSize());
            
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
            
     //       b1.addActionListener(new partbutton());
            
            
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
            createkitbutton = new JButton("Create Kit");
            createkitbutton.addActionListener(this);
            
            ck_main.add(createkitbutton,c);
            
            
    
            createkit.add(ck_main);
            
            
            
            updatekit = new JPanel();
            
            i = new ImageIcon("kit/edit.png");
            tabbedPane.addTab("Modify Kit", i, updatekit);
            
            
            
            deletekit = new JPanel();
            
            i = new ImageIcon("kit/delete.jpg");
            tabbedPane.addTab("Delete Kit", i, deletekit);
            
            
            delete_combo = new JComboBox();
            for(int j=0;j<this.bpkit.getSize();j++){
    		 delete_combo.addItem(this.bpkit.getKitAt(j)); 
               
            }
            
            
            
            
            deletekit.add(delete_combo);
            
            JButton deletebutton = new JButton("Delete Kit");
            deletekit.add(deletebutton);
            
        }
        
        
        /**
     * @brief sends an update request which should result in the parts list being updated
     */
        public void update() {
            
            this.mcon.out(Message.PULL_PARTS_LIST);
            System.out.println("Updated kits list from the server");
            

            this.mcon.out(Message.PULL_KITS_LIST);
            System.out.println("Updates kits list from the server");
            
     

         }
        
        
        private void updateComboBox(){
              delete_combo.removeAllItems();
               for(int i=0;i<bpkit.getSize();i++){
    		 delete_combo.addItem(bpkit.getKitAt(i));     
    	
               }   
    	
    	 }
    
        

    public void createKit()
    {
        
	Kit newkit = new Kit(kitname.getText(),"description");//this will be the kit that just got made
	newkit.setParts(partlist);
        //handle gui here
        
        

	String msg = Message.DEFINE_NEW_KIT+":"+newkit.serialize();
        System.out.println(msg);
	this.mcon.out(msg);
    }

    public void processMessage(String msg)
    {
	super.processMessage(msg);
        if(msg.contains( Message.PUSH_KITS_LIST)) {
        	
	       this.bpkit.recreate(this.grabParameter(msg));
	       System.out.println("GRABBED NEW KITS LIST FROM SERVER!: "+msg);
		   this.bpkit.debug();
	   }
        if(msg.contains(Message.PUSH_PARTS_LIST))
        {
            this.bppart.recreate(this.grabParameter(msg));
            System.out.println("GRABBED NEW PARTS LIST FROM SERVER!" + msg);
            this.bppart.debug();
        }

    }        
        
         /**
     * @brief deletes the kit with the specified kit name and sends data to
     * the server and updates existing kits list
     */
        
         public void deletePart(Kit in) {
            bpkit.removeKit(in);
        //	updateComboBox();
    }
         
         public void processtabchange()
         {
              Component c = tabbedPane.getSelectedComponent();
                if (c.equals(deletekit))
                {
                     updateComboBox();
                }   
             
         }
         

}
