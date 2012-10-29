
package factory.controlGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @brief contains FeederPanel, LanePanel, and NestPanel tabs uses JTabbedPane
 * @author Dongyoung Jung
 */
public class GUIPanel extends JPanel {

    private LaneManagerApp laneManagerApp;
    private JTabbedPane tab = new JTabbedPane();
    private GUILane laneGUI;
    private GUINest nestGUI;
    private GUIFeeder feederGUI;
    private GUINonNormativePanel nonNormativeGUI;

    public GUIPanel(final int feederCount, final int laneCount, final int nestCount, LaneManagerApp laneManagerApp) {
        this.laneManagerApp = laneManagerApp;
        feederGUI = new GUIFeeder(feederCount);
        laneGUI = new GUILane(laneCount);
        nestGUI = new GUINest(nestCount);
        nonNormativeGUI = new GUINonNormativePanel();

        setPreferredSize(new Dimension(300, 640));
        setLayout(new BorderLayout());

        tab.add("Feeder", feederGUI);
        tab.add("Lane", laneGUI);
        tab.add("Nest", nestGUI);
        tab.add("Non-normative", nonNormativeGUI);
        add(tab, BorderLayout.CENTER);
    }

    public GUINest getGUINest() {
        return nestGUI;
    }

    public GUILane getGUILane() {
        return laneGUI;
    }

    public GUIFeeder getGUIFeeder() {
        return feederGUI;
    }
}
