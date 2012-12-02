package factory.factory200.laneManager.ClientSide;

import java.awt.GridLayout;
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

	// Feed Parts Switch
	private TitledBorder feedPartsSwitchTitle = new TitledBorder("Feed Parts Switch");
	private JRadioButton onFeedPartsSwitch = new JRadioButton("ON");
	private JRadioButton offFeedPartsSwitch = new JRadioButton("OFF");
	private LMGUIContentPanel feedPartsSwitchPanel = new LMGUIContentPanel(onFeedPartsSwitch, offFeedPartsSwitch, feedPartsSwitchTitle);
	
	// Part Fed Counter
	private TitledBorder partFedCounterTitle = new TitledBorder("Parts Fed Counter");
	private int partFedCounter = 0;
	private LMGUIContentPanel partFedCounterPanel = new LMGUIContentPanel(partFedCounter, partFedCounterTitle);

	// Diverter Left/Right Switch
	private TitledBorder diverterLeftRightSwitchTitle = new TitledBorder("Diverter Left/Right");
	private JRadioButton leftDiverterSwitch = new JRadioButton("LEFT");
	private JRadioButton rightDiverterSwitch = new JRadioButton("RIGHT");
	private LMGUIContentPanel diverterLeftRightSwitchPanel = new LMGUIContentPanel(leftDiverterSwitch, rightDiverterSwitch, diverterLeftRightSwitchTitle);

	public LMGUIFeederPanel(){
		setLayout(new GridLayout(4,1));
		add(feederSwitchPanel);
		add(feedPartsSwitchPanel);
		add(partFedCounterPanel);
		add(diverterLeftRightSwitchPanel);
		setDivertToRight();
	}

	public void setFeederSwitchOn(){
		onFeederSwitch.setSelected(true);
	}

	public void setFeederSwitchOff(){
		offFeederSwitch.setSelected(true);
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

	public void setDivertToLeft(){
		 leftDiverterSwitch.setSelected(true);
	}

	public void setDivertToRight(){
		 rightDiverterSwitch.setSelected(true);
	}
}
