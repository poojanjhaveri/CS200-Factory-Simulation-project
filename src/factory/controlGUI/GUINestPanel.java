
package factory.controlGUI;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
<<<<<<< HEAD
 * @brief shows hardware status of lane uses TitledBorder, JRadioButton, JPanel
 * @author Dongyoung Jung
 */
public class GUINestPanel extends JPanel {

    // Up/Down Switch
    private TitledBorder upDownSwitchTitle = new TitledBorder("Up/Down Switch");
    private JRadioButton upSwitch = new JRadioButton("UP");
    private JRadioButton downSwitch = new JRadioButton("DOWN");
    private ContentPanel upDownSwitchPanel = new ContentPanel(downSwitch, upSwitch, upDownSwitchTitle);

    public GUINestPanel() {
        // Layout Setting
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        // Add GUI contents
        add(upDownSwitchPanel);
    }

    // Switch setting(This is decided by message from server. Not controllable by lane manager)
    public void setNestSwitch(Boolean signal) {
        if (signal == true) {
            upSwitch.setSelected(true);
        } else if (signal == false) {
            downSwitch.setSelected(true);
        }
    }
=======
	@brief shows hardware status of lane
			   uses TitledBorder, JRadioButton, JPanel
	@author Dongyoung Jung
*/
public class GuiNestPanel extends JPanel{
	
	// Up/Down Switch
	private TitledBorder upDownSwitchTitle = new TitledBorder("Up/Down Switch");
	private JRadioButton upSwitch = new JRadioButton("UP");
	private JRadioButton downSwitch = new JRadioButton("DOWN");
	private ContentPanel upDownSwitchPanel = new ContentPanel(downSwitch, upSwitch, upDownSwitchTitle);
	
	public GuiNestPanel(){
		// Layout Setting
		setLayout(new FlowLayout(FlowLayout.CENTER,0,15));
		
		// Add GUI contents
		add(upDownSwitchPanel);
	}
	
	// Switch setting(This is decided by message from server. Not controllable by lane manager)
	public void setNestSwitch( Boolean signal ){
		if( signal == true ){
			upSwitch.setSelected(true);
		}
		else if( signal == false ){
			downSwitch.setSelected(true);
		}
	}
>>>>>>> e55bea809a4f01ab018a7b8b99f6378440c1052c
}
