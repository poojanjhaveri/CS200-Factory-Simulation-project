package factory.factory200.laneManager.ServerSide;

/**
 * @brief Class for 'Part'
 *	@author Dongyoung Jung
 */
public class LMPart {
	
	private int partNum;
	private int partStatus;
	
	/**
	 * @brief Assigns part number 
	 * @param PartNum : part number
	 */
	public LMPart(int partNum, int partStatus){
		this.partNum = partNum;
		this.partStatus = partStatus;
	}
	
	/**
	 * @brief Getter
	 * @return Integer part number
	 */
	public int getPartNum(){
		return partNum;
	}
	
	/**
	 * @brief Getter
	 * @return Part Status(good or bad)
	 */
	public int getPartStatus(){
		return partStatus;
	}
}