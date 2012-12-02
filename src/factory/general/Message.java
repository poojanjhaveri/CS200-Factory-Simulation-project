package factory.general;

/**
 * @brief This class stores constant strings to establish clear messages between
 * the server and client. Protocol: Some messages will be passed with a ":";
 * anything after this is parameter data (e.g., a number)
 * @author David Zhang, YiWei Roy Zheng
 */
public class Message {

    public static final String CREATE_KIT = "CREATE_KIT"; ///< manager: sendToServer(Message.CREATE_KIT+":"+asdf)
    public static final String CLIENT_EXITED = "CLIENT_EXITED";
    public static final String TEST_SERVER = "TEST_SERVER";
    public static final String TEST_CLIENT = "TEST_CLIENT";

    //partsmanager section
    public static final String PULL_PARTS_LIST = "PULL_PARTS_LIST";///<simple request from partsmanager to server asking for parts list
    public static final String PUSH_PARTS_LIST = "PUSH_PARTS_LIST";///<send serialized parts list over from server to partsmanager
    public static final String DEFINE_NEW_PART = "DEFINE_NEW_PART";///<send a new type of part over to the server
    public static final String UPDATE_PART = "UPDATE_PART";
    public static final String UNDEFINE_PART = "UNDEFINE_PART";///<removes a part definition

    //kitmanager section
    public static final String IDENTIFY_KITMANAGER = "IDENTIFY_KITMANAGER";
    public static final String PULL_KITS_LIST = "PULL_KITS_LIST";///<simple request form kits to server asking for kits list
    public static final String PUSH_KITS_LIST = "PUSH_KITS_LIST";///<send serialized kits list over form server to kitsmanager
    public static final String DEFINE_NEW_KIT = "DEFINE_NEW_KIT";///<send a new type of kit over to the server
    public static final String UPDATE_KIT = "UPDATE_KIT";
    public static final String UNDEFINE_KIT = "UNDEFINE_KIT";///<removes a kit definition

    //gantryrobotmanager section
    public static final String IDENTIFY_GANTRYROBOTMANAGER = "IDENTIFY_GANTRYROBOTMANAGER";//reports to server that this is a gantryrbtmgr
    public static final String GANTRY_CARRY_A_BIN = "GANTRY_CARRY_A_BIN";
    public static final String MOVE_GANTRY_TO_FEEDER = "MOVE_GANTRY_TO_FEEDER"; // takes in int for which feeder
    public static final String MOVE_GANTRY_TO_BIN = "MOVE_GANTRY_TO_BIN"; // takes in int for which bin
    public static final String MOVE_GANTRY_TO_PURGE = "MOVE_GANTRY_TO_PURGE"; // no params
    public static final String MOVE_GANTRY_TO_DUMP_PART = "GANTRY_TO_DUMP_PART"; // no params
    public static final String MOVE_GANTRY_TO_PICK_BIN = "MOVE_GANTRY_TO_PICK_BIN"; // takes in int for which bin
    public static final String GRM_FINISH_MOVE_TO_BIN = "GRM_FINISH_MOVE_TO_BIN";
    public static final String GRM_FINISH_MOVE_TO_FEEDER = "GRM_FINISH_MOVE_TO_FEEDER";
    public static final String GRM_FINISH_MOVE_TO_DUMP = "GRM_FINISH_MOVE_TO_DUMP";
    public static final String FREEZE_GANTRY_ROBOT = "FREEZE_GANTRY_ROBOT";
    public static final String UNFREEZE_GANTRY_ROBOT = "UNFREEZE_GANTRY_ROBOT";

