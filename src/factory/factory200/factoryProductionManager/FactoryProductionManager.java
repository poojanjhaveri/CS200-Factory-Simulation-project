package factory.factory200.factoryProductionManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import factory.general.BlueprintKits;
import factory.general.Kit;
import factory.general.Manager;
import factory.general.Message;
import factory.general.Util;
import javax.swing.ImageIcon;


/**
 * Factory Production Manager selects active kit production routines, how many
 * of each kit to produce, and also will control non-normative scenario
 * simulation. This client also displays graphics representing the entire
 * factory simulation. Referenced classes are KitRobot, PartsRobot, GantryRobot,
 * Nest, Lane, Bin, and CameraHandler Inner Class FPMGUI extends JPanel and is
 * an instance of the Factory Management GUI Inner Class FPMGraphics extends
 * Jpanel and is an instance of the entire Factory Graphics display InnerClass
 * NonNormative extends JPanel and is an instance of the FPM Non-Normative
 * Control Panel
 *
 * @author Matt Kane
 * @brief selects active kit production routines, how many of each kit to
 * produce, and also will control non-normative scenario simulation
 *
 */
public class FactoryProductionManager extends Manager implements ActionListener {
	/**
	 * To avoid unexpected InvalidClassExceptions during deserialization
	 * we explicitly specify a UID, instead of relying on the compiler to generate one
	 */
	private static final long serialVersionUID = 5L;
	
    private JPanel basePanel, topPanel,parentMidPanel, queuePanel, botPanel, mainPanel, selPanel, leftPanel, midPanel, rightPanel, botBotPanel, slotsPanel;
    private JLabel selLabel, queueLabel, numLabel, consoleLabel, schedLabel, inProdLabel;
    private JComboBox selKit;
    private JTextField numE;
    private JButton queueue, start, stop, reset;
    private JTextArea schedField, outField, serverQueueDisplay, inProdField;
    private JScrollPane schedPane, outPane, queuePane;
    private JTabbedPane tabs;
    
    JPanel nonnorm;
    JPanel mparts;
    
    JButton nonnormupdate;
    
    private ArrayList<Kit> prodQueue; // used for queue of kits

    public GraphicsPanel gfx;

    private String nameToAdd; // name of currently selected kit in combo box, selKit
    private Kit kitToAdd; // currently selected kit in the combo box (selKit); clicking 'add kit' adds this kit to selectedKits and puts its name on the pending queue text area
    private int qtyToAdd;
    private int serverQueueSize;
    private ArrayList<String> availableKits; // strings in the combobox selKit; generally, availableKits.get(i) refers to selKit.getItemAt(i)
    private ArrayList<Kit> selectedKits; // stores the selected kits before the kits are added to production queue
    		// so say you select kit1 with the combo box and put 20 in the text field; we end up with 20 of kit1 in selectedKits

    private boolean kitListFromServerIsEmpty; // true if kit list from server is empty
    private boolean constructed; // variable to determine if this class's constructor has finished; used for real time updates
    private BlueprintKits kitsbp;
    
     ArrayList<JButton> nonnormpart;
    
    
    
    JLabel  kitnorname;
    
    //	private Image bgimg;

    private final static String newline = "\n";

    GridBagLayout gridbag;
    GridBagConstraints c;

    public FactoryProductionManager() {
        kitListFromServerIsEmpty = false;
        constructed = false;

        //Instantiate components that need to exist before the pull
        instantiateDynamicComponents();
        //Pull From Server
        this.sendToServer(Message.PULL_KITS_LIST); 
        //Instantiate components that don't rely on server data
        instantiateStaticComponents();
        //Set up JFrame
        panelLayout();
        //Signal to future pull reconstructions that the frame is complete
        constructed = true;

        // Identify this manager to server
        this.sendToServer(Message.IDENTIFY_FACTORYPRODUCTIONMANAGER);
    }
    
