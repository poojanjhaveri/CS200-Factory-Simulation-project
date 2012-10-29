<<<<<<< HEAD
=======
package factory.agentGUI;
>>>>>>> e55bea809a4f01ab018a7b8b99f6378440c1052c

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @brief waits to receive signals from server
 * @author Dongyoung Jung
 */
public class SetNetwork {

    private LaneManagerApp laneManagerApp;
    private Socket mySocket;
    private WaitingForSignalThread thread;
    private VerifySignalsFromServer verifyMessage;
    private SendSignalsToServer sendSignalsToServer;

    public SetNetwork(LaneManagerApp laneManagerApp) {
        this.laneManagerApp = laneManagerApp;

        serverSet();
        thread = new WaitingForSignalThread();
        verifyMessage = new VerifySignalsFromServer(laneManagerApp);
        new Thread(thread).start();
    }

    public void serverSet() {
        try {
            mySocket = new Socket("aludra.usc.edu", 40000);
        } catch (Exception e) {
            System.out.println("Server doesn't exist.");
            System.exit(0);
        }
    }

    class WaitingForSignalThread implements Runnable {

        private PrintWriter pw;
        private BufferedReader br;
        private String message;

        public WaitingForSignalThread() {
            try {
                pw = new PrintWriter(mySocket.getOutputStream(), true);
                br = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            } catch (Exception e) {
                System.out.println("failed to set reading and writing from the server");
                e.printStackTrace();
            }

            sendSignalsToServer = new SendSignalsToServer(pw);
        }

        public void run() {
            while (true) {
                try {
                    message = br.readLine();
                    System.out.println("(Client) Client received a message : " + message);
                    verifyMessage.verify(message);
                } catch (Exception e) {
                    System.out.println("failed to read from the server");
                    e.printStackTrace();
                }
            }
        }
    }
}
