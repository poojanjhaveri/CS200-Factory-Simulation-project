package factory.general;

import java.io.Serializable;

/**
 * @brief digital representation of a part
 * @author Alex Young, David Zhang
 */
public class Part implements Serializable {
    /** CS201 stuff */
    public enum Type {
        p1, p2, p3, p4, p5, p6, p7, p8
    };
    
    public Type type;
    public boolean inKit;
    public int size;
//    public boolean good;

    /**
     * Creates a part and gives it a unique ID.
     *
     * @brief instantiates a part into the correct state
     */
    
    /* I am adding this constructor to test my agent codes (kevin) */
    public Part(int num){
    	if(num==1)
    	this.type=Type.p1;
    	if(num==2)
        	this.type=Type.p2;
    	if(num==3)
        	this.type=Type.p3;
    	if(num==4)
        	this.type=Type.p4;
    	if(num==5)
        	this.type=Type.p5;
    	if(num==6)
        	this.type=Type.p6;
    	if(num==7)
        	this.type=Type.p7;
    	if(num==8)
        	this.type=Type.p8;
    }
    
    public Part(String n, String d, String fn) {
        //TODO need to implement system to generate unique part numbers (we could cheat and do it the dumb way, grab time())
    }
    
    /** End CS 201 stuff */
    
    /** CS 200 stuff */
    private String name;
    private String description;
    private String imageFileName;
//    Integer number;///< the part number, like an ID; useful for comparing

    

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
    
    public Part(Type t, boolean inkit, int size) {
        this.type = t;
        this.inKit = false;
        this.size = size;
    }
}
