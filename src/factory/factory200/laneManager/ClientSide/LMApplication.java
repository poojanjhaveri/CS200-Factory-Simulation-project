package factory.factory200.laneManager.ClientSide;

import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 * @brief Main Frame
 * @author Dongyoung Jung
 */
public class LMApplication extends JFrame{
	
	private LMGraphicsPanel panelGraphics;
	private LMGUIPanel panelGUI;
	private LMSignalFromServerVerification verifyMessage;
	
	public LMApplication(){
		setTitle("Lane Manager");
		setSize(850,720);
		setLocation(0,0);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		setResizable(false);

		panelGraphics = new LMGraphicsPanel(this);
		panelGUI = new LMGUIPanel();
		verifyMessage = new LMSignalFromServerVerification(this);
		
		add( panelGraphics );
		add( panelGUI );
		
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