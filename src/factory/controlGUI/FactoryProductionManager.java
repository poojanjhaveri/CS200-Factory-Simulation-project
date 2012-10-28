
/**

*/
Factory Production Manager
Class: FactoryProductionManager
Matt Kane
General description
Factory Production Manager selects active kit production routines, how many of each kit to produce, and also will control non-normative scenario simulation. This client also displays graphics representing the entire factory simulation.
Referenced classes are KitRobot, PartsRobot, GantryRobot, Nest, Lane, Bin, and CameraHandler
Inner Class FPMGUI extends JPanel and is an instance of the Factory Management GUI
Inner Class FPMGraphics extends Jpanel and is an instance of the entire Factory Graphics display
InnerClass NonNormative extends JPanel and is an instance of the FPM Non-Normative Control Panel
Data
1 JFrame with cardLayout, 3 JPanels, specifically, two swing panels and one graphical panel.
1 Socket with Server
Graphic panel has kit robot, parts robot, gantry robot, nests, lanes, bins, cameras and feeders.
CardLayout
3 Panes, FPMGUI, FPMGraphics, NonNormative
JPanel FPMGUI
JPanel NonNormative (v2)
JPanel FPMGraphics

Methods
void start() - start the factory production queue with the current kit selection

void stop() - stop the factory sim
void displayGraphics() - once user clicks this menu item, set the GUI panels invisible and the graphic panel visible
void displayFPMGUI() - once user clicks this menu item, set the NonNormative and FPMGraphics panels invisible and the FPMGUI panel visible
void displayFPMGUI() - once user clicks this menu item, set the FPMGUI and FPMGraphics panels invisible and the NonNormative panel visible
void exit() - force to exit the program

Inner Class: FPMGUI
General Description
Extends JPanel
Implements ActionListener
Controls Kit selection and Factory ON/OFF
Data
JComboBox selectKit
JTextField kitAmt
JLabel prodSched
JLabel status
JButton start
JButton stop
JButton reset
Methods
bool start() send signal to start the sim
bool stop() send signal to stop the sim
void reset() - resets kit selection, does not start sim
Image


Inner Class: FPMGraphics
General Description
Extends JPanel
Displays animation of entire Factory Simulation
Data
Instantiate each: KitRobot, PartsRobot, GantryRobot, Nest, Lane, Bin, and CameraHandler
Methods
main method - construct the graphic panel
constructor - Initialize
Set a Timer
Initialize all components in the graphic panel: ie. bins, feeders purge station and gantry Robots
actionPerformed
repaint
listen to messages or data Objects sent by server

void sentFromServer() - change status of robot, feeders, purge station and bins by coping data from the object sent by server


Inner Class: NonNormative
General Description
Extends JPanel
Controls execution of non-normative scenarios
