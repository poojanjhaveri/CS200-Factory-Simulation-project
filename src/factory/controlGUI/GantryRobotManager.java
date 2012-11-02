package factory.controlGUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Gantry Robot Manager takes care of movement of gantry robot, bins, purge
 * station, feeders. Inner classes are GantryRobot,
 * GUIGantryManager,GraphicGantryPanel, GantryState, Feeder and Bins
 * GUIGantryManager extends a JPanel with JButtons, JMenu and JLabels
 * GraphicGantryPanel extends a JPanel which shows animation of every process
 * before feeders GantryState stores data needed to do animation for bins,
 * feeders,robot GantryRobot handles the movement of gantry robot <img
 * src="../img/image01.png" /> <img src="../img/image12.png" />
 *
 * @brief takes care of movement of gantry robot, bins, purge station, feeders.
 * @author Yuting Liu
 */
public class GantryRobotManager extends Manager {
    //TODO figure out what the cords are for the following constants
    public static final Integer FEED0X = 0;///<x-coordinate of feeder 0
    public static final Integer FEED0Y = 0;///<y-coordinate of feeder 0
    public static final Integer FEED1X = 0;///<x-coordinate of feeder 1
    public static final Integer FEED1Y = 0;///<y-coordinate of feeder 1
    public static final Integer FEED2X = 0;///<x-coordinate of feeder 2
    public static final Integer FEED2Y = 0;///<y-coordinate of feeder 2
    public static final Integer FEED3X = 0;///<x-coordinate of feeder 3
    public static final Integer FEED3Y = 0;///<y-coordinate of feeder 3

    public static final Integer DUMPX = 0;///<x-coordinate of dump 
    public static final Integer DUMPY = 0;///<y-coordinate of dump 

    public static final Integer BIN_X = 0;///<x coordinate of all bin locations
    public static final Integer BIN0Y = 0;///<y coordinate of bin0
public static final Integer BIN1Y = 0;///<y coordinate of bin1
public static final Integer BIN2Y = 0;///<y coordinate of bin2
public static final Integer BIN3Y = 0;///<y coordinate of bin3
public static final Integer BIN4Y = 0;///<y coordinate of bin4
public static final Integer BIN5Y = 0;///<y coordinate of bin5
public static final Integer BIN6Y = 0;///<y coordinate of bin6
public static final Integer BIN7Y = 0;///<y coordinate of bin7

    public static final Integer ROBOT_INITIAL_X = 0;///<spawn x coordinate of gantrybot
    public static final Integer ROBOT_INITIAL_Y = 0;///<spawn y coordinate of gantrybot

    GantryState gs;
    GraphicGantryPanel graphicPanel;///<shows the animations of the gantry robot. has bins, gantry robot, and feeders.
    GUIGantryManager gui;///<break the nonormative situations
    Socket s;///<connection to the server
    GantryRobot gbot;///<class which includes Gantry Robot Manager Methods
    int purgeStationx;//x coordinate of purgeStation
    int purgeStationy;//y coordinate of purgeStation

    public void receiveFromServer();///<pull data from server

    /**
     * @brief Inner class GUIGantryManager
     */
    public class GUIGantryManager extends JPanel implements ActionListener 

        (){

		JButton breakPurgeStation;
        JButton breakFeeder;
        JButton breakBins;
        JButton breakGantryRobot;

        public void breakPurgeStation() {
        }

        ;///<break the purge station 
		public void breakFeeder(int index) {
        }

        ;///<break a selected feeder
		public void breakBins() {
        }

        ;///<break a selected bin
		public void breakGantryRobot() {
        }

        ;///<break the gantry robot under non-normative

		/**
if actions are performed this handles it
@brief if actions are performed this handles it
		 */
		public void actionPerformed(ActionEvent ae) {
            if (ae == breakPurgeStation) {
                breakPurgeStation();
            }
            if (ae == breakFeeder) {
                breakFeeder(int 
                i
            
            );
			}
			if (ae == breakBins() {
                breakBins();
            }
            if (ae == breakGantryRobot() {
                breakGantryRobot();
            }
        }
    }

    public class GraphicGantryPanel extends JPanel {

        GraphicGantryPanel ggp;
        boolean changed;

        public GraphicGantryPanel() {//set up a Timer	
        }

        /**
         * check if Server send any message or data back
         */
        public boolean checkServerMessage() {
        }// 

        /**
         * get all coordinates from GantryState class
         */
        public resetComponent() {
        }

        ;//
		/**
paint all components: bins, purge station, feeders and the gantry robot
		 */
		public paint(Graphics g) {
        }

        ;
		public actionPerformed() {
            changed = checkServerMessage();

            if (changed) {
                resetComponents();//get information of recently changedd
            }
            repaint();
        }
    }

    public class GantryState {

        Feeder feeder;
        Bin bins;

        /**
         * update Feeder coordinate or status
         */
        public void updateFeeder() {
        }

        /**
         * update Bins coordinates or status;
         */
        public void updateBins() {
        }

        /**
         * update gantryRobot infomation
         */
        public void updateGantryRobot() {
        }

        /**
         * update purgestation. move empty bin to (purgeStationx, purgeStationy)
         */
        public void updatePurgeStation() {
        }
    }

    public class Bin {

        int corx;///<coordinate x
        int cory;///<coordinate y
        image binImage;

        public moveBin(int newx, int newy) {
        }//moveBin to the new coordinate
    }

    public class Feeder {

        int number;//feeder number
        int x;//coordinate x
        int y;//coordinate y
        image feederImage;

        /**
         * drop parts from bins to the selected number feeder
         */
        public dropPartsOn(int number) {
        }
    }
}
