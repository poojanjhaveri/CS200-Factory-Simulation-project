/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.partsManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import agent.Agent;
import factory.factory200.laneManager.ServerSide.LMServerMain;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.Lane;
import factory.factory201.interfaces.PartsInterface;
import factory.factory201.interfaces.NestInterface;
import factory.general.Message;
import factory.general.Nest;
import factory.general.Part;
import factory.general.Result;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class NestAgent extends Agent implements NestInterface {
    Timer timer = new Timer();
    Lane lane0;
    Lane lane1;
    Lane lane2;
    Lane lane3;
    Lane lane4;
    Lane lane5;
    Lane lane6;
    Lane lane7;
    Lane lanes[] = {lane0, lane1, lane2, lane3, lane4, lane5, lane6, lane7};//instances of lanes
    
    public List<Part> requests;//parts that partsAgent requests, removed when part given back to partsagent
    public List<Nest> myNests;//nests 0-7 that this agent controls
    public List<Part> needParts;//the parts that have been requested, used to set an individual's nest part
    boolean requestEarlyInspection=false;
    private LMServerMain serverLM;
   // Result result;
    PartsInterface partsagent;
    Camera camera;
    @Override
    public void msgHereAreParts(List<Part> parts) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   public NestAgent(String name) {
    super(name);
    myNests = Collections.synchronizedList(new ArrayList<Nest>());//initialize lists
    needParts = Collections.synchronizedList(new ArrayList<Part>());
    requests = Collections.synchronizedList(new ArrayList<Part>());
    myNests.add(new Nest(0));//initialize nests
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

    public void msgRequestEarlyInspection(){
       requestEarlyInspection=true;
       stateChanged();
    }
    
    public void msgPileUpParts(int n){
        DoPurge(n);
    }
    
    public void msgAllPartsBad(int n){
        
    }
  
    public void msgNeedPart(Part p) {//called by partsAgent
        requests.add(p);
        needParts.add(p);
        stateChanged();
    }
    
    public void msgPartsShaking(int nestNum){
    for (Nest n: myNests){
                if (n.nestNum==nestNum)
                    n.shaking=true;
         }
    stateChanged();
    }

    public void msgHereAreParts(List<Part> kitParts, int laneIndex){
        //requires syncrhonization: many msgs can come in to modify mynests.
        Part p = kitParts.get(0);
                for (int i =0; i<kitParts.size(); i++){//add parts given from laneAgent to nests
                    kitParts.get(i).setNestNum(laneIndex);
                    myNests.get(laneIndex).parts.add(kitParts.get(i));
                    myNests.get(laneIndex).parts.get(i).setNestNum(laneIndex);
                }
                if (myNests.get(laneIndex).parts.size()>=myNests.get(laneIndex).threshold) {//NEEDTO give parts back to lanes if too full?
                    myNests.get(laneIndex).status = Nest.Status.full;     
                }
                print("adding " + kitParts.size() + " " + p.getInt() + " to the nest " + myNests.get(laneIndex).nestNum);

        stateChanged();
    }

    @Override
    public void msgNestInspected(Nest n, Result result) {//NEEDTO change from boolean to setting individual parts?
        if (result.is == Result.Is.verified) {
            n.status = Nest.Status.readyForKit;
            print("Nest inspected and verified");
        } else  if (result.is == Result.Is.partsMissing && !requestEarlyInspection){
            //do not call action from messages- kevin 
            purge(n);
           print("Nest is not verified, will be dumped");
        } else if (result.is == Result.Is.partsMissing && requestEarlyInspection){
            requestEarlyInspection=false;
            print("Nest " + n.nestNum + " has not been verified and needs to wait for more parts");
            if (n.status!=Nest.Status.full)
            n.status = Nest.Status.gettingPart;
        }
        /* added by Kevin- if the parts are unstable, set the shaking to true*/
        else  if (result.is == Result.Is.unstableParts){
           //n.shaking=true;
              for (Nest n1: myNests){
                if (n1.nestNum==n.nestNum)
                {   //stabilize once and then wait for nest to be full again (or request new inspection. )
                    System.out.println("nest is shaking, need to stabilize");
                    n1.shaking=true;
                    // stabilizeNest(n1.nestNum);
                    //n.shaking=false;
                   // return true;
                }
            }
              
           System.out.println("Result received is that parts are unstable");
        }
        
        stateChanged();
    }
    
    //scheduler
    @Override
    public boolean pickAndExecuteAnAction() {

        //PRIORITIZE STABILIZING A SHAKING NEST
        for (Nest n: myNests){
                if (n.shaking==true)
                {   //stabilize once and then wait for nest to be full again (or request new inspection. )
                    System.out.println("nest is shaking, need to stabilize");
                    stabilizeNest(n.nestNum);
                    n.shaking=false;
                    return true;
                }
            }
//  change this 
  
        if(requestEarlyInspection){
            for (Nest n: myNests){
                if (n.status == Nest.Status.needPart)
                    requestInspection(n);
                    requestEarlyInspection=false;
                    print("Nest [" + n.nestNum + "] has requested early inspection NONNORM");
                    return true;
            }
        }   
        
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
        // synchronized(needParts){      
         if(!needParts.isEmpty()){
                
               for (Nest n: myNests){
                if(n.status == Nest.Status.empty){  
                    n.setPart(needParts.remove(0));//assigns part to nest
                    n.status = Nest.Status.needPart;
                    print("Part " + n.part.getInt() + " is not taken by a nest, part is being assigned to the nest "+ n.nestNum);
                    return true;
                }
                }
            
              //  synchronized(myNests){//gets stuck in look w/ this
                for (Nest n: myNests){
                    if(n.status == Nest.Status.readyForKit) {
                        boolean next = true;
                        for (int i=0; i<requests.size(); i++){
                            if (n.part.type == requests.get(i).type)
                                next = false;//flag next set false if the nest has a requested part
                    }
                            if (next){//if nest doesn't have requested part purge and assign a needed part
                        
                             print("PURGE IS HAPPENING");
                             n.setPart(needParts.remove(0));
                             purge(n);
                             n.status = Nest.Status.needPart;
                             print("Part " + n.part.getInt() + " is not taken by a nest, part is being assigned to the nest "+ n.nestNum);
                        return true;
                }}}}}//}
     


        return false;
    }
    
    private void stabilizeNest(int nestN){
        int i=nestN;
      try {
         Thread.sleep(3000);
         } catch (InterruptedException ex) {
         }
    //    print("about to stabilize the nest ");
        System.out.println("sending message to stabilize nest at " + nestN);
    client.sendMessage(Message.KAM_ACTION_STABILIZE_NEST+":"+nestN);
            try {
         Thread.sleep(1000);
         } catch (InterruptedException ex) {
         }
    }
    private void stabilize(int nestN){
    }
    private void requestPart(Nest n){
        print("requesting " + n.part.getInt() + "to lane " + n.getLane().getIndex());
    	n.status = Nest.Status.gettingPart;
        n.getLane().msgNeedPart(n.part);
    	stateChanged();
   }

    private void requestInspection(Nest n) {
        //print("Requesting inspection for nest " + n.getNestNum());
        n.status = Nest.Status.gettingInspected;
        
        /*
        if(n.nestNum==0)
        cam1.msgNestIsFull(n);
        
        if(n.nestNum==1)
        cam2.msgNestIsFull(n);
        
        if(n.nestNum==2)
        cam3.msgNestIsFull(n);
        
        if(n.nestNum==3)
        cam4.msgNestIsFull(n);
        */
        camera.msgNestIsFull(n);
        
        stateChanged();
    }

    
    private void giveToKit(Nest n){
        requests.remove(0);
    	partsagent.msgHereIsPart(n.parts.remove(0));//physically remove the part from the nest
        //print("giving part " + n.part.getInt() + " to kit now nest has " + n.parts.size());
        if (n.parts.isEmpty())
                n.status = Nest.Status.empty;
    	stateChanged();	
    }
    
    private void purge(Nest n){
    print("PURGING Nest "+ n.nestNum);
    n.parts.clear();
    //DoPurge();//NEEDTO implement this method
    n.status = Nest.Status.needPart;
    stateChanged();
    }
  
   public Nest getNest(int n){
        return myNests.get(n);
    }
    
    public void setCamera(Camera c){
        this.camera = c;
    }
    
    public void DoPurge(int nestNum){
        if (this.client != null) {
            serverLM.getForAgentNest().setSwitchDown(nestNum);
            try {
         Thread.sleep(1000);
         } catch (InterruptedException e) {
         e.printStackTrace();
         }
            serverLM.getForAgentNest().setSwitchUp(nestNum);
           // this.client.sendMessage(Message.PURGE_NEST + ":" + nestNum);
            //this.fpm.sendMessage(Message.PURGE_NEST + ":" + nestNum);
        }
        
    }

    public void setServer(LMServerMain lms){
        this.serverLM = lms;
    }
    public void setPartsAgent(PartsInterface parts) {
        this.partsagent = parts;
    }
    
}
