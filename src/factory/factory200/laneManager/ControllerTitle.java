package factory.factory200.laneManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * @brief Title Panel
 * @author Dongyoung
 */
public class ControllerTitle extends JPanel{

	private JLabel title = new JLabel("Lane&Nest&Feeder Agent Controller(For V0)", JLabel.CENTER);
	
	public ControllerTitle(){
		setBorder(new LineBorder( Color.black ));
		setPreferredSize(new Dimension(480,50));
		setLayout(new BorderLayout());
		
		title.setFont(new Font("Arial", Font.BOLD, 20));
		
		add(title, BorderLayout.CENTER);
	}
}