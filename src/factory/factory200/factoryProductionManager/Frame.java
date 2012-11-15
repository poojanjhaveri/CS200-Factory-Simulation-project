package factory.factory200.factoryProductionManager;

import javax.swing.JFrame;

public class Frame extends JFrame {

	GraphicsPanel graphicsPanel = new GraphicsPanel();
	
	public Frame(){
		setSize(1350,700);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setVisible(true);
		setLocationRelativeTo(null);
		
		add(graphicsPanel);
	}
	
	public static void main(String[] args){
		new Frame();
	}
}
