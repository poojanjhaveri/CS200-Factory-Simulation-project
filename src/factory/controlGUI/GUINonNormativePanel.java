
package factory.controlGUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
<<<<<<< HEAD
 * @brief Non-normative scenario
 * @author Dongyoung Jung
 */
public class GUINonNormativePanel extends JPanel implements ActionListener {

    JButton button;
    TitledBorder buttonTitle;
    ArrayList<JButton> buttons = new ArrayList<JButton>();

    public GUINonNormativePanel() {
        // JPanel setting
        setPreferredSize(new Dimension(300, 640));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 31));

        // Button generation
        for (int i = 0; i < 7; i++) {
            button = new JButton("example");
            buttonTitle = new TitledBorder("Non-normative scenario " + (i + 1));
            button.addActionListener(this);
            button.setBorder(buttonTitle);
            button.setPreferredSize(new Dimension(200, 50));
            buttons.add(button);
            add(button);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        for (int i = 0; i < buttons.size(); i++) {
            if (ae.getSource() == buttons.get(i)) {
                // Send Signal to server
            }
        }
    }
=======
	@brief Non-normative scenario
	@author Dongyoung Jung
*/
public class GuiNonNormativePanel extends JPanel implements ActionListener{

	JButton button;
	TitledBorder buttonTitle;
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	public GuiNonNormativePanel(){
		// JPanel setting
		setPreferredSize(new Dimension(300,640));
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 31));
		
		// Button generation
		for(int i=0 ; i<7 ; i++){
			button = new JButton("example");
			buttonTitle = new TitledBorder("Non-normative scenario " + (i+1));
			button.addActionListener(this);
			button.setBorder(buttonTitle);
			button.setPreferredSize(new Dimension(200,50));
			buttons.add(button);
			add(button);
		}
	}
	
	public void actionPerformed(ActionEvent ae){
		for(int i=0 ; i<buttons.size() ; i++){
			if( ae.getSource() == buttons.get(i) ){
				// Send Signal to server
				
			}
		}
	}
>>>>>>> e55bea809a4f01ab018a7b8b99f6378440c1052c
}
