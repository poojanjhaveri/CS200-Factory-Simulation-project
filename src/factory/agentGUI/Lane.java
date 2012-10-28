import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
/**
	@brief creates lane image on graphic panel
	@author Dongyoung Jung
*/
class Lane extends JLayeredPane{

		int insertNewLabelTerm;///<enables to draw track image on lane as lane moves 5 pixels to left
		ArrayList<LaneTrack> laneTracks;///<enables to control all tracks on lane
		ArrayList<Part> parts;///<enables to control all parts on lane
		int vibrateAmplitude;///<3 states ( 0 - weak, 1 - normal, 2 - strong )
		double randomVibrate;///<randomly generated ( weak - 10% vibrate, normal - 50%, strong - 100% )
		double randomVibrate2;///<randomly generated ( moved to randomly-decided way by 1 pixel )
		Boolean nestOpen;///<tells if the nest is open or close ( If there are 8 parts in nest, it means close )
		
		void moveLane(){}///<inserts ¡®LaneTrack¡¯ JLabel to ¡®Lane¡¯ Panel. Also moves ¡®Part¡¯ image on the panel all together
		void setPartLimit(){}///<if nest is full of parts already, this function sets the limits of parts currently on lane. For example, if there are 4 parts, this function sets the limits as 0, 6, 12, 18 so that parts can queue up behind one another.
		void movePartToNest(){}///<transfers part to nest
		void nestOpenCheck(){}///<checks nest¡¯s availability
		void laneVibrateAmplitudeControl(){}///<decides if the lane vibrates with a random generator
		void laneVibrateWithCoordinates(){}///<decides which way the lane vibrates to
		void addPartOntoLane(){}///<creates a new ¡®Part¡¯ instance and display it

		class LaneTrack extends JLabel{///<track on lane
			void trackMove(){}///<move ¡®LaneTrack¡¯ instances to left through ¡®Lane¡¯ JPanel
		}
}