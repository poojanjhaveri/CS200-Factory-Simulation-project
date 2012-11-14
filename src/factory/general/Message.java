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

    //kitsmanager section
    public static final String PULL_KITS_LIST = "PULL_KITS_LIST";///<simple request form kits to server asking for kits list
    public static final String PUSH_KITS_LIST = "PUSH_PARTS_LIST";///<send serialized kits list over form server to kitsmanager
    public static final String DEFINE_NEW_KIT = "DEFINE_NEW_KIT";///<send a new type of kit over to the server
    public static final String UPDATE_KIT = "UPDATE_KIT";
    public static final String UNDEFINE_KIT = "UNDEFINE_KIT";///<removes a kit definition

    //factoryproductionmanager section
    //PULL_KITS_LIST is also used by FPM
    public static final String PUSH_PRODUCTION_QUEUE = "PUSH_PRODUCTION_QUEUE";///<sends the server a list of comma delimited kit IDs 
}