    //kitassemblymanager section
    public static final String IDENTIFY_KITASSEMBLYMANAGER = "IDENTIFY_KITASSEMBLYMANAGER";
    public static final String KAM_DROP_OFF_FULL_KIT = "KAM_DROP_OFF_FULL_KIT";
    public static final String KAM_MOVE_ACTIVE_KIT_TO_INSPECTION = "KAM_MOVE_ACTIVE_KIT_TO_INSPECTION";
    public static final String KAM_PICK_UP_EMPTY_KIT_TO_ACTIVE = "PICK_UP_EMPTY_KIT_TO_ACTIVE";
    public static final String KAM_PICK_UP_EMPTY_KIT = "PICK_UP_EMPTY_KIT";
    public static final String KAM_MOVE_EMPTY_KIT_TO_ACTIVE = "MOVE_EMPTY_KIT_TO_ACTIVE";
    public static final String KAM_FLASH_KIT_CAMERA = "KAM_FLASH_KIT_CAMERA";
    public static final String KAM_FLASH_NEST_CAMERA = "KAM_FLASH_NEST_CAMERA";
    public static final String KAM_PARTS_MOVE_TO_NEST = "KAM_PARTS_MOVE_TO_NEST";
    public static final String KAM_PARTS_PICK_PART = "KAM_PARTS_PICK_PART";
    public static final String KAM_PARTS_DROP_OFF_PARTS = "KAM_PARTS_DROP_OFF_PARTS";
    public static final String KAM_MOVE_FROM_0_TO_2 = "KAM_MOVE_FROM_0_TO_2";
    public static final String KAM_ADD_KIT = "KAM_ADD_KIT";///<places another empty kit onto the conveyor
    public static final String KAM_REMOVE_KIT = "KAM_REMOVE_KIT";///<removes a kit off screen from conveyor
    public static final String LM_ADD_PART="LMA_";
    public static final String KAM_FINISH_KITBOT_ANIMATION = "KAM_FINISH_KITBOT_ANIMATION";
    public static final String KAM_FINISH_KITTER_ANIMATION = "KAM_FINISH_KITTER_ANIMATION";
    public static final String KAM_MOVE_FROM_2_TO_0 = "KAM_MOVE_FROM_2_TO_0";
    public static final String KAM_NEST_STABLE = "KAM_NEST_STABLE";
    public static final String KAM_NEST_UNSTABLE = "KAM_NEST_UNSTABLE";
    public static final String KAM_NEST_PILED = "KAM_NEST_PILED";
    public static final String KAM_BAD_KIT = "KAM_BAD_KIT";///<a part was dropped
    public static final String KAM_ACTION_STABILIZE_NEST = "KAM_ACTION_STABILIZE_NEST";
    public static final String KAM_ACTION_UNPILE_NEST = "KAM_ACTION_UNPILE_NEST";
    public static final String EARLY_CAMERA_FLASH = "EARLY_CAMERA_FLASH";
    public static final String NEST_UP = "NEST_UP";
    public static final String NEST_DOWN = "NEST_DOWN";
    public static final String PARTS_ROBOT_IN_WAY = "PARTS_ROBOT_IN_WAY";
    public static final String NO_GOOD_PARTS = "NO_GOOD_PARTS";
    //    public static final String KAM_FINISH_DROP_OFF_FULL_KIT = "KAM_FINISH_DROP_OFF_FULL_KIT";
    //    public static final String KAM_FINISH_MOVE_TO_NEST = "KAM_FINISH_MOVE_TO_NEST";
    //public static final String KAM_FINISH_DROP_OFF_PARTS = "KAM_FINISH_DROP_OFF_PARTS";

    //lanemanager section
    public static final String IDENTIFY_LANEMANAGER = "IDENTIFY_LANEMANAGER";
    public static final String PART_TO_NEST_FROM_LANE = "PART_TO_NEST_FROM_LANE";
    public static final String PART_TAKE_BY_PARTROBOT = "PART_TAKE_BY_PARTROBOT";
    public static final String BAD_PART_INSERTION = "BAD_PART_INSERTION";
    public static final String LANE_JAMMED = "LANE_JAMMED";
    public static final String PART_PILED = "PART_PILED";
    public static final String PART_TOGGLING = "PART_TOGGLING";
    public static final String PART_UNTOGGLING = "PART_UNTOGGLING";
    public static final String FEEDER_NOT_WORKING = "FEEDER_NOT_WORKING";
    
    //PULL_KITS_LIST is also used by FPM
    public static final String IDENTIFY_FACTORYPRODUCTIONMANAGER = "IDENTIFY_FACTORYPRODUCTIONMANAGER";
    public static final String PUSH_PRODUCTION_QUEUE = "PUSH_PRODUCTION_QUEUE";///<sends the server a list of comma delimited kit IDs
    public static final String ALERT_FPM_KIT_INSPECTED = "ALERT_FPM_KIT_INSPECTED";
    public static final String SUPPLY_PART_ON_FEEDER = "SUPPLY_PART_ON_FEEDER";
    public static final String MOVE_GANTRY_TO_DUMP = "MOVE_GANTRY_TO_DUMP";
    public static final String KIT_IN_PRODUCTION = "KIT_IN_PRODUCTION";
    public static final String GIVE_KITS_IN_QUEUE = "GIVE_KITS_IN_QUEUE";
}