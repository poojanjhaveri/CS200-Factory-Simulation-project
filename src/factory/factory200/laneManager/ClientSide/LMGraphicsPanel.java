package factory.factory200.laneManager.ClientSide;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LMGraphicsPanel extends JPanel{
		
	private LMApplication app;
	private LMDrawableAllFeeder paintAllFeeder;
	private LMDrawableAllNest paintAllNest;
	private LMDrawableAllLane paintAllLane;
	private LMDrawableAllCamera paintAllCamera;
	private LMDrawableAllPart paintAllPart;
	private LMDrawableAllBin paintAllBin;
	private ImageIcon backgroundImage = new ImageIcon( LMGraphicsPanel.class.getResource("./pics/background.png") );
	
	public LMGraphicsPanel(LMApplication app){
		setPreferredSize(new Dimension(520,700));
		this.app = app;
		
		paintAllFeeder = new LMDrawableAllFeeder();
		paintAllNest = new LMDrawableAllNest();
		paintAllLane = new LMDrawableAllLane();
		paintAllCamera = new LMDrawableAllCamera();
		paintAllPart = new LMDrawableAllPart(app, this);
		paintAllBin = new LMDrawableAllBin();
	}

	public void paint(Graphics graphics){
			backgroundImage.paintIcon(this, (Graphics2D)graphics, 0, 0);
			paintAllLane.paint(this, (Graphics2D)graphics);
			paintAllNest.paint(this, (Graphics2D)graphics);
			paintAllPart.paint(this, (Graphics2D)graphics);
			paintAllFeeder.paint(this, (Graphics2D)graphics);
			paintAllCamera.paint(this, (Graphics2D)graphics);
			paintAllBin.paint(this, (Graphics2D)graphics);
	}
	
	public LMDrawableAllFeeder getAllFeeder(){
		return paintAllFeeder;
	}
	
	public LMDrawableAllNest getAllNest(){
		return paintAllNest;
	}
	
	public LMDrawableAllLane getAllLane(){
		return paintAllLane;
	}
	
	public LMDrawableAllCamera getAllCamera(){
		return paintAllCamera;
	}
	
	public LMDrawableAllPart getAllPart(){
		return paintAllPart;
	}
	
	public LMDrawableAllBin getAllBin(){
		return paintAllBin;
	}
}