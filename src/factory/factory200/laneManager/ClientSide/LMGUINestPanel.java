package factory.factory200.laneManager.ClientSide;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
 * Nest GUI : Switch Up, Switch Down
 * 
 * @brief Nest GUI
 * @author Dongyoung Jung
 */
public class LMGUINestPanel extends JPanel{
	
	// Up/Down Switch
	private TitledBorder upDownSwitchTitle = new TitledBorder("Up/Down Switch");
	private JRadioButton upSwitch = new JRadioButton("UP");
	private JRadioButton downSwitch = new JRadioButton("DOWN");
	private LMGUIContentPanel upDownSwitchPanel = new LMGUIContentPanel(downSwitch, upSwitch, upDownSwitchTitle);
	
	/**
	 * @brief Constructor
	 */
	public LMGUINestPanel(){
		// Layout Setting
		setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
		
		// Add GUI contents
		add(upDownSwitchPanel);
	}
	
	/**
	 * Server signal changes the switch settings.
	 * 
	 * @brief Switch Change
	 * @param signal : Signal from server
	 */
	public void setNestSwitch( Boolean signal ){
		if( signal == true ){
			upSwitch.setSelected(true);
		}
		else if( signal == false ){
			downSwitch.setSelected(true);
		}
	}
}
