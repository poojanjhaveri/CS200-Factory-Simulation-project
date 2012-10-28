package factory.feederManagement;

import agent.Agent;
import factory.Part;
import factory.interfaces.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @brief agent for the Lane
 * This class is the agent for the Lane agent, which receives messages from nest to supply parts.
 * It asks the gantry for parts if it has less parts available.
 * It sends the parts to the nest when it has enough parts available.
 * @author Kevin Macwan
 * @version 0
 */

public class LaneAgent extends Agent implements Lane {

    private List<myParts> parts = Collections.synchronizedList(new ArrayList<myParts>());
    private Nest nest;

    private class myParts {

        Part part_type;
        int quantity;
        int supplyAmount;
        boolean send = false;

        public myParts(Part part, int quantity) {
            this.part_type.type = part.type;
            this.quantity = quantity;

        }
    }

    @Override
    public void msgNeedPart(Part part) {
        for (myParts p : parts) {
            if (p.part_type.type == part.type) {
                p.send = true;
                p.supplyAmount = nest.capacity;
                //p.state=PartState.canSend;
                stateChanged();
                return;
            }
            stateChanged();

        }
    }

    @Override
    public void msgHereAreParts(Part part, int quantity) {

        for (myParts p : parts) {
            if (p.part_type.type == part.type) {
                p.quantity = p.quantity + quantity;
                stateChanged();
                return;
            }
        }

        //create a new type if the current list does not contain parts of this type.	
        parts.add(new myParts(part, quantity));
        stateChanged();
        stateChanged();
    }

    @Override
    protected boolean pickAndExecuteAnAction() {
        // TODO Auto-generated method stub
        for (myParts p : parts) {
            if (p.send == true) {
                supplyPart(p);
            }
            return true;
        }

        //return false if no scheduler rule is fired
        return false;
    }

    private void supplyPart(myParts part) {

        nest.msgHereAreParts(part.part_type, part.supplyAmount);
        part.send = false;

        //update the myParts object
        part.quantity = part.quantity - part.supplyAmount;

    }
}
