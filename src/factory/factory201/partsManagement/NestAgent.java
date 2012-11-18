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
    
    public List<Nest> myNests;
    private List<Part> needParts;
    private List<Nest> nests;
    PartsInterface partsagent;
    Camera camera;
    private int nestNumber;
    private List<Part> requests;

    
    enum Status {none, needPart, gettingPart, full, gettingInspected, readyForKit, purge};
    
   public NestAgent(String name) {
    super(name);
    this.myNests = Collections.synchronizedList(new ArrayList<Nest>());
    this.needParts = Collections.synchronizedList(new ArrayList<Part>());
    this.nests = Collections.synchronizedList(new ArrayList<Nest>());
    this.requests = Collections.synchronizedList(new ArrayList<Part>());
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

    
  
    public void msgNeedPart(Part p) {
        requests.add(p);
        needParts.add(p);
        stateChanged();
    }

    public void msgHereAreParts(List<Part> kitParts){
        Part p = kitParts.get(0);
        synchronized(myNests){
        for (Nest n : myNests) {
            if (n.part.type == p.type) {
                for (int i =0; i<kitParts.size(); i++){
                    n.parts.add(kitParts.get(i));
                }
                if (n.parts.size()>=n.threshold) {
                    n.status = Nest.Status.full;     
                }
                print("adding " + kitParts.size() + " " + p.getString() + " to the nest " + n.nestNum);

            }
        }}
        stateChanged();
    }

    @Override
    public void msgNestInspected(Nest n, boolean result) {
        if (result) {
            n.status = Nest.Status.readyForKit;
            print("Nest inspected and verified");
        } else {
           purge(n);
            //n.status = Nest.Status.purge;
            print("Nest is not verified, will be dumped");
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
     
     if (!requests.isEmpty()){
                
                for (Nest n: myNests){
                    if (n.status == Nest.Status.readyForKit){
                        for (Part part: n.parts){
                            if(part.type == requests.get(0).type){
                            giveToKit(n);
                            return true;
                }}}}
                
         if(!needParts.isEmpty()){
                
               for (Nest n: myNests){
                if(n.status == Nest.Status.empty){  
                    n.setPart(needParts.remove(0));
                    n.status = Nest.Status.needPart;
                    print("Part " + n.part.getString() + " is not taken by a nest, part is being assigned to the nest "+ n.nestNum);
                    return true;
                }
                }
            
                synchronized(myNests){
                for (Nest n: myNests){
                    if(n.status == Nest.Status.readyForKit) {
                        boolean next = true;
                        for (int i=0; i<requests.size(); i++){
                            if (n.part.type == requests.get(i).type)
                                next = false;
                    }
                            if (next){
                        
                             print("PURGE IS HAPPENING");
                             n.setPart(needParts.remove(0));
                             purge(n);
                             n.status = Nest.Status.needPart;
                             print("Part " + n.part.getString() + " is not taken by a nest, part is being assigned to the nest "+ n.nestNum);
                        return true;
                }}}}}}
            


        return false;
    }
 
    private void requestPart(Nest n){
        print("requesting " + n.part.getString());
    	n.status = Nest.Status.gettingPart;
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
        requests.remove(0);
    	partsagent.msgHereIsPart(n.parts.remove(0));
        print("giving part " + n.part.getString() + " to kit now nest has " + n.parts.size());
        if (n.parts.isEmpty())
                n.status = Nest.Status.empty;
    	stateChanged();	
    }
    
    private void purge(Nest n){
    print("PURGING Nest "+ n.nestNum);
    n.parts.clear();
    //DoPurge();
    n.status = Nest.Status.empty;
    stateChanged();
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
