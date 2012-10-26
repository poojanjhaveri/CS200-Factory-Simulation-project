
/**
Creates, Edits and Deletes a kit.
<img src="../img/image_00.jpg" alt="create kit"/>
<img src="../img/image_05.jpg" alt="update kit"/>
<img src="../img/image_06.jpg" alt="delete kit"/>
@author Poojan Jhaveri
@brief creates, edits, deletes kits
*/
public class GUIKitsManager extends JFrame implements ActionListener, Manager{

JTabbedPanel tabpane;///<tabbed panel for 4 different views for the kitmanager ie create kit, update kit, delete kit and kit production schedule.
JPanel createpanel;///<kit creation panel
JPanel deletepanel;///<kit deletion panel
JPanel updatepanel;///<kit modification panel
JPanel viewschedulepanel;///<view schedule panel
JTextField create_kit_name, update_kit_name, delete_kit_name;
JCombobox create_item ;///< This will have the list of items obtained from the server
JCombobox update_kit ;///< list of names of kits
JCombobox update_item ;///< list of existing maintained items
ArrayList<JButton> buttons;///<holds the buttons used in the menu
JButton create_kit  , update_kit, delete_kit;
Kit newkit ;///< Object of Kit class to hold the values of current kit

public void actionPerformed(ActionEvent ae) ;///<trigger when button is clicked
/**
Creates the Kit ( triggered from actionperformed when create_kit button is clicked ).Passes the contents of current kit ie kit name , items in the kit to the server.
*/
void createKit()
{

}
/**
Updates existing kit ie updates the kit for eg. parts in the kit .
 */
void updateKit()
{

}
/**
(  triggered from actionperformed  when update_kit is clicked ) and sends it to the Server.
Deletes the kit selected by the user and sends the updated list of kits to the server.( triggered from actionperformed  when delete_kit is clicked ) 
*/
void deleteKit()
{

}
/**
 to get the list of existing items from the server
*/
void getPartsList()
{

}
/**
 - to get the list of existing kits from the server
 */
void getKitsList()
{

}
/**
- to get details of the kit scheduling from the server
*/
void getScheduling{

} 
/**
sends message to Server to receive the existing collection of Items.
*/
void getItemList(){

}
}