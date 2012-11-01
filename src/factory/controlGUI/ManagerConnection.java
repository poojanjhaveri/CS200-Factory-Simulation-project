

public class ManagerConnection implements Runnable{

    private Manager mgr;
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;

    public ManagerConnection(Manager ref)
    {
        socket = null;
        out = null;
        in = null;
	this.mgr = ref;
	if(ref == null)
	    System.out.println("ManagerConnection initialized with a null Manager object. This is bad. Fix it!");
    }
/**
     * @brief attempts to connect to the server
     */
    public void connect() {
        p.println("host name: " + Server.HOST_NAME);
        p.println("port num: " + Server.PORT_NUMBER);

        System.out.println("Connecting...");
        socket = null;
        out = null;
        in = null;

        try {
            socket = new Socket(hostName, portNum);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection.");
            System.exit(1);
        }

        System.out.println("Done connecting");
    }

    public void run()
    {
	throw new UnimplementedException("Don't call this, extend this class and override run for custom functionality");
    }
  /**
     * @brief send a message to the server
     * @param msg - the message to send. Append ":"+whateverInfoYouWant to send
     * specific parameters.
     */
    public void out(String o)
    {
	this.out.println(o);
    }
    /**
     * @brief listen for a message from the server
     */
    public void listenToServer() {
        p.println("Listening to the server");
        try {
            String msg = in.readLine();
            processMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}