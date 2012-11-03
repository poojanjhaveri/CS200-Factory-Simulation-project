package factory.factory200.laneManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
 * This JPanel is made to put switches on each tab JPanel easily.
 * There 3 constructors. ( Belows exclude TitledBorder variables )
 * 1 : No specific parameters : Part Low Sensor
 * 2 : One Integer : Part Fed Counter
 * 3 : Two JRadioButtons : Most of cases
 * 
 * @author Dongyoung Jung
 */
class LaneManagerGUIContentPanel extends JPanel{
		
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel counterLabel;
	private JLabel sensorLabel;
	private static ImageIcon redSensorImage = new ImageIcon(".//pics//sensor_red.png");
	private static ImageIcon whiteSensorImage = new ImageIcon(".//pics//sensor_white.png");
	int partsFedCounter = 0;
	
	/**
	 * @brief Constructor ( No specific parameters : Part Low Sensor )
	 * @param border : TitledBorder 
	 */
	public LaneManagerGUIContentPanel(TitledBorder border){
		setPreferredSize(new Dimension(250,70));
		setLayout(new BorderLayout());
		border.setTitleColor(Color.red);
		setBorder(border);
		
		sensorLabel = new JLabel("", JLabel.CENTER);
		sensorLabel.setIcon(whiteSensorImage);
		add(sensorLabel);
	}
	
	/**
	 * @brief Constructor ( One Integer : Part Fed Counter )
	 * @param counter : Integer
	 * @param border : TitledBorder
	 */
	public LaneManagerGUIContentPanel(int counter, TitledBorder border){
		setPreferredSize(new Dimension(250,70));
		setLayout(new BorderLayout());
		border.setTitleColor(Color.red);
		setBorder(border);
		
		counterLabel = new JLabel("" + counter, JLabel.CENTER);
		add(counterLabel);
	}
	
	/**
	 * @brief Constructor ( Two JRadioButtons : Most of cases )
	 * @param button1 : JRadioButton
	 * @param button2 : JRadioButton
	 * @param border : TitledBorder
	 */
	public LaneManagerGUIContentPanel(JRadioButton button1, JRadioButton button2, TitledBorder border){		
		setPreferredSize(new Dimension(250,70));
		setLayout(new FlowLayout(FlowLayout.CENTER, 30, 8));
		border.setTitleColor(Color.red);
		setBorder(border);
		buttonGroup.add(button1);
		buttonGroup.add(button2);
		add(button1);
		add(button2);
	}
	
	/**
	 * @brief Counter Setter
	 */
	public void setCounter(){
		counterLabel.setText("" + (++partsFedCounter) );
	}
	
	/**
	 * @brief Sensor Status Change
	 */
	public void setSensor( Boolean sensorStatus ){
		if( sensorStatus == true ){
			sensorLabel.setIcon(redSensorImage);
		}
		else if( sensorStatus == false ){
			sensorLabel.setIcon(whiteSensorImage);
		}
	}
}