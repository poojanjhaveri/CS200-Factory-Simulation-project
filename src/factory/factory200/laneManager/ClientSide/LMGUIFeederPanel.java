package factory.factory200.laneManager.ClientSide;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
 * @brief Feeder GUI Panel
 * @author Dongyoung Jung
 */
public class LMGUIFeederPanel extends JPanel{

	// On/Off Switch
	private TitledBorder feederSwitchTitle = new TitledBorder("Feeder Switch");
	private JRadioButton onFeederSwitch = new JRadioButton("ON");
	private JRadioButton offFeederSwitch = new JRadioButton("OFF");
	private LMGUIContentPanel feederSwitchPanel = new LMGUIContentPanel(onFeederSwitch, offFeederSwitch, feederSwitchTitle);
	
	// Part Low Switch
	private TitledBorder partLowSensorTitle = new TitledBorder("Part Low Sensor");
	private LMGUIContentPanel partLowSensorPanel = new LMGUIContentPanel(partLowSensorTitle);
	
	// Feed Parts Switch
	private TitledBorder feedPartsSwitchTitle = new TitledBorder("Feed Parts Switch");
	private JRadioButton onFeedPartsSwitch = new JRadioButton("ON");
	private JRadioButton offFeedPartsSwitch = new JRadioButton("OFF");
	private LMGUIContentPanel feedPartsSwitchPanel = new LMGUIContentPanel(onFeedPartsSwitch, offFeedPartsSwitch, feedPartsSwitchTitle);
	
	// Part Fed Counter
	private TitledBorder partFedCounterTitle = new TitledBorder("Parts Fed Counter");
	private int partFedCounter = 0;
	private LMGUIContentPanel partFedCounterPanel = new LMGUIContentPanel(partFedCounter, partFedCounterTitle);
	
	// Lower/Raise Rear Gate Switch
	private TitledBorder lowerRaiseRearGateSwitchTitle = new TitledBorder("Rear Gate Lower/Raise Gate Switch");
	private JRadioButton onLowerRaiseRearGateSwitch = new JRadioButton("RAISE");
	private JRadioButton offLowerRaiseRearGateSwitch = new JRadioButton("LOWER");
	private LMGUIContentPanel lowerRaiseRearGateSwitchPanel = new LMGUIContentPanel(onLowerRaiseRearGateSwitch, offLowerRaiseRearGateSwitch, lowerRaiseRearGateSwitchTitle);
	
	// Purge Bin Switch
	private TitledBorder purgeBinSwitchTitle = new TitledBorder("Purge Bin");
	private JRadioButton onPurgeBinSwitch = new JRadioButton("ON");
	private JRadioButton offPurgeBinSwitch = new JRadioButton("OFF");
	private LMGUIContentPanel purgeBinSwitchPanel = new LMGUIContentPanel(onPurgeBinSwitch, offPurgeBinSwitch, purgeBinSwitchTitle);
	
	// Diverter Left/Right Switch
	private TitledBorder diverterLeftRightSwitchTitle = new TitledBorder("Diverter Left/Right");
	private JRadioButton leftDiverterSwitch = new JRadioButton("LEFT");
	private JRadioButton rightDiverterSwitch = new JRadioButton("RIGHT");
	private LMGUIContentPanel diverterLeftRightSwitchPanel = new LMGUIContentPanel(leftDiverterSwitch, rightDiverterSwitch, diverterLeftRightSwitchTitle);

	public LMGUIFeederPanel(){
		// Layout Setting
		setLayout(new FlowLayout(FlowLayout.CENTER,0,11));
		
		// Add GUI contents
		add(feederSwitchPanel);
		add(partLowSensorPanel);
		add(feedPartsSwitchPanel);
		add(partFedCounterPanel);
		add(lowerRaiseRearGateSwitchPanel);
		add(purgeBinSwitchPanel);
		add(diverterLeftRightSwitchPanel);
		
		setPurgeBinSwitchOff();
		setLowerRaiseRearGateSwitchOff();
		setDivertToRight();
	}

	public void setFeederSwitchOn(){
		onFeederSwitch.setSelected(true);
	}

	public void setFeederSwitchOff(){
		offFeederSwitch.setSelected(true);
	}

	public void setFeederPartLowSensor(){
		partLowSensorPanel.setSensor(true);
	}

	public void setFeederPartHighSensor(){
		partLowSensorPanel.setSensor(false);
	}

	public void setFeederFeedPartsSwitchOn(){
		onFeedPartsSwitch.setSelected(true);
	}

	public void setFeederFeedPartsSwitchOff(){
		offFeedPartsSwitch.setSelected(true);
	}

	public void setPartFedCounterIncrease(){
		partFedCounterPanel.setCounter();
	}

	public void setLowerRaiseRearGateSwitchOn(){
		onLowerRaiseRearGateSwitch.setSelected(true);
	}

	public void setLowerRaiseRearGateSwitchOff(){
		offLowerRaiseRearGateSwitch.setSelected(true);
	}

	public void setPurgeBinSwitchOn(){
		onPurgeBinSwitch.setSelected(true);
	}

	public void setPurgeBinSwitchOff(){
		offPurgeBinSwitch.setSelected(true);
	}

	public void setDivertToLeft(){
		 leftDiverterSwitch.setSelected(true);
	}

	public void setDivertToRight(){
		 rightDiverterSwitch.setSelected(true);
	}
}
