package factory.factory200.laneManager.ServerSide;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class LMControlPanel4 extends JPanel implements ActionListener{
	
	private LMCameraForAgent agentNestCamera;
	private Integer[] cameraList = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private JComboBox cameraNum = new JComboBox(cameraList);
	private TitledBorder border = new TitledBorder("Camera");
	private JButton button = new JButton("Camera Shoot");
	private int chosenCamera;
	
	public LMControlPanel4(LMCameraForAgent agentNestCamera){
		this.agentNestCamera = agentNestCamera;
		setBorder(border);
		setPreferredSize(new Dimension(235,70));
		
		add(cameraNum);
		button.addActionListener(this);
		add(button);
	}
	
	/**
	 * @brief Signal To Server( In V0, it is platform )
	 */
	public void actionPerformed(ActionEvent ae){
		chosenCamera = cameraNum.getSelectedIndex();
		
		if(ae.getSource() == button){
			agentNestCamera.cameraShoot( chosenCamera );
		}
	}
}
