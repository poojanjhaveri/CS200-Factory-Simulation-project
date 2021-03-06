package agent;

import factory.general.HandleAManager;
import java.util.concurrent.*;

/**
 * Base class for simple agents
 *
 * @brief Base class for simple agents
 */
public abstract class Agent {

    Semaphore stateChange;
    private AgentThread agentThread;
    private String name;
    public boolean partsRequested;
    public boolean print;

    /**
     * @brief communication channel with client
     * A reference to the corresponding client
     * @author Roy YiWei Zheng
     */
    protected HandleAManager client;
    /**
* @brief communication channel with factory production manager
* A reference to the factory production manager
     */
    protected HandleAManager fpm;
    
    protected Agent(String name) {
        this.name = name;
        this.stateChange = new Semaphore(1, true);
        this.partsRequested = false;
        this.print = true;
    }

    /**
     * This should be called whenever state has changed that might cause the
     * agent to do something.
     */
    protected void stateChanged() {
        stateChange.release();
    }

    /**
     * Agents must implement this scheduler to perform any actions appropriate
     * for the current state. Will be called whenever a state change has
     * occurred, and will be called repeated as long as it returns true.
     *
     * @return true iff some action was executed that might have changed the
     * state.
     */
    public abstract boolean pickAndExecuteAnAction();

    /**
     * Return agent name for messages. Default is to return java instance name.
     */
    public String getName() {
//        return StringUtil.shortName(this);
        return this.name;

    }

    /**
     * The simulated action code
     */
    protected void Do(String msg) {
        print(msg, null);
    }

    /**
     * Print message
     */
    protected void print(String msg) {
        print(msg, null);
    }

    /**
     * Print message with exception stack trace
     */
    protected void print(String msg, Throwable e) {
        if(print) {
            StringBuilder sb = new StringBuilder();
            sb.append(getName());
            sb.append(": ");
            sb.append(msg);
            sb.append("\n");
            if (e != null) {
                sb.append(StringUtil.stackTraceString(e));
            }
            System.out.print(sb.toString());
        }
    }

    /**
     * Start agent scheduler thread. Should be called once at init time.
     */
    public synchronized void startThread() {
        if (agentThread == null) {
            agentThread = new AgentThread(getName());
            agentThread.start(); // causes the run method to execute in the AgentThread below
        } else {
            agentThread.interrupt();//don't worry about this for now
        }
    }

    /**
     * Stop agent scheduler thread.
     */
    //In this implementation, nothing calls stopThread().
    //When we have a user interface to agents, this can be called.
    public void stopThread() {
        if (agentThread != null) {
            agentThread.stopAgent();
            agentThread = null;
        }
    }

    /**
     * @brief Agent scheduler thread Agent scheduler thread, calls
     * respondToStateChange() whenever a state change has been signalled.
     */
    private class AgentThread extends Thread {

        private volatile boolean goOn = false;

        private AgentThread(String name) {
            super(name);
        }

        @Override
        @SuppressWarnings("empty-statement")
        public void run() {
            goOn = true;

            while (goOn) {
                try {
                    // The agent sleeps here until someone calls, stateChanged(),
                    // which causes a call to stateChange.give(), which wakes up agent.
                    stateChange.acquire();
                    //The next while clause is the key to the control flow.
                    //When the agent wakes up it will call respondToStateChange()
                    //repeatedly until it returns FALSE.
                    //You will see that pickAndExecuteAnAction() is the agent scheduler.
                    while (pickAndExecuteAnAction());
                } catch (InterruptedException e) {
                    // no action - expected when stopping or when deadline changed
                } catch (Exception e) {
                    print("Unexpected exception caught in Agent thread:", e);
                }
            }
        }

        private void stopAgent() {
            goOn = false;
            this.interrupt();
        }
    }
    /**
     * @brief sets the client
     * @author Roy YiWei Zheng
     */
    public void setClient(HandleAManager i) {
    	this.client = i;
    }
    
    public HandleAManager getClient() {
    	return this.client;
    }
    
    /**
@brief sets the factory production manager client
@author Roy YiWei Zheng
     */
    public void setFactoryProductionManagerClient(HandleAManager i) {
    	this.fpm = i;
    }
    
    public HandleAManager getFactoryProductionManagerClient() {
    	return this.fpm;
    }
}
