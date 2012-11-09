package factory.controlGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import factory.agentGUI.*;

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
	public JButton start, stop, reset;
	public JLabel schedField, outField;

	public int num;
	public List<Kit> availableKits;
	

	public static void main(String[] args)
	{
		
	}

	public FactoryProductionManager()
	{
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		availableKits = new List<Kit>();

		selLabel = new JLabel ("Select Kit");
		numLabel = new JLabel ("Qty");
		consoleLabel = new JLabel ("Console Out");
		schedLabel = new JLabel ("Production Schedule");
		selKit = new JComboBox();
		numE = new JTextField(20);
		start = new JButton("Start");
		stop = new JButton("Stop");
		reset = new JButton("Reset");

		basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());
		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
		botPanel = new JPanel();
		botPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
		leftPanel = new JPanel();
		midPanel = new JPanel();
		midPanel.setLayout(gridbag);
		rightPanel = new JPanel();
		blankPanel = new JPanel();

		topPanel.add(consoleLabel);
		topPanel.add(blankPanel);
		topPanel.add(schedLabel);
		
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
    class FPMGUI extends JPanel implements ActionListener {

        JComboBox selectKit;///<
        JTextField kitAmt;///<
        JLabel prodSched;///<
        JLabel status;///<
        JButton start;///<
        JButton stop;///<
        JButton reset;///<

        /**
         * send signal to start the sim
         */
        boolean start() {
			return true;
        }

        /**
         * send signal to stop the sim
         */
        boolean stop() {
			return true;
        }

        /**
         * resets kit selection, does not start sim
         */
        void reset() {
        }

        public void actionPerformed(ActionEvent ae) {
        }
    }

    /**
     * Displays animation of entire Factory Simulation
     *
     * @brief Displays animation of entire Factory Simulation
     *
     * <img src="../img/image03.jpg" />
     * @author Matt Kane
     */
    class FPMGraphics extends JPanel implements ActionListener {

		/*
        KitRobot kr;
        PartsRobot pr;
        GantryRobot gr;
        Nest n;
        Lane l;
        Bin b;
        CameraHandler ch;
		*/

        public void actionPerformed(ActionEvent ae) {
        }

        /**
         * listen to messages or data Objects sent by server
         */
        public void listenMessage() {
        }

        public void repaint() {
        }

        /**
         * change status of robot, feeders, purge station and bins by coping
         * data from the object sent by server
         */
        void sentFromServer() {
        }
    }

    /**
     * @brief Controls execution of non-normative scenarios Controls execution
     * of non-normative scenarios
     * @author Matt Kane
     */
    class NonNormative extends JPanel {
    }
}
