package factory.factory200.factoryProductionManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import factory.general.BlueprintKits;
import factory.general.Kit;
import factory.general.Manager;
import factory.general.Message;
import factory.general.Util;
import java.util.HashSet;
import java.util.Set;


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
	private JPanel basePanel, topPanel,parentMidPanel, queuePanel, botPanel, mainPanel, selPanel, leftPanel, midPanel, rightPanel, botBotPanel, slotsPanel;
	private JLabel selLabel, queueLabel, numLabel, consoleLabel, schedLabel, inProdLabel;
	private JComboBox selKit;
	private JTextField numE;
	private JButton queueue, start, stop, reset;
	private JTextArea schedField, outField, serverQueueDisplay, inProdField;
	private JScrollPane schedPane, outPane, queuePane;
	private JTabbedPane tabs;

	private ArrayList<String> serverQueue;
	private String inProduction;

	public GraphicsPanel gfx;

	private String nameToAdd;
	private Kit kitToAdd;
	private int qtyToAdd;
	private int num;
	private ArrayList<String> availableKits;
	private ArrayList<Kit> selectedKits;

	private boolean empty;
	private boolean constructed;
	private BlueprintKits kitsbp;
	//	private Image bgimg;

	private final static String newline = "\n";

	GridBagLayout gridbag;
	GridBagConstraints c;

	public FactoryProductionManager() {
		empty = false;
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

	private void instantiateDynamicComponents() {
		//Instantiate Parts Blueprint
		availableKits = new ArrayList<String>();
		selKit = new JComboBox();
		this.kitsbp = new BlueprintKits();

		//Instantiate Graphics Panel
		gfx = new GraphicsPanel();
		gfx.setPreferredSize(new Dimension(1350, 700));

		//Instantiate ServerQueue
		serverQueue = new ArrayList<String>();
		serverQueueDisplay = new JTextArea(12, 20);

		inProdField = new JTextArea(1, 20);

		serverQueueDisplay.setEditable(false);
		inProdField.setEditable(false);
	}

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

		basePanel = new JPanel()
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
	}

	private void panelLayout()
	{
            topPanel.add(Box.createHorizontalGlue());
            topPanel.add(Box.createHorizontalStrut(10));
            topPanel.add(schedLabel);
            topPanel.add(Box.createHorizontalGlue());
            topPanel.add(Box.createHorizontalStrut(700));
            topPanel.add(consoleLabel);
            topPanel.add(Box.createHorizontalGlue());
            topPanel.add(Box.createHorizontalStrut(10));

            leftPanel.add(schedPane);
            leftPanel.setPreferredSize(new Dimension(320, 600));

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

            rightPanel.add(outPane);
            rightPanel.setPreferredSize(new Dimension(320, 600));

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

            c.ipady = 10;
            c.ipadx = 75;
            parentMidPanel.add(queuePanel);
            parentMidPanel.add(midPanel);

            botPanel.add(leftPanel);
            botPanel.add(parentMidPanel);
            botPanel.add(rightPanel);

            basePanel.add(topPanel, BorderLayout.NORTH);
            basePanel.add(botPanel, BorderLayout.CENTER);
            start.setPreferredSize(new Dimension(100, 30));

            basePanel.setSize(1350, 700);
            tabs.addTab("Control GUI", basePanel);
            tabs.addTab("Simulation", gfx);
            gfx.repaint();

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
					//Print to console the list of kit names to push
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

	private void selKitRoutine(Object source)
	{
		JComboBox cb = (JComboBox)source;
		nameToAdd = (String)cb.getSelectedItem();
		System.out.println("Name to add = " + nameToAdd);

		for(Kit kitty : kitsbp.getKits())
			if(nameToAdd.equals(kitty.getName()))
				kitToAdd = kitty;
	}

	@Override
	public void processMessage(String msg) {
		super.processMessage(msg);
		if (msg == null) {
			return;
		}

		if (msg.contains(Message.PUSH_KITS_LIST)) {
			this.kitsbp.recreate(this.grabParameter(msg));
			System.out.println("GRABBED A NEW BLUEPRINTKITS FROM THE SERVER");
			this.kitsbp.debug();
			this.reconstructComboBox();
		}
		if(msg.contains(Message.KIT_IN_PRODUCTION))
		{
			inProdField.setText("");
			inProdField.append(this.grabParameter(msg));
		}
		if(msg.contains(Message.GIVE_KITS_IN_QUEUE))
		{
			serverQueueDisplay.setText("");
			BlueprintKits temp = new BlueprintKits();
			temp.recreate(this.grabParameter(msg));
			ArrayList<Kit> prodqueue = temp.getKits();
			for(Kit kitty : prodqueue)
			{
				serverQueueDisplay.append(kitty.getName() + newline);
			}
		}
		//Lane Manager( pass 'msg' into Lane Manager Message Interpreter and take a proper action )
		gfx.verifyMessage(msg);
	}

	public void reconstructComboBox()
	{
		System.out.println("Incoming number of kits = " + kitsbp.getKits().size());

		//Populate Combobox array with names of Blueprint Kits
		for(int i=0;i<kitsbp.getKits().size();i++)
		{
			//Populate string list of names of incoming kits
			availableKits.add(kitsbp.getKits().get(i).getName());
		}
		System.out.println("Available kits size = " + availableKits.size());

		selKit.removeAllItems();
		//Add strings to the combobox component
		for(String kitty : availableKits)
		{
			selKit.addItem(kitty);
		}

		//if the incoming kit list isn't empty, make the combo box now
		if(!empty)
		{
			selKitRoutine(selKit);
			selKit.setSelectedItem(0);
		}

		if(constructed)
		{
			queuePanel.remove(queuePane);
			gridbag.setConstraints(queuePane,c);
			queuePanel.add(queuePane);
			basePanel.updateUI();
		}
	}

	void start() 
	{
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

	public static void main(String[] args)
	{
		FactoryProductionManager fpm = new FactoryProductionManager();

		fpm.setVisible(true);
		fpm.setSize(1350,700); //CONSULT PEOPLE IN CHARGE OF GRAPHICS BEFORE CHANGING THIS SIZE! 
		fpm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fpm.setResizable(false);
	}
}