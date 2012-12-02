
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
 * @brief : KitManager containing the parts for production of kits. Creates, Modifies and Updates the kit.
 * 
 * 
 * 
 * 
 */

public class KitManager extends Manager  implements ActionListener {

	/**
	 * To avoid unexpected InvalidClassExceptions during deserialization
	 * we explicitly specify a UID, instead of relying on the compiler to generate one
	 */
	private static final long serialVersionUID = 2L;
	
	/**
	 * @param bpkit -   object which deals with list of existing kits from server
         * @param bppart -  object which deals with list of existing parts from server
         * @param partlist -  arraylist which holds the parts for the kit in create panel
         * @param updatepartlist -  arraylist which holds the parts for the kit in modify panel
         * @param create_combo - JCombobox for parts - to select a part and then set it on the button when the button is clicking
	 */
          static BlueprintKits bpkit;///<contains list of kits seria from server
          static BlueprintParts bppart;///<contains list of parts useable from server
          JPanel mainpanel; // main panel of the JFrame 
          JTabbedPane tabbedPane; // panel for tabbed layout
          
          JPanel createkit; // create kit panel - allows user to create kit
          JPanel updatekit; // update kit panel - allows user to update existing kit
          JPanel deletekit2; // delete kit panel - allows user to delete existing kit
          
          // Panels for laying out nicely
          JPanel ck_main;
          JPanel uk_main;
          JPanel dk_main;
          
          // TextField to enter  kit name by the user while creating kit
          JTextField kitname;
          
          // JCombobox for showing parts from the server in create kit panel
          JComboBox create_combo;
          
          // JCombobox for showing parts from server in modifying kit panel
          JComboBox ucreate_combo;
          
          //JCombobox for showing kit list from the server in delete panel
  
          JComboBox delete_combo;
          JComboBox update_kitcombo;
         
          
          JButton createkitbutton; // create kit button
          JButton deletekitbutton; // delete kit button
          JButton updatekitbutton;  // update kit button
          
         ArrayList<Part> partlist = new ArrayList<Part>();  // List to store parts of a kit
         
          ArrayList<Part> updatepartlist = new ArrayList<Part>();  // List to store parts of modified kit
         
          
          JButton b1,b2,b3,b4,b5,b6,b7,b0;
          JButton ub1,ub2,ub3,ub4,ub5,ub6,ub7,ub0;
          
          Part nullpart; // null part for no part - ie if we have less than 8 parts at times
          String newkitname;
          Kit tempkit;
    
	
         public KitManager() {
             
            KitManager.bpkit = new BlueprintKits();
            KitManager.bppart = new BlueprintParts();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             setBounds(100, 100, 600 , 400);
           
           
            prepareMainPane();
            this.update();  
            
            this.sendToServer(Message.PULL_PARTS_LIST);
            this.sendToServer(Message.IDENTIFY_KITMANAGER);
         }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

