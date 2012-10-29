
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

/**
 * @brief shows graphical section(actual cell)
 * @author Dongyoung Jung
 */
public class GraphicsPanel extends JLayeredPane {

    private LaneManagerApp laneManagerApp;
    // Lane
    private Lane newLane;
    private ArrayList<Lane> lanes = new ArrayList<Lane>();
    // Nest
    private Nest newNest;
    private ArrayList<Nest> nests = new ArrayList<Nest>();
    // Feeder
    private Feeder newFeeder;
    private ArrayList<Feeder> feeders = new ArrayList<Feeder>();
    // Lane, Nest, Feeder, Camera coordinates
    private final int xCoordinateLane = 60, xCoordinateNest = 30, xCoordinateFeeder = 460, xCoordinateCamera = 10;
    private int yCoordinateLane = 50, yCoordinateNest = 50, yCoordinateFeeder = 50, yCoordinateCamera = 75;
    // Camera
    private JLabel camera;
    private ArrayList<JLabel> cameras = new ArrayList<JLabel>();

    public GraphicsPanel(final int feederCount, final int laneCount, final int nestCount, final int cameraCount, LaneManagerApp laneManagerApp) {
        this.laneManagerApp = laneManagerApp;
        setLayout(null);
        setPreferredSize(new Dimension(530, 640));
        setBorder(new LineBorder(Color.black));
        setBackground(Color.white);

        // Add camera to 'cameras' ArrayList
        for (int i = 0; i < cameraCount; i++) {
            camera = new JLabel(new ImageIcon(".//pics//camera.png"));
            camera.setBounds(xCoordinateCamera, yCoordinateCamera, 80, 80);
            camera.setVisible(false);
            cameras.add(camera);
            add(camera, new Integer(2));
            yCoordinateCamera += 140;
        }

        // Add nest to 'nests' ArrayList
        for (int i = 0; i < nestCount; i++) {
            newNest = new Nest(xCoordinateNest, yCoordinateNest);
            nests.add(newNest);
            add(newNest);
            yCoordinateNest += 70;
        }

        // Add lane to 'lanes' ArrayList
        for (int i = 0; i < laneCount; i++) {
            newLane = new Lane(xCoordinateLane, yCoordinateLane, nests.get(i));
            lanes.add(newLane);
            add(newLane);
            yCoordinateLane += 70;
        }

        // Add feeder to 'feeders' ArrayList
        for (int i = 0; i < feederCount; i++) {
            // REFERENCE : lanes.get(2*i), lanes.get(2*i+1) - assign two lanes in one feeder
            newFeeder = new Feeder(xCoordinateFeeder, yCoordinateFeeder, lanes.get(2 * i), lanes.get(2 * i + 1), laneManagerApp);
            feeders.add(newFeeder);
            add(newFeeder);
            yCoordinateFeeder += 140;
        }
    }

    public Feeder getFeederArray(int feederNum) {
        return feeders.get(feederNum);
    }

    public Lane getLaneArray(int laneNum) {
        return lanes.get(laneNum);
    }

    public Nest getNestArray(int nestNum) {
        return nests.get(nestNum);
    }

    public JLabel getCameraArray(int cameraNum) {
        return cameras.get(cameraNum);
    }
}