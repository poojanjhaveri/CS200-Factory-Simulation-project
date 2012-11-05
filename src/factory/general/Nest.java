/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.general;

import factory.factory201.feederManagement.LaneAgent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex
 */
public class Nest {
   
    public String name;
    public int nestNum;
    public Part.Type partType;
    public Status status;
    public List<Part> parts = new ArrayList<Part>();
    public enum Status {none, needPart, gettingPart, full, gettingInspected, readyForKit, purge};
    public int threshold;
    public Part part;
    public int howMany = 0;
    public LaneAgent lane;
        
        public Nest (Part p, int nestNumber){
        	this.part = p;
        	this.threshold = 10/p.getSize();
        	this.status = Status.needPart;
                this.nestNum = nestNumber;
        }
        
        public void setNestNum(int nestNum){
            this.nestNum = nestNum;
        }
        public void setPart(Part p){
        	this.part = p;
        	this.threshold = 10/p.getSize();
        }
        
        public void setLane(LaneAgent lane){
        	this.lane = lane;
        }
        
        public LaneAgent getLane(){
            return this.lane;
        }
        
        public int getNestNum(){
            return nestNum;
        }
        
    
    
    
    
}