            KitManager frame = new KitManager();
            frame.setVisible(true);
            
	}
        
        
        
	
	 /**
     * @brief Prepares the entire layout and all panels of the kit manager. It basically lays stuff out in proper place.
     * It has tabbed panel and 3 panels - creating panel, modifying panel and deleting panel
     */
	
        private void prepareMainPane()
        {
            mainpanel = new JPanel()
            {     
                
             // Function to Set background image in the panel 
            public void paintComponent(Graphics g) 
            {
                Image img = new ImageIcon("pics/background/mainbg.png").getImage();
                Dimension size = new Dimension(600, 400);
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
                
                g.drawImage(img, 0, 0, null);
            } 
        };
            nullpart = new Part("no part","","pics/parts/no.png");    
           
            partlist = new ArrayList();
            partlist.add(0,nullpart);
             partlist.add(1,nullpart);
              partlist.add(2,nullpart);
               partlist.add(3,nullpart);
                partlist.add(4,nullpart);
                 partlist.add(5,nullpart);
                  partlist.add(6,nullpart);
                  partlist.add(7,nullpart);
                  
             
                   
        //      System.out.println("partlist in preparepane is "+partlist.size());
                
            
            mainpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            mainpanel.setLayout(new BorderLayout(0, 0));
            setContentPane(mainpanel);

            
            JLabel kittitle = new JLabel("Kit Manager",JLabel.CENTER);
            kittitle.setPreferredSize(new Dimension(200,50));
            kittitle.setFont(new Font("Verdana", Font.BOLD, 18));
            mainpanel.add(kittitle, BorderLayout.NORTH);

            tabbedPane = new JTabbedPane(JTabbedPane.TOP);
            tabbedPane.setOpaque(false);
            
            
            tabbedPane.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                   processtabchange();
                    }
                });
            
            
            mainpanel.add(tabbedPane,BorderLayout.CENTER);
            
            

            
            
            
            // CREATE KIT PANEL
            
            createkit = new JPanel();
           
            
            
            
            tabbedPane.addTab("Create Kit", createkit);
            
            ck_main = new JPanel();
            ck_main.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            
            c.ipady=10;
            c.ipadx=10;
            c.insets=new Insets(0, 0, 15, 0);;
          
            
            c.gridx=0;
            c.gridy=0;
            JLabel namekit1 = new JLabel("Kit Name :");
            namekit1.setFont(new Font("sansserif", Font.BOLD, 14));
            ck_main.add(namekit1,c);
            
            c.gridx=1;
            c.gridy=0;
            kitname = new JTextField(15);
            ck_main.add(kitname,c);
            
            c.gridx=0;
            c.gridy=1;
             JLabel selectpart1 = new JLabel("Select part :");
            selectpart1.setFont(new Font("sansserif", Font.BOLD, 14));
            
            ck_main.add(selectpart1 ,c);
            
 
            c.gridx=1;
            c.gridy=1;
       //     System.out.println("Size of part list is "+this.bppart.getSize());
            
            
           
            
            
            create_combo = new JComboBox(); // parts list
            create_combo.addItem("No Part - ");
            for(int p=0;p<KitManager.bppart.getSize();p++){
    		 create_combo.addItem(KitManager.bppart.getPartAt(p).getName()); 
               
            }
            
            
            
            ck_main.add(create_combo,c);
            
            c.gridx=0;
            c.gridy=2;
            c.gridwidth=2;
            
            JPanel partgrid = new JPanel();
            partgrid.setLayout(new GridLayout(2,4));
            
            
            
            b0 = new JButton();
            b1 = new JButton();
            b2 = new JButton();
            b3 = new JButton();
            b4 = new JButton();
            b5 = new JButton();
            b6 = new JButton();
            b7 = new JButton();
            
            b0.setPreferredSize(new Dimension(40,40));
            b1.setPreferredSize(new Dimension(40,40));
            b2.setPreferredSize(new Dimension(40,40));
            b3.setPreferredSize(new Dimension(40,40));
            b4.setPreferredSize(new Dimension(40,40));
            b5.setPreferredSize(new Dimension(40,40));
            b6.setPreferredSize(new Dimension(40,40)); 
            b7.setPreferredSize(new Dimension(40,40));
            
            b0.setIcon(new ImageIcon("pics/parts/no.png"));
            b1.setIcon(new ImageIcon("pics/parts/no.png"));
            b2.setIcon(new ImageIcon("pics/parts/no.png"));
            b3.setIcon(new ImageIcon("pics/parts/no.png"));
            b4.setIcon(new ImageIcon("pics/parts/no.png"));
            b5.setIcon(new ImageIcon("pics/parts/no.png"));
            b6.setIcon(new ImageIcon("pics/parts/no.png"));
            b7.setIcon(new ImageIcon("pics/parts/no.png"));
            
            
            partgrid.add(b0);
            partgrid.add(b1);
            partgrid.add(b2);
            partgrid.add(b3);
            partgrid.add(b4);
            partgrid.add(b5);
            partgrid.add(b6);
            partgrid.add(b7);
            
            b0.addActionListener(new KitManager.itembutton());
            b1.addActionListener(new KitManager.itembutton());
            b2.addActionListener(new KitManager.itembutton());
            b3.addActionListener(new KitManager.itembutton());
            b4.addActionListener(new KitManager.itembutton());
            b5.addActionListener(new KitManager.itembutton());
            b6.addActionListener(new KitManager.itembutton());
            b7.addActionListener(new KitManager.itembutton());
           
            
             
            
     //       b1.addActionListener(new partbutton());
            
            
            
            ck_main.add(partgrid,c);
            partgrid.setOpaque(false);
            c.gridx=0;
            c.gridy=3;
            c.gridwidth=2;
            createkitbutton = new JButton("Create Kit");
            createkitbutton.addActionListener(this);
            
            ck_main.add(createkitbutton,c);
            
            
    
            createkit.add(ck_main);
            
           
            
            updatekit = new JPanel();
            tabbedPane.addTab("Modify Kit",updatekit);
            
            
            uk_main = new JPanel();
            uk_main.setLayout(new GridBagLayout());
            c = new GridBagConstraints();
            
 
            
            c.ipady=10;
            c.ipadx=10;
            c.insets=new Insets(0, 0, 15, 0);;
          
            
            c.gridx=0;
            c.gridy=0;
            JLabel selectkit2 = new JLabel("Select Kit :");
            selectkit2.setFont(new Font("sansserif", Font.BOLD, 14));
             uk_main.add(selectkit2,c);
            
            c.gridx=1;
            c.gridy=0;
            update_kitcombo = new JComboBox();  
           
            
            
            for(int j=0;j<KitManager.bpkit.getSize();j++)
            {
    		 update_kitcombo.addItem(KitManager.bpkit.getKitAt(j).getName()); 
            }
            uk_main.add(update_kitcombo,c);
             update_kitcombo.addActionListener (new updatekitclass());
            c.gridx=0;
            c.gridy=1;
            
            JLabel selectpart2 = new JLabel("Select part :");
            selectpart2.setFont(new Font("sansserif", Font.BOLD, 14));
             uk_main.add(selectpart2,c);
            
 
            c.gridx=1;
            c.gridy=1;
         //   System.out.println("Size of part list is "+bppart.getSize());
            
            ucreate_combo = new JComboBox(); // parts list
            ucreate_combo.addItem("No Part - ");
            for(int p=0;p<KitManager.bppart.getSize();p++){
    		 ucreate_combo.addItem(KitManager.bppart.getPartAt(p).getName()); 
               
            }
            
             uk_main.add(ucreate_combo,c);
            
            c.gridx=0;
            c.gridy=2;
            c.gridwidth=2;
            
            JPanel upartgrid = new JPanel();
            upartgrid.setLayout(new GridLayout(2,4));
            upartgrid.setOpaque(false);
            ub0 = new JButton();
            ub1 = new JButton();
            ub2 = new JButton();
            ub3 = new JButton();
            ub4 = new JButton();
            ub5 = new JButton();
            ub6 = new JButton();
            ub7 = new JButton();
            
             ub0.setPreferredSize(new Dimension(40,40));
            ub1.setPreferredSize(new Dimension(40,40));
            ub2.setPreferredSize(new Dimension(40,40));
            ub3.setPreferredSize(new Dimension(40,40));
            ub4.setPreferredSize(new Dimension(40,40));
            ub5.setPreferredSize(new Dimension(40,40));
            ub6.setPreferredSize(new Dimension(40,40)); 
            ub7.setPreferredSize(new Dimension(40,40));
            
            
            
            ub0.setIcon(new ImageIcon("pics/parts/no.png"));
            ub1.setIcon(new ImageIcon("pics/parts/no.png"));
            ub2.setIcon(new ImageIcon("pics/parts/no.png"));
            ub3.setIcon(new ImageIcon("pics/parts/no.png"));
            ub4.setIcon(new ImageIcon("pics/parts/no.png"));
            ub5.setIcon(new ImageIcon("pics/parts/no.png"));
            ub6.setIcon(new ImageIcon("pics/parts/no.png"));
            ub7.setIcon(new ImageIcon("pics/parts/no.png"));
            
            
            
            upartgrid.add(ub0);
            upartgrid.add(ub1);
            upartgrid.add(ub2);
            upartgrid.add(ub3);
            upartgrid.add(ub4);
            upartgrid.add(ub5);
            upartgrid.add(ub6);
            upartgrid.add(ub7);
            
            ub0.addActionListener(new KitManager.updateitembutton());
            ub1.addActionListener(new KitManager.updateitembutton());
            ub2.addActionListener(new KitManager.updateitembutton());
            ub3.addActionListener(new KitManager.updateitembutton());
            ub4.addActionListener(new KitManager.updateitembutton());
            ub5.addActionListener(new KitManager.updateitembutton());
            ub6.addActionListener(new KitManager.updateitembutton());
            ub7.addActionListener(new KitManager.updateitembutton());
          
            
            uk_main.add(upartgrid,c);
            
            c.gridx=0;
            c.gridy=3;
            c.gridwidth=2;
            updatekitbutton = new JButton("Update Kit");
            updatekitbutton.addActionListener(this);
            
            uk_main.add(updatekitbutton,c);
            updatekit.add(uk_main);
            

            
            deletekit2 = new JPanel();
            
            
            tabbedPane.addTab("Delete Kit", deletekit2);
            
            
            delete_combo = new JComboBox();
            for(int m=0;m<KitManager.bpkit.getSize();m++){
    		 delete_combo.addItem(KitManager.bpkit.getKitAt(m).getName()); 
               
            }
            
            
            
            
            deletekit2.add(delete_combo);
            
            deletekitbutton = new JButton("Delete Kit");
            deletekitbutton.addActionListener(this);
            deletekit2.add(deletekitbutton);
            
           createkit.setOpaque(false);
           updatekit.setOpaque(false);
           deletekit2.setOpaque(false);
           ck_main.setOpaque(false);
           uk_main.setOpaque(false);
           
            
        }
        
        
        /**
     * @brief Action Listeners for the buttons - create kit, modify kit and update kit. Checks if there is a kit to delete or modify.
     * 
     */
          public void actionPerformed(ActionEvent e) {
                if (e.getSource() == createkitbutton) {
                createKit();
                }
                if (e.getSource() == deletekitbutton) {
                System.out.println("Kit deleted");
                if((bpkit.getSize())!=0)
                {
                    
                    System.out.println("kit will now get deleted");
                deleteKit(bpkit.getKitAt(delete_combo.getSelectedIndex()));
                }
                else
                {
                     JOptionPane.showMessageDialog(this, "No kit exists");
                }
              
                }
                if (e.getSource() == updatekitbutton) {
                System.out.println("Kit update called");
                if((bpkit.getSize())!=0)
                {
                    
                updateKit();
                }
                else
                {
                     JOptionPane.showMessageDialog(this, "No kit exists");
                }
                
                }
                
                
        }
        
           /**
     * @brief Returns the current kit from the delete combobox so that we have the kit at the selected index position
     * 
     */
       private Kit getCurrentKit() {
    	String s= (String) delete_combo.getSelectedItem();
    	Kit temp= new Kit(null,null);
    	for (int i=0;i<bpkit.getSize();i++) {
    		if (bpkit.getKitAt(i).getName().equals(s)) {
    			temp=bpkit.getKitAt(i);
    			break;
    		}
    		
            }
            return temp;
        }
        
        
        
        
        /**
     * @brief Sends an update request which should result in the parts list and kit list being updated and getting it from the server
     */
        public void update() {
            
            this.sendToServer(Message.PULL_PARTS_LIST);
           // this.sendToServer(Message.PULL_PARTS_LIST);
            System.out.println("Pulling parts list from the server");
                

            this.sendToServer(Message.PULL_KITS_LIST);
          //  this.sendToServer(Message.PULL_KITS_LIST);
            System.out.println("Pulling kits list from the server");

            
         }
        
        

    
        
        /**
     * @brief Function to Create kit when the Create kit button is pressed and then pass the new kit to the server so that the list gets updated.
     * It gets the kit name and the parts in the kit. Also should check if the user entered the name and if there are 4-8 parts in the kit
     */

        public void createKit()
        {
            ArrayList<Part> finalpartlist = new ArrayList();
            for(int pt=0;pt<8;pt++)
            {
                if(!"pics/parts/no.png".equals(partlist.get(pt).getFilename()))
                {
                    finalpartlist.add(partlist.get(pt));
                }
                
            }
            System.out.println("Size of partprintlist "+partlist.size());
            System.out.println("Size of finalprintlist"+finalpartlist.size());
            
            
            if((kitname.getText().length() == 0))
            {
                JOptionPane.showMessageDialog(this, "Please enter valid kit name");
                
            }
            if(finalpartlist.size()<=3)
            {
                JOptionPane.showMessageDialog(this, "Select at least 4 parts");
            }
            
            
            
            if(finalpartlist.size()>3 && !(kitname.getText().isEmpty()))
            {
            Kit newkit = new Kit(kitname.getText(),"description");//this will be the kit that just got made
            

            newkit.setParts(finalpartlist);
            bpkit.add(newkit);
            String msg = Message.DEFINE_NEW_KIT+":"+newkit.serialize();
            System.out.println("kit Created");
            System.out.println(msg);
            this.sendToServer(msg);
            
            update();
            
            
            
        
            tabbedPane.setSelectedIndex(0);
            }
            
        }

        
        /**
     * @brief Function to Update kit when the Update kit button is pressed and then pass the modified kit to the server so that the list gets updated.
     * It should get the kit name from the combobox and show parts accordingly and then allow user to modify kit. There is no kitname change because thats similar to deleting a kit and adding new kit.
     */
        
            public void updateKit()
        {
            ArrayList<Part> finalpartlist = new ArrayList();
            for(int py=0;py<8;py++)
            {
                
                // Check if there is a null part in the partlist so discard it if there is any
                if(!"pics/parts/no.png".equals(updatepartlist.get(py).getFilename()))
                {
                    finalpartlist.add(updatepartlist.get(py));
                }
                
            }
            System.out.println("Size of partprintlist "+updatepartlist.size());
            System.out.println("Size of finalprintlist"+finalpartlist.size());
            
          
            if(finalpartlist.size()<=3)
            {
                JOptionPane.showMessageDialog(this, "Select atleast 4 parts");
            }
            
            
            
            if(finalpartlist.size()>3) {
      
                
            Kit newkit = new Kit(newkitname,"description");//this will be the kit that just got made
            newkit.setParts(finalpartlist);
            
           
            this.sendToServer(Message.UNDEFINE_KIT+":"+tempkit.getNumber());
            
            
        
            String msg = Message.DEFINE_NEW_KIT+":"+newkit.serialize();
            System.out.println("kit updated");
            System.out.println(msg);
            this.sendToServer(msg);
            
            update();
          
            tabbedPane.setSelectedIndex(1);
            }
            
        }
        
        
        
        /**
     * @brief Process messages sent using Manager Class ie processes that message coming from the Server and set the kit and part list accordingly
     */
    
    public void processMessage(String msg)
    {
	super.processMessage(msg);
        if(msg.contains( Message.PUSH_KITS_LIST)) {
        	
	       KitManager.bpkit.recreate(this.grabParameter(msg));
	       System.out.println("GRABBED NEW KITS LIST FROM SERVER!: "+msg);
		   KitManager.bpkit.debug();
                    prepareMainPane();
	   }


        if(msg.contains(Message.PUSH_PARTS_LIST))
        {
            KitManager.bppart.recreate(this.grabParameter(msg));
            System.out.println("GRABBED NEW PARTS LIST FROM SERVER!" + msg);
            KitManager.bppart.debug();


             prepareMainPane();
        }

        

    }        
        
         /**
     * @brief deletes the kit with the specified kit name and sends data to
     * the server and hence updates existing kits list
     */
        
         public void deleteKit(Kit in) {
             
             
            bpkit.removeKit(in);
            
           this.sendToServer(Message.UNDEFINE_KIT+":"+in.getNumber());
            update();
            
            tabbedPane.setSelectedIndex(2);
            
            
        }
         
         public void processtabchange()
         {
             /*
            int t=tabbedPane.getSelectedIndex();
            if(t==1)
            {
                update();
                tabbedPane.setSelectedIndex(1);
            }
            if(t==2)
            {
                update();
            }
            
            */
              
              
         }
         
         
      
         
         
         
         /**
     * @brief Class for the Action to be performed when the buttons for alloting parts in the kit in create panel is clicked.The button property seticon
     * should be updated depending on the button clicked by getting the part selected from JComboBox. And accordingly the partlist arraylist for the kit should
     * also be updated and parts should be added in
     *
     */
         
       public class itembutton implements ActionListener
         {
             
             public void actionPerformed(ActionEvent e) {
                    
                 
                  Integer i = create_combo.getSelectedIndex();
             
                  
                  
                 if( e.getSource() == b0)
                 {
                     if(i==0)
                     {
                        b0.setIcon(new ImageIcon("pics/parts/no.png"));  
                        
                        }
                        else
                     {
                       b0.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                   //  b0.setIcon(new ImageIcon("pics/parts/part1.png"));
                    partlist.set(0, bppart.getPartAt(i-1));
                    
                     }  
                   
                 }
                 if( e.getSource() == b1)
                 {
                    
                     if(i==0)
                     {
                        b1.setIcon(new ImageIcon("pics/parts/no.png"));
                      
                        }
                        else
                     {
                    b1.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                     partlist.set(1, bppart.getPartAt(i-1));
               //       System.out.println("partlist in preparepane is "+partlist.size());
                     }
                     
                 }
                 if( e.getSource() == b2)
                 {
                    if(i==0)
                     {
                        b2.setIcon(new ImageIcon("pics/parts/no.png"));
                      
                        }
                        else
                     {
                     
                      b2.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                    partlist.set(2, bppart.getPartAt(i-1));
                     }
                    
                 }
                 if( e.getSource() == b3)
                 {
                     if(i==0)
                     {
                        b3.setIcon(new ImageIcon("pics/parts/no.png"));
                      
                        }
                        else
                     {
                      b3.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                    partlist.set(3, bppart.getPartAt(i-1));
                     }
                 }
                 if( e.getSource() == b4)
                 {
                    if(i==0)
                     {
                        b4.setIcon(new ImageIcon("pics/parts/no.png"));
                      
                        }
                        else
                     {
                    b4.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                     partlist.set(4, bppart.getPartAt(i-1));
                     }
                 }
                 if( e.getSource() == b5)
                 {
                     if(i==0)
                     {
                        b5.setIcon(new ImageIcon("pics/parts/no.png"));
                      
                        }
                        else
                     {
                      b5.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                    partlist.set(5, bppart.getPartAt(i-1));
                     }
                 }
                 if( e.getSource() == b6)
                 {
                     if(i==0)
                     {
                        b6.setIcon(new ImageIcon("pics/parts/no.png"));
                      
                        }
                        else
                     {
                     b6.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                    partlist.set(6, bppart.getPartAt(i-1));
                     }
                 }
                 if( e.getSource() == b7)
                 {
                     if(i==0)
                     {
                        b7.setIcon(new ImageIcon("pics/parts/no.png"));
                      
                        }
                        else
                     {
                     b7.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                     partlist.set(7, bppart.getPartAt(i-1));
                     }
                 }
                    
                   
                }
         } 

