package factory.factory200.laneManager.ClientSide;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * This class contains Non-normative scenarios(For V2)
 * 
 * @brief Non-normative Scenarios
 * @author Dongyoung Jung
 */
public class LMGUINonNormativePanel extends JPanel{

	private LMApplication app;
	
	private LMGUINonNormativePanel1 panel1;
	private LMGUINonNormativePanel2 panel2;
	
	private TitledBorder feederTitledBorder = new TitledBorder("FEEDER");
	private TitledBorder laneTitledBorder = new TitledBorder("LANE");
	
	public LMGUINonNormativePanel(LMApplication app){
		this.app = app;
		
		panel1 = new LMGUINonNormativePanel1(app);
		panel2 = new LMGUINonNormativePanel2(app);
		
		setPreferredSize(new Dimension(300,640));
		setLayout(new GridLayout(2,1));
		
		panel1.setBorder(feederTitledBorder);
		panel2.setBorder(laneTitledBorder);
		
		add(panel1);
		add(panel2);
	}
}