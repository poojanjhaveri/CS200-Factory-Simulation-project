//package factory.controlGUI;
//import factory.agentGUI.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;


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
public class FactoryProductionManager extends JFrame implements ActionListener {
	public JPanel basePanel, topPanel, botPanel, leftPanel, midPanel, rightPanel, blankPanel;
	public JLabel selLabel, numLabel, consoleLabel, schedLabel;
	public JComboBox selKit;
	public JTextField numE;
	public JButton queueue, start, stop, reset;
	public JTextArea schedField, outField;
	public JScrollPane schedPane, outPane;

	public String kitToAdd;
	public int qtyToAdd;
	public int num;
	public ArrayList<String> availableKits;
	public ArrayList<String> selectedKits;

	private final static String newline = "\n";
	

	public static void main(String[] args)
	{
		FactoryProductionManager fpm = new FactoryProductionManager();
		
		fpm.setVisible(true);
		fpm.setSize(1000,600);
		fpm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public FactoryProductionManager()
	{
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		/*
		availableKits = new ArrayList<String>();

		availableKits.add("Uno");
		availableKits.add("Dos");
		availableKits.add("Tres");
		*/

		selectedKits = new ArrayList<String>();

		String[] testKits = { "Uno", "Dos", "Tres" };


		kitToAdd = "Uno";
		selLabel = new JLabel ("Select Kit");
		numLabel = new JLabel ("Qty");
		consoleLabel = new JLabel ("Console Out");
		schedLabel = new JLabel ("Production Schedule");
		schedField = new JTextArea(40, 20);
		outField = new JTextArea(40, 20);
		schedPane = new JScrollPane(schedField);
		outPane = new JScrollPane(outField);
		schedField.setEditable(false);
		outField.setEditable(false);
		selKit = new JComboBox(testKits);
		selKit.setSelectedIndex(0);
		numE = new JTextField(20);
		numE.setPreferredSize(new Dimension(60,10));
		queueue = new JButton("Add Kits");
		start = new JButton("Start");
		stop = new JButton("Stop");
		reset = new JButton("Reset");

		/*
		for(String kitty : availableKits)
		{
			selKit.addItem((Object)kitty);
		}
		*/

		basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());

		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
		botPanel = new JPanel();
		botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.LINE_AXIS));
		leftPanel = new JPanel();
		midPanel = new JPanel();
		midPanel.setLayout(gridbag);
		rightPanel = new JPanel();
		blankPanel = new JPanel();

		topPanel.add(schedLabel);
		topPanel.add(blankPanel);
		topPanel.add(consoleLabel);
		
		leftPanel.add(schedPane);
		leftPanel.setPreferredSize(new Dimension(320, 600));

		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 1.0;
		//c.weighty = 0.5;
		c.ipadx = 10;
		//c.ipady = 25;
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
		gridbag.setConstraints(start, c);
		midPanel.add(start);
		gridbag.setConstraints(stop, c);
		midPanel.add(stop);
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
		

		add(basePanel);

		selKit.addActionListener(this);
		queueue.addActionListener(this);
		start.addActionListener(this);
		stop.addActionListener(this);
		reset.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == selKit)
		{
			JComboBox cb = (JComboBox)ae.getSource();
			kitToAdd = (String)cb.getSelectedItem();
			System.out.println(kitToAdd);
			System.out.println(numE.getText());
		}
		if(ae.getSource() == queueue)
		{
			if(numE.getText() != "")
			{
				try {
					Integer.parseInt(numE.getText());
					qtyToAdd = Integer.parseInt(numE.getText());
					for(int i=0;i<qtyToAdd;i++)
					{
						System.out.println(kitToAdd);
						selectedKits.add(kitToAdd);
						schedField.append(kitToAdd + newline);
					}
				}
				catch (NumberFormatException nfe) {
					
				}
				
			}
		}
	}

    /**
     * start the factory production queue with the current kit selection
     */
    void start() {
		
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

    /**
     * @brief Controls Kit selection and Factory ON/OFF Controls Kit selection
     * and Factory ON/OFF <img src="../img/image08.jpg" />
     * @author Matt Kane
     */
}
