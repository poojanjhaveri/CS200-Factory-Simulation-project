import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
	@brief creates feeder image on graphic panel
	@author Dongyoung Jung
*/
class Feeder extends JPanel{
        
		ImageIcon offFeederImage;///<image when feeder turns off
		ImageIcon onFeederImage;///<image when feeder turns on
		Boolean divertLeftRight;///<enables feeder to feed an available lane
		
        void setSwitchOff(){}///<function to set image
        void setSwitchOn(){}///<function to set image
        void setPartBoxInFeeder(){}///<function to set image
        void feedPartOntoLaneLeft(){}///<feeds part to left lane
        void feedPartOntoLaneRight(){}///<feeds part to right lane
}