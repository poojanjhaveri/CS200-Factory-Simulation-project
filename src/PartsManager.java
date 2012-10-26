Class: PartsManager
David Zhang
General description

extends JFrame
implements ActionListener
Data
various JPanels and similar components to create a nice layout

Methods
main method to individually run this class


/**
<img src="../img/image_002.jpg"/>
@brief JFrame that represents the parts manager
*/
class PartsManager extends JFrame implements Manager{
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
@brief creates a part based on form data; sends data to server
*/
public void createPart()
{

}
/**
@brief updates part data and sends data to server
*/
public void updatePart()
{
}
/**
@brief deletes the part with the specified part number and sends data to the server
*/
public void deletePart(int partNumber)
{
}
/**
@brief pass part data to FCS / server
*/
public void sendToServer()
{

}

public static void main(String args[])
{
//TODO run the class here
}
}