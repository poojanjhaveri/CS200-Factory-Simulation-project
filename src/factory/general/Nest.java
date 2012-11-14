/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.general;

import factory.factory201.feederManagement.LaneAgent;
import factory.factory201.interfaces.Lane;
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
    public enum Status {none, empty, needPart, gettingPart, hasPart, full, gettingInspected, readyForKit, purge};
    public int threshold = 8;
    public Part part;
    public int howMany = 0;
    public Lane lane;
        
        public Nest (int nestNumber){
        	
        	//this.threshold = 10/p.getSize();
        	this.status = Status.empty;
                this.nestNum = nestNumber;
                
        }
        
        public void setNestNum(int nestNum){
            this.nestNum = nestNum;
        }
        public void setPart(Part p){
        	this.part = p;
        	//this.threshold = 10/p.getSize();
        }
        
        public void setLane(Lane l){
        	this.lane = l;
        }
        
        public Lane getLane(){
            return this.lane;
        }
        
        public int getNestNum(){
            return nestNum;
        }
        
    
    
    
    
}
