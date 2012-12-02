package factory.factory200.laneManager.ClientSide;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Hashtable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;

/**
 * @brief Lane GUI Panel
 * @author Dongyoung Jung
 */
public class LMGUILanePanel extends JPanel{
	
	// On/Off Switch
	private TitledBorder onOffSwitchTitle = new TitledBorder("On/Off Switch");
	private JRadioButton onSwitch = new JRadioButton("ON");
	private JRadioButton offSwitch = new JRadioButton("OFF");
	private LMGUIContentPanel onOffSwitchPanel = new LMGUIContentPanel(onSwitch, offSwitch, onOffSwitchTitle);
	
	// Vibration Amplitude Setting
	private TitledBorder vibrationAmplitudeTitle = new TitledBorder("Vibration");
	private JSlider vibrationAmplitudeSlider = new JSlider(0,1,0);
	private Hashtable<Integer, JLabel> sliderLabels = new Hashtable<Integer, JLabel>();

	public LMGUILanePanel(){
		setLayout(new GridLayout(2,1));
		add(onOffSwitchPanel);
		sliderLabels.put(new Integer(0), new JLabel("normal"));
		sliderLabels.put(new Integer(1), new JLabel("strong"));
		vibrationAmplitudeSlider.setLabelTable(sliderLabels);
		vibrationAmplitudeSlider.setPaintLabels(true);
		vibrationAmplitudeSlider.setPreferredSize(new Dimension(250,100));
		vibrationAmplitudeSlider.setEnabled(false);
		vibrationAmplitudeSlider.setBorder(vibrationAmplitudeTitle);
		vibrationAmplitudeTitle.setTitleColor(Color.red);
		add(vibrationAmplitudeSlider);
	}

	public void setLaneSwitch( Boolean signal ){
		if( signal == true ){
			onSwitch.setSelected(true);
		}
		else if( signal == false ){
			offSwitch.setSelected(true);
		}
	}
	
	public void setLaneVibrationAmplitudeStrong(){
		vibrationAmplitudeSlider.setValue(1);
	}

	public void setLaneVibrationAmplitudeNormal(){
		vibrationAmplitudeSlider.setValue(0);
	}
}