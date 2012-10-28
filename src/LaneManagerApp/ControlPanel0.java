import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class ControlPanel0 extends JPanel implements ActionListener{
	
	LaneManagerApp laneManagerApp;
	JButton button = new JButton("Factory Start");
	TitledBorder border = new TitledBorder("Factory Startup");
	
	public ControlPanel0(LaneManagerApp laneManagerApp){
		this.laneManagerApp = laneManagerApp;
		setBorder(border);
		setPreferredSize(new Dimension(480,70));
		
		button.addActionListener(this);
		add(button);
	}
	
	public void actionPerformed(ActionEvent ae){
		if( ae.getSource() == button ){
			// Feeder Startup ----------------------------------------------------------
			// Turn on 'feeder' switchs
			laneManagerApp.verify.verify("&Feeder&Feeder On0");
			laneManagerApp.verify.verify("&Feeder&Feeder On1");
			laneManagerApp.verify.verify("&Feeder&Feeder On2");
			laneManagerApp.verify.verify("&Feeder&Feeder On3");
			
			// 'feeder' sensor
			laneManagerApp.verify.verify("&Feeder&Part Low Sensor On0");
			laneManagerApp.verify.verify("&Feeder&Part Low Sensor On1");
			laneManagerApp.verify.verify("&Feeder&Part Low Sensor On2");
			laneManagerApp.verify.verify("&Feeder&Part Low Sensor On3");
			
			// Turn on 'feed parts' switch
			laneManagerApp.verify.verify("&Feeder&Feed Parts Switch On0");
			laneManagerApp.verify.verify("&Feeder&Feed Parts Switch On1");
			laneManagerApp.verify.verify("&Feeder&Feed Parts Switch On2");
			laneManagerApp.verify.verify("&Feeder&Feed Parts Switch On3");
			
			// Raise rear gate
			laneManagerApp.verify.verify("&Feeder&Raised Rear Gate0");
			laneManagerApp.verify.verify("&Feeder&Raised Rear Gate1");
			laneManagerApp.verify.verify("&Feeder&Raised Rear Gate2");
			laneManagerApp.verify.verify("&Feeder&Raised Rear Gate3");
			
			// Turn on 'purge bin' switch
			laneManagerApp.verify.verify("&Feeder&Purge Bin Switch On0");
			laneManagerApp.verify.verify("&Feeder&Purge Bin Switch On1");
			laneManagerApp.verify.verify("&Feeder&Purge Bin Switch On2");
			laneManagerApp.verify.verify("&Feeder&Purge Bin Switch On3");
			
			// Turn feeder to divert to right
			laneManagerApp.verify.verify("&Feeder&Divert To Right0");
			laneManagerApp.verify.verify("&Feeder&Divert To Right1");
			laneManagerApp.verify.verify("&Feeder&Divert To Right2");
			laneManagerApp.verify.verify("&Feeder&Divert To Right3");
			// -----------------------------------------------------------------------------------
			
			// Lane Startup --------------------------------------------------------------
			// Turn on 'lane' switch
			laneManagerApp.verify.verify("&Lane&Switch On0");
			laneManagerApp.verify.verify("&Lane&Switch On1");
			laneManagerApp.verify.verify("&Lane&Switch On2");
			laneManagerApp.verify.verify("&Lane&Switch On3");
			laneManagerApp.verify.verify("&Lane&Switch On4");
			laneManagerApp.verify.verify("&Lane&Switch On5");
			laneManagerApp.verify.verify("&Lane&Switch On6");
			laneManagerApp.verify.verify("&Lane&Switch On7");
			laneManagerApp.controller.panel2.threads.get(0).timer.start();
			laneManagerApp.controller.panel2.threads.get(1).timer.start();
			laneManagerApp.controller.panel2.threads.get(2).timer.start();
			laneManagerApp.controller.panel2.threads.get(3).timer.start();
			laneManagerApp.controller.panel2.threads.get(4).timer.start();
			laneManagerApp.controller.panel2.threads.get(5).timer.start();
			laneManagerApp.controller.panel2.threads.get(6).timer.start();
			laneManagerApp.controller.panel2.threads.get(7).timer.start();
			
			// Set up amplitude weak
			laneManagerApp.verify.verify("&Lane&Amplitude Weak0");
			laneManagerApp.verify.verify("&Lane&Amplitude Weak1");
			laneManagerApp.verify.verify("&Lane&Amplitude Weak2");
			laneManagerApp.verify.verify("&Lane&Amplitude Weak3");
			laneManagerApp.verify.verify("&Lane&Amplitude Weak4");
			laneManagerApp.verify.verify("&Lane&Amplitude Weak5");
			laneManagerApp.verify.verify("&Lane&Amplitude Weak6");
			laneManagerApp.verify.verify("&Lane&Amplitude Weak7");
			// -----------------------------------------------------------------------------------
			
			// Nest Startup --------------------------------------------------------------
			// Set up nest up
			laneManagerApp.verify.verify("&Nest&Switch Up0");
			laneManagerApp.verify.verify("&Nest&Switch Up1");
			laneManagerApp.verify.verify("&Nest&Switch Up2");
			laneManagerApp.verify.verify("&Nest&Switch Up3");
			laneManagerApp.verify.verify("&Nest&Switch Up4");
			laneManagerApp.verify.verify("&Nest&Switch Up5");
			laneManagerApp.verify.verify("&Nest&Switch Up6");
			laneManagerApp.verify.verify("&Nest&Switch Up7");
			// -----------------------------------------------------------------------------------
		}
	}
}
