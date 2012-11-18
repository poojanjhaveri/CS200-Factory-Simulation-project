package factory.factory200.laneManager.ServerSide;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class LMControlPanel7 extends JPanel{
	
	LMServerMain serverMain;
	
	JLabel f1 = new JLabel("Feeder1");
	JLabel f2 = new JLabel("Feeder2");
	JLabel f3 = new JLabel("Feeder3");
	JLabel f4 = new JLabel("Feeder4");
	JLabel f10 = new JLabel("0");
	JLabel f20 = new JLabel("0");
	JLabel f30 = new JLabel("0");
	JLabel f40 = new JLabel("0");
	
	JLabel l1 = new JLabel("L1");
	JLabel l2 = new JLabel("L2");
	JLabel l3 = new JLabel("L3");
	JLabel l4 = new JLabel("L4");
	JLabel l5 = new JLabel("L5");
	JLabel l6 = new JLabel("L6");
	JLabel l7 = new JLabel("L7");
	JLabel l8 = new JLabel("L8");
	JLabel l10 = new JLabel("0");
	JLabel l20 = new JLabel("0");
	JLabel l30 = new JLabel("0");
	JLabel l40 = new JLabel("0");
	JLabel l50 = new JLabel("0");
	JLabel l60 = new JLabel("0");
	JLabel l70 = new JLabel("0");
	JLabel l80 = new JLabel("0");
	
	JLabel n1 = new JLabel("N1");
	JLabel n2 = new JLabel("N2");
	JLabel n3 = new JLabel("N3");
	JLabel n4 = new JLabel("N4");
	JLabel n5 = new JLabel("N5");
	JLabel n6 = new JLabel("N6");
	JLabel n7 = new JLabel("N7");
	JLabel n8 = new JLabel("N8");
	JLabel n10 = new JLabel("0");
	JLabel n20 = new JLabel("0");
	JLabel n30 = new JLabel("0");
	JLabel n40 = new JLabel("0");
	JLabel n50 = new JLabel("0");
	JLabel n60 = new JLabel("0");
	JLabel n70 = new JLabel("0");
	JLabel n80 = new JLabel("0");	
	
	JPanel panel1 = new JPanel(new GridLayout(1,8));
	JPanel panel2 = new JPanel(new GridLayout(1,12));
	JPanel panel3 = new JPanel(new GridLayout(1,12));
	
	updateThread thread;
	
	private TitledBorder border = new TitledBorder("Status");
	
	public LMControlPanel7(LMServerMain serverMain){
		this.serverMain = serverMain;
		setBorder(border);
		setPreferredSize(new Dimension(480,150));
		setLayout(new GridLayout(3,1));
		
		panel1.setBorder(new LineBorder(Color.black));
		panel2.setBorder(new LineBorder(Color.black));
		panel3.setBorder(new LineBorder(Color.black));
		
		add(panel1); add(panel2); add(panel3);
		
		panel1.add(f1);panel1.add(f10);panel1.add(f2);panel1.add(f20);panel1.add(f3);panel1.add(f30);panel1.add(f4);panel1.add(f40);
		panel2.add(l1);panel2.add(l10);panel2.add(l2);panel2.add(l20);panel2.add(l3);panel2.add(l30);panel2.add(l4);panel2.add(l40);
		panel2.add(l5);panel2.add(l50);panel2.add(l6);panel2.add(l60);panel2.add(l7);panel2.add(l70);panel2.add(l8);panel2.add(l80);
		panel3.add(n1);panel3.add(n10);panel3.add(n2);panel3.add(n20);panel3.add(n3);panel3.add(n30);panel3.add(n4);panel3.add(n40);
		panel3.add(n5);panel3.add(n50);panel3.add(n6);panel3.add(n60);panel3.add(n7);panel3.add(n70);panel3.add(n8);panel3.add(n80);
		
		thread = new updateThread();
		new Thread(thread).start();
	}
	
	class updateThread implements Runnable{
		public updateThread(){}
		
		public void run(){
			while(true){
				f10.setText( "" + serverMain.getPartData().getFeederPartSize(0) );
				f20.setText( "" + serverMain.getPartData().getFeederPartSize(1) );
				f30.setText( "" + serverMain.getPartData().getFeederPartSize(2) );
				f40.setText( "" + serverMain.getPartData().getFeederPartSize(3) );
				
				l10.setText( "" + serverMain.getPartData().getLanePartSize(0) );
				l20.setText( "" + serverMain.getPartData().getLanePartSize(1) );
				l30.setText( "" + serverMain.getPartData().getLanePartSize(2) );
				l40.setText( "" + serverMain.getPartData().getLanePartSize(3) );
				l50.setText( "" + serverMain.getPartData().getLanePartSize(4) );
				l60.setText( "" + serverMain.getPartData().getLanePartSize(5) );
				l70.setText( "" + serverMain.getPartData().getLanePartSize(6) );
				l80.setText( "" + serverMain.getPartData().getLanePartSize(7) );
				
				n10.setText( "" + serverMain.getPartData().getNestPartSize(0) );
				n20.setText( "" + serverMain.getPartData().getNestPartSize(1) );
				n30.setText( "" + serverMain.getPartData().getNestPartSize(2) );
				n40.setText( "" + serverMain.getPartData().getNestPartSize(3) );
				n50.setText( "" + serverMain.getPartData().getNestPartSize(4) );
				n60.setText( "" + serverMain.getPartData().getNestPartSize(5) );
				n70.setText( "" + serverMain.getPartData().getNestPartSize(6) );
				n80.setText( "" + serverMain.getPartData().getNestPartSize(7) );
			}
		}
	}
}
