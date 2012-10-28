import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

// Lane
public class ControlPanel2 extends JPanel implements ActionListener{

	LaneManagerApp laneManagerApp;
	TitledBorder border = new TitledBorder("Lane");
	Integer[] laneList = { 0, 1, 2, 3, 4, 5, 6, 7 };
	JComboBox laneNum = new JComboBox(laneList);
	ArrayList<LaneThread> threads = new ArrayList<LaneThread>();
	String message = "";
	int chosenLane;
	
	JButton b0 = new JButton("Switch On");
	JButton b1 = new JButton("Switch Off");
	JButton b2 = new JButton("Amplitude Strong");
	JButton b3 = new JButton("Amplitude Normal");
	JButton b4 = new JButton("Amplitude Weak");
	
	LaneThread thread;
	
	public ControlPanel2(LaneManagerApp laneManagerApp){
		this.laneManagerApp = laneManagerApp;
		setBorder(border);
		setPreferredSize(new Dimension(480,100));
		
		b0.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		add(laneNum);
		add(b0);add(b1);add(b2);add(b3);add(b4);
		for(int i=0 ; i<8 ; i++){
			thread = new LaneThread(i);
			new Thread(thread).start();
			threads.add(thread);
		}
		
		updateUI();
	}
	
	public void actionPerformed(ActionEvent ae){
		message = "&Lane&";
		chosenLane = laneList[ laneNum.getSelectedIndex() ];
		
		// DONE
		if( ae.getSource() == b0 ){
			message += "Switch On" + chosenLane;
			laneManagerApp.verify.verify(message);
			threads.get( chosenLane ).timer.start();
		}
		// DONE
		else if( ae.getSource() == b1 ){
			message += "Switch Off" + chosenLane;
			laneManagerApp.verify.verify(message);
			threads.get( chosenLane ).timer.stop();
		}
		// DONE
		else if( ae.getSource() == b2 ){
			message += "Amplitude Strong" + chosenLane;
			laneManagerApp.verify.verify(message);
		}
		// DONE
		else if( ae.getSource() == b3 ){
			message += "Amplitude Normal" + chosenLane;
			laneManagerApp.verify.verify(message);
		}
		// DONE
		else if( ae.getSource() == b4 ){
			message += "Amplitude Weak" + chosenLane;
			laneManagerApp.verify.verify(message);
		}
	}	
	
	class LaneThread implements Runnable{
		
		Timer timer = new Timer(50,new timerRunning());
		ArrayList<Part> parts = new ArrayList<Part>();
		int laneNum;
		
		public LaneThread(int laneNum){
			this.laneNum = laneNum;
		}
		
		public void run(){}
		
		class timerRunning extends JFrame implements ActionListener{
			public void actionPerformed(ActionEvent ae){
				// Lane Move
				laneManagerApp.verify.verify("&Lane&Lane Move" + laneNum);
			}
		}
	}
}