package factory.factory200.laneManager.ClientSide;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * @brief Main Frame
 * @author Dongyoung Jung
 */
public class LMApplication extends JFrame{
	
	private LMGraphicsPanel panelGraphics;
	private LMGUIPanel panelGUI;
	private LMGUINonNormativePanel nonNormativeGUI;
	private LMSignalFromServerVerification verifyMessage;
	private JTabbedPane tab = new JTabbedPane();
	
	public LMApplication(){
		setTitle("Lane Manager");
		setSize(560,740);
		setLocation(0,0);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		setResizable(false);

		panelGraphics = new LMGraphicsPanel(this);
		panelGUI = new LMGUIPanel(this);
		nonNormativeGUI = new LMGUINonNormativePanel(this);
		verifyMessage = new LMSignalFromServerVerification(this);
		
		tab.add("Animation", panelGraphics);
		tab.add("Status",panelGUI);
		tab.add("Non-Normative", nonNormativeGUI);
		add(tab);
		
		panelGUI.updateUI();
		panelGraphics.updateUI();
	}
	
	public LMGraphicsPanel getGraphicsPanel(){
		return panelGraphics;
	}

	public LMGUIPanel getGUIPanel(){
		return panelGUI;
	}

	public LMSignalFromServerVerification getVerifyMessage(){
		return verifyMessage;
	}
}