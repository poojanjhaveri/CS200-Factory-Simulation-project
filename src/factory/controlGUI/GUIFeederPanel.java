
package factory.controlGUI;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
 * @brief shows hardware status of feeder uses TitledBorder, JRadioButton,
 * JPanel
 * @author Dongyoung Jung
 */
public class GUIFeederPanel extends JPanel {

    // On/Off Switch
    private TitledBorder feederSwitchTitle = new TitledBorder("Feeder Switch");
    private JRadioButton onFeederSwitch = new JRadioButton("ON");
    private JRadioButton offFeederSwitch = new JRadioButton("OFF");
    private ContentPanel feederSwitchPanel = new ContentPanel(onFeederSwitch, offFeederSwitch, feederSwitchTitle);
    // Part Low Switch
    private TitledBorder partLowSensorTitle = new TitledBorder("Part Low Sensor");
    private ContentPanel partLowSensorPanel = new ContentPanel(partLowSensorTitle);
    // Feed Parts Switch
    private TitledBorder feedPartsSwitchTitle = new TitledBorder("Feed Parts Switch");
    private JRadioButton onFeedPartsSwitch = new JRadioButton("ON");
    private JRadioButton offFeedPartsSwitch = new JRadioButton("OFF");
    private ContentPanel feedPartsSwitchPanel = new ContentPanel(onFeedPartsSwitch, offFeedPartsSwitch, feedPartsSwitchTitle);
    // Part Fed Counter
    private TitledBorder partFedCounterTitle = new TitledBorder("Parts Fed Counter");
    private int partFedCounter = 0;
    private ContentPanel partFedCounterPanel = new ContentPanel(partFedCounter, partFedCounterTitle);
    // Lower/Raise Rear Gate Switch
    private TitledBorder lowerRaiseRearGateSwitchTitle = new TitledBorder("Rear Gate Lower/Raise Gate Switch");
    private JRadioButton onLowerRaiseRearGateSwitch = new JRadioButton("RAISE");
    private JRadioButton offLowerRaiseRearGateSwitch = new JRadioButton("LOWER");
    private ContentPanel lowerRaiseRearGateSwitchPanel = new ContentPanel(onLowerRaiseRearGateSwitch, offLowerRaiseRearGateSwitch, lowerRaiseRearGateSwitchTitle);
    // Purge Bin Switch
    private TitledBorder purgeBinSwitchTitle = new TitledBorder("Purge Bin");
    private JRadioButton onPurgeBinSwitch = new JRadioButton("ON");
    private JRadioButton offPurgeBinSwitch = new JRadioButton("OFF");
    private ContentPanel purgeBinSwitchPanel = new ContentPanel(onPurgeBinSwitch, offPurgeBinSwitch, purgeBinSwitchTitle);
    // Diverter Left/Right Switch
    private TitledBorder diverterLeftRightSwitchTitle = new TitledBorder("Diverter Left/Right");
    private JRadioButton leftDiverterSwitch = new JRadioButton("LEFT");
    private JRadioButton rightDiverterSwitch = new JRadioButton("RIGHT");
    private ContentPanel diverterLeftRightSwitchPanel = new ContentPanel(leftDiverterSwitch, rightDiverterSwitch, diverterLeftRightSwitchTitle);

    public GUIFeederPanel() {
        // Layout Setting
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 11));

        // Add GUI contents
        add(feederSwitchPanel);
        add(partLowSensorPanel);
        add(feedPartsSwitchPanel);
        add(partFedCounterPanel);
        add(lowerRaiseRearGateSwitchPanel);
        add(purgeBinSwitchPanel);
        add(diverterLeftRightSwitchPanel);
    }

    // Switch setting(This is decided by message from server. Not controllable by lane manager)
    public void setFeederSwitchOn() {
        onFeederSwitch.setSelected(true);
    }

    public void setFeederSwitchOff() {
        offFeederSwitch.setSelected(true);
    }

    // Part low sensor setting(This is decided by message from server. Not controllable by lane manager)
    public void setFeederPartLowSensor() {
        partLowSensorPanel.setSensor(true);
    }

    public void setFeederPartHighSensor() {
        partLowSensorPanel.setSensor(false);
    }

    // Feed parts switch setting(This is decided by message from server. Not controllable by lane manager)
    public void setFeederFeedPartsSwitchOn() {
        onFeedPartsSwitch.setSelected(true);
    }

    public void setFeederFeedPartsSwitchOff() {
        offFeedPartsSwitch.setSelected(true);
    }

    // Fed parts counter setting(This is decided by message from server. Not controllable by lane manager)
    public void setPartFedCounterIncrement() {
        partFedCounterPanel.setCounter(++partFedCounter);
    }

    // Lower/Raise rear gate switch setting(This is decided by message from server. Not controllable by lane manager)
    public void setLowerRaiseRearGateSwitchOn() {
        onLowerRaiseRearGateSwitch.setSelected(true);
    }

    public void setLowerRaiseRearGateSwitchOff() {
        offLowerRaiseRearGateSwitch.setSelected(true);
    }

    // Purge bin switch setting(This is decided by message from server. Not controllable by lane manager)
    public void setPurgeBinSwitchOn() {
        onPurgeBinSwitch.setSelected(true);
    }

    public void setPurgeBinSwitchOff() {
        offPurgeBinSwitch.setSelected(true);
    }

    // Diverter Left/Right switch setting(This is decided by message from server. Not controllable by lane manager)
    public void setDivertToLeft() {
        leftDiverterSwitch.setSelected(true);
    }

    public void setDivertToRight() {
        rightDiverterSwitch.setSelected(true);
    }
}
