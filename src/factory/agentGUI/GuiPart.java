package factory.agentGUI;

import java.io.Serializable;

/**
 * digital representation of a part
 *
 * @brief digital representation of a part
 * @author David Zhang
 */
public class GuiPart implements Serializable {

    String name;
    String description;
    String imageFileName;
    Integer number;///< the part number, like an ID; useful for comparing

    /**
     * Creates a part and gives it a unique ID.
     *
     * @brief instantiates a part into the correct state
     */
    public GuiPart(String n, String d, String fn) {
//TODO need to implement system to generate unique part numbers (we could cheat and do it the dumb way, grab time())
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
}

/*
Class

: PartsManager
David Zhang
General description
JFrame that represents the parts manager
extends JFrame
implements ActionListener
Data
various JPanels and similar components to create a nice layout
JPanel contentPane
JLabel lblPartsManager
JTabbedPane tabbedPane
JPanel managePartsPanel, comboBoxPanel
JComboBox partComboBox
JPanel managePartsImagePanel, managePartsButtonPanel
JPanel selectedPartPanel, formPanel, partNamePanel, imageFileNamePanel, descriptionPanel, nonformPanel, imagePanel, buttonPanel, newPartPanel
JButton submitButton, viewButton, deleteButton
JLabel lblTitle
Methods
main method to individually run this class  

    void createPart()
    - creates a part based on form data; sends data to server

    void updatePart()

    - updates part data and sends data to server
void deletePart(int partNumber)

    - deletes the part with the specified part number and sends data to the server
void sendToServer()

- pass part data to FCS / server
*/