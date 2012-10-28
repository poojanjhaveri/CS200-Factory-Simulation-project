package factory.feederManagement;

import agent.Agent;
import factory.Part;
import factory.interfaces.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GantryAgent extends Agent implements Gantry {

    private List<myParts> parts = Collections.synchronizedList(new ArrayList<myParts>());
    private Feeder feeder;

    /* to hold the info about its list of parts*/
    private class myParts {

        Part part_type;
        int quantity;
        int supplyAmount;
        boolean send = false;
        //PartState state=PartState.noState;

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
                p.supplyAmount = feeder.capacity;
                //p.state=PartState.canSend;
                stateChanged();
                return;
            }
            stateChanged();

        }

    }

    @Override
    protected boolean pickAndExecuteAnAction() {

        for (myParts p : parts) {
            if (p.send == true) {
                supplyPart(p);
            }
            return true;
        }

        return false;
    }

    private void supplyPart(myParts p) {

        feeder.msgHereAreParts(p.part_type, p.supplyAmount);
        p.send = false;

        //update the myParts object
        p.quantity = p.quantity - p.supplyAmount;

    }

    public void setFeeder(Feeder feeder) {
        this.feeder = feeder;
    }

    @Override //Unimplemented
    public void msgHereAreParts(Part part, int quantity) {
        // TODO Auto-generated method stub
    }
}
