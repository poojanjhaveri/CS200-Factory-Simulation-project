package factory.factory200.laneManager.ClientSide;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
 * @brief Nest GUI Panel
 * @author Dongyoung Jung
 */
public class LMGUINestPanel extends JPanel{
	
	// Up/Down Switch
	private TitledBorder upDownSwitchTitle = new TitledBorder("Up/Down Switch");
	private JRadioButton upSwitch = new JRadioButton("UP");
	private JRadioButton downSwitch = new JRadioButton("DOWN");
	private LMGUIContentPanel upDownSwitchPanel = new LMGUIContentPanel(downSwitch, upSwitch, upDownSwitchTitle);

	public LMGUINestPanel(){
		setLayout(new GridLayout(1,2));
		add(upDownSwitchPanel);
		setNestSwitch(true);
	}

	public void setNestSwitch( boolean signal ){
		if( signal == true ){
			upSwitch.setSelected(true);
		}
		else if( signal == false ){
			downSwitch.setSelected(true);
		}
	}
}
