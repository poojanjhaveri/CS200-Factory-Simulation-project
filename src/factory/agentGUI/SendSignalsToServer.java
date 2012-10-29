<<<<<<< HEAD
=======
package factory.agentGUI;
>>>>>>> e55bea809a4f01ab018a7b8b99f6378440c1052c

import java.io.PrintWriter;

/**
 * @brief Sends signals to server Signals to server Non-normative scenario Part
 * addition to nest from lane
 * @author Dongyoung Jung
 */
public class SendSignalsToServer {

    private PrintWriter pw;

    public SendSignalsToServer(PrintWriter pw) {
        this.pw = pw;
    }

    public void sendToServer(String signal) {
        pw.write(signal);
    }
}
