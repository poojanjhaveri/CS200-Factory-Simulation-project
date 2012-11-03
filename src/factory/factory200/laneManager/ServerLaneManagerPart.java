package factory.factory200.laneManager;
/**
 * @brief Class for 'Part'
 *	@author Dongyoung Jung
 */
public class ServerLaneManagerPart {
	
	private int partNum;	///< Part number
	
	/**
	 * @brief Assigns part number 
	 * @param PartNum : part number
	 */
	public ServerLaneManagerPart(int partNum){
		this.partNum = partNum;
	}
	
	/**
	 * @brief Getter
	 * @return Integer part number
	 */
	public int getPartNum(){
		return partNum;
	}
}