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
    private boolean inKit;
    private int size;

    /**
     * Creates a part and gives it a unique ID.
     * @author YiWei Roy Zheng
     * @brief instantiates a part into the correct state
     */

    public Part(Type t, boolean inkit, int size) {
        this.type = t;
        this.inKit = false;
        this.size = size;
    }
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
    
    
    /** End CS 201 stuff */

    /** CS 200 stuff */
    private String name;
    private String description;
    private ImageIcon img;
    Integer number;///< the part number, like an ID; useful for comparing

    public Part(String n, String d, String fn) {
        this.name = n;
        this.description = d;
        this.img = new ImageIcon(fn);
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public ImageIcon getImage()
    {
        return this.img;
    }
}
