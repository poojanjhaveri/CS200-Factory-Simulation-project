package factory.agentGUI;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
	@brief creates nest image on graphic panel
	@author Dongyoung Jung
*/
class Nest extends JPanel{
    
        ArrayList<JLabel> nestCells;///< - 8 JLabels in ¡®Nest¡¯ JPanel
        ArrayList<ImageIcon> partImages;///<part images on nest. Parts passed from lane are saved on nest as images not ¡®Part¡¯ instances
    
        void receivePartFromLane( Part newPart ){}///<add the new part¡¯s image to ¡®partImages¡¯ ArrayList
        void robotTakePart(){}///<remove the first part image from ¡®partImages¡¯ ArrayList
        void update(){}///<reorganizes ¡®Nest¡¯ JPanel with ¡®nestCells¡¯ and ¡®partImages¡¯
}