    //These components must be instantiated before server pull
    private void instantiateDynamicComponents() {
        //Instantiate Parts Blueprint
        availableKits = new ArrayList<String>();
        selKit = new JComboBox();
        this.kitsbp = new BlueprintKits();

        //Instantiate Graphics Panel
        gfx = new GraphicsPanel();
        gfx.setPreferredSize(new Dimension(1350, 700));

        //Instantiate ServerQueue
        serverQueueDisplay = new JTextArea(12, 20);
        serverQueueSize = 0;

        inProdField = new JTextArea(1, 20);

        serverQueueDisplay.setEditable(false);
        inProdField.setEditable(false);
    }
    
    //These components do not need to be instantiated for server pull to occur
    private void instantiateStaticComponents() {
        mainPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        gridbag = new GridBagLayout();
        c = new GridBagConstraints();

        selectedKits = new ArrayList<Kit>();
        selLabel = new JLabel ("Select Kit");
        numLabel = new JLabel ("Quantity");
        consoleLabel = new JLabel ("System Message");
        schedLabel = new JLabel ("Pending Queue");
        queueLabel = new JLabel ("Server Production Queue");
        inProdLabel = new JLabel ("In Production");
        schedField = new JTextArea(30, 20);
        outField = new JTextArea(30, 20);
        schedPane = new JScrollPane(schedField);
        outPane = new JScrollPane(outField);
        queuePane = new JScrollPane(serverQueueDisplay);
        schedField.setEditable(false);
        outField.setEditable(false);

        numE = new JTextField(3);
        queueue = new JButton("Add Kits");
        start = new JButton("Start");
        stop = new JButton("Stop");
        reset = new JButton("Clear List");
        
        //basePanel to display background image
        basePanel = new JPanel();
        /*
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
        */
        basePanel.setLayout(new BorderLayout());

        tabs = new JTabbedPane();
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        botPanel = new JPanel();
        botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.LINE_AXIS));
        parentMidPanel = new JPanel();
        parentMidPanel.setLayout(new GridLayout(2,1));
        queuePanel = new JPanel();
        queuePanel.setLayout(gridbag);
        leftPanel = new JPanel();
        midPanel = new JPanel();
        midPanel.setLayout(gridbag);
        rightPanel = new JPanel();
        botBotPanel = new JPanel(gridbag);
        selPanel = new JPanel(gridbag);
        slotsPanel = new JPanel(gridbag);

        /*
        topPanel.setOpaque(false);
        botPanel.setOpaque(false);
        parentMidPanel.setOpaque(false);
        queuePanel.setOpaque(false);
        leftPanel.setOpaque(false);
        midPanel.setOpaque(false);
        rightPanel.setOpaque(false);
        botBotPanel.setOpaque(false);
        selPanel.setOpaque(false);
        slotsPanel.setOpaque(false);
         * 
         */
    }

    private void panelLayout()
    {
        //Layout topPanel, goes to NORTH on basePanel, has field labels
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(schedLabel);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(Box.createHorizontalStrut(700));
        topPanel.add(consoleLabel);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(Box.createHorizontalStrut(10));

        //Layout for leftPanel, contains Pending Queue
        leftPanel.add(schedPane);
        leftPanel.setPreferredSize(new Dimension(320, 600));

        //selPanel contains Kit Selection Combo Box and Numeric TextField
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 1.0;
        c.ipadx = 10;
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridx = 0;

        gridbag.setConstraints(selLabel, c);
        selPanel.add(selLabel);
        gridbag.setConstraints(selKit, c);
        selPanel.add(selKit);

        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 0;
        gridbag.setConstraints(numLabel, c);
        selPanel.add(numLabel);
        c.gridy = 1;
        c.ipadx = 10;
        gridbag.setConstraints(numE, c);
        selPanel.add(numE);

        //Layout midPanel, contains all user input controls
        c.ipadx = 75;
        c.gridx = 0;
        c.gridy = 0;
        gridbag.setConstraints(selPanel, c);
        midPanel.add(selPanel);

        c.ipadx = 8;
        c.ipady = 16;
        c.gridy = 2;
        gridbag.setConstraints(queueue, c);
        midPanel.add(queueue);

        c.gridy = 4;
        c.ipadx = 0;
        gridbag.setConstraints(reset, c);
        midPanel.add(reset);

        c.gridy = 6;
        gridbag.setConstraints(start, c);
        midPanel.add(start);

        //Layout rightPanel, contains System Message Pane
        rightPanel.add(outPane);
        rightPanel.setPreferredSize(new Dimension(320, 600));

        //Layout Server Status Panel
        c.gridx = 0;
        c.ipady = 10;
        c.ipadx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        gridbag.setConstraints(inProdLabel, c);
        slotsPanel.add(inProdLabel);
        c.ipady = 12;
        c.ipadx = 120;
        gridbag.setConstraints(inProdField, c);
        slotsPanel.add(inProdField);

        c.gridx = 0;
        c.ipadx = 75;
        c.gridy = GridBagConstraints.RELATIVE;
        gridbag.setConstraints(slotsPanel, c);
        queuePanel.add(slotsPanel, c);
        c.ipadx = 0;
        gridbag.setConstraints(queueLabel, c);
        queuePanel.add(queueLabel);
        c.ipady = 180;
        c.ipadx = 300;
        gridbag.setConstraints(queuePane, c);
        queuePanel.add(queuePane);

        //Layout Middle of Main Panel
        c.ipady = 10;
        c.ipadx = 75;
        parentMidPanel.add(queuePanel);
        parentMidPanel.add(midPanel);

        //Add left right and mid to main panel
        botPanel.add(leftPanel);
        botPanel.add(parentMidPanel);
        botPanel.add(rightPanel);

        //Setup basePanel for GUI Control
        basePanel.add(topPanel, BorderLayout.NORTH);
        basePanel.add(botPanel, BorderLayout.CENTER);
        start.setPreferredSize(new Dimension(100, 30));

        //Setup tabbed pane
        basePanel.setSize(1350, 700);
        tabs.addTab("Control GUI", basePanel);
        tabs.addTab("Simulation", gfx);
       
        nonnorm = new JPanel();
        preparenonnorm();
        tabs.addTab("Non-normative",nonnorm);
        
        
        
        
        
        gfx.repaint();

        //Register ActionListeners
        selKit.addActionListener(this);
        queueue.addActionListener(this);
        start.addActionListener(this);
        stop.addActionListener(this);
        reset.addActionListener(this);

        mainPanel.add(tabs, BorderLayout.CENTER);
        
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        //JComboBox functionality
        if(ae.getSource() == selKit)
        {

            if(!kitListFromServerIsEmpty)
            {
                p.println("Action listener called " + selKit.getItemCount());
                selKitRoutine(ae.getSource());
            }
        }

        //Add selection to pending queue
        if(ae.getSource() == queueue)
        {
            if(!kitListFromServerIsEmpty)
            {
                if(!numE.getText().equals(""))
                {
                    try {
                        Integer.parseInt(numE.getText());
                        qtyToAdd = Integer.parseInt(numE.getText());
                        for(int i=0;i<qtyToAdd;i++) {
                            p.println("Name = " + nameToAdd);
                            selectedKits.add(kitToAdd);
                            schedField.append(nameToAdd + newline);
                        }
                    }
                    catch (NumberFormatException nfe) {
                        numE.setText("");
                        outField.append("Invalid number." + newline);
                    }
                }
                else
                {
                    outField.append("Please enter a number of kits to add." + newline);
                }
            }
        }

        //Commit list, start simulation
        if(ae.getSource() == start)
        {
            if(!kitListFromServerIsEmpty)
            {
                if(selectedKits.size() > 0)
                {
                    //Print to console the list of kit names to push
                    outField.append("~~~~~~~~~~~~~" + newline);
                    for(Kit kitty : selectedKits)
                    {
                        p.println("Kitty name = " + kitty.getName());
                        outField.append(kitty.getName() + newline);
                    }
                    start();
                    
                    //Set System output based on Factory run condition
                    if(serverQueueSize == 0 && inProdField.getText().equals(""))
                        outField.append("Starting Factory Simulation" + newline);
                    else
                        outField.append("Appending Production Queue" + newline);
                    reset();
                }
                else
                {
                    //Error to console out
                    outField.append("No kits added to Blueprint" + newline);
                }
            }
        }

        //Clear pending queue
        if(ae.getSource() == reset)
        {
            reset();
        }
    }
    
    //Functionality for JComboBox usage
    private void selKitRoutine(Object source) {
        if(selKit.getItemCount() == kitsbp.getKits().size()) {
            JComboBox cb = (JComboBox)source;
            nameToAdd = (String)cb.getSelectedItem();
            p.println("Name to add = " + nameToAdd);

            for(Kit kitty : kitsbp.getKits())
                if(nameToAdd.equals(kitty.getName()))
                    kitToAdd = kitty;
        }
    }
    
    // Functionality for JComboBox usage
    // Set the combo box item that is selected in selKit 
    private void setComboBoxSelection() {
    	nameToAdd = (String) selKit.getSelectedItem(); // get the name of the selected item from the combo box
    	p.println("Name to add = " + nameToAdd);

    	for(Kit kitty : kitsbp.getKits())
    		if(nameToAdd.equals(kitty.getName()))
    			kitToAdd = kitty;
    }

    //Deserialize messages from server
    @Override
    public void processMessage(String msg) {
        super.processMessage(msg);
        if (msg == null) {
            return;
        }

        if (msg.contains(Message.PUSH_KITS_LIST)) {
            this.kitsbp.recreate(this.grabParameter(msg));
            p.println("GRABBED A NEW BLUEPRINTKITS FROM THE SERVER");
            //this.kitsbp.debug();
            this.reconstructComboBox();
        } else if(msg.contains(Message.KIT_IN_PRODUCTION)) { // DoGiveKitsInAction() in PartsAgent
        	// Update the in-production textfield to show the kit in production (the kit "in action")
            inProdField.setText("");
            inProdField.append(this.grabParameter(msg));
            
        	// Remove the kit that is now in action from the queue (kit should no longer be in the queue if it is in action)
            // TODO: @parse22 handle this (read the comments around) 
        } else if(msg.contains(Message.GIVE_KITS_IN_QUEUE)) {
            serverQueueDisplay.setText("");
            serverQueueSize = 0;
            BlueprintKits temp = new BlueprintKits();
            temp.recreate(this.grabParameter(msg)); // grabs the arraylist of kits sent to him
            prodQueue = temp.getKits();
            for (Kit kitty : prodQueue) {
                serverQueueDisplay.append(kitty.getName() + newline);
                serverQueueSize++;
            }
        }
        //Lane Manager( pass 'msg' into Lane Manager Message Interpreter and take a proper action )
        gfx.verifyMessage(msg);
    }

    // Finalize creation of dynamic components; follows server pull thread
    // post condition: availableKits.get(i) refers to selKit.getItemAt(i), and both are properly populated
    public void reconstructComboBox() {
    	p.println("Incoming number of kits = " + kitsbp.getKits().size());
    	p.println("----reconstructComboBox(): Printing available kits----");
        for (String kit : availableKits)
        	p.println(kit);
        
        availableKits.clear();

        // Populate Combobox array with names of Blueprint Kits
        for(int i=0;i<kitsbp.getKits().size();i++) {
            //Populate string list of names of incoming kits
            p.println(kitsbp.getKits().get(i).getName());
            availableKits.add(kitsbp.getKits().get(i).getName());
        }
        p.println("---New list of kits in available kits (size = " + availableKits.size()+")---");
        for (String kit : availableKits)
        	p.println(kit);
        
        // Clears each item in selKit
//        selKit.removeAllItems(); // mysteriously doesn't work according to Matt!
        int size = selKit.getItemCount(); // need constant store of selKit's original item count
        for (int i=0; i<size; i++) {
            selKit.removeItemAt(0);
        }
        p.println("--Just removed all kits: new size of combo box: "+selKit.getItemCount());
        
        // Add strings to the combobox component
        for (String kitty : availableKits) {
            selKit.addItem(kitty);
        }
        p.println("Just added each string from available Kits to combo box: new size: " + selKit.getItemCount() + " should equal " + availableKits.size());

        // if the incoming kit list isn't empty, make the combo box now
        if (!kitListFromServerIsEmpty) {
            selKit.setSelectedItem(0);
            p.println("Name to add = " + (String)selKit.getSelectedItem());
            setComboBoxSelection();
        }

        if (constructed) { // call only if the constructor has already finished
            queuePanel.remove(queuePane);
            gridbag.setConstraints(queuePane,c);
            queuePanel.add(queuePane);
            basePanel.updateUI();
        }
        p.println("--Final available kits list--");
        for (String kit : availableKits)
        	p.println(kit);
        
        p.println("--end reconstruct--");
    }
    
    //Clear pending queue display list and selected kits container
    private void reset()
    {
        if(!kitListFromServerIsEmpty)
        {
            if(selectedKits.size() > 0)
            {
                //Message to console
                selectedKits.clear();
                schedField.setText("");
            }
        }
    }
    
    //Commit list, push to server
    void start() 
    {
        updatenonnorm();
        //add this when you change arraylist to kits
        String msg = Message.PUSH_PRODUCTION_QUEUE+":";
        ArrayList<String> serialized = new ArrayList<String>();
        for(int i = 0; i != this.selectedKits.size(); i++)
        {
            serialized.add(this.selectedKits.get(i).getNumber()+"");
        }
        msg = msg + Util.serialize(serialized);
        this.sendToServer(msg);
    }

    public static void main(String[] args)
    {
        FactoryProductionManager fpm = new FactoryProductionManager();

        fpm.setVisible(true);
        fpm.setSize(1350,700); //CONSULT PEOPLE IN CHARGE OF GRAPHICS BEFORE CHANGING THIS SIZE! 
        fpm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fpm.setResizable(false);
    }
    
    
    public void preparenonnorm()
    {
            mparts = new JPanel();
            mparts.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            
            c.gridx=0;
            c.gridy=0;
            c.gridwidth=2;
            
            kitnorname = new JLabel("");
            mparts.add(kitnorname,c);
            nonnormupdate = new JButton("Update this kit ");
            nonnormupdate.setEnabled(false);
            
        //    mparts.add(nonnormupdate);
              nonnormpart = new ArrayList();
            nonnorm.add(mparts);

    }
    
    private void updatenonnorm()
    {
         nonnormupdate.setEnabled(true);
        kitnorname.setText(this.selectedKits.get(this.selectedKits.size()-1).getName());
        GridBagConstraints c = new GridBagConstraints();
        
        for(int i=0;i<this.selectedKits.get(this.selectedKits.size()-1).getSize();i++)
        {
           c.gridx=0;
           c.gridy=i+1;
           JButton b1 = new JButton("Remove Part" + i);
           nonnormpart.add(new JButton("Remove Part" + i+1));
           nonnormpart.get(i).addActionListener(new updatebuttonnorm());
         nonnormpart.get(i).setIcon(new ImageIcon(this.selectedKits.get(this.selectedKits.size()-1).getPart(i).getFilename()));
           mparts.add(nonnormpart.get(i),c);
           
        }
        
    }
    
    
     public class updatebuttonnorm implements ActionListener
         {
    
         public void actionPerformed(ActionEvent ae) {
             
             
             for(int k=0;k<nonnormpart.size();k++)
             {
             if(ae.getSource()==nonnormpart.get(k))
             {
                 System.out.println("Button  is pressed "+k);
                 
             }
             }
             
    
       }
    
     }
}