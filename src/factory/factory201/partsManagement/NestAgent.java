/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.partsManagement;

import agent.Agent;
import factory.factory201.feederManagement.LaneAgent;
import factory.factory201.interfaces.Lane;
import factory.factory201.interfaces.NestInterface;
import factory.factory201.interfaces.PartsInterface;
import factory.factory201.test.mock.MockCamera;
import factory.factory201.test.mock.MockLane;
import factory.general.Nest;
import factory.general.Part;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NestAgent extends Agent implements NestInterface {

<<<<<<< HEAD
public class NestAgent extends Agent implements NestInterface{
    Lane lane0;
=======
>>>>>>> 6c77d956ceb45adf295668eb6ca7622ff9158f71
    Lane lane1;
    Lane lane2;
    Lane lane3;
    Lane lane4;
    Lane lane5;
    Lane lane6;
    Lane lane7;
    Lane lanes[] = {lane0, lane1, lane2, lane3, lane4, lane5, lane6, lane7};
    Kit kit;
    
    MockLane lane;
<<<<<<< HEAD
	private List<Nest> myNests = Collections.synchronizedList(new ArrayList<Nest>());
    private Map <Integer, Part> nestParts = new HashMap<Integer, Part>();

    private List<Part> needParts =
            Collections.synchronizedList(new ArrayList<Part>());
=======
    private List<Nest> myNests;
    private Map<Nest, LaneAgent> lanes;
    private List<Part> needParts;
>>>>>>> 6c77d956ceb45adf295668eb6ca7622ff9158f71
    PartsInterface partsagent;
    MockCamera camera;
    private int nestNumber;
<<<<<<< HEAD
    private List<Nest> nests = Collections.synchronizedList(new ArrayList<Nest>());
    enum Status {none, needPart, gettingPart, full, gettingInspected, readyForKit, purge};
    
    NestAgent(String name) {
    super(name);
    myNests.add(new Nest(0));
    myNests.add(new Nest(1));
    myNests.add(new Nest(2));
    myNests.add(new Nest(3));
    myNests.add(new Nest(4));
    myNests.add(new Nest(5));
    myNests.add(new Nest(6));
    myNests.add(new Nest(7));
 }
    
//messages
=======
    private List<Nest> nests;

    enum Status {

        none, needPart, gettingPart, full, gettingInspected, readyForKit, purge
    };

    public NestAgent(String name) {
        super(name);
        this.myNests = Collections.synchronizedList(new ArrayList<Nest>());
        this.lanes = new HashMap<Nest, LaneAgent>();
        this.needParts = Collections.synchronizedList(new ArrayList<Part>());
        this.nests = Collections.synchronizedList(new ArrayList<Nest>());
    }
>>>>>>> 6c77d956ceb45adf295668eb6ca7622ff9158f71

//messages
    /**
     * This message checks if the part
     *
     * @param p This is a part
     */
<<<<<<< HEAD
    
    public void msgNewKit(List<Part> kitParts){
        
            for (Nest n: myNests){
                for(Part p: kitParts){
                if (n.parts.contains(p))
                    n.status = Nest.Status.hasPart;
                    kitParts.remove(p);
            }
        }
    }
    public void msgNeedPart(Part p) {
        
    	if (emptyNest()){
            for (Nest n: myNests){
                if (n.status == Nest.Status.empty){
                    n.setPart(p);
                    //nestParts.put(n.nestNum, p);
                    n.status = Nest.Status.needPart;
                    print("Part " + p + " is not taken by a nest, part is being assigned to the nest "+ n.nestNum);
                    break;
                }
            }
          
        }
        else{
            for (Nest n: myNests){
    		if(n.parts.contains(p)){
            giveToKit(n);}}}
    	
=======
    @Override
    public void msgNeedPart(Part p) {
        if (!hasPart(p)) {
            print("Part " + p + " is not taken by a nest, a new nest is being created");

            myNests.add(new Nest(p, myNests.size()));
            /*  for (Nest n: myNests){
             if(n.part.equals(p)){
             switch(n.nestNum){
             case 0:  n.setLane(lane1);
             case 1:  n.setLane(lane1);
             break;
             case 2:  n.setLane(lane2);
             case 3:  n.setLane(lane2);
             break;
             case 4: n.setLane(lane3);
             case 5: n.setLane(lane3);
             break;
             case 6:  n.setLane(lane4);
             case 7:  n.setLane(lane4);
             break;
             }
             }
             }*/
        } else {
            for (Nest n : myNests) {
                if (n.part.equals(p)) {
                    giveToKit(n);
                }
            }
        }

>>>>>>> 6c77d956ceb45adf295668eb6ca7622ff9158f71
        stateChanged();
    }

    @Override
    public void msgHereAreParts(Part p, int quantity) {
        for (Nest n : myNests) {
            if (n.part.equals(p)) {
                n.howMany += quantity;
            }
        }
        print("adding " + quantity + " " + p + " to the nest ");
        for (Nest n : myNests) {
            if (n.part.equals(p)) {
                n.status = Nest.Status.full;
            }
        }
        stateChanged();
    }

    @Override
    public void msgNestInspected(Nest n, boolean result) {
        if (result) {
            n.status = Nest.Status.readyForKit;
            print("Nest inspected and verified");
        } else {
            n.status = Nest.Status.purge;
            print("Nest is not verified");
        }
        stateChanged();
    }
    //scheduler

    @Override
    protected boolean pickAndExecuteAnAction() {

        if (!myNests.isEmpty()) {

            for (Nest n : myNests) {
                if (n.status == Nest.Status.needPart) {
                    requestPart(n);
                    return true;
                }

            }

            for (Nest n : myNests) {
                if (n.status == Nest.Status.full) {
                    requestInspection(n);
                    return true;
                }

            }
        }

        for (Nest n : myNests) {
            if (n.status == Nest.Status.readyForKit) {
                giveToKit(n);
                return true;
            }
        }

        for (Nest n : myNests) {
            if (n.status == Nest.Status.purge) {
                purge(n);
                return true;
            }
        }
        return false;
    }
<<<<<<< HEAD
    
    
    private void requestPart(Nest n){
       
    print("requesting " + n.part);
    	
    	n.status = Nest.Status.gettingPart;
        lanes[n.nestNum].msgNeedPart(n.part);
    	stateChanged();
=======

    private void requestPart(Nest n) {

        print("requesting " + n.part);

        n.status = Nest.Status.gettingPart;
        lane.msgNeedPart(n.part);
        stateChanged();
>>>>>>> 6c77d956ceb45adf295668eb6ca7622ff9158f71
    }

    private void requestInspection(Nest n) {

        print("requesting inspecting for nest " + n.getNestNum());
        n.status = Nest.Status.gettingInspected;
        camera.msgNestIsFull(n);
        stateChanged();
    }
<<<<<<< HEAD
    
    private void giveToKit(Nest n){
        
        //n.part.setNestNum(n.getNestNum());
    	partsagent.msgHereIsPart(n.parts.remove(0));
        
    	//n.howMany--;
        print("giving part " + n.part + " to kit now nest has " + n.parts.size());
        n.status = Nest.Status.none;
    	if (n.parts.size()<2)
    		n.status = Nest.Status.needPart;
    	stateChanged();	
    }
    
    private void purge(Nest n){
    n.parts.clear();
    //DoPurge();
    
    n.status = Nest.Status.none;
    stateChanged();
    }
    
    public boolean hasPart(Part p){
    	for (Nest n: myNests){
    	if (n.parts.contains(p))
    		return true;	}
    	return false;
    }
    
    public boolean emptyNest(){
        for (Nest n: myNests){
            if (n.status == Nest.Status.empty)
                return true;
        }
        return false;
    }
    
    public Nest getNest(int n){
        return myNests.get(n);
    }
    
    public void setCameraAgent(MockCamera c){
        this.camera = c;
    }
   /*
    public void setLane(Lane l){
        this.lane = l;
    }*/
    
    
=======

    private void giveToKit(Nest n) {

        n.part.setNestNum(n.getNestNum());
        partsagent.msgHereIsPart(n.part);
        n.howMany--;
        print("giving part " + n.part + " to kit now nest has " + n.howMany);
        n.status = Nest.Status.none;
        if (n.howMany == 0) {
            n.status = Nest.Status.needPart;
        }
        stateChanged();
    }

    private void purge(Nest n) {
        n.howMany = 0;
        //DoPurge();

        n.status = Nest.Status.needPart;
        stateChanged();
    }

    public boolean hasPart(Part p) {
        for (Nest n : myNests) {
            if (n.part.equals(p)) {
                return true;
            }
        }
        return false;
    }

    public void setCameraAgent(MockCamera c) {
        this.camera = c;
    }

    public void setLane(MockLane l) {
        this.lane = l;
    }

>>>>>>> 6c77d956ceb45adf295668eb6ca7622ff9158f71
    public void setPartsAgent(PartsInterface parts) {
        this.partsagent = parts;
    }
}
