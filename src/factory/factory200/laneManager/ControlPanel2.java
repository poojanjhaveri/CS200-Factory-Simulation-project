import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

// Lane
public class ControlPanel2 extends JPanel implements ActionListener{

	private ServerForAgentLane agentLane;
	
	private TitledBorder border = new TitledBorder("Lane");
	private Integer[] laneList = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private JComboBox laneNum = new JComboBox(laneList);
	private int chosenLane;
	
	private JButton b0 = new JButton("Switch On");
	private JButton b1 = new JButton("Switch Off");
	private JButton b2 = new JButton("Amplitude Strong");
	private JButton b3 = new JButton("Amplitude Normal");
	private JButton b4 = new JButton("Amplitude Weak");

	public ControlPanel2(ServerForAgentLane agentLane){
		this.agentLane = agentLane;
		setBorder(border);
		setPreferredSize(new Dimension(480,100));
		
		b0.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		add(laneNum);
		add(b0);add(b1);add(b2);add(b3);add(b4);

		updateUI();
	}
	
	public void actionPerformed(ActionEvent ae){
		chosenLane = laneList[ laneNum.getSelectedIndex() ] - 1;
		
		if( ae.getSource() == b0 ){
			agentLane.setSwitchOn( chosenLane );
		}
		
		else if( ae.getSource() == b1 ){
			agentLane.setSwitchOff( chosenLane );
		}
		
		else if( ae.getSource() == b2 ){
			agentLane.setVibrationAmplitudeStrong( chosenLane );
		}
		
		else if( ae.getSource() == b3 ){
			agentLane.setVibrationAmplitudeNormal( chosenLane );
		}
		
		else if( ae.getSource() == b4 ){
			agentLane.setVibrationAmplitudeWeak( chosenLane );
		}
	}	
}