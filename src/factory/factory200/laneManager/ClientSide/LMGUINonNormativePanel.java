package factory.factory200.laneManager.ClientSide;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
	private LMGUINonNormativePanel3 panel3;
	
	private TitledBorder feederTitledBorder = new TitledBorder("FEEDER");
	private TitledBorder laneTitledBorder = new TitledBorder("LANE");
	private TitledBorder nestTitledBorder = new TitledBorder("NEST");
	
	public LMGUINonNormativePanel(LMApplication app){
		this.app = app;
		
		panel1 = new LMGUINonNormativePanel1(app);
		panel2 = new LMGUINonNormativePanel2(app);
		panel3 = new LMGUINonNormativePanel3(app);
		
		setPreferredSize(new Dimension(300,640));
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		panel1.setBorder(feederTitledBorder);
		panel2.setBorder(laneTitledBorder);
		panel3.setBorder(nestTitledBorder);
		
		add(panel1);
		add(panel2);
		add(panel3);
	}
}
