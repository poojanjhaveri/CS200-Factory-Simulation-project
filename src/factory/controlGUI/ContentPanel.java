
package factory.controlGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
 * @brief makes it easier to put GUIs on manager control panel
 * @author Dongyoung Jung
 */
class ContentPanel extends JPanel {

    private ButtonGroup buttonGroup = new ButtonGroup();
    private JLabel counterLabel;
    private JLabel sensorLabel;
    private static ImageIcon redSensorImage = new ImageIcon(".//pics//sensor_red.png");
    private static ImageIcon whiteSensorImage = new ImageIcon(".//pics//sensor_white.png");

    public ContentPanel(TitledBorder border) {
        setPreferredSize(new Dimension(250, 70));
        setLayout(new BorderLayout());
        border.setTitleColor(Color.red);
        setBorder(border);

        sensorLabel = new JLabel("", JLabel.CENTER);
        sensorLabel.setIcon(whiteSensorImage);
        add(sensorLabel);
    }

    public ContentPanel(int counter, TitledBorder border) {
        setPreferredSize(new Dimension(250, 70));
        setLayout(new BorderLayout());
        border.setTitleColor(Color.red);
        setBorder(border);

        counterLabel = new JLabel("" + counter, JLabel.CENTER);
        add(counterLabel);
    }

    public ContentPanel(JRadioButton button1, JRadioButton button2, TitledBorder border) {
        setPreferredSize(new Dimension(250, 70));
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 8));
        border.setTitleColor(Color.red);
        setBorder(border);
        buttonGroup.add(button1);
        buttonGroup.add(button2);
        add(button1);
        add(button2);
    }

    public void setCounter(int counter) {
        counterLabel.setText("" + counter);
    }

    public void setSensor(Boolean sensorStatus) {
        if (sensorStatus == true) {
            sensorLabel.setIcon(redSensorImage);
        } else if (sensorStatus == false) {
            sensorLabel.setIcon(whiteSensorImage);
        }
    }
}