package factory.controlGUI;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * <img src="../img/image02.png"/> @brief JFrame that represents the parts
 * manager
 * @author David Zhang
 */
public class PartsManager extends Manager {
	//TODO please add individual data documentation

	private JPanel contentPane;
	private JLabel lblPartsManager;
	private JTabbedPane tabbedPane;
	private JPanel managePartsPanel;
	private JPanel comboBoxPanel;
	private JComboBox partComboBox;
	private JPanel managePartsImagePanel, managePartsButtonPanel;
	private JPanel selectedPartPanel, formPanel, partNamePanel, imageFileNamePanel, descriptionPanel, nonformPanel, imagePanel, buttonPanel, newPartPanel;
	private JButton submitButton, viewButton, deleteButton;
	private JLabel lblTitle;

	/**
	 * @brief creates a part based on form data; sends data to server
	 */
	public void createPart() {
	}

	/**
	 * @brief updates part data and sends data to server
	 */
	public void updatePart() {
	}

	/**
	 * @brief deletes the part with the specified part number and sends data to
	 * the server
	 */
	public void deletePart(int partNumber) {
	}

	/**
	 * @brief pass part data to FCS / server
	 */
	public void sendToServer() {
	}

	public static void main(String args[]) {
		//TODO run the class here
	}

	@Override
	public void giveState() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}