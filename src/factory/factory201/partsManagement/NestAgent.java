/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.partsManagement;

import agent.Agent;
import factory.factory201.feederManagement.LaneAgent;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.Lane;
import factory.factory201.interfaces.NestInterface;
import factory.factory201.interfaces.PartsInterface;
import factory.factory201.test.mock.MockLane;
import factory.general.Nest;
import factory.general.Part;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NestAgent extends Agent implements NestInterface {

    Lane lane0;
    Lane lane1;
    Lane lane2;
    Lane lane3;
    Lane lane4;
    Lane lane5;
    Lane lane6;
    Lane lane7;
    Lane lanes[] = {lane0, lane1, lane2, lane3, lane4, lane5, lane6, lane7};
    //Kit kit;
    
    MockLane lane;

    public List<Nest> myNests;
    //private Map<Nest, LaneAgent> lanes = new HashMap<Nest, LaneAgent>();
    private List<Part> needParts;
    private List<Nest> nests;
    PartsInterface partsagent;
    Camera camera;
    private int nestNumber;

    //private List<Nest> nests = Collections.synchronizedList(new ArrayList<Nest>());
    enum Status {none, needPart, gettingPart, full, gettingInspected, readyForKit, purge};
    
   public NestAgent(String name) {
    super(name);
    this.myNests = Collections.synchronizedList(new ArrayList<Nest>());
    this.needParts = Collections.synchronizedList(new ArrayList<Part>());
    this.nests = Collections.synchronizedList(new ArrayList<Nest>());
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
    /**
     * This message checks if the part
     *
     * @param p This is a part
     */

    /*
    public void msgNewKit(List<Part> kitParts){
        
            for (Nest n: myNests){
                for(Part p: kitParts){
                if (n.parts.contains(p))
                    n.status = Nest.Status.hasPart;
                    kitParts.remove(p);
            }
        }
            for (Part p: kitParts){
                for (Nest n: myNests){
                    if(n.status != Nest.Status.hasPart)
                }
            }
    }*/
    public void msgNeedPart(Part p) {
        synchronized(myNests){
    	if (!hasPart(p)){
            for (Nest n: myNests){
                //if (n.status == Nest.Status.empty){
                if(n.status == Nest.Status.empty){  
                n.setPart(p);
                    //nestParts.put(n.nestNum, p);
                    n.status = Nest.Status.needPart;
                    print("Part " + p.getString() + " is not taken by a nest, part is being assigned to the nest "+ n.nestNum);
                    break;
                }
            }
     }
        else{
            for (Nest n: myNests){
    		if(n.parts.contains(p)){
            giveToKit(n);}}}
    	

        stateChanged();}
    }

    public void msgHereAreParts(List<Part> kitParts){
        Part p = kitParts.get(0);
        for (Nest n : myNests) {
            if (n.part == p) {
                for (int i =0; i<kitParts.size(); i++){
                    n.parts.add(kitParts.get(i));
                }
                if (n.parts.size()==n.threshold)
                n.status = Nest.Status.full;
            }
        }
        print("adding " + kitParts.size() + " " + p.getString() + " to the nest ");
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
    public boolean pickAndExecuteAnAction() {


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

    
    
    private void requestPart(Nest n){
       
    print("requesting " + n.part.getString());
    	
    	n.status = Nest.Status.gettingPart;
        //lanes[n.nestNum]
        n.getLane().msgNeedPart(n.part);
    	stateChanged();

    }

    private void requestInspection(Nest n) {

        print("Requesting inspection for nest " + n.getNestNum());
        n.status = Nest.Status.gettingInspected;
        camera.msgNestIsFull(n);
        stateChanged();
    }

    
    private void giveToKit(Nest n){
        
    	partsagent.msgHereIsPart(n.parts.remove(0));
        print("giving part " + n.part.getString() + " to kit now nest has " + n.parts.size());
        n.status = Nest.Status.none;
    	if (n.parts.size()<2)
    		n.status = Nest.Status.needPart;
    	stateChanged();	
    }
    
    private void purge(Nest n){
    n.parts.clear();
    //DoPurge();
    
    n.status = Nest.Status.empty;
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
    
    public void setCamera(Camera c){
        this.camera = c;
    }

    public void setPartsAgent(PartsInterface parts) {
        this.partsagent = parts;
    }
}