/**
     * @brief Class for the Action to be performed when the buttons for alloting parts in the kit in update panel is clicked.The button seticon
     * should be updated depending on the button clicked by getting the part selected from JComboBox. And accordingly the updatepartlist arraylist for the kit should
     * also be updated and parts should be added in
     *
     */
       public class updateitembutton implements ActionListener
         {
             
             public void actionPerformed(ActionEvent e) {
                    
                 
                  Integer i = ucreate_combo.getSelectedIndex();
                  
                  
                  
                 if( e.getSource() == ub0)
                 {
                     if(i==0)
                     {
                        ub0.setIcon(new ImageIcon("pics/parts/no.png"));
                        updatepartlist.set(0, nullpart);
                        }
                        else
                     {
                        ub0.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                       
                        updatepartlist.set(0, bppart.getPartAt(i-1));
                     }  
                   
                 }
                 if( e.getSource() == ub1)
                 {
                    
                     if(i==0)
                     {
                        ub1.setIcon(new ImageIcon("pics/parts/no.png"));
                        updatepartlist.set(1, nullpart); 
                        }
                        else
                     {
                      ub1.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                     updatepartlist.set(1, bppart.getPartAt(i-1));
                     }
                     
                 }
                 if( e.getSource() == ub2)
                 {
                    if(i==0)
                     {
                        ub2.setIcon(new ImageIcon("pics/parts/no.png"));
                       updatepartlist.set(2, nullpart);
                        }
                        else
                     {
                     
                    ub2.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                    updatepartlist.set(2, bppart.getPartAt(i-1));
                     }
                    
                 }
                 if( e.getSource() == ub3)
                 {
                     if(i==0)
                     {
                        ub3.setIcon(new ImageIcon("pics/parts/no.png"));
                       updatepartlist.set(3, nullpart);
                        }
                        else
                     {
                    ub3.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                    updatepartlist.set(3, bppart.getPartAt(i-1));
                     }
                 }
                 if( e.getSource() == ub4)
                 {
                    if(i==0)
                     {
                        ub4.setIcon(new ImageIcon("pics/parts/no.png"));
                       updatepartlist.set(4, nullpart);
                        }
                        else
                     {
                     ub4.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                    updatepartlist.set(4, bppart.getPartAt(i-1));
                     }
                 }
                 if( e.getSource() == ub5)
                 {
                     if(i==0)
                     {
                        ub5.setIcon(new ImageIcon("pics/parts/no.png"));
                       updatepartlist.set(5, nullpart);
                     }
                        else
                     {
                     ub5.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                     updatepartlist.set(5, bppart.getPartAt(i-1));
                     }
                 }
                 if( e.getSource() == ub6)
                 {
                     if(i==0)
                     {
                        ub6.setIcon(new ImageIcon("pics/parts/no.png"));
                       updatepartlist.set(6, nullpart);
                        }
                        else
                     {
                   ub6.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                     updatepartlist.set(6, bppart.getPartAt(i-1));
                     }
                 }
                 if( e.getSource() == ub7)
                 {
                     if(i==0)
                     {
                        ub7.setIcon(new ImageIcon("pics/parts/no.png"));
                       updatepartlist.set(7, nullpart);
                        }
                        else
                     {
                    ub7.setIcon(new ImageIcon(bppart.getPartAt(i-1).getFilename()));
                     updatepartlist.add(7, bppart.getPartAt(i-1));
                     }
                 }
                    
                   
                }
         } 

       
 /**
     * @brief This is fired when the update panel is set. It should get information of the kit depending upon the kit selected from the combobox from the user.
     *So it should get the parts of the selected kit and display it and then allow the user to modify parts in the kit
     */
       public class updatekitclass implements ActionListener
         {
             
             public void actionPerformed(ActionEvent e) {
                 JComboBox cb = (JComboBox)e.getSource();
                 updatepartlist = new ArrayList();
                 ArrayList<JButton> jbtnlist = new ArrayList();
                 jbtnlist.add(ub0);
                 jbtnlist.add(ub1);
                 jbtnlist.add(ub2);
                 jbtnlist.add(ub3);
                 jbtnlist.add(ub4);
                 jbtnlist.add(ub5);
                 jbtnlist.add(ub6);
                 jbtnlist.add(ub7);
                
                 
                 
            ub0.setIcon(new ImageIcon("pics/parts/no.png"));
            ub1.setIcon(new ImageIcon("pics/parts/no.png"));
            ub2.setIcon(new ImageIcon("pics/parts/no.png"));
            ub3.setIcon(new ImageIcon("pics/parts/no.png"));
            ub4.setIcon(new ImageIcon("pics/parts/no.png"));
            ub5.setIcon(new ImageIcon("pics/parts/no.png"));
            ub6.setIcon(new ImageIcon("pics/parts/no.png"));
            ub7.setIcon(new ImageIcon("pics/parts/no.png"));
                 


                 Integer p=(bpkit.getKitAt(cb.getSelectedIndex())).getSize();
                 
                 updatepartlist.add(nullpart);
                 updatepartlist.add(nullpart);
                 updatepartlist.add(nullpart);
                 updatepartlist.add(nullpart);
                 updatepartlist.add(nullpart);
                 updatepartlist.add(nullpart);
                 updatepartlist.add(nullpart);
                 updatepartlist.add(nullpart);
                 
                 tempkit=(bpkit.getKitAt(cb.getSelectedIndex()));
                newkitname=(bpkit.getKitAt(cb.getSelectedIndex())).getName();
                 
                System.out.println("my kit size is prt size is"+p);
                
                 for(int s=0;s<p;s++)
                 {
                    jbtnlist.get(s).setIcon(new ImageIcon(((bpkit.getKitAt(cb.getSelectedIndex())).getPart(s).getFilename())));
                    updatepartlist.set(s, ((bpkit.getKitAt(cb.getSelectedIndex())).getPart(s)));
                    System.out.println("part should be"+(bpkit.getKitAt(cb.getSelectedIndex())).getPart(s).getName());
                 }
                 
                
                
       }

}
}
