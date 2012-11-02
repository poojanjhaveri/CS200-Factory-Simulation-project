import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

public class LaneManagerGraphicsPanel extends JLayeredPane{
	
	private LaneManagerApp app;
	
	// Lane
	private LaneManagerLane newLane;
	private ArrayList<LaneManagerLane> lanes = new ArrayList<LaneManagerLane>();
	
	// Nest
	private LaneManagerNest newNest;
	private ArrayList<LaneManagerNest> nests = new ArrayList<LaneManagerNest>();
	
	// Feeder
	private LaneManagerFeeder newFeeder;
	private ArrayList<LaneManagerFeeder> feeders = new ArrayList<LaneManagerFeeder>();
	
	// Camera
	private JLabel camera;
	private ArrayList<JLabel> cameras = new ArrayList<JLabel>();
	
	// Lane, Nest, Feeder, Camera coordinates
	private final int xCoordinateLane = 50, xCoordinateNest = 15, xCoordinateFeeder = 460, xCoordinateCamera = 0;
	private int yCoordinateLane = 25, yCoordinateNest = 5, yCoordinateFeeder = 20, yCoordinateCamera = 45;
			
	public LaneManagerGraphicsPanel(final int feederCount, final int laneCount, final int nestCount, final int cameraCount, LaneManagerApp app){
		this.app = app;
		setLayout(null);
		setPreferredSize(new Dimension(530,640));
		setBorder(new LineBorder( Color.black ));
		setBackground( Color.white );
		
		// Add camera to 'cameras' ArrayList
		for(int i=0 ; i<cameraCount ; i++){
			camera = new JLabel(new ImageIcon(".//pics//camera.png"));
			camera.setBounds(xCoordinateCamera, yCoordinateCamera, 80, 80);
			camera.setVisible(false);
			cameras.add(camera);
			add(camera, new Integer(2));
			yCoordinateCamera += 156;
		}
		
		// Add nest to 'nests' ArrayList
		for( int i=0 ; i<nestCount ; i++ ){
			newNest = new LaneManagerNest(xCoordinateNest, yCoordinateNest, i);
			nests.add(newNest);
			add(newNest);
			yCoordinateNest += 78;
		}
		
		// Add lane to 'lanes' ArrayList
		for( int i=0 ; i<laneCount ; i++ ){
			newLane = new LaneManagerLane(xCoordinateLane, yCoordinateLane, nests.get(i), i, app);
			lanes.add(newLane);
			add(newLane);
			yCoordinateLane += 78;
		}
		
		// Add feeder to 'feeders' ArrayList
		for( int i=0 ; i<feederCount ; i++ ){
			// REFERENCE : lanes.get(2*i), lanes.get(2*i+1) - assign two lanes in one feeder
			newFeeder = new LaneManagerFeeder(xCoordinateFeeder, yCoordinateFeeder, lanes.get(2*i), lanes.get(2*i+1), i);
			feeders.add(newFeeder);
			add(newFeeder, new Integer(2));
			yCoordinateFeeder += 156;
		}
	}
	
	public LaneManagerFeeder getFeederArray(int feederNum){
		return feeders.get(feederNum);
	}
	
	public LaneManagerLane getLaneArray(int laneNum){
		return lanes.get(laneNum);
	}
	
	public LaneManagerNest getNestArray(int nestNum){
		return nests.get(nestNum);
	}
	
	public JLabel getCameraArray(int cameraNum){
		return cameras.get(cameraNum);
	}
}