package factory.controlGUI;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
/**
	@brief makes it easier to put GUIs on manager control panel
	@author Dongyoung Jung
*/
class ContentPanel extends JPanel{
	
	    ButtonGroup buttonGroup;///<disables multiple buttons to be checked simultaneously
	    JLabel counterLabel;///<shows counter of parts fed
	    JLabel sensorLabel;///<shows part low sensor
	    ImageIcon redSensorImage;///<image when sensor is on
	    ImageIcon whiteSensoImage;///<image when sensor is off
  
        public ContentPanel(TitledBorder border){}///<constructor for part low sensor GUI
        public ContentPanel(int counter, TitledBorder border){}///<constructor for part fed counter
        public ContentPanel(JRadioButton button1, JRadioButton button2, TitledBorder border){}///< constructor with two JRadioButtons
   
        void setCounter(int counter){}///<sets a new counter
        void setSensor(Boolean sensorStatus){}///<turns sensor on or off
        
}