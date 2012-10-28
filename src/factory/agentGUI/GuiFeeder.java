package factory.agentGUI;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
@brief Creates feeder image on graphic panel
Creates feeder image on graphic panel
@author Yuting Liu
*/
public class GuiFeeder extends JPanel{
ImageIcon offFeederImage;///<when feeder turns off
ImageIcon divertRightImage;///<when feeder diverts parts to right
ImageIcon diverLeftImage;///<when feeder diverts parts to left
ImageIcon lowerRearGateImage;///<when rear gate is lowered
ImageIcon raiseRearGateImage;///<when rear gate is raised
ArrayList<GuiPart> parts;///<part images

/**
 put part image on lane
*/
public void putPartToLane(int partNum)
{
}
}


