package factory;

/**
 * @brief This class stores constant strings to establish clear messages between the server and client.
 * Protocol: Some messages will be passed with a ":"; anything after this is parameter data (e.g., a number)
 * @author David Zhang
 */
public class Message {
	public static final String CREATE_KIT = "CREATE_KIT"; // manager: sendToServer(Message.CREATE_KIT+":"+asdf)
	
	public static final String CLIENT_EXITED = "CLIENT_EXITED";
	
	public static final String TEST_SERVER = "TEST_SERVER";
	public static final String TEST_CLIENT = "TEST_CLIENT";
}