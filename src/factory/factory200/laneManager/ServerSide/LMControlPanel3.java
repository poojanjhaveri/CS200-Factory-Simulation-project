package factory.factory200.laneManager.ServerSide;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * @brief Control Panel For Nest
 * @author Dongyoung Jung
 */
public class LMControlPanel3 extends JPanel implements ActionListener{

	private LMNestForAgent agentNest;
	private TitledBorder border = new TitledBorder("Nest");
	private Integer[] nestList = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private JComboBox nestNum = new JComboBox(nestList);
	private int chosenNest;
	
	private JButton b0 = new JButton("Switch Up");
	private JButton b1 = new JButton("Switch Down");
	
	public LMControlPanel3(LMNestForAgent agentNest){
		this.agentNest = agentNest;
		setBorder(border);
		setPreferredSize(new Dimension(480,70));
		
		b0.addActionListener(this);
		b1.addActionListener(this);
		
		add(nestNum);
		add(b0);
		add(b1);
		
		updateUI();
	}
	
	/**
	 * @brief Signal To Server( In V0, it is platform )
	 */
	public void actionPerformed(ActionEvent ae){
		chosenNest = nestNum.getSelectedIndex();
		
		if(ae.getSource() == b0){
			agentNest.setSwitchUp( chosenNest );
		}
		
		else if(ae.getSource() == b1){
			agentNest.setSwitchDown( chosenNest );
		}
	}
}
