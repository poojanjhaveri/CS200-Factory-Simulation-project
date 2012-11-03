package factory.factory200.laneManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;

/**
 * Lane GUI : Switch On, Switch Off, Vibration Amplitude Strong, Vibration Amplitude Normal, Vibration Amplitude Weak
 * 
 * @brief Lane GUI
 * @author Dongyoung Jung
 */
public class LaneManagerGUILanePanel extends JPanel{
	
	// On/Off Switch
	private TitledBorder onOffSwitchTitle = new TitledBorder("On/Off Switch");
	private JRadioButton onSwitch = new JRadioButton("ON");
	private JRadioButton offSwitch = new JRadioButton("OFF");
	private LaneManagerGUIContentPanel onOffSwitchPanel = new LaneManagerGUIContentPanel(onSwitch, offSwitch, onOffSwitchTitle);
	
	// Vibration Amplitude Setting
	private TitledBorder vibrationAmplitudeTitle = new TitledBorder("Vibration Amplitude");
	private JSlider vibrationAmplitudeSlider = new JSlider(0,2,0);
	private Hashtable<Integer, JLabel> sliderLabels = new Hashtable<Integer, JLabel>();
	
	/**
	 * @brief Constructor
	 */
	public LaneManagerGUILanePanel(){
		// Layout Setting
		setLayout(new FlowLayout(FlowLayout.CENTER,0,30));

		// Add GUI contents
		add(onOffSwitchPanel);
		
		// Add GUI contents
		sliderLabels.put(new Integer(0), new JLabel("weak"));
		sliderLabels.put(new Integer(1), new JLabel("normal"));
		sliderLabels.put(new Integer(2), new JLabel("strong"));
		vibrationAmplitudeSlider.setLabelTable(sliderLabels);
		vibrationAmplitudeSlider.setPaintLabels(true);
		vibrationAmplitudeSlider.setPreferredSize(new Dimension(250,100));
		vibrationAmplitudeSlider.setEnabled(true);
		vibrationAmplitudeSlider.setBorder(vibrationAmplitudeTitle);
		vibrationAmplitudeTitle.setTitleColor(Color.red);
		add(vibrationAmplitudeSlider);
	}
	
	/**
	 * Server signal changes the switch settings.
	 * 
	 * @brief Switch Change
	 * @param signal : Signal from server
	 */
	public void setLaneSwitch( Boolean signal ){
		if( signal == true ){
			onSwitch.setSelected(true);
		}
		else if( signal == false ){
			offSwitch.setSelected(true);
		}
	}
	
	/**
	 * @brief Setter
	 */
	public void setLaneVibrationAmplitudeStrong(){
		vibrationAmplitudeSlider.setValue(2);
	}
	
	/**
	 * @brief Setter
	 */
	public void setLaneVibrationAmplitudeNormal(){
		vibrationAmplitudeSlider.setValue(1);
	}
	
	/**
	 * @brief Setter
	 */
	public void setLaneVibrationAmplitudeWeak(){
		vibrationAmplitudeSlider.setValue(0);
	}
}