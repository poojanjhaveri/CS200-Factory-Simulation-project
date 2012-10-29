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
	@brief shows hardware status of lane
	   		   uses TitledBorder, JRadioButton, JPanel
	@author Dongyoung Jung
*/
public class GUILanePanel extends JPanel{
	
	// On/Off Switch
	private TitledBorder onOffSwitchTitle = new TitledBorder("On/Off Switch");
	private JRadioButton onSwitch = new JRadioButton("ON");
	private JRadioButton offSwitch = new JRadioButton("OFF");
	private ContentPanel onOffSwitchPanel = new ContentPanel(onSwitch, offSwitch, onOffSwitchTitle);
	
	// Vibration Amplitude Setting
	private TitledBorder vibrationAmplitudeTitle = new TitledBorder("Vibration Amplitude");
	private JSlider vibrationAmplitudeSlider = new JSlider(0,2,0);
	private Hashtable<Integer, JLabel> sliderLabels = new Hashtable<Integer, JLabel>();
	
	public GUILanePanel(){
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
	
	// Switch setting(This is decided by message from server. Not controllable by lane manager)
	public void setLaneSwitch( Boolean signal ){
		if( signal == true ){
			onSwitch.setSelected(true);
		}
		else if( signal == false ){
			offSwitch.setSelected(true);
		}
	}
	
	// Amplitude slider setting(This is decided by message from server. Not controllable by lane manager)
	public void setLaneVibrationAmplitudeStrong(){
		vibrationAmplitudeSlider.setValue(2);
	}
	public void setLaneVibrationAmplitudeNormal(){
		vibrationAmplitudeSlider.setValue(1);
	}
	public void setLaneVibrationAmplitudeWeak(){
		vibrationAmplitudeSlider.setValue(0);
	}
}