/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.general;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex
 */
public class Nest {
    
    public enum Status {
        empty, full, verified, error
    };
    
    public String name;
    public int nestNum;
    public Part.Type partType;
    public Status status = Status.empty;
    public List<Part> parts = new ArrayList<Part>();
    
    public Nest() {
        
    }
}
