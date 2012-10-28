import java.util.concurrent.Executor;
import javax.swing.JLayeredPane;
/**
	@brief enables to draw camera image when agents order to take a picture
	@author Dongyoung Jung
*/
class CameraHandler extends JLayeredPane{
        
		Executor executor;///<enables one-time thread ( when camera picture is drawn, after 0.5 second, it disappears automatically )
}
