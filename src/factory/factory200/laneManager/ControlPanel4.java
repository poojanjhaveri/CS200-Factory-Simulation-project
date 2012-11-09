package factory.factory200.laneManager;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * @brief Control Panel For Camera
 * @author Dongyoung Jung
 */
public class ControlPanel4 extends JPanel implements ActionListener{
	
	private ServerForAgentNestCamera agentNestCamera;
	private Integer[] cameraList = { 1, 2, 3, 4 };
	private JComboBox cameraNum = new JComboBox(cameraList);
	private TitledBorder border = new TitledBorder("Camera");
	private JButton button = new JButton("Camera Shoot");
	private int chosenCamera;
	
	public ControlPanel4(ServerForAgentNestCamera agentNestCamera){
		this.agentNestCamera = agentNestCamera;
		setBorder(border);
		setPreferredSize(new Dimension(480,70));
		
		add(cameraNum);
		button.addActionListener(this);
		add(button);
	}
	
	/**
	 * @brief Signal To Server( In V0, it is platform )
	 */
	public void actionPerformed(ActionEvent ae){
		chosenCamera = cameraList[ cameraNum.getSelectedIndex() ] - 1;
		
		if(ae.getSource() == button){
			agentNestCamera.cameraShoot( chosenCamera );
		}
	}
}
