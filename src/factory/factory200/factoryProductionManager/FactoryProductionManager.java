package factory.factory200.factoryProductionManager;
//import factory.agentGUI.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import factory.general.BlueprintKits;
import factory.general.Util;
import factory.general.Kit;
import factory.general.Manager;
import factory.general.Message;

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
	public JPanel basePanel, topPanel, botPanel, leftPanel, midPanel, rightPanel, blankPanel;
	public JLabel selLabel, numLabel, consoleLabel, schedLabel;
	public JComboBox selKit;
	public JTextField numE;
	public JButton queueue, start, stop, reset;
	public JTextArea schedField, outField;
	public JScrollPane schedPane, outPane;
	public JTabbedPane tabs;
	//public PaintPanel gfx;
        
        public GraphicsPanel gfx;

	public String nameToAdd;
	public Kit kitToAdd;
	public int qtyToAdd;
	public int num;
	public ArrayList<String> availableKits;
	public ArrayList<Kit> selectedKits;

	private boolean debug;
        private boolean empty;
	private BlueprintKits debugbp;
        private BlueprintKits kitsbp;
	private final static String newline = "\n";
	

	public static void main(String[] args)
	{
		FactoryProductionManager fpm = new FactoryProductionManager();
		
		fpm.setVisible(true);
		fpm.setSize(1375,700);
		fpm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fpm.setResizable(false);
	}

	public FactoryProductionManager()
	{
            	gfx = new GraphicsPanel();
		gfx.setPreferredSize(new Dimension(1350, 700));
		// Send Identification to Server
		super.sendToServer(Message.IDENTIFY_FACTORYPRODUCTIONMANAGER);
		
		debug = false;
                empty = false;
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		availableKits = new ArrayList<String>();
                selKit = new JComboBox();
		//Pull Blueprint from server
                this.kitsbp = new BlueprintKits();
		this.mcon.out(Message.PULL_KITS_LIST);

		
		selectedKits = new ArrayList<Kit>();
		selLabel = new JLabel ("Select Kit");
		numLabel = new JLabel ("Qty");
		consoleLabel = new JLabel ("Console Out");
		schedLabel = new JLabel ("Production Schedule");
		schedField = new JTextArea(30, 20);
		outField = new JTextArea(30, 20);
		schedPane = new JScrollPane(schedField);
		outPane = new JScrollPane(outField);
		schedField.setEditable(false);
		outField.setEditable(false);
		
		numE = new JTextField(20);
		numE.setPreferredSize(new Dimension(5,8));
		queueue = new JButton("Add Kits");
		start = new JButton("Start");
		stop = new JButton("Stop");
		reset = new JButton("Reset");


		basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());


		tabs = new JTabbedPane();
		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
		botPanel = new JPanel();
		botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.LINE_AXIS));
		leftPanel = new JPanel();
		midPanel = new JPanel();
		midPanel.setLayout(gridbag);
		rightPanel = new JPanel();
		blankPanel = new JPanel();
                
                topPanel.add(new JPanel());
		topPanel.add(schedLabel);
		topPanel.add(new JPanel());
                topPanel.add(new JPanel());
                topPanel.add(new JPanel());
                topPanel.add(new JPanel());
                topPanel.add(new JPanel());
                topPanel.add(new JPanel());
                topPanel.add(new JPanel());
                topPanel.add(new JPanel());
                topPanel.add(new JPanel());
		topPanel.add(consoleLabel);
                topPanel.add(new JPanel());
		
		leftPanel.add(schedPane);
		leftPanel.setPreferredSize(new Dimension(320, 600));

		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 1.0;
		c.ipadx = 10;
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridx = 0;

		gridbag.setConstraints(selLabel, c);
		midPanel.add(selLabel);
		gridbag.setConstraints(selKit, c);
		midPanel.add(selKit);

		c.gridx = 1;
		c.gridy = 2;
		gridbag.setConstraints(queueue, c);
		midPanel.add(queueue);
                c.gridy = GridBagConstraints.RELATIVE;
                JPanel fill1 = new JPanel();
                gridbag.setConstraints(fill1, c);
                midPanel.add(fill1);
		gridbag.setConstraints(start, c);
		midPanel.add(start);
                JPanel fill2 = new JPanel();
                gridbag.setConstraints(fill2, c);
                midPanel.add(fill2);
		//gridbag.setConstraints(stop, c);
		//midPanel.add(stop);
		gridbag.setConstraints(reset, c);
		midPanel.add(reset);

		c.gridx = 2;
		c.ipadx = 75;
		gridbag.setConstraints(numLabel, c);
		midPanel.add(numLabel);
		gridbag.setConstraints(numE, c);
		midPanel.add(numE);

		rightPanel.add(outPane);
		rightPanel.setPreferredSize(new Dimension(320, 600));

		botPanel.add(leftPanel);
		botPanel.add(midPanel);
		botPanel.add(rightPanel);

		basePanel.add(topPanel, BorderLayout.NORTH);
		basePanel.add(botPanel, BorderLayout.CENTER);
		
                basePanel.setSize(1350, 700);
		tabs.addTab("Control GUI", basePanel);
		tabs.addTab("Simulation", gfx);
                gfx.repaint();

		selKit.addActionListener(this);
		queueue.addActionListener(this);
		start.addActionListener(this);
		stop.addActionListener(this);
		reset.addActionListener(this);
                
		add(tabs);

		//this.sendToServer(Message.IDENTIFY_FACTORYPRODUCTIONMANAGER);
	}
	
    @Override
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == selKit)
		{
                    if(!empty)
                    {
			selKitRoutine(ae.getSource());
                    }
		}
		if(ae.getSource() == queueue)
		{
                    if(!empty)
                    {
			if(!numE.getText().equals(""))
			{
				try {
					Integer.parseInt(numE.getText());
					qtyToAdd = Integer.parseInt(numE.getText());
					for(int i=0;i<qtyToAdd;i++)
					{

						System.out.println("Name = " + nameToAdd);
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
		if(ae.getSource() == start)
		{
                    if(!empty)
                    {
			if(selectedKits.size() > 0)
			{
				outField.append("~~~~~~~~~~~~~" + newline);
				for(Kit kitty : selectedKits)
				{
					System.out.println("Kitty name = " + kitty.getName());
					outField.append(kitty.getName() + newline);
				}
				start();
				outField.append("Starting Factory Simulation" + newline);
			}
			else
			{
				//Error to console out
				outField.append("No kits added to Blueprint" + newline);
			}
                    }
		}
		if(ae.getSource() == reset)
		{
                    if(!empty)
                    {
			if(selectedKits.size() > 0)
			{
				//Message to console
				selectedKits.clear();
				schedField.setText("");
			}
                    }
		}
	}

    /**
     * start the factory production queue with the current kit selection
     */
	private void selKitRoutine(Object source)
	{
		JComboBox cb = (JComboBox)source;
		nameToAdd = (String)cb.getSelectedItem();
		System.out.println("Name to add = " + nameToAdd);
		if(!debug)
		{
			for(Kit kitty : kitsbp.getKits())
			{
				if(nameToAdd.equals(kitty.getName()))
				{
					kitToAdd = kitty;
				}
			}
		}
		else
		{
			for(Kit kitty : debugbp.getKits())
			{
				if(nameToAdd.equals(kitty.getName()))
				{
					kitToAdd = kitty;
				}
			}
		}
		System.out.println("NumE = " + numE.getText());
	}

    void start() {
		//add this when you change arraylist to kits
		String msg = Message.PUSH_PRODUCTION_QUEUE+":";
		/*for(int i = 0; i != this.selectedKits.size(); i++)
		{
			msg = msg+this.selectedKits.get(i).getNumber();
			if(i != this.selectedKits.size()-1)
				msg=msg+",";
		}*/
		ArrayList<String> serialized = new ArrayList<String>();
		for(int i = 0; i != this.selectedKits.size(); i++)
		    {
			serialized.add(this.selectedKits.get(i).getNumber()+"");
		    }
		msg = msg + Util.serialize(serialized);
		this.sendToServer(msg);
    }

    /**
     * stop the factory sim
     */
    void stop() {
    }

    /**
     * once user clicks this menu item, set the GUI panels invisible and the
     * graphic panel visible
     */
    void displayGraphics() {
    }

    /**
     * once user clicks this menu item, set the NonNormative and FPMGraphics
     * panels invisible and the FPMGUI panel visible
     */
    void displayFPMGUI() {
    }

    /**
     * force to exit the program
     */
    void exit() {
    }

	public void paintGraphics(Graphics2D g2){
		
	}
    /**
@brief processes the serve'rs message
     */
    @Override
    public void processMessage(String msg)
    {
	super.processMessage(msg);

	if(msg.contains(Message.PUSH_KITS_LIST))
	    {
			this.kitsbp.recreate(this.grabParameter(msg));
			System.out.println("GRABBED A NEW BLUEPRINTKITS FROM THE SERVER");
			this.kitsbp.debug();
                        this.reconstructComboBox();
	    }
	
		//Lane Manager( pass 'msg' into Lane Manager Message Interpreter and take a proper action )
//	    gfx.verifyMessage(msg); // TODO: Why nullpointer?
    }
    
    public void reconstructComboBox()
    {
        System.out.println("Kitsbp size = " + kitsbp.getKits().size());
        //Populate Debug Blueprint if no Blueprint exists on server
        if(kitsbp.getKits().isEmpty())
        {
                debug = true;
                ArrayList<Kit> tempKits = new ArrayList<Kit> ();
                tempKits.add(new Kit("Uno", "One"));
                tempKits.add(new Kit("Dos", "Two"));
                tempKits.add(new Kit("Tres", "Three"));
                debugbp = new BlueprintKits(tempKits);
        }

        //Populate Combobox array with names of Blueprint Kits

        for(int i=0;i<kitsbp.getKits().size();i++)
        {
                availableKits.add(kitsbp.getKits().get(i).getName());
        }

        System.out.println("Available kits size = " + availableKits.size());
        for(String kitty : availableKits)
        {
                selKit.addItem(kitty);
        }
        //selKit.setSelectedItem(0);
        if(!empty)
        {
            selKitRoutine(selKit);
        }
    }
    /**
     * @brief Controls Kit selection and Factory ON/OFF Controls Kit selection
     * and Factory ON/OFF <img src="../img/image08.jpg" />
     * @author Matt Kane
     */
	public class PaintPanel extends JPanel {	

		FactoryProductionManager myFPM;
	
		public PaintPanel(FactoryProductionManager mg) {
			myFPM = mg;
		}
	

        @Override
	  	public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
	
			myFPM.paintGraphics(g2);

		/*
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		*/
		
	}
}
}